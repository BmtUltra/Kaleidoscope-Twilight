package com.bmt.kaleidoscope_twilight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import twilightforest.capabilities.CapabilityList;
import twilightforest.capabilities.shield.IShieldCapability;

public class WitchcraftProtectionEffect extends MobEffect {

    public WitchcraftProtectionEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 160 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            entity.getCapability(CapabilityList.SHIELDS).ifPresent(IShieldCapability::replenishShields);
        }
    }
}