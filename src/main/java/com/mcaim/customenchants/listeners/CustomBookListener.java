package com.mcaim.customenchants.listeners;

import com.mcaim.core.item.ItemUtil;
import com.mcaim.core.util.ChatPrefix;
import com.mcaim.core.util.Util;
import com.mcaim.customenchants.EnchantPlugin;
import com.mcaim.customenchants.models.CustomEnchant;
import com.mcaim.customenchants.models.EnchantAdder;
import com.mcaim.customenchants.util.EnchantStorage;
import com.mcaim.customenchants.util.EnchantUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * Handles adding custom enchant to
 * item stack from a custom enchanted book
 */
public class CustomBookListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack cursor = event.getCursor();
        ItemStack clickedItem = event.getCurrentItem();
        boolean bothItemsExist = cursor != null && (clickedItem != null && clickedItem.getType() != Material.AIR);

        if (!bothItemsExist) return;

        if (!isCursorHoldingCustomBook(cursor)) return;

        Player player = (Player) event.getWhoClicked();
        CustomEnchant customEnchant = Objects.requireNonNull(getCustomEnchantFromBook(cursor));

        EnchantAdder enchantAdder = new EnchantAdder(player, clickedItem, customEnchant);

        if (!enchantAdder.canAddCustomEnchantForPlayer())
            return;

        cursor.setAmount(0);
        enchantAdder.forceAddCustomEnchant(1);
        player.sendMessage(ChatPrefix.SUCCESS + "Custom enchant " + customEnchant.getColoredName() + Util.trans(" &7successfully added!"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }

    private boolean isCursorHoldingCustomBook(ItemStack cursor) {
        Material type = cursor.getType();

        if (type != Material.ENCHANTED_BOOK)
            return false;

        return ItemUtil.hasUniqueKey(cursor, EnchantUtil.ENCHANT_KEY);
    }

    private CustomEnchant getCustomEnchantFromBook(ItemStack book) {
        EnchantStorage storage = EnchantPlugin.getInstance().getEnchantStorage();

        for (Enchantment enchantment : book.getEnchantments().keySet()) {
            CustomEnchant customEnchant = storage.getCustomEnchant(enchantment.getName());

            if (customEnchant != null)
                return customEnchant;
        }

        return null;
    }
}
