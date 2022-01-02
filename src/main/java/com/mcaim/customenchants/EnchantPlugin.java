package com.mcaim.customenchants;

import com.mcaim.core.command.CommandRegistry;
import com.mcaim.core.command.QuickCommand;
import com.mcaim.customenchants.gui.MainMenuGui;
import com.mcaim.customenchants.listeners.BookApplyListener;
import com.mcaim.customenchants.listeners.armor.*;
import com.mcaim.customenchants.listeners.tools.AutoPickup;
import com.mcaim.customenchants.listeners.tools.AutoSmelt;
import com.mcaim.customenchants.listeners.tools.QuickSwitch;
import com.mcaim.customenchants.util.EnchantRegistry;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

//TODO: Make sure custom enchants can't be used in protected areas (Example -> SPAWN)
public class EnchantPlugin extends JavaPlugin {
    private static EnchantPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        EnchantRegistry.registerAllCustomEnchants();
        registerCommand();
        registerListeners();
    }

    @Override
    public void onDisable() {
        CommandRegistry.unRegister("custom_enchants");
    }

    private void registerCommand() {
        QuickCommand.create().assertPlayer().register("custom_enchants", sender -> {
            Player player = (Player) sender;
            new MainMenuGui(player).open();
        });
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();

        // Normal listeners
        pm.registerEvents(new BookApplyListener(), this);

        // Armor
        pm.registerEvents(new Flight(), this);
        pm.registerEvents(new NoHunger(), this);
        pm.registerEvents(new Stomper(), this);
        pm.registerEvents(new Jump(), this);
        pm.registerEvents(new NightVision(), this);

        // Tools
        pm.registerEvents(new QuickSwitch(), this);
        pm.registerEvents(new AutoPickup(), this);
        pm.registerEvents(new AutoSmelt(), this);
    }

    public static EnchantPlugin getInstance() { return plugin; }
}
