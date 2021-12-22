package com.mcaim.customenchants.models;

import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.util.ChatPrefix;
import com.mcaim.customenchants.util.EnchantUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EnchantAdder {
    private final ItemStack enchanted;
    private final CustomEnchant customEnchant;
    private Player player;

    public EnchantAdder(ItemStack enchanted, CustomEnchant customEnchant) {
        this.enchanted = enchanted;
        this.customEnchant = customEnchant;
    }

    public EnchantAdder(Player player, CustomEnchant customEnchant) {
        this.player = player;

        int slot = customEnchant.getTargets()[0].getSlot();
        PlayerInventory inv = player.getInventory();
        this.enchanted = slot == -1 ? inv.getItem(slot) : inv.getItemInMainHand();

        this.customEnchant = customEnchant;
    }

    public EnchantAdder(Player player, ItemStack enchanted, CustomEnchant customEnchant) {
        this.player = player;
        this.enchanted = enchanted;
        this.customEnchant = customEnchant;
    }

    public void addCustomEnchantForPlayer() {
        if (!canAddCustomEnchantForPlayer())
            return;

        forceAddCustomEnchant(1);
        player.sendMessage(ChatPrefix.SUCCESS + "Custom enchant " + customEnchant.getName() + " was successfully added!");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }

    public boolean canAddCustomEnchantForPlayer() {
        boolean itemExists = enchanted != null && enchanted.getType() != Material.AIR;

        if (!itemExists) {
            player.sendMessage(ChatPrefix.FAIL + "Attempted enchanted item does not exist!");
            return false;
        }

        if (!canAddCustomEnchantToItemStack()) {
            player.sendMessage(ChatPrefix.FAIL + "Enchantment can't be added to item!");
            return false;
        }

        if (itemStackContainsCustomEnchant()) {
            player.sendMessage(ChatPrefix.FAIL + "Item already has that custom enchantment!");
            return false;
        }

        return true;
    }

    public void forceAddCustomEnchant(int tier) {
        String lore = EnchantUtil.getCustomEnchantLoreName(tier, customEnchant);
        String key = EnchantUtil.ENCHANT_KEY;

        ItemBuild builder = ItemBuild.of(enchanted).enchant(customEnchant, tier).giveUniqueKey(key);
        boolean isCustomEnchantedBook = enchanted.getType() == Material.ENCHANTED_BOOK;

        if (!isCustomEnchantedBook)
            builder.addLore(lore);
    }

    public void upgradeCustomEnchant(int currentTier) {
        int newTier = currentTier + 1;
        String oldLore = EnchantUtil.getCustomEnchantLoreName(currentTier, customEnchant);

        // Item builder for custom enchanted item
        ItemBuild builder = ItemBuild.of(enchanted);

        // Removing old lore and old custom enchant
        builder.removeLore(oldLore).removeEnchant(customEnchant);

        // Force adding new enchantment
        forceAddCustomEnchant(newTier);
    }

    private boolean canAddCustomEnchantToItemStack() {
        for (EnchantTarget enchantTarget : customEnchant.getTargets()) {
            if (enchantTarget.includes(enchanted.getType()))
                return true;
        }

        return false;
    }

    private boolean itemStackContainsCustomEnchant() {
        return enchanted.getEnchantments().containsKey(customEnchant);
    }
}