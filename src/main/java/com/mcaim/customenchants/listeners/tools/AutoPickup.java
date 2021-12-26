package com.mcaim.customenchants.listeners.tools;

import com.mcaim.core.util.PlayerUtil;
import com.mcaim.customenchants.models.CustomEnchantments;
import com.mcaim.customenchants.models.ICustomEnchant;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public final class AutoPickup extends ToolCustomEnchantListener {
    @Override
    public ICustomEnchant getCustomEnchant() {
        return CustomEnchantments.AUTO_PICKUP;
    }

    @Override
    public <T extends Event> void run(T bukkitEvent) {
        BlockBreakEvent event = (BlockBreakEvent) bukkitEvent;

        Player player = event.getPlayer();
        ItemStack inHand = player.getInventory().getItemInMainHand();
        Block block = event.getBlock();

        Collection<ItemStack> items = block.getDrops(inHand);
        event.setDropItems(false);
        items.forEach((item) -> { PlayerUtil.giveItem(player, item);});
    }
}
