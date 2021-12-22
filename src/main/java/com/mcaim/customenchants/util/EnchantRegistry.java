package com.mcaim.customenchants.util;

import com.mcaim.customenchants.EnchantPlugin;
import com.mcaim.customenchants.listeners.enchants.tools.AutoPickup;
import com.mcaim.customenchants.listeners.enchants.boots.Flight;
import com.mcaim.customenchants.listeners.enchants.boots.Jump;
import com.mcaim.customenchants.models.CustomEnchant;
import com.mcaim.customenchants.models.EnchantTier;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class EnchantRegistry {
    public static void registerAllCustomEnchants() {
        registerCustomEnchant(new Jump(), EnchantTier.COMMON);
        registerCustomEnchant(new AutoPickup(), EnchantTier.UNCOMMON);
        registerCustomEnchant(new Flight(), EnchantTier.VERY_RARE);
    }

    private static void registerCustomEnchant(CustomEnchant customEnchant, EnchantTier tier) {
        registerListener(customEnchant);
        registerStorage(customEnchant, tier);
        registerThroughMinecraft(customEnchant);
    }

    private static void registerListener(CustomEnchant customEnchant) {
        EnchantPlugin plugin = EnchantPlugin.getInstance();
        plugin.getServer().getPluginManager().registerEvents(customEnchant, plugin);
    }

    private static void registerStorage(CustomEnchant customEnchant, EnchantTier tier) {
        EnchantStorage storage = EnchantPlugin.getInstance().getEnchantStorage();
        storage.addCustomEnchant(customEnchant);
        storage.addCustomEnchantToTier(customEnchant, tier);
    }

    public static void registerThroughMinecraft(CustomEnchant customEnchant) {
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");

            field.setAccessible(true);
            field.set(null, true);

            boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(customEnchant);

            if (!registered)
                Enchantment.registerEnchantment(customEnchant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
