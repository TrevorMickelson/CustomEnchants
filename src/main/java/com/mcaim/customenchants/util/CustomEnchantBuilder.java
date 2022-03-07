package com.mcaim.customenchants.util;

import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.item.ItemUtil;
import com.mcaim.core.util.Util;
import com.mcaim.customenchants.enchants.ICustomEnchant;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class CustomEnchantBuilder {
    private final ItemBuild builder;

    public CustomEnchantBuilder(ItemStack itemStack) {
        this.builder = new ItemBuild(itemStack);
    }

    public CustomEnchantBuilder addCustomEnchant(ICustomEnchant customEnchant, int tier) {
        String lore = getCustomEnchantLoreName(customEnchant);
        builder.enchant(customEnchant.getEnchantment(), tier);

        // Not adding lore on enchanted books
        if (builder.build().getType() != Material.ENCHANTED_BOOK) {
            builder.addLore(lore);
            addUniqueKey(customEnchant);
        }

        return this;
    }

    public CustomEnchantBuilder upgradeCustomEnchant(ICustomEnchant customEnchant) {
        int newTier = getCurrentTier(customEnchant) + 1;
        removeCustomEnchant(customEnchant);
        addCustomEnchant(customEnchant, newTier);
        return this;
    }

    public CustomEnchantBuilder removeCustomEnchant(ICustomEnchant customEnchant) {
        String lore = getCustomEnchantLoreName(customEnchant);
        builder.removeLore(lore);
        builder.removeEnchant(customEnchant.getEnchantment());
        return this;
    }

    private void addUniqueKey(ICustomEnchant customEnchant) {
        String key = customEnchant.getName();
        ItemStack item = getItem();

        if (!ItemUtil.hasUniqueKey(item, key))
            builder.giveUniqueKey(key);
    }

    private String getCustomEnchantLoreName(ICustomEnchant customEnchant) {
        int currentTier = getCurrentTier(customEnchant);
        String lore = customEnchant.getColoredName();
        lore += currentTier > 1 ? " " + Util.intToRomanNumeral(currentTier) : "";
        return lore;
    }

    private int getCurrentTier(ICustomEnchant customEnchant) {
        return getItem().getEnchantmentLevel(customEnchant.getEnchantment());
    }

    private ItemStack getItem() {
        return builder.build();
    }

    public static CustomEnchantBuilder of(ItemStack itemStack) {
        return new CustomEnchantBuilder(itemStack);
    }
}
