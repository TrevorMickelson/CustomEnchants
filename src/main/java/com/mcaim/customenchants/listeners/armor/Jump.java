package com.mcaim.customenchants.listeners.armor;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.ArmorPotionCustomEnchant;
import org.bukkit.potion.PotionEffectType;

public class Jump extends ArmorPotionCustomEnchant {
    public Jump() {
        super(PotionEffectType.JUMP, CustomEnchants.JUMP);
    }
}
