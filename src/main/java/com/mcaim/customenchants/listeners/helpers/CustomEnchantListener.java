package com.mcaim.customenchants.listeners.helpers;

import com.mcaim.core.util.ChatPrefix;
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

        if (itemStack.getEnchantments().isEmpty()) return false;

        if (!hasCustomEnchant(itemStack)) return false;

        if (!hasCustomEnchantAccess(player)) {
            player.sendMessage(ChatPrefix.FAIL + "You don't have the ability to use this CustomEnchant");
            return false;
        }

        return true;
    }

    /**
     * Gets the current tier of the CustomEnchant
     * that is associated with the ItemStack
     */
    protected int getCurrentTier(ItemStack itemStack) {
        return itemStack.getEnchantmentLevel(customEnchant.getEnchantment());
    }

    protected int getCurrentTier(Player player) {
        return getCurrentTier(getItemStackFromPlayer(player));
    }

    /**
     * Random chance is based on the tier of the
     * weapon being used, I.E the higher the tier,
     * the more likely this will be true
     *
     * Tier 1 = 10%
     * Tier 2 = 20% and so on
     */
    protected boolean randomChanceSuccessfullyReached(int currentTier) {
        return (int) (100 * Math.random()) < (currentTier * 10);
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
