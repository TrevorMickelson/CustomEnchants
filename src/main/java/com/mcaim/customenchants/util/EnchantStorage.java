package com.mcaim.customenchants.util;

import com.mcaim.customenchants.models.CustomEnchant;
import com.mcaim.customenchants.models.EnchantTier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EnchantStorage {
    private final Map<String, CustomEnchant> customEnchants = new HashMap<>();
    private final Map<EnchantTier, List<CustomEnchant>> customEnchantTiers = new HashMap<>();

    public void addCustomEnchant(CustomEnchant customEnchant) {
        customEnchants.put(customEnchant.getName().toLowerCase(), customEnchant);
    }

    public void addCustomEnchantToTier(CustomEnchant customEnchant, EnchantTier tier) {
        List<CustomEnchant> customEnchantList = getCustomEnchantsFromTier(tier);
        customEnchantList.add(customEnchant);

        if (customEnchantTiers.containsKey(tier)) {
            customEnchantTiers.replace(tier, customEnchantList);
        } else {
            customEnchantTiers.put(tier, customEnchantList);
        }
    }

    public CustomEnchant getCustomEnchant(String name) {
        return customEnchants.get(name.toLowerCase());
    }

    public List<CustomEnchant> getCustomEnchantsFromTier(EnchantTier tier) {
        if (customEnchantTiers.containsKey(tier))
            return customEnchantTiers.get(tier);

        return new ArrayList<>();
    }

    public List<CustomEnchant> getAllCustomEnchants() {
        return new ArrayList<>(customEnchants.values());
    }

    public List<String> getAllCustomEnchantsByName() {
        return new ArrayList<>(customEnchants.keySet());
    }
}
