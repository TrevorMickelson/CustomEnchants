package com.mcaim.customenchants;

import com.mcaim.core.command.CommandRegistry;
import com.mcaim.core.command.QuickCommand;
import com.mcaim.customenchants.gui.MainMenuGui;
import com.mcaim.customenchants.listeners.BookApplyListener;
import com.mcaim.customenchants.listeners.armor.*;
import com.mcaim.customenchants.listeners.tools.AutoPickup;
import com.mcaim.customenchants.listeners.tools.AutoSmelt;
import com.mcaim.customenchants.listeners.tools.QuickSwitch;
import com.mcaim.customenchants.listeners.weapons.*;
import com.mcaim.customenchants.util.EnchantRegistry;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

//TODO: Make sure custom enchants can't be used in protected areas (Example -> SPAWN)
//TODO: Make sure to add a remove option on custom enchants (make free?)
//TODO: Implement costs to obtain, upgrade, and remove
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
        pm.registerEvents(new NoFall(), this);
        pm.registerEvents(new HealthBoost(), this);
        pm.registerEvents(new Speed(), this);
        pm.registerEvents(new FireResistance(), this);

        // Tools
        pm.registerEvents(new QuickSwitch(), this);
        pm.registerEvents(new AutoPickup(), this);
        pm.registerEvents(new AutoSmelt(), this);

        // Weapons
        pm.registerEvents(new HealthDisplay(), this);
        pm.registerEvents(new OneShot(), this);
        //pm.registerEvents(new ArmyOfDeath(), this);
        pm.registerEvents(new Poison(), this);
        pm.registerEvents(new Decapitator(), this);
    }

    public static EnchantPlugin getInstance() { return plugin; }
}
