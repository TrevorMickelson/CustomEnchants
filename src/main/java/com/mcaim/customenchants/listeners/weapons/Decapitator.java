package com.mcaim.customenchants.listeners.weapons;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.WeaponEventListener;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

public class Decapitator extends WeaponEventListener {
    public Decapitator() {
        super(CustomEnchants.DECAPITATOR);
    }

    @Override
    protected void onEntityHitWithCustomEnchant(Player player, LivingEntity entity, double damageDealt) {
        if (!(entity instanceof Player)) return;

        if (!entityIsNowDead(entity, damageDealt)) return;

        if (!randomChanceSuccessfullyReached(getCurrentTier(player))) return;

        player.getWorld().dropItemNaturally(player.getLocation(), Objects.requireNonNull(getHeadItemFromPlayer(player)));
    }

    private ItemStack getHeadItemFromPlayer(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        if (meta == null) return null;

        meta.setOwner(player.getName());
        item.setItemMeta(meta);
        return item;
    }
}
