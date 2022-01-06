package com.mcaim.customenchants.util;

import com.mcaim.core.item.ItemBuild;
import com.mcaim.core.item.ItemUtil;
import com.mcaim.core.util.Util;
import com.mcaim.customenchants.enchants.ICustomEnchant;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

//TODO: Upgrading adds new lore lines everytime (It should be replacing them)
//TODO: Fix this garbage class (re-factor it) it works, but it's a bit gross
public class CustomEnchantBuilder {
    private static final String ENCHANT_KEY = "CustomEnchant";

    private final ItemBuild builder;
    private final ICustomEnchant customEnchant;
    private int currentTier;

    public CustomEnchantBuilder(ItemStack itemStack, ICustomEnchant customEnchant) {
        this.builder = new ItemBuild(itemStack);
        this.customEnchant = customEnchant;

        Enchantment enchantment = customEnchant.getEnchantment();

        // If the enchantment is already on the item, setting
        // tier to the actual tier on the item, otherwise defaulting to 1
        if (itemStack.containsEnchantment(enchantment)) {
            currentTier = itemStack.getEnchantments().get(enchantment);
        } else {
            currentTier = 1;
        }
    }

    public void addCustomEnchant() {
        String lore = getCustomEnchantLoreName();
        builder.enchant(customEnchant.getEnchantment(), currentTier);

        // Not adding lore on enchanted books
        if (builder.build().getType() != Material.ENCHANTED_BOOK)
            builder.addLore(lore);

        if (!ItemUtil.hasUniqueKey(builder.build(), ENCHANT_KEY))
            builder.giveUniqueKey(ENCHANT_KEY);
    }

    public void upgradeCustomEnchant() {
        removeCustomEnchant();
        currentTier += 1;
        addCustomEnchant();
    }

    public void removeCustomEnchant() {
        String lore = getCustomEnchantLoreName();
        builder.removeLore(lore);
        builder.removeEnchant(customEnchant.getEnchantment());
    }

    private String getCustomEnchantLoreName() {
        String lore = customEnchant.getColoredName();
        lore += currentTier > 1 ? " " + Util.intToRomanNumeral(currentTier) : "";
        return lore;
    }

    public static CustomEnchantBuilder of(ItemStack itemStack, ICustomEnchant customEnchant) {
        return new CustomEnchantBuilder(itemStack, customEnchant);
    }
}
