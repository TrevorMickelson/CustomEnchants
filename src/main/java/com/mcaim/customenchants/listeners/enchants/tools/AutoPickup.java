package com.mcaim.customenchants.listeners.enchants.tools;

import com.mcaim.core.util.PlayerUtil;
import com.mcaim.customenchants.models.CustomEnchant;
import com.mcaim.customenchants.models.EnchantTarget;
import com.mcaim.customenchants.util.EnchantUsage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class AutoPickup extends CustomEnchant {
    public AutoPickup() {
        super("Autopickup", ChatColor.LIGHT_PURPLE, 1, EnchantTarget.TOOLS);
        setDescription("Automatically pick up blocks");
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (EnchantUsage.canUseEnchantment(player, this)) {
            ItemStack inHand = player.getInventory().getItemInMainHand();

            Collection<ItemStack> items = event.getBlock().getDrops(inHand);
            event.setDropItems(false);
            items.forEach((item) -> { PlayerUtil.giveItem(player, item);});
        }
    }
}
