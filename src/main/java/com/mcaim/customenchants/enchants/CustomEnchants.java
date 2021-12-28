package com.mcaim.customenchants.enchants;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.EnchantmentTarget;

public final class CustomEnchants {
    /**
     * --------------------------- ( ARMOR ) ---------------------------
     */
    // COMMON
    public static final ICustomEnchant NIGHT_VISION = new CustomEnchantWrapper(
            "NightVision", ChatColor.AQUA,
            1, EnchantmentTarget.ARMOR_HEAD,
            "I can finally see", CustomEnchantTier.COMMON);

    public static final ICustomEnchant JUMP = new CustomEnchantWrapper(
            "Jump", ChatColor.GREEN,
            5, EnchantmentTarget.ARMOR_FEET,
            "Ability to jump higher", CustomEnchantTier.COMMON);

    // UNCOMMON
    public static final ICustomEnchant NO_FALL = new CustomEnchantWrapper(
            "NoFall", ChatColor.GOLD,
            1, EnchantmentTarget.ARMOR_LEGS,
            "Never take fall damage again", CustomEnchantTier.UNCOMMON);

    public static final ICustomEnchant STOMPER = new CustomEnchantWrapper(
            "Stomper", ChatColor.GOLD,
            5, EnchantmentTarget.ARMOR_FEET,
            "Damages nearby entities upon fall damage", CustomEnchantTier.UNCOMMON);

    // RARE
    public static final ICustomEnchant FLIGHT = new CustomEnchantWrapper(
            "Flight", ChatColor.WHITE,
            1, EnchantmentTarget.ARMOR_FEET,
            "I believe I can fly", CustomEnchantTier.RARE);

    public static final ICustomEnchant SPEED = new CustomEnchantWrapper(
            "Speed", ChatColor.AQUA,
            5, EnchantmentTarget.ARMOR_LEGS,
            "Run baby run", CustomEnchantTier.RARE);

    // VERY RARE
    public static final ICustomEnchant HEALTH_BOOST = new CustomEnchantWrapper(
            "HealthBoost", ChatColor.YELLOW,
            3, EnchantmentTarget.ARMOR_TORSO,
            "Gives more health", CustomEnchantTier.VERY_RARE);

    public static final ICustomEnchant NO_HUNGER = new CustomEnchantWrapper(
            "NoHunger", ChatColor.DARK_GREEN,
            1, EnchantmentTarget.ARMOR_TORSO,
            "Never go hungry again", CustomEnchantTier.VERY_RARE);

    /**
     * --------------------------- ( TOOLS ) ---------------------------
     */
    // COMMON
    public static final ICustomEnchant SPAWNER_MINER = new CustomEnchantWrapper(
            "SpawnerMiner", ChatColor.GOLD,
            1, EnchantmentTarget.TOOL,
            "Gain ability to mine spawners", CustomEnchantTier.COMMON);

    // UNCOMMON
    public static final ICustomEnchant AUTO_SMELT = new CustomEnchantWrapper(
            "AutoSmelt", ChatColor.LIGHT_PURPLE,
            1, EnchantmentTarget.TOOL,
            "Automatically smelts blocks", CustomEnchantTier.UNCOMMON);

    // RARE
    public static final ICustomEnchant AUTO_PICKUP = new CustomEnchantWrapper(
            "AutoPickup", ChatColor.GREEN,
            1, EnchantmentTarget.TOOL,
            "Automatically picks up blocks", CustomEnchantTier.RARE);

    // VERY RARE
    public static final ICustomEnchant QUICK_SWITCH = new CustomEnchantWrapper(
            "QuickSwitch", ChatColor.RED,
            1, EnchantmentTarget.TOOL,
            "Updates item based on block broken", CustomEnchantTier.VERY_RARE);

    /**
     * --------------------------- ( WEAPONS ) ---------------------------
     */
    // COMMON
    public static final ICustomEnchant HEALTH_DISPLAY = new CustomEnchantWrapper(
            "HealthDisplay", ChatColor.RED,
            1, EnchantmentTarget.WEAPON,
            "Displays enemies health bar", CustomEnchantTier.COMMON);

    // UNCOMMON
    public static final ICustomEnchant POISON = new CustomEnchantWrapper(
            "Poison", ChatColor.GREEN,
            5, EnchantmentTarget.WEAPON,
            "Poisons the enemy", CustomEnchantTier.UNCOMMON);

    public static final ICustomEnchant DECAPITATOR = new CustomEnchantWrapper(
            "Decapitator", ChatColor.GOLD,
            5, EnchantmentTarget.WEAPON,
            "Chance to drop a enemies head", CustomEnchantTier.UNCOMMON);

    // RARE
    public static final ICustomEnchant ARMY_OF_DEATH = new CustomEnchantWrapper(
            "ArmyOfDeath", ChatColor.DARK_AQUA,
            5, EnchantmentTarget.WEAPON,
            "Spawns attack wolfs when hitting an enemy", CustomEnchantTier.RARE);

    // VERY RARE
    public static final ICustomEnchant ONE_SHOT = new CustomEnchantWrapper(
            "OneShot", ChatColor.DARK_RED,
            1, EnchantmentTarget.WEAPON,
            "One shots all mobs", CustomEnchantTier.VERY_RARE);
}
