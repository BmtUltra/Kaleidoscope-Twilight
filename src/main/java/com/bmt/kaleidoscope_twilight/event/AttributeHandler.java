package com.bmt.kaleidoscope_twilight.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.effect.GiantBlessingEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class AttributeHandler {

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Remove event) {
        if (event.getEffect() instanceof GiantBlessingEffect) {
            LivingEntity entity = event.getEntity();
            GiantBlessingEffect.removeAllBonuses(entity);
        }
    }

    @SubscribeEvent
    public static void onEffectExpire(MobEffectEvent.Expired event) {
        if (event.getEffectInstance() != null && event.getEffectInstance().getEffect() instanceof GiantBlessingEffect) {
            LivingEntity entity = event.getEntity();
            GiantBlessingEffect.removeAllBonuses(entity);
        }
    }
}