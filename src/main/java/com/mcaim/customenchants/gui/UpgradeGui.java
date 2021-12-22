package com.mcaim.customenchants.gui;

import com.mcaim.core.gui.Gui;
import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.util.ChatPrefix;
import com.mcaim.customenchants.EnchantPlugin;
import com.mcaim.customenchants.models.CustomEnchant;
import com.mcaim.customenchants.models.EnchantAdder;
import com.mcaim.customenchants.util.EnchantStorage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class UpgradeGui extends Gui {
    public UpgradeGui(Player player) {
        super(player, "Upgrade Menu", 27);
    }

    @Override
    protected void init() {
        ItemStack inHand = player.getInventory().getItemInMainHand();
        int index = 0;

        for (CustomEnchant customEnchant : getCustomEnchantsFromItemStack(inHand)) {
            int currentTier = inHand.getEnchantmentLevel(customEnchant);
            int upgradeCost = getUpgradeCost(currentTier);
            ItemStack guiItem = getGuiItemFromCustomEnchant(currentTier, customEnchant);

            setItem(index, guiItem, (player) -> {
                if (isFullyUpgraded(currentTier, customEnchant)) {
                    player.sendMessage(ChatPrefix.FAIL + "Enchant is fully upgraded");
                    return;
                }

                boolean hasEnoughXP = player.getLevel() >= upgradeCost || player.isOp();

                if (!hasEnoughXP) {
                    player.sendMessage(ChatPrefix.FAIL + "You don't have enough XP to upgrade!");
                    return;
                }

                new EnchantAdder(inHand, customEnchant).upgradeCustomEnchant(currentTier);
                player.sendMessage(ChatPrefix.SUCCESS + "You have successfully upgraded the custom enchant: " + customEnchant.getColoredName());

                if (!player.isOp())
                    player.setLevel(player.getLevel() - upgradeCost);

                new UpgradeGui(player).open();
            });

            index++;
        }

        setBackButton(new MainMenuGui(player));
        fillBackGround();
    }

    private List<CustomEnchant> getCustomEnchantsFromItemStack(ItemStack item) {
        EnchantStorage storage = EnchantPlugin.getInstance().getEnchantStorage();
        List<CustomEnchant> customEnchantList = new ArrayList<>();

        for (Enchantment enchantment : item.getEnchantments().keySet()) {
            CustomEnchant customEnchant = storage.getCustomEnchant(enchantment.getName());

            if (customEnchant != null)
                customEnchantList.add(customEnchant);
        }

        return customEnchantList;
    }

    private ItemStack getGuiItemFromCustomEnchant(int currentTier, CustomEnchant customEnchant) {
        int nextTier = currentTier + 1;
        int maxTier = customEnchant.getMaxLevel();

        String name = customEnchant.getColoredName();
        Material type = Material.ENCHANTED_BOOK;
        List<String> lore = new ArrayList<>();

        if (isFullyUpgraded(currentTier, customEnchant)) {
            lore.add("&7Current Tier: &fMAXED OUT");
            lore.add("&7Next Tier: &fMAXED OUT");
            lore.add("&7Max Tier: &fMAXED OUT");
        } else {
            lore.add("&7Current Tier: &f" + currentTier);
            lore.add("&7Next Tier: &f" + nextTier);
            lore.add("&7Max Tier: &f" + maxTier);
            lore.add("");
            lore.add("&7Upgrade XP cost: &e" + getUpgradeCost(currentTier));
        }

        return ItemBuild.of(type).name(name).lore(lore).flag(ItemFlag.HIDE_ATTRIBUTES).build();
    }

    private boolean isFullyUpgraded(int currentTier, CustomEnchant customEnchant) {
        int maxTier = customEnchant.getMaxLevel();
        return currentTier >= maxTier;
    }

    private int getUpgradeCost(int currentTier) {
        return currentTier * 15;
    }
}
