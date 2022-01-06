package com.mcaim.customenchants.listeners.armor;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.CustomEnchantListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoFall extends CustomEnchantListener {
    public NoFall() {
        super(CustomEnchants.NO_FALL);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            boolean damageIsFall = event.getCause() == EntityDamageEvent.DamageCause.FALL;

            if (!damageIsFall) return;

            if (canUseCustomEnchant(player))
                event.setCancelled(true);
        }
    }
}
