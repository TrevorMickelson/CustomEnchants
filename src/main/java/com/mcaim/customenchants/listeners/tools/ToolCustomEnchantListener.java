package com.mcaim.customenchants.listeners.tools;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public abstract class ToolCustomEnchantListener implements CustomEnchantListener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack inHand = player.getInventory().getItemInMainHand();

        if (inHand.containsEnchantment(getCustomEnchant().getEnchantment()))
            run(event);
    }
}
