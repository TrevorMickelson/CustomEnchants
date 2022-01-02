package com.mcaim.customenchants.listeners.tools;

import com.mcaim.core.util.PlayerUtil;
import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.CustomEnchantListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public final class AutoPickup extends CustomEnchantListener {
    public AutoPickup() {
        super(CustomEnchants.AUTO_PICKUP);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack inHand = player.getInventory().getItemInMainHand();

        if (!canUseCustomEnchant(player, inHand)) return;

        Block block = event.getBlock();

        // Auto-smelt support
        if (isUsingAutoSmelt(inHand)) return;

        event.setDropItems(false);
        Collection<ItemStack> items = block.getDrops(inHand);
        items.forEach((item) -> { PlayerUtil.giveItem(player, item); });
    }

    private boolean isUsingAutoSmelt(ItemStack itemStack) {
        return itemStack.containsEnchantment(CustomEnchants.AUTO_SMELT.getEnchantment());
    }
}
