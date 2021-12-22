package com.mcaim.customenchants.util;

import com.mcaim.core.item.ItemUtil;
import com.mcaim.core.util.Util;
import com.mcaim.customenchants.models.CustomEnchant;
import org.bukkit.inventory.ItemStack;

public final class EnchantUtil {
    public static final String ENCHANT_KEY = "CustomEnchanted";

    public static int getTierFromCustomEnchant(ItemStack itemStack, CustomEnchant customEnchant) {
        return itemStack.getEnchantments().get(customEnchant);
    }

    public static boolean containsCustomEnchant(ItemStack itemStack) {
        return ItemUtil.hasUniqueKey(itemStack, ENCHANT_KEY);
    }

    public static String getCustomEnchantLoreName(int tier, CustomEnchant customEnchant) {
        String lore = customEnchant.getColoredName();
        lore += tier > 1 ? " " + Util.intToRomanNumeral(tier) : "";
        return lore;
    }
}
