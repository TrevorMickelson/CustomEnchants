package com.mcaim.customenchants.util;

import com.mcaim.customenchants.enchants.CustomEnchantTier;
import com.mcaim.customenchants.enchants.ICustomEnchant;

import java.util.*;

public final class EnchantStorage {
    private static final EnchantStorage instance = new EnchantStorage();
    public static EnchantStorage getInstance() { return instance; }

    private final Map<String, ICustomEnchant> customEnchants = new LinkedHashMap<>();
    private final Map<CustomEnchantTier, Set<ICustomEnchant>> customEnchantTiers = new HashMap<>();

    private void addCustomEnchantToTier(ICustomEnchant customEnchant, CustomEnchantTier tier) {
        Set<ICustomEnchant> customEnchantList = getCustomEnchantsFromTier(tier);
        customEnchantList.add(customEnchant);

        if (customEnchantTiers.containsKey(tier)) {
            customEnchantTiers.replace(tier, customEnchantList);
        } else {
            customEnchantTiers.put(tier, customEnchantList);
        }
    }

    public void storeCustomEnchant(ICustomEnchant customEnchant) {
        customEnchants.put(customEnchant.getName(), customEnchant);
        addCustomEnchantToTier(customEnchant, customEnchant.getEnchantTier());
    }

    public Set<ICustomEnchant> getCustomEnchantsFromTier(CustomEnchantTier tier) {
        if (customEnchantTiers.containsKey(tier))
            return customEnchantTiers.get(tier);

        return new HashSet<>();
    }

    public ICustomEnchant getCustomEnchantFromName(String name) {
        return customEnchants.get(name);
    }

    public List<ICustomEnchant> getAllCustomEnchants() {
        return new ArrayList<>(customEnchants.values());
    }
}
