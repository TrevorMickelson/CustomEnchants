package com.mcaim.customenchants.listeners.weapons;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.WeaponEventListener;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class OneShot extends WeaponEventListener {
    public OneShot() {
        super(CustomEnchants.ONE_SHOT);
    }

    @Override
    protected void onEntityHitWithCustomEnchant(Player player, LivingEntity entity, double damageDealt) {
        if (entity instanceof Player) return;

        // Killing entity
        entity.setHealth(0);
    }
}
