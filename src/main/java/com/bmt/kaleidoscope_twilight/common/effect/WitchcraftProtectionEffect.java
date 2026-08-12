package com.bmt.kaleidoscope_twilight.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import twilightforest.init.TFDataAttachments;

public class WitchcraftProtectionEffect extends MobEffect {

    public WitchcraftProtectionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 120 == 0;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
                var fortificationShields = TFDataAttachments.FORTIFICATION_SHIELDS;
                var shieldsData = entity.getData(fortificationShields);
                shieldsData.setShields(entity, 3, true);
                return true;
        }
        return true;
    }
}