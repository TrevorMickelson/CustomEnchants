package com.mcaim.customenchants.util;

import com.mcaim.customenchants.models.ICustomEnchant;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class EnchantUsage {
    public static boolean hasAccessToEnchantment(Player player, ICustomEnchant customEnchant) {
        boolean hasPermission = player.hasPermission(customEnchant.getPermission());

        if (!hasPermission) {
            player.sendMessage(ChatColor.RED + "You don't have access to the custom enchantment: " + customEnchant.getName());
            return false;
        }

        return true;
    }
}
