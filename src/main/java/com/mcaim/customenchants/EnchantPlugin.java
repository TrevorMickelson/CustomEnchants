package com.mcaim.customenchants;

import com.mcaim.core.command.QuickCommand;
import com.mcaim.customenchants.gui.MainMenuGui;
import com.mcaim.customenchants.listeners.CustomBookListener;
import com.mcaim.customenchants.util.EnchantRegistry;
import com.mcaim.customenchants.util.EnchantStorage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnchantPlugin extends JavaPlugin {
    private static EnchantPlugin main;
    public static EnchantPlugin getInstance() { return main; }

    private final EnchantStorage enchantStorage = new EnchantStorage();
    public EnchantStorage getEnchantStorage() { return enchantStorage; }

    @Override
    public void onEnable() {
        main = this;
        EnchantRegistry.registerAllCustomEnchants();
        registerCommand();
        getServer().getPluginManager().registerEvents(new CustomBookListener(), this);
    }

    public void registerCommand() {
        QuickCommand.create().assertPlayer().register("customenchants", sender -> {
            Player player = (Player) sender;
            new MainMenuGui(player).open();
        });
    }
    @Override
    public void onDisable() {
    }
}
