package com.bmt.kaleidoscope_twilight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class PhantomEffect extends MobEffect {

    public PhantomEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}