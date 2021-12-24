package com.mcaim.customenchants.util;

import com.mcaim.customenchants.models.CustomEnchantTier;
import com.mcaim.customenchants.models.ICustomEnchant;

import java.util.*;

public final class EnchantStorage {
    private final HashSet<ICustomEnchant> customEnchants = new HashSet<>();
    private final Map<CustomEnchantTier, List<ICustomEnchant>> customEnchantTiers = new HashMap<>();

    public void addCustomEnchantToTier(ICustomEnchant customEnchant, CustomEnchantTier tier) {
        List<ICustomEnchant> customEnchantList = getCustomEnchantsFromTier(tier);
        customEnchantList.add(customEnchant);

        if (customEnchantTiers.containsKey(tier)) {
            customEnchantTiers.replace(tier, customEnchantList);
        } else {
            customEnchantTiers.put(tier, customEnchantList);
        }
    }

    public List<ICustomEnchant> getCustomEnchantsFromTier(CustomEnchantTier tier) {
        if (customEnchantTiers.containsKey(tier))
            return customEnchantTiers.get(tier);

        return new ArrayList<>();
    }

    public HashSet<ICustomEnchant> getCustomEnchants() { return customEnchants; }
}
