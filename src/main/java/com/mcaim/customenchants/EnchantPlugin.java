package com.mcaim.customenchants;

import com.mcaim.core.command.CommandRegistry;
import com.mcaim.core.command.QuickCommand;
import com.mcaim.customenchants.gui.MainMenuGui;
import com.mcaim.customenchants.listeners.tools.AutoPickup;
import com.mcaim.customenchants.models.CustomEnchantBuilder;
import com.mcaim.customenchants.models.CustomEnchantments;
import com.mcaim.customenchants.util.EnchantRegistry;
import com.mcaim.customenchants.util.EnchantStorage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EnchantPlugin extends JavaPlugin {
    private static EnchantPlugin plugin;
    private EnchantStorage enchantStorage;

    @Override
    public void onEnable() {
        plugin = this;
        enchantStorage = new EnchantStorage();
        registerCommand();
        EnchantRegistry.registerAllCustomEnchants();
        getServer().getPluginManager().registerEvents(new AutoPickup(), this);
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

        QuickCommand.create().assertPlayer().register("autopickup_test", sender -> {
            Player player = (Player) sender;
            ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
            CustomEnchantBuilder.of(itemStack, CustomEnchantments.AUTO_PICKUP).addCustomEnchant();
            player.getInventory().addItem(itemStack);
        });
    }

    public static EnchantPlugin getInstance() { return plugin; }
    public EnchantStorage getEnchantStorage() { return enchantStorage; }
}
