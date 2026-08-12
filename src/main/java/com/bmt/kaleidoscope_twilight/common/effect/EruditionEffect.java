package com.bmt.kaleidoscope_twilight.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import org.jetbrains.annotations.NotNull;

public class EruditionEffect extends MobEffect {

    public EruditionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return false;
    }

    @Override
    public boolean applyEffectTick(@NotNull net.minecraft.world.entity.LivingEntity entity, int amplifier) {
        return true;
    }
}