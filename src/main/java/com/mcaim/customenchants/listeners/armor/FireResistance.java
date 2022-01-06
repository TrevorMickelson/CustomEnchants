package com.mcaim.customenchants.listeners.armor;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.PotionedArmorEventListener;
import org.bukkit.potion.PotionEffectType;

public class FireResistance extends PotionedArmorEventListener {
    public FireResistance() {
        super(PotionEffectType.FIRE_RESISTANCE, CustomEnchants.FIRE_RESISTANCE);
    }
}
