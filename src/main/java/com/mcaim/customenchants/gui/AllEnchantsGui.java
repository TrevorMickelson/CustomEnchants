package com.mcaim.customenchants.gui;

import com.mcaim.core.gui.Gui;
import com.mcaim.core.item.ItemBuild;
import com.mcaim.customenchants.EnchantPlugin;
import com.mcaim.customenchants.models.CustomEnchant;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class AllEnchantsGui extends Gui {
    public AllEnchantsGui(Player player, boolean permissionCheck) {
        super(player, permissionCheck ? "Your Custom Enchants" : "All Custom Enchants", 54);
    }

    @Override
    public void init() {
        int inventoryIndex = 0;

        for (CustomEnchant customEnchant : EnchantPlugin.getInstance().getEnchantStorage().getAllCustomEnchants()) {
            setItem(inventoryIndex, getGuiItemFromCustomEnchant(customEnchant));
            inventoryIndex++;
        }

        setBackButton(new MainMenuGui(player));
        fillBackGround();
    }

    private ItemStack getGuiItemFromCustomEnchant(CustomEnchant customEnchant) {
        String name = customEnchant.getColoredName();
        Material material = Material.ENCHANTED_BOOK;

        String[] lore = {
            "&f" + customEnchant.getDescription(),
            "",
            "&7Maximum Tier &8> &a&l" + customEnchant.getMaxLevel(),
            "&7Used on &8> &a&l" + Arrays.toString(customEnchant.getTargets())
        };

        return ItemBuild.of(material).name(name).flag(ItemFlag.HIDE_ATTRIBUTES).listLore(lore).build();
    }
}
