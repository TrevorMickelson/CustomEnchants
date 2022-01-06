package com.mcaim.customenchants.listeners.tools;

import com.mcaim.core.item.ItemUtil;
import com.mcaim.core.util.PlayerUtil;
import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.CustomEnchantListener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public final class AutoSmelt extends CustomEnchantListener {
    public AutoSmelt() {
        super(CustomEnchants.AUTO_SMELT);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack inHand = player.getInventory().getItemInMainHand();

        if (!canUseCustomEnchant(player, inHand)) return;

        Block block = event.getBlock();
        block.getDrops().clear();
        Material smeltedType = getSmeltedTypeFromBlock(block);

        if (smeltedType == null) return;

        event.setDropItems(false);
        boolean isUsingAutoPickup = isUsingAutoPickup(inHand);

        block.getDrops(inHand).forEach(item -> {
            // "Smelting" the item by updating the type
            item.setType(smeltedType);

            // Auto-pickup support
            if (isUsingAutoPickup) {
                PlayerUtil.giveItem(player, item);
            } else {
                block.getWorld().dropItemNaturally(block.getLocation(), item);
            }
        });
    }

    private Material getSmeltedTypeFromBlock(Block block) {
        if (block == null) return null;

        return ItemUtil.getFurnaceSmeltResult(block.getType());
    }

    private boolean isUsingAutoPickup(ItemStack itemStack) {
        return itemStack.containsEnchantment(CustomEnchants.AUTO_PICKUP.getEnchantment());
    }
}
