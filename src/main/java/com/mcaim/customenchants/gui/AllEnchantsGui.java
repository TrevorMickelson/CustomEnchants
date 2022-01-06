package com.mcaim.customenchants.gui;

import com.mcaim.core.gui.Gui;
import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.util.Util;
import com.mcaim.customenchants.enchants.ICustomEnchant;
import com.mcaim.customenchants.util.EnchantStorage;
import com.mcaim.customenchants.util.EnchantUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class AllEnchantsGui extends Gui {
    public AllEnchantsGui(Player player) {
        super(player, "All Custom Enchants", 54);
    }

    @Override
    public void init() {
        int inventoryIndex = 0;

        for (ICustomEnchant customEnchant : EnchantStorage.getInstance().getAllCustomEnchants()) {
            setItem(inventoryIndex, getGuiItemFromCustomEnchant(customEnchant), (p) -> {
                if (!player.isOp()) return;

                EnchantUtil.givePlayerCustomEnchantBook(player, customEnchant);
            });
            inventoryIndex++;
        }

        setBackButton(new MainMenuGui(player));
        fillBackGround();
    }

    private ItemStack getGuiItemFromCustomEnchant(ICustomEnchant customEnchant) {
        String name = customEnchant.getColoredName();
        Material material = Material.ENCHANTED_BOOK;

        String[] lore = {
            "&f" + customEnchant.getDescription(),
            "",
            "&7Rarity &8> &a&l" + Util.format(customEnchant.getEnchantTier().name()),
            "&7Maximum Tier &8> &a&l" + customEnchant.getMaxLevel(),
            "&7Used on &8> &a&l" + Util.format(customEnchant.getEnchantmentTarget().name())
        };

        return ItemBuild.of(material).name(name).flag(ItemFlag.HIDE_ATTRIBUTES).listLore(lore).build();
    }
}
