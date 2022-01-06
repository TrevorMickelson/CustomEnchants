package com.mcaim.customenchants.listeners.armor;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.PotionedArmorEventListener;
import org.bukkit.potion.PotionEffectType;

public class Speed extends PotionedArmorEventListener {
    public Speed() {
        super(PotionEffectType.SPEED, CustomEnchants.SPEED);
    }
}
