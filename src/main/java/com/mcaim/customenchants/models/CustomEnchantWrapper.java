package com.mcaim.customenchants.models;

import com.mcaim.customenchants.EnchantPlugin;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public final class CustomEnchantWrapper extends Enchantment implements ICustomEnchant {
    private final String name;
    private final String coloredName;
    private final int maxLevel;
    private final EnchantmentTarget enchantmentTarget;
    private final String permission;
    private final String description;
    private final CustomEnchantTier enchantTier;

    public CustomEnchantWrapper(String name, ChatColor chatColor, int maxLevel, EnchantmentTarget enchantmentTarget, String description, CustomEnchantTier enchantTier) {
        super(new NamespacedKey(EnchantPlugin.getInstance(), name));
        this.name = name;
        this.coloredName = chatColor + name;
        this.maxLevel = maxLevel;
        this.enchantmentTarget = enchantmentTarget;
        this.permission = "custom_enchant." + name;
        this.description = description;
        this.enchantTier = enchantTier;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColoredName() { return coloredName; }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public EnchantmentTarget getEnchantmentTarget() {
        return enchantmentTarget;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public CustomEnchantTier getEnchantTier() {
        return enchantTier;
    }

    @Override
    public Enchantment getEnchantment() {
        return this;
    }

    @Override
    public int getStartLevel() { return 0; }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return false;
    }
}
