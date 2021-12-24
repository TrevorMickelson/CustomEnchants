package com.mcaim.customenchants.listeners.tools;

import com.mcaim.core.util.PlayerUtil;
import com.mcaim.customenchants.models.CustomEnchants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public final class AutoPickup implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack inHand = player.getInventory().getItemInMainHand();

        if (inHand.containsEnchantment(CustomEnchants.AUTO_PICKUP.getEnchantment())) {
            Collection<ItemStack> items = event.getBlock().getDrops(inHand);
            event.setDropItems(false);
            items.forEach((item) -> { PlayerUtil.giveItem(player, item);});
        }
    }
}
