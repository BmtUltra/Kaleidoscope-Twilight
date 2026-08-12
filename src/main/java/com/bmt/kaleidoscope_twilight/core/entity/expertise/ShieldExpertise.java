package com.bmt.kaleidoscope_twilight.core.entity.expertise;

import com.bmt.kaleidoscope_twilight.core.entity.boss.UmbralSunflower;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import twilightforest.init.TFDataAttachments;

public class ShieldExpertise extends AbstractSunflowerExpertise {
    public static final int DURATION = 35;
    private static final int COOLDOWN_BASE = 600;
    private static final int SHIELD_COUNT = 5;

    private float lockedYaw;

    public ShieldExpertise(EntityDataAccessor<Integer> animTimeData) {
        super("Shield", DURATION, 200, animTimeData);
    }

    @Override
    protected int rollCooldown(RandomSource random) {
        return COOLDOWN_BASE + random.nextInt(120);
    }

    @Override
    protected boolean canUse(UmbralSunflower boss, LivingEntity target) {
        return super.canUse(boss, target);
    }

    @Override
    protected void onStart(UmbralSunflower boss, LivingEntity target) {
        this.lockedYaw = boss.getYRot();
        boss.setDeltaMovement(0, boss.getDeltaMovement().y, 0);
        boss.getNavigation().stop();
        boss.setYRot(this.lockedYaw);
        boss.yBodyRot = this.lockedYaw;
        boss.yHeadRot = this.lockedYaw;
        boss.playSound(SoundEvents.SHIELD_BLOCK, 1.0F, 0.8F);
    }

    @Override
    protected void tick(UmbralSunflower boss, int remaining) {
        boss.setDeltaMovement(0, boss.getDeltaMovement().y, 0);
        boss.getNavigation().stop();
        boss.setYRot(this.lockedYaw);
        boss.yBodyRot = this.lockedYaw;
        boss.yHeadRot = this.lockedYaw;
    }

    @Override
    protected void onEnd(UmbralSunflower boss) {
        if (!boss.level().isClientSide()) {
            var shieldsData = boss.getData(TFDataAttachments.FORTIFICATION_SHIELDS);
            shieldsData.setShields(boss, SHIELD_COUNT, true);
            boss.playSound(SoundEvents.SHIELD_BLOCK, 1.0F, 1.2F);
        }
    }
}
