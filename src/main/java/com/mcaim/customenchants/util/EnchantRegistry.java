package com.mcaim.customenchants.util;

import com.mcaim.customenchants.EnchantPlugin;
import com.mcaim.customenchants.models.CustomEnchantTier;
import com.mcaim.customenchants.models.CustomEnchantments;
import com.mcaim.customenchants.models.ICustomEnchant;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public final class EnchantRegistry {
    public static void registerAllCustomEnchants() {
        Field[] constants = CustomEnchantments.class.getFields();

        for (Field field : constants) {
            if (!Modifier.isStatic(field.getModifiers())) continue;

            if (field.getType() != ICustomEnchant.class) continue;

            try {
                ICustomEnchant customEnchant = (ICustomEnchant) field.get(null);
                registerCustomEnchant(customEnchant);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerCustomEnchant(ICustomEnchant customEnchant) {
        registerStorage(customEnchant, customEnchant.getEnchantTier());
        registerThroughMinecraft(customEnchant);
    }

    private static void registerStorage(ICustomEnchant customEnchant, CustomEnchantTier tier) {
        EnchantStorage storage = EnchantPlugin.getPlugin(EnchantPlugin.class).getEnchantStorage();
        storage.getCustomEnchants().add(customEnchant);
        storage.addCustomEnchantToTier(customEnchant, tier);
    }

    private static void registerThroughMinecraft(ICustomEnchant customEnchant) {
        try {
            boolean registered = Arrays.stream(Enchantment.values()).toList().contains(customEnchant.getEnchantment());

            if (!registered) {
                Field acceptingNew = Enchantment.class.getDeclaredField("acceptingNew");
                acceptingNew.setAccessible(true);
                acceptingNew.set(null, true);
                Enchantment.registerEnchantment(customEnchant.getEnchantment());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
