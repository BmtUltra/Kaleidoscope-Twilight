package com.bmt.kaleidoscope_twilight.event;

import com.bmt.kaleidoscope_twilight.init.KTEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.init.TFDamageTypes;

@EventBusSubscriber(modid = "kaleidoscope_twilight")
public class EventHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(KTEffects.PHANTOM)) {
            if (event.getSource().type().msgId().equals("inWall")) {
                event.setCanceled(true);
            }
        }

        if (entity.hasEffect(KTEffects.STURDY_SCALES)) {
            var effectInstance = entity.getEffect(KTEffects.STURDY_SCALES);
            if (effectInstance != null) {
                int amplifier = effectInstance.getAmplifier();
                float originalDamage = event.getAmount();
                float reducedDamage = originalDamage * 0.7f;
                float flatReduction = 5.0f + (amplifier * 2.0f);
                reducedDamage = Math.max(0, reducedDamage - flatReduction);
                event.setAmount(reducedDamage);
            }
        }

        if (event.getSource().is(TFDamageTypes.HYDRA_MORTAR)) {
            if (event.getSource().getDirectEntity() instanceof HydraMortar mortar) {
                if (mortar.getOwner() != null && mortar.getOwner().equals(entity)) {
                    float originalDamage = event.getAmount();
                    float reducedDamage = originalDamage * 0.2f;
                    event.setAmount(reducedDamage);
                }
            }
        }
    }
}