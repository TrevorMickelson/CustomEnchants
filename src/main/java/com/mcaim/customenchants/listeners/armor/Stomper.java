package com.mcaim.customenchants.listeners.armor;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.CustomEnchantListener;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public final class Stomper extends CustomEnchantListener {
    public Stomper() {
        super(CustomEnchants.STOMPER);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void onStomperDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player) {
            if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;

            if (!canUseCustomEnchant(player)) return;

            // Stopping fall damage
            event.setCancelled(true);

            ItemStack boots = player.getInventory().getBoots();

            if (boots == null) return;

            double attackDamage = getDamageAmount(boots, event.getDamage());

            damageEntitiesNearPlayer(player, attackDamage);
        }
    }

    // Damages based on (fall damage) and (custom enchant tier)
    private double getDamageAmount(ItemStack boots, double fallDamage) {
        int tier = boots.getEnchantmentLevel(customEnchant.getEnchantment());
        return fallDamage * (tier * 1.5);
    }

    private void damageEntitiesNearPlayer(Player player, double attackDamage) {
        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
            if (!(entity instanceof LivingEntity livingEntity)) continue;

            livingEntity.damage(attackDamage);
        }
    }
}
