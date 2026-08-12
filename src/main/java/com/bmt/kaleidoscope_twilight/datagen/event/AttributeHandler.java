package com.bmt.kaleidoscope_twilight.datagen.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.core.effect.GiantBlessingEffect;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class AttributeHandler {

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Remove event) {
        if (event.getEffect().value() instanceof GiantBlessingEffect) {
            LivingEntity entity = event.getEntity();
            GiantBlessingEffect.removeAllBonuses(entity);
        }
    }

    @SubscribeEvent
    public static void onEffectExpire(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().value() instanceof GiantBlessingEffect) {
            LivingEntity entity = event.getEntity();
            GiantBlessingEffect.removeAllBonuses(entity);
        }
    }
}