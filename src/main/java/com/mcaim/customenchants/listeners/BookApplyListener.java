package com.mcaim.customenchants.listeners;

import com.mcaim.core.util.ChatPrefix;
import com.mcaim.core.util.Util;
import com.mcaim.customenchants.enchants.ICustomEnchant;
import com.mcaim.customenchants.util.CustomEnchantBuilder;
import com.mcaim.customenchants.util.EnchantStorage;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BookApplyListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack cursor = event.getCursor();
        ItemStack clickedItem = event.getCurrentItem();
        boolean bothItemsExist = cursor != null && (clickedItem != null && clickedItem.getType() != Material.AIR);

        if (!bothItemsExist) return;

        if (!isCursorHoldingBook(cursor)) return;

        ICustomEnchant customEnchant = getCustomEnchantFromCursor(cursor);

        if (customEnchant == null) return;

        Player player = (Player) event.getWhoClicked();
        boolean canAddCustomEnchantToItem = customEnchant.getEnchantmentTarget().includes(clickedItem) &&
                                            !clickedItem.getEnchantments().containsKey(customEnchant.getEnchantment());

        if (!canAddCustomEnchantToItem) {
            player.sendMessage(ChatPrefix.FAIL + "Custom enchant cannot be added to this item!");
            return;
        }

        CustomEnchantBuilder.of(clickedItem).addCustomEnchant(customEnchant, 1);
        cursor.setAmount(0);
        player.sendMessage(ChatPrefix.SUCCESS + "Custom enchant " + customEnchant.getColoredName() + Util.trans(" &7successfully added!"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }

    private boolean isCursorHoldingBook(ItemStack cursor) {
        return cursor.getType() == Material.ENCHANTED_BOOK;
    }

    private ICustomEnchant getCustomEnchantFromCursor(ItemStack book) {
        EnchantStorage storage = EnchantStorage.getInstance();

        for (Enchantment enchantment : book.getEnchantments().keySet()) {
            ICustomEnchant customEnchant = storage.getCustomEnchantFromName(enchantment.getName());

            if (customEnchant != null)
                return customEnchant;
        }

        return null;
    }
}
