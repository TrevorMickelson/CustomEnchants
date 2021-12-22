package com.mcaim.customenchants.listeners.enchants.boots;

import com.mcaim.core.events.ArmorEquipEvent;
import com.mcaim.core.events.ArmorRemoveEvent;
import com.mcaim.customenchants.models.CustomEnchant;
import com.mcaim.customenchants.models.EnchantTarget;
import com.mcaim.customenchants.util.EnchantUsage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;

public class Flight extends CustomEnchant {
    public Flight() {
        super("Flight", ChatColor.GREEN, 1, EnchantTarget.BOOTS);
        setDescription("Burrddy go fly fly");
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        toggleFlightForPlayer(event.getPlayer(), true);
    }

    @EventHandler
    public void onArmorRemove(ArmorRemoveEvent event) {
        toggleFlightForPlayer(event.getPlayer(), false);
    }

    private void toggleFlightForPlayer(Player player, boolean toggle) {
        if (EnchantUsage.canUseEnchantment(player, this)) {
            player.setAllowFlight(toggle);
            player.setFlying(toggle);
        }
    }
}
