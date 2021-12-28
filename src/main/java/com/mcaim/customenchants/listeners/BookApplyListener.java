package com.mcaim.customenchants.listeners;

import com.mcaim.core.item.ItemUtil;
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

import java.util.Objects;

public class BookApplyListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack cursor = event.getCursor();
        ItemStack clickedItem = event.getCurrentItem();
        boolean bothItemsExist = cursor != null && (clickedItem != null && clickedItem.getType() != Material.AIR);

        if (!bothItemsExist) return;

        if (!isCursorHoldingCustomBook(cursor)) return;

        Player player = (Player) event.getWhoClicked();
        ICustomEnchant customEnchant = Objects.requireNonNull(getCustomEnchantFromBook(cursor));
        boolean canAddCustomEnchantToItem = customEnchant.getEnchantmentTarget().includes(clickedItem) &&
                                            !clickedItem.getEnchantments().containsKey(customEnchant.getEnchantment());

        if (!canAddCustomEnchantToItem) {
            player.sendMessage(ChatPrefix.FAIL + "Custom enchant cannot be added to this item!");
            return;
        }

        CustomEnchantBuilder.of(clickedItem, customEnchant).addCustomEnchant();
        cursor.setAmount(0);
        player.sendMessage(ChatPrefix.SUCCESS + "Custom enchant " + customEnchant.getColoredName() + Util.trans(" &7successfully added!"));
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }

    private boolean isCursorHoldingCustomBook(ItemStack cursor) {
        Material type = cursor.getType();

        if (type != Material.ENCHANTED_BOOK)
            return false;

        return ItemUtil.hasUniqueKey(cursor, "CustomEnchant");
    }

    private ICustomEnchant getCustomEnchantFromBook(ItemStack book) {
        EnchantStorage storage = EnchantStorage.getInstance();

        for (Enchantment enchantment : book.getEnchantments().keySet()) {
            ICustomEnchant customEnchant = storage.getCustomEnchantFromName(enchantment.getName());

            if (customEnchant != null)
                return customEnchant;
        }

        return null;
    }
}
