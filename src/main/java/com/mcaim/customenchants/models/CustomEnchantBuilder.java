package com.mcaim.customenchants.models;

import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.util.ChatPrefix;
import com.mcaim.core.util.Util;
import com.mcaim.customenchants.util.EnchantUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantBuilder {
    private final ItemBuild builder;
    private final ICustomEnchant customEnchant;

    public CustomEnchantBuilder(ItemStack itemStack, ICustomEnchant customEnchant) {
        builder = new ItemBuild(itemStack);
        this.customEnchant = customEnchant;
    }

    public void addCustomEnchant() {
        addCustomEnchant(1);
    }

    public void addCustomEnchant(int tier) {
        String lore = getCustomEnchantLoreName(customEnchant, tier);
        String key = EnchantUtil.ENCHANT_KEY;
        builder.enchant(customEnchant.getEnchantment(), tier).giveUniqueKey(key).addLore(lore);
    }

    public void upgradeCustomEnchant(int currentTier) {
        int newTier = currentTier + 1;
        String oldLore = getCustomEnchantLoreName(customEnchant, currentTier);

        // Removing old lore and old custom enchant
        builder.removeLore(oldLore).removeEnchant(customEnchant.getEnchantment());

        addCustomEnchant(newTier);
    }

    public void addCustomEnchantForPlayer(Player player) {
        addCustomEnchantForPlayer(player, 1);
    }

    public void addCustomEnchantForPlayer(Player player, int tier) {
        if (!canAddCustomEnchant(player))
            return;

        addCustomEnchant(tier);
    }

    private boolean canAddCustomEnchant(Player player) {
        ItemStack itemStack = builder.build();
        boolean itemExists = itemStack != null && itemStack.getType() != Material.AIR;

        if (!itemExists) {
            player.sendMessage(ChatPrefix.FAIL + "Attempted enchanted item does not exist!");
            return false;
        }

        boolean canAddCustomEnchantToItem = customEnchant.getEnchantmentTarget().includes(builder.build());

        if (!canAddCustomEnchantToItem) {
            player.sendMessage(ChatPrefix.FAIL + "Enchantment can't be added to item!");
            return false;
        }

        boolean itemContainsCustomEnchant = itemStack.containsEnchantment(customEnchant.getEnchantment());

        if (itemContainsCustomEnchant) {
            player.sendMessage(ChatPrefix.FAIL + "Item already has that custom enchantment!");
            return false;
        }

        return true;
    }

    private String getCustomEnchantLoreName(ICustomEnchant customEnchant, int tier) {
        String lore = customEnchant.getColoredName() + customEnchant.getName();
        lore += tier > 1 ? " " + Util.intToRomanNumeral(tier) : "";
        return lore;
    }

    public static CustomEnchantBuilder of(ItemStack itemStack, ICustomEnchant customEnchant) {
        return new CustomEnchantBuilder(itemStack, customEnchant);
    }
}
