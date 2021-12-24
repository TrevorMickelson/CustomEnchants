package com.mcaim.customenchants.gui;

import com.mcaim.core.gui.Gui;
import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.util.ChatPrefix;
import com.mcaim.core.util.PlayerUtil;
import com.mcaim.customenchants.EnchantPlugin;
import com.mcaim.customenchants.models.CustomEnchantBuilder;
import com.mcaim.customenchants.models.CustomEnchantTier;
import com.mcaim.customenchants.models.ICustomEnchant;
import com.mcaim.customenchants.util.EnchantStorage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnchanterGui extends Gui {
    private static final int FIRST_INVENTORY_SLOT = 10;
    private static final int INVENTORY_SLOT_INCREMENT = 2;

    public EnchanterGui(Player player) {
        super(player, "Enchanter Menu", 27);
    }

    @Override
    public void init() {
        int inventoryIndex = FIRST_INVENTORY_SLOT;

        for (CustomEnchantTier enchantTier : CustomEnchantTier.values()) {
            setItem(inventoryIndex, getEnchantTierItemStack(enchantTier), (player) -> {
                ICustomEnchant randomEnchant = getRandomCustomEnchantForPlayer(enchantTier);

                if (randomEnchant == null) {
                    player.sendMessage(ChatPrefix.FAIL + "You don't have access to any custom enchants! Obtain from /Rankups!");
                    return;
                }

                int level = player.getLevel();
                int cost = enchantTier.getXpCost();
                boolean hasEnoughXP = level >= cost || player.isOp();

                if (!hasEnoughXP) {
                    player.sendMessage(ChatPrefix.FAIL + "You don't have enough xp!");
                    return;
                }

                if (!player.isOp())
                    player.setLevel(level - cost);

                player.sendMessage(ChatPrefix.SUCCESS + "Custom enchant " + randomEnchant.getName() + "" + ChatColor.GRAY + " successfully purchased!");
                PlayerUtil.giveItem(player, getCustomEnchantBookItemStack(randomEnchant));
            });

            inventoryIndex += INVENTORY_SLOT_INCREMENT;
        }

        setBackButton(new MainMenuGui(player));
        fillBackGround();
    }

    private ItemStack getEnchantTierItemStack(CustomEnchantTier tier) {
        ItemBuild build = ItemBuild.of(Material.ENCHANTED_BOOK);
        build.name(tier.getName()).lore(getLoreFromCustomEnchantTier(tier));
        build.flag(ItemFlag.HIDE_ATTRIBUTES);
        return build.build();
    }

    private List<String> getLoreFromCustomEnchantTier(CustomEnchantTier tier) {
        EnchantStorage storage = EnchantPlugin.getInstance().getEnchantStorage();

        List<ICustomEnchant> customEnchantsList = storage.getCustomEnchantsFromTier(tier);
        List<String> lore = new ArrayList<>();

        for (ICustomEnchant customEnchant : customEnchantsList) {
            boolean hasPermission = player.hasPermission(customEnchant.getPermission());
            String accessDisplay = !hasPermission ? "&c✖ &c" : "&a✔ &a";
            String loreLine = accessDisplay + customEnchant.getName();
            lore.add(loreLine);
        }

        lore.add("");
        lore.add("&7XP Cost: &e" + tier.getXpCost());
        return lore;
    }

    private List<ICustomEnchant> getPlayersCustomEnchantsFromTier(CustomEnchantTier enchantTier) {
        List<ICustomEnchant> customEnchantList = new ArrayList<>();
        EnchantStorage storage = EnchantPlugin.getInstance().getEnchantStorage();

        for (ICustomEnchant customEnchant : storage.getCustomEnchantsFromTier(enchantTier)) {
            if (player.hasPermission(customEnchant.getPermission()))
                customEnchantList.add(customEnchant);
        }

        return customEnchantList;
    }

    private ICustomEnchant getRandomCustomEnchantForPlayer(CustomEnchantTier enchantTier) {
        List<ICustomEnchant> playersCustomEnchants = getPlayersCustomEnchantsFromTier(enchantTier);

        if (playersCustomEnchants.isEmpty()) return null;

        int randomIndex = new Random().nextInt(playersCustomEnchants.size());
        return playersCustomEnchants.get(randomIndex);
    }

    private ItemStack getCustomEnchantBookItemStack(ICustomEnchant customEnchant) {
        String name = customEnchant.getName();
        Material type = Material.ENCHANTED_BOOK;

        ItemStack enchantedBook = ItemBuild.of(type).name(name).lore("&7&oDrag over desired item").build();
        CustomEnchantBuilder.of(enchantedBook, customEnchant).addCustomEnchant();
        return enchantedBook;
    }
}
