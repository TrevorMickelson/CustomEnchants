package com.mcaim.customenchants.listeners.tools;

import com.mcaim.customenchants.enchants.CustomEnchants;
import com.mcaim.customenchants.listeners.CustomEnchantListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

public final class AutoSmelt extends CustomEnchantListener {
    public AutoSmelt() {
        super(CustomEnchants.AUTO_SMELT);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

    }
}
