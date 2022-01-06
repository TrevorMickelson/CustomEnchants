package com.mcaim.customenchants.listeners.weapons;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.helpers.WeaponEventListener;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HealthDisplay extends WeaponEventListener {
    public HealthDisplay() {
        super(CustomEnchants.HEALTH_DISPLAY);
    }

    @Override
    protected void onEntityHitWithCustomEnchant(Player player, LivingEntity entity, double damageDealt) {
        // Displays nothing. Put here to over-ride an action-bar
        // if it was already displayed (avoids it lingering too long)
        if (entityIsNowDead(entity, damageDealt)) {
            sendActionBar(player, "");
            return;
        }

        // Entity is not dead, so I'm displaying the health indicator
        String healthIndicator = getAttackedEntityHealthIndicator(entity, damageDealt);
        sendActionBar(player, healthIndicator);
    }

    private void sendActionBar(Player player, String msg) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
    }

    private String getAttackedEntityHealthIndicator(LivingEntity attackedEntity, double damageDealt) {
        String HEALTH_SYMBOL = "❤";
        ChatColor NO_HEALTH_COLOR = ChatColor.GRAY;
        ChatColor HEALTH_COLOR = ChatColor.GREEN;
        byte MIN_HEALTH = 0;
        double MAX_HEALTH = formatHealth(getMaxHealth(attackedEntity));

        double currentHealth = attackedEntity.getHealth();
        double newHealthAfterHit = formatHealth(currentHealth - damageDealt);

        StringBuilder healthIndicator = new StringBuilder();

        for (byte healthCounter = MIN_HEALTH; healthCounter <= MAX_HEALTH; healthCounter++) {
            if (healthCounter <= newHealthAfterHit) {
                healthIndicator.append(HEALTH_COLOR);
            } else {
                healthIndicator.append(NO_HEALTH_COLOR);
            }

            healthIndicator.append(HEALTH_SYMBOL);
        }

        return healthIndicator.toString();
    }

    // This is to make the health look normal to
    // the player, so I'm dividing the health by 2
    private double formatHealth(double health) {
        return health / 2;
    }

    private double getMaxHealth(LivingEntity livingEntity) {
        return Objects.requireNonNull(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
    }
}
