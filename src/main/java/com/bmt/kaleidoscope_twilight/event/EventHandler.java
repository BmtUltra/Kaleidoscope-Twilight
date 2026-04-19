package com.bmt.kaleidoscope_twilight.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.init.TFDamageTypes;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(KTEffects.PHANTOM.get())) {
            if (event.getSource().getMsgId().equals("inWall")) {
                event.setCanceled(true);
            }
        }

        if (entity.hasEffect(KTEffects.STURDY_SCALES.get())) {
            var effectInstance = entity.getEffect(KTEffects.STURDY_SCALES.get());
            if (effectInstance != null) {
                int amplifier = effectInstance.getAmplifier();
                float originalDamage = event.getAmount();
                float reducedDamage = originalDamage * 0.7f;
                float flatReduction = 5.0f + (amplifier * 2.0f);
                reducedDamage = Math.max(0, reducedDamage - flatReduction);
                if (reducedDamage < originalDamage) {
                    event.setCanceled(true);
                    entity.hurt(event.getSource(), reducedDamage);
                }
            }
        }

        if (event.getSource().is(TFDamageTypes.HYDRA_MORTAR)) {
            if (event.getSource().getDirectEntity() instanceof HydraMortar mortar) {
                if (mortar.getOwner() != null && mortar.getOwner().equals(entity)) {
                    float originalDamage = event.getAmount();
                    float reducedDamage = originalDamage * 0.1f;
                    if (reducedDamage < originalDamage) {
                        event.setCanceled(true);
                        entity.hurt(event.getSource(), reducedDamage);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onExperienceCost(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity();
        if (player.hasEffect(KTEffects.ERUDITION.get())) {
            if (event.getAmount() < 0) {
                event.setAmount(0);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelChange(PlayerXpEvent.LevelChange event) {
        Player player = event.getEntity();
        if (player.hasEffect(KTEffects.ERUDITION.get())) {
            if (event.getLevels() < 0) {
                event.setLevels(0);
            }
        }
    }
}