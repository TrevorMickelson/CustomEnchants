package com.mcaim.customenchants.models;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;

public interface ICustomEnchant {
    String getName();

    String getColoredName();

    int getMaxLevel();

    EnchantmentTarget getEnchantmentTarget();

    String getPermission();

    Enchantment getEnchantment();

    String getDescription();

    CustomEnchantTier getEnchantTier();
}