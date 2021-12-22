package com.mcaim.customenchants.models;

import org.bukkit.Material;

public enum EnchantTarget {
    HELMET(39) {
        public boolean includes(Material type) {
            return type.toString().contains("HELMET");
        }
    },
    CHESTPLATE(38) {
        public boolean includes(Material type) {
            return type.toString().contains("CHESTPLATE");
        }
    },
    LEGGINGS(37) {
        public boolean includes(Material type) {
            return type.toString().contains("LEGGINGS");
        }
    },
    BOOTS(36) {
        public boolean includes(Material type) {
            return type.toString().contains("BOOTS");
        }
    },
    SWORD(-1) {
        public boolean includes(Material type) {
            return type.toString().contains("SWORD");
        }
    },
    AXE(-1) {
        public boolean includes(Material type) {
            return type.toString().contains("AXE") && !type.toString().contains("PICKAXE");
        }
    },
    PICKAXE(-1) {
        public boolean includes(Material type) {
            return type.toString().contains("PICKAXE");
        }
    },
    SHOVEL(-1) {
        public boolean includes(Material type) {
            return type.toString().contains("SHOVEL");
        }
    },
    TOOLS(-1) {
        public boolean includes(Material type) {
            String typeString = type.toString();
            return typeString.contains("AXE") || typeString.contains("SHOVEL");
        }
    },
    HOE(-1) {
        public boolean includes(Material type) {
            return type.toString().contains("HOE");
        }
    },
    BOW(-1) {
        public boolean includes(Material type) {
            return type.toString().contains("BOW");
        }
    };

    private final int slot;

    EnchantTarget(int slot) {
        this.slot = slot;
    }

    public final int getSlot() { return slot; }
    public abstract boolean includes(Material type);
}
