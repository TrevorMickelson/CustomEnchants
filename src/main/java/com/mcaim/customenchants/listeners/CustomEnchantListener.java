package com.mcaim.customenchants.listeners;

import com.mcaim.customenchants.enchants.ICustomEnchant;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CustomEnchantListener implements Listener {
    protected final ICustomEnchant customEnchant;

    public CustomEnchantListener(ICustomEnchant customEnchant) {
        this.customEnchant = customEnchant;
    }

    protected boolean canUseCustomEnchant(Player player) {
        return canUseCustomEnchant(player, getItemStackFromPlayer(player));
    }

    protected boolean canUseCustomEnchant(Player player, ItemStack itemStack) {
        if (itemStack == null) return false;

        return hasCustomEnchantAccess(player) && hasCustomEnchant(itemStack);
    }

    /**
     * Gets the current tier of the CustomEnchant
     * that is associated with the ItemStack
     */
    protected int getCurrentTier(ItemStack itemStack) {
        return itemStack.getEnchantmentLevel(customEnchant.getEnchantment());
    }

    private boolean hasCustomEnchantAccess(Player player) {
        return player.hasPermission(customEnchant.getPermission());
    }

    private boolean hasCustomEnchant(ItemStack itemStack) {
        return itemStack.containsEnchantment(customEnchant.getEnchantment());
    }

    /**
     * Gets the item stack on the player, based on
     * what enchantment-target type CustomEnchant is
     */
    private ItemStack getItemStackFromPlayer(Player player) {
        ItemStack customEnchantedItem = null;
        PlayerInventory playerInventory = player.getInventory();

        switch (customEnchant.getEnchantmentTarget()) {
            case TOOL, WEAPON -> customEnchantedItem = playerInventory.getItemInMainHand();
            case ARMOR_HEAD -> customEnchantedItem = playerInventory.getHelmet();
            case ARMOR_TORSO -> customEnchantedItem = playerInventory.getChestplate();
            case ARMOR_LEGS -> customEnchantedItem = playerInventory.getLeggings();
            case ARMOR_FEET -> customEnchantedItem = playerInventory.getBoots();
        }

        return customEnchantedItem;
    }
}
