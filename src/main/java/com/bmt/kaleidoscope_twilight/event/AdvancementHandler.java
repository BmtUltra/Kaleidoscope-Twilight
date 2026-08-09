package com.bmt.kaleidoscope_twilight.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.entity.boss.UmbralSunflower;
import com.bmt.kaleidoscope_twilight.init.KTTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import twilightforest.entity.boss.Naga;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class AdvancementHandler {
    public static final int NAGA_SLAYER_KILLS = 20;

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Naga)) {
            return;
        }
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) {
            return;
        }
        int kills = player.getStats().getValue(Stats.ENTITY_KILLED, event.getEntity().getType()) + 1;
        if (kills >= NAGA_SLAYER_KILLS) {
            KTTriggers.NAGA_SLAYER.get().trigger(player);
        }
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getTarget() instanceof UmbralSunflower)) {
            return;
        }
        if (event.getEntity() instanceof ServerPlayer player) {
            KTTriggers.SUNFLOWER_SEEN.get().trigger(player);
        }
    }
}
