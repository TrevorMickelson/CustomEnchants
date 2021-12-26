package com.mcaim.customenchants.listeners.tools;

import com.mcaim.customenchants.models.ICustomEnchant;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public interface CustomEnchantListener extends Listener {
    ICustomEnchant getCustomEnchant();
    <T extends Event> void run(T bukkitEvent);
}
