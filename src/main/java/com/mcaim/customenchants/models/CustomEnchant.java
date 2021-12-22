package com.mcaim.customenchants.models;

import com.mcaim.customenchants.EnchantPlugin;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class CustomEnchant extends Enchantment implements Listener {
    private final String name;
    private final ChatColor color;
    private final int maxLevel;
    private final EnchantTarget[] target;
    private String description = "";

    public CustomEnchant(String name, ChatColor color, int maxLevel, EnchantTarget... target) {
        super(new NamespacedKey(EnchantPlugin.getInstance(), name));
        this.name = name;
        this.color = color;
        this.maxLevel = maxLevel;
        this.target = target;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) { return false; }

    @Override
    public boolean conflictsWith(Enchantment enchantment) { return false; }

    @Override
    public EnchantmentTarget getItemTarget() { return EnchantmentTarget.ALL; }

    @Override
    public int getMaxLevel() { return maxLevel; }

    @Override
    public int getStartLevel() { return 0; }

    @Override
    public boolean isTreasure() { return false; }

    @Override
    public boolean isCursed() { return false; }

    @Override
    public String getName() { return name; }
    public String getColoredName() { return color + name; }
    public EnchantTarget[] getTargets() { return target; }
    public String getPermission() { return "customenchant." + name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
