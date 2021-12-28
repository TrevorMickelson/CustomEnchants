package com.mcaim.customenchants.listeners;

import com.mcaim.core.events.ArmorEquipEvent;
import com.mcaim.core.events.ArmorRemoveEvent;
import com.mcaim.customenchants.enchants.ICustomEnchant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * If an Armor custom enchant is strictly a potion
 * effect, this handles that for every custom enchant
 */
public class ArmorPotionCustomEnchant extends CustomEnchantListener {
    private final PotionEffectType effectType;

    public ArmorPotionCustomEnchant(PotionEffectType effectType, ICustomEnchant customEnchant) {
        super(customEnchant);
        this.effectType = effectType;
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        Player player = event.getPlayer();
        ItemStack armorItem = event.getArmor().getItemStack();

        if (!canUseCustomEnchant(player, armorItem)) return;

        player.addPotionEffect(new PotionEffect(effectType, 1000000, getCurrentTier(armorItem)));
    }

    @EventHandler
    public void onArmorRemove(ArmorRemoveEvent event) {
        Player player = event.getPlayer();

        if (!canUseCustomEnchant(player)) return;

        player.removePotionEffect(effectType);
    }
}
