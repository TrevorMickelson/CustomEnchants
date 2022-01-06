package com.mcaim.customenchants.listeners.armor;

import com.mcaim.core.events.ArmorEquipEvent;
import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.CustomEnchantListener;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public final class NoHunger extends CustomEnchantListener {
    private static final byte MAX_FOOD_LEVEL = 30;

    public NoHunger() {
        super(CustomEnchants.NO_HUNGER);
    }

    @EventHandler
    public void onEquip(ArmorEquipEvent event) {
        handleNoHungerCustomEnchant(event.getPlayer());
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player) {
            handleNoHungerCustomEnchant(player);
        }
    }

    private void handleNoHungerCustomEnchant(Player player) {
        if (canUseCustomEnchant(player))
            player.setFoodLevel(MAX_FOOD_LEVEL);
    }
}
