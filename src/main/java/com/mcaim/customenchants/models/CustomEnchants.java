package com.mcaim.customenchants.models;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.EnchantmentTarget;

public final class CustomEnchants {
    public static final ICustomEnchant AUTO_PICKUP = new CustomEnchantWrapper(
            "AutoPickup", ChatColor.GREEN,
            1, EnchantmentTarget.TOOL,
            "Automatically picks up blocks", CustomEnchantTier.RARE);
}
