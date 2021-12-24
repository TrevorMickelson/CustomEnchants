package com.mcaim.customenchants.util;

import com.mcaim.core.item.ItemUtil;
import com.mcaim.customenchants.models.ICustomEnchant;
import org.bukkit.inventory.ItemStack;

public final class EnchantUtil {
    public static final String ENCHANT_KEY = "CustomEnchanted";

    public static int getTierFromCustomEnchant(ItemStack itemStack, ICustomEnchant customEnchant) {
        return itemStack.getEnchantments().get(customEnchant.getEnchantment());
    }

    public static boolean containsCustomEnchant(ItemStack itemStack) {
        return ItemUtil.hasUniqueKey(itemStack, ENCHANT_KEY);
    }
}
