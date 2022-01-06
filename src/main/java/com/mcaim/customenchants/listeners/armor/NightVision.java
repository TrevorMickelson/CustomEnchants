package com.mcaim.customenchants.listeners.armor;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.PotionedArmorEventListener;
import org.bukkit.potion.PotionEffectType;

public class NightVision extends PotionedArmorEventListener {
    public NightVision() {
        super(PotionEffectType.NIGHT_VISION, CustomEnchants.NIGHT_VISION);
    }
}
