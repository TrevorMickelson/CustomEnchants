package com.mcaim.customenchants.listeners.tools;

import com.mcaim.core.util.Util;
import com.mcaim.customenchants.EnchantPlugin;
import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.CustomEnchantListener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.scheduler.BukkitRunnable;

//TODO: Still not quite functioning
public final class SpawnerMiner extends CustomEnchantListener {
    public SpawnerMiner() {
        super(CustomEnchants.SPAWNER_MINER);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (isNotSpawner(block)) return;

        if (!canUseCustomEnchant(event.getPlayer())) return;

        ItemStack spawner = getSpawnerFromBlock(block);

        if (spawner == null) return;

        block.getWorld().dropItemNaturally(block.getLocation(), spawner);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();

        if (isNotSpawner(block)) return;

        BlockState blockState = block.getState();

        new BukkitRunnable() {
            @Override
            public void run() {
                blockState.update();
            }
        }.runTaskLater(EnchantPlugin.getInstance(), 0L);
    }

    private ItemStack getSpawnerFromBlock(Block block) {
        BlockState blockState = block.getState();

        ItemStack spawner = new ItemStack(block.getType());
        BlockStateMeta meta = (BlockStateMeta) spawner.getItemMeta();

        if (meta == null) return null;

        meta.setDisplayName(Util.trans("&6SPAWNER: &f" + Util.format(((CreatureSpawner) blockState).getSpawnedType().name())));
        meta.setBlockState(blockState);
        spawner.setItemMeta(meta);
        return spawner;
    }

    private boolean isNotSpawner(Block block) {
        return block.getType() != Material.SPAWNER;
    }
}
