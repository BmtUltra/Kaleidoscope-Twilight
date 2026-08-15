package com.bmt.kaleidoscope_twilight.util;

import net.minecraft.world.entity.player.Player;

public class FlightHelper {

    public static void enableFlight(Player player) {
        boolean changed = false;

        if (!player.getAbilities().flying) {
            player.getAbilities().flying = true;
            changed = true;
        }

        if (!player.getAbilities().mayfly) {
            player.getAbilities().mayfly = true;
            changed = true;
        }

        if (changed) {
            player.onUpdateAbilities();
        }

        player.fallDistance = 0.0F;
    }
}