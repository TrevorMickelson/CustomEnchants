package com.mcaim.customenchants.listeners.tools;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.CustomEnchantListener;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

//TODO: Update via paper using block.getBreakSpeed() (METHOD ONLY EXISTS IN PAPER)
public final class QuickSwitch extends CustomEnchantListener {
    public QuickSwitch() {
        super(CustomEnchants.QUICK_SWITCH);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.LEFT_CLICK_BLOCK) return;

        ItemStack item = event.getItem();

        if (item == null) return;

        Player player = event.getPlayer();

        if (!canUseCustomEnchant(player, item)) return;

        Block block = event.getClickedBlock();

        if (block == null) return;

        ToolType blockType = getToolType(block);
        Material newToolType = getNewToolType(blockType, item.getType());
        item.setType(newToolType);
    }

    private ToolType getToolType(Block block) {
        for (ToolType tool : ToolType.values()){
            if (tool.canFastBreak(block.getType()))
                return tool;
        }

        return ToolType.PICKAXE;
    }

    private Material getNewToolType(ToolType toolType, Material oldMaterial) {
        String name = oldMaterial.name();
        String prefix = name.substring(0, name.indexOf("_"));
        return Material.valueOf(prefix + "_" + toolType.name());
    }


    private enum ToolType {
        AXE() {
            public boolean canFastBreak(Material type) {
                return Tag.LOGS.isTagged(type) ||
                        Tag.PLANKS.isTagged(type) ||
                        Tag.DOORS.isTagged(type) ||
                        Tag.TRAPDOORS.isTagged(type) ||
                        Tag.SIGNS.isTagged(type) ||
                        Tag.FENCES.isTagged(type) ||
                        Tag.FENCE_GATES.isTagged(type) ||
                        Tag.WOODEN_STAIRS.isTagged(type) ||
                        Tag.WOODEN_PRESSURE_PLATES.isTagged(type) ||
                        Tag.CAMPFIRES.isTagged(type) ||
                        Tag.BANNERS.isTagged(type) ||
                        Tag.BEEHIVES.isTagged(type) ||
                        Tag.BEDS.isTagged(type) ||
                        Tag.MUSHROOM_GROW_BLOCK.isTagged(type) ||
                        type == Material.CHEST ||
                        type == Material.TRAPPED_CHEST ||
                        type == Material.LECTERN ||
                        type == Material.CRAFTING_TABLE ||
                        type == Material.SMITHING_TABLE ||
                        type == Material.FLETCHING_TABLE ||
                        type == Material.BARREL ||
                        type == Material.JUKEBOX ||
                        type == Material.BOOKSHELF ||
                        type == Material.MELON ||
                        type == Material.NOTE_BLOCK ||
                        type == Material.LADDER ||
                        type == Material.VINE ||
                        type == Material.BAMBOO ||
                        type == Material.COCOA ||
                        type == Material.DAYLIGHT_DETECTOR ||
                        type == Material.PUMPKIN ||
                        type == Material.CARVED_PUMPKIN ||
                        type == Material.JACK_O_LANTERN;
            }
        },
        SHOVEL() {
            public boolean canFastBreak(Material type) {
                return Tag.DIRT.isTagged(type) ||
                        Tag.SAND.isTagged(type) ||
                        Tag.SNOW.isTagged(type) ||
                        type == Material.CLAY ||
                        type == Material.FARMLAND ||
                        type == Material.GRASS_BLOCK ||
                        type == Material.DIRT_PATH ||
                        type == Material.MYCELIUM ||
                        type == Material.PODZOL ||
                        type == Material.SOUL_SAND ||
                        type == Material.SOUL_SOIL ||
                        type == Material.GRAVEL ||
                        type.name().contains("POWDER");
            }
        },
        HOE() {
            public boolean canFastBreak(Material type) {
                return Tag.LEAVES.isTagged(type) ||
                        Tag.WART_BLOCKS.isTagged(type) ||
                        type == Material.SCULK_SENSOR ||
                        type == Material.MOSS_BLOCK ||
                        type == Material.SHROOMLIGHT ||
                        type == Material.HAY_BLOCK ||
                        type == Material.TARGET ||
                        type == Material.DRIED_KELP_BLOCK ||
                        type == Material.SPONGE || type == Material.WET_SPONGE;
            }
        },

        // The pickaxe acts as a default
        PICKAXE() {
            public boolean canFastBreak(Material type) {
                return false;
            }
        };

        public abstract boolean canFastBreak(Material type);
    }
}
