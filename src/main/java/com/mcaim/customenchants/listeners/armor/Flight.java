package com.mcaim.customenchants.listeners.armor;

import com.mcaim.core.events.ArmorEquipEvent;
import com.mcaim.core.events.ArmorRemoveEvent;
import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.CustomEnchantListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public final class Flight extends CustomEnchantListener {
    public Flight() {
        super(CustomEnchants.FLIGHT);
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        handleCustomEnchantFlight(event.getPlayer(), true);
    }

    @EventHandler
    public void onArmorRemove(ArmorRemoveEvent event) {
        handleCustomEnchantFlight(event.getPlayer(), false);
    }

    private void handleCustomEnchantFlight(Player player, boolean enable) {
        if (canUseCustomEnchant(player)) {
            player.setAllowFlight(enable);
            player.setFlying(enable);
        }
    }
}
