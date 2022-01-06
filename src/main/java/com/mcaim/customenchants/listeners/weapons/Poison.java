package com.mcaim.customenchants.listeners.weapons;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.WeaponEventListener;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Poison extends WeaponEventListener {
    public Poison() {
        super(CustomEnchants.POISON);
    }

    @Override
    protected void onEntityHitWithCustomEnchant(Player player, LivingEntity entity, double damageDealt) {
        int currentTier = getCurrentTier(player);

        if (randomChanceSuccessfullyReached(currentTier))
            entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, currentTier - 1));
    }
}
