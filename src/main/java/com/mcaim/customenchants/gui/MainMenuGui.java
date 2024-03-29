package com.mcaim.customenchants.gui;

import com.mcaim.core.gui.Gui;
import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.item.ItemUtil;
import com.mcaim.core.util.ChatPrefix;
import com.mcaim.customenchants.util.EnchantUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MainMenuGui extends Gui {
    public MainMenuGui(Player player) {
        super(player, "Custom Enchants Menu", 27);
    }

    @Override
    public void init() {
        ItemStack customEnchants = ItemBuild.of(Material.ENCHANTED_BOOK).name("&a&lCustom Enchants").lore("&7&oView all custom enchants").flag(ItemFlag.HIDE_ATTRIBUTES).build();
        ItemStack enchanter = ItemBuild.of(Material.DIAMOND_PICKAXE).name("&b&lEnchanter").lore("&7&oClick to obtain custom enchants").flag(ItemFlag.HIDE_ATTRIBUTES).build();
        ItemStack upgrader = ItemBuild.of(Material.ANVIL).name("&6&lEnchant Upgrader").lore("&7&oUpgrades existing enchant").build();
        ItemStack remover = ItemBuild.of(Material.ENDER_CHEST).name("&3&lEnchant Remover").lore("&7&oRemove unwanted enchants").build();

        setItem(10, customEnchants, (player) -> {
            new AllEnchantsGui(player).open();
        });

        setItem(12, enchanter, (player) -> {
            new EnchanterGui(player).open();
        });

        setCustomEnchantEditorMenuButton(14, upgrader, new UpgradeGui(player));
        setCustomEnchantEditorMenuButton(16, remover, new RemoveEnchantGui(player));

        fillBackGround();
    }

    private void setCustomEnchantEditorMenuButton(int slot, ItemStack display, Gui gui) {
        setItem(slot, display, (player) -> {
            ItemStack enchantedItem = player.getInventory().getItemInMainHand();

            if (!EnchantUtil.hasCustomEnchant(enchantedItem)) {
                player.closeInventory();
                player.sendMessage(ChatPrefix.FAIL + "Must be holding a custom enchanted item to use this!");
                return;
            }

            if (enchantedItem.getType() == Material.ENCHANTED_BOOK) {
                player.closeInventory();
                player.sendMessage(ChatPrefix.FAIL + "This menu does not work on books! Only works on items!");
                return;
            }

            gui.open();
        });
    }
}
