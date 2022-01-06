package com.mcaim.customenchants.listeners.armor;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.PotionedArmorEventListener;
import org.bukkit.potion.PotionEffectType;

public class Jump extends PotionedArmorEventListener {
    public Jump() {
        super(PotionEffectType.JUMP, CustomEnchants.JUMP);
    }
}
