package com.bmt.kaleidoscope_twilight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import twilightforest.init.TFMobEffects;

public class YetiThrowEffect extends MobEffect {

    public YetiThrowEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity.getLastHurtMob() != null && entity.getLastHurtMobTimestamp() == entity.tickCount - 1) {
            LivingEntity target = entity.getLastHurtMob();

            int frostDuration = 120 + (amplifier * 40);

            target.addEffect(new MobEffectInstance(
                    TFMobEffects.FROSTY,
                    frostDuration,
                    1,
                    false,
                    true,
                    true
            ));
        }
        return true;
    }
}