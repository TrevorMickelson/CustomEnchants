package com.mcaim.customenchants.listeners.helpers;

import com.mcaim.customenchants.enchants.ICustomEnchant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public abstract class WeaponEventListener extends CustomEnchantListener {
    public WeaponEventListener(ICustomEnchant customEnchant) {
        super(customEnchant);
    }

    @EventHandler
    public void onEntityDamageEntityEvent(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        if (!(event.getEntity() instanceof LivingEntity livingEntity)) return;

        if (!canUseCustomEnchant(player)) return;

        onEntityHitWithCustomEnchant(player, livingEntity, event.getDamage());
    }

    protected abstract void onEntityHitWithCustomEnchant(Player player, LivingEntity entity, double damageDealt);

    protected boolean entityIsNowDead(LivingEntity entity, double damageDealt) {
        return entity.getHealth() - damageDealt <= 0;
    }
}
