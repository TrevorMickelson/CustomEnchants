package com.mcaim.customenchants.util;

import com.mcaim.customenchants.models.CustomEnchant;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class EnchantUsage {
    public static boolean canUseEnchantment(Player player, CustomEnchant customEnchant) {
        int slot = customEnchant.getTargets()[0].getSlot();
        ItemStack enchanted = slot == -1 ? player.getInventory().getItemInMainHand() : player.getInventory().getItem(slot);

        if (enchanted == null || enchanted.getType() == Material.AIR)
            return false;

        return enchanted.getEnchantments().get(customEnchant) != null && hasAccessToEnchantment(player, customEnchant);
    }

    private static boolean hasAccessToEnchantment(Player player, CustomEnchant customEnchant) {
        boolean hasPermission = player.hasPermission(customEnchant.getPermission());

        if (!hasPermission) {
            player.sendMessage(ChatColor.RED + "You don't have access to the custom enchantment: " + customEnchant.getName());
            return false;
        }

        return true;
    }
}
