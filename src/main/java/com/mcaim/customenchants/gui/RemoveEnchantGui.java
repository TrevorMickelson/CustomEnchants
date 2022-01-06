package com.mcaim.customenchants.gui;

import com.mcaim.core.gui.Gui;
import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.util.ChatPrefix;
import com.mcaim.customenchants.enchants.ICustomEnchant;
import com.mcaim.customenchants.util.CustomEnchantBuilder;
import com.mcaim.customenchants.util.EnchantUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RemoveEnchantGui extends Gui {
    public RemoveEnchantGui(Player player) {
        super(player, "Removal Menu", 27);
    }

    @Override
    protected void init() {
        ItemStack inHand = player.getInventory().getItemInMainHand();
        int inventoryIndex = 0;

        for (ICustomEnchant customEnchant : EnchantUtil.getCustomEnchantsFromItemStack(inHand)) {
            ItemStack enchantedBook = ItemBuild
                    .of(Material.ENCHANTED_BOOK)
                    .name(customEnchant.getColoredName())
                    .lore("&7&oClick to remove enchant...", "&7&oEnchant will be returned to you!")
                    .build();

            setItem(inventoryIndex, enchantedBook, (player) -> {
                CustomEnchantBuilder.of(inHand).removeCustomEnchant(customEnchant);
                EnchantUtil.givePlayerCustomEnchantBook(player, customEnchant);

                // Re-opening to "refresh" the gui
                new RemoveEnchantGui(player).open();
                player.sendMessage(ChatPrefix.SUCCESS + "Custom enchant " + customEnchant.getColoredName() + ChatColor.GRAY + " has successfully been removed!");
            });

            inventoryIndex++;

        }

        setBackButton(new MainMenuGui(player));
        fillBackGround();
    }
}
