package com.bmt.kaleidoscope_twilight.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public final class FoodHelper {

    public static FoodProperties withEffect(FoodProperties original, Holder<MobEffect> effect, int duration, int amplifier, float probability) {
        List<FoodProperties.PossibleEffect> effects = new ArrayList<>(original.effects());
        effects.add(new FoodProperties.PossibleEffect(
                () -> new MobEffectInstance(effect, duration, amplifier),
                probability
        ));
        return new FoodProperties(
                original.nutrition(),
                original.saturation(),
                original.canAlwaysEat(),
                original.eatSeconds(),
                original.usingConvertsTo(),
                effects
        );
    }
}