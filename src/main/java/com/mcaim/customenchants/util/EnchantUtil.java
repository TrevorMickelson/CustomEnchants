package com.mcaim.customenchants.util;

import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.util.PlayerUtil;
import com.mcaim.customenchants.enchants.ICustomEnchant;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.LinkedList;

public final class EnchantUtil {
    public static void givePlayerCustomEnchantBook(Player player, ICustomEnchant customEnchant) {
        String name = customEnchant.getColoredName();
        Material type = Material.ENCHANTED_BOOK;

        ItemStack enchantedBook = ItemBuild.of(type).name(name).lore("&7&oDrag over desired item").build();
        CustomEnchantBuilder.of(enchantedBook).addCustomEnchant(customEnchant, 1);
        PlayerUtil.giveItem(player, enchantedBook);
    }

    public static Iterable<ICustomEnchant> getCustomEnchantsFromItemStack(ItemStack item) {
        Collection<ICustomEnchant> customEnchantList = new LinkedList<>();
        EnchantStorage storage = EnchantStorage.getInstance();

        for (Enchantment enchantment : item.getEnchantments().keySet()) {
            ICustomEnchant customEnchant = storage.getCustomEnchantFromName(enchantment.getName());

            if (customEnchant != null)
                customEnchantList.add(customEnchant);
        }

        return customEnchantList;
    }

    public static boolean hasCustomEnchant(ItemStack itemStack) {
        EnchantStorage storage = EnchantStorage.getInstance();

        for (Enchantment enchantment : itemStack.getEnchantments().keySet()) {
            ICustomEnchant customEnchant = storage.getCustomEnchantFromName(enchantment.getName());

            if (customEnchant != null)
                return true;
        }

        return false;
    }
}
