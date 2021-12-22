package com.mcaim.customenchants.listeners.enchants.boots;

import com.mcaim.core.events.ArmorEquipEvent;
import com.mcaim.core.events.ArmorRemoveEvent;
import com.mcaim.customenchants.models.CustomEnchant;
import com.mcaim.customenchants.models.EnchantTarget;
import com.mcaim.customenchants.util.EnchantUsage;
import com.mcaim.customenchants.util.EnchantUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Jump extends CustomEnchant {
    public Jump() {
        super("Jump", ChatColor.DARK_GREEN, 4, EnchantTarget.BOOTS);
        setDescription("Jump baby jump");
    }

    @EventHandler
    public void onArmorEquip(ArmorEquipEvent event) {
        Player player = event.getPlayer();
        ItemStack armorItem = event.getArmor().getItemStack();

        if (EnchantUsage.canUseEnchantment(player, this)) {
            int tier = EnchantUtil.getTierFromCustomEnchant(armorItem, this);
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, tier * 3));
        }
    }

    @EventHandler
    public void onArmorRemove(ArmorRemoveEvent event) {
        Player player = event.getPlayer();

        if (EnchantUsage.canUseEnchantment(player, this))
            player.removePotionEffect(PotionEffectType.JUMP);
    }
}
