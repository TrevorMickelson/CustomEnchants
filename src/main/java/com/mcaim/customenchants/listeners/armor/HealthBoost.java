package com.mcaim.customenchants.listeners.armor;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.PotionedArmorEventListener;
import org.bukkit.potion.PotionEffectType;

public class HealthBoost extends PotionedArmorEventListener {
    public HealthBoost() {
        super(PotionEffectType.HEALTH_BOOST, CustomEnchants.HEALTH_BOOST);
    }
}
