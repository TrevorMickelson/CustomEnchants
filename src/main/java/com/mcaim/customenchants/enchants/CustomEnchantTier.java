package com.mcaim.customenchants.enchants;

public enum CustomEnchantTier {
    COMMON("&f&lCOMMON", 15),
    UNCOMMON("&6&lUNCOMMON", 30),
    RARE("&d&lRARE", 50),
    VERY_RARE("&5&lVERY RARE", 100);

    private final String name;
    private final int xpCost;

    CustomEnchantTier(String name, int xpCost) {
        this.name = name;
        this.xpCost = xpCost;
    }

    public final String getName() { return name; }
    public final int getXpCost() { return xpCost; }
}
