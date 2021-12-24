package com.mcaim.customenchants.gui;

import com.mcaim.core.gui.Gui;
import com.mcaim.core.item.ItemBuild;
import com.mcaim.customenchants.EnchantPlugin;
import com.mcaim.customenchants.models.ICustomEnchant;
import com.mcaim.customenchants.util.EnchantStorage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class AllEnchantsGui extends Gui {
    public AllEnchantsGui(Player player) {
        super(player, "All Custom Enchants", 54);
    }

    @Override
    public void init() {
        int inventoryIndex = 0;
        EnchantStorage storage = EnchantPlugin.getInstance().getEnchantStorage();


        for (ICustomEnchant customEnchant : storage.getCustomEnchants()) {
            setItem(inventoryIndex, getGuiItemFromCustomEnchant(customEnchant));
            inventoryIndex++;
        }

        setBackButton(new MainMenuGui(player));
        fillBackGround();
    }

    private ItemStack getGuiItemFromCustomEnchant(ICustomEnchant customEnchant) {
        String name = customEnchant.getColoredName();
        Material material = Material.ENCHANTED_BOOK;

        String[] lore = {
            "&f" + customEnchant.getDescription(),
            "",
            "&7Maximum Tier &8> &a&l" + customEnchant.getMaxLevel(),
            "&7Used on &8> &a&l" + customEnchant.getEnchantmentTarget().name()
        };

        return ItemBuild.of(material).name(name).flag(ItemFlag.HIDE_ATTRIBUTES).listLore(lore).build();
    }
}