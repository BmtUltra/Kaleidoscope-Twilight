package com.bmt.kaleidoscope_twilight.common.entity.expertise;

import com.bmt.kaleidoscope_twilight.common.entity.SwordAuraEntity;
import com.bmt.kaleidoscope_twilight.common.entity.boss.UmbralSunflower;
import com.bmt.kaleidoscope_twilight.init.KTEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class SwordAuraExpertise extends AbstractSunflowerExpertise {
    public static final int DURATION = 62;
    private static final int COOLDOWN_BASE = 300;
    private static final double RANGE = 25.0;
    private static final float AURA_SPEED = 1.5F;
    private static final int FIRST_SLASH_TICK = 48;
    private static final int SECOND_SLASH_TICK = 57;
    private static final float YAW_LERP_FACTOR = 0.35F;

    public SwordAuraExpertise(EntityDataAccessor<Integer> animTimeData) {
        super("SwordAura", DURATION, 150, animTimeData);
    }

    @Override
    protected int rollCooldown(RandomSource random) {
        return COOLDOWN_BASE + random.nextInt(120);
    }

    @Override
    protected boolean canUse(UmbralSunflower boss, LivingEntity target) {
        return boss.distanceToSqr(target) <= RANGE * RANGE;
    }

    @Override
    protected void onStart(UmbralSunflower boss, LivingEntity target) {
        boss.getNavigation().stop();
        boss.setDeltaMovement(0, boss.getDeltaMovement().y, 0);
        boss.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0F, 0.8F);
    }

    @Override
    protected void tick(UmbralSunflower boss, int remaining) {
        int elapsed = this.elapsed(remaining);
        if (!boss.getNavigation().isDone()) {
            boss.getNavigation().stop();
            boss.setDeltaMovement(0, boss.getDeltaMovement().y, 0);
        }

        LivingEntity target = boss.getTarget();
        if (target != null && target.isAlive()) {
            double dx = target.getX() - boss.getX();
            double dz = target.getZ() - boss.getZ();
            float targetYaw = (float) (Mth.atan2(dz, dx) * Mth.RAD_TO_DEG) - 90.0F;
            float newYaw = boss.getYRot() + Mth.wrapDegrees(targetYaw - boss.getYRot()) * YAW_LERP_FACTOR;
            boss.setYRot(newYaw);
            boss.yBodyRot = newYaw;
            boss.yHeadRot = newYaw;
        }

        if (elapsed == FIRST_SLASH_TICK || elapsed == SECOND_SLASH_TICK) {
            this.fireSwordAura(boss);
        }
    }

    private void fireSwordAura(UmbralSunflower boss) {
        if (boss.level().isClientSide()) return;

        SwordAuraEntity aura = KTEntities.SWORD_AURA.get().create(boss.level());
        if (aura == null) return;

        float yawRad = boss.getYRot() * Mth.DEG_TO_RAD;
        float dirX = -Mth.sin(yawRad);
        float dirZ = Mth.cos(yawRad);

        double spawnX = boss.getX() + dirX * 1.5;
        double spawnY = boss.getY() + 0.5;
        double spawnZ = boss.getZ() + dirZ * 1.5;

        aura.setPos(spawnX, spawnY, spawnZ);
        aura.setOwner(boss);

        LivingEntity target = boss.getTarget();
        Vec3 velocity;
        if (target != null && target.isAlive()) {
            Vec3 toTarget = new Vec3(
                    target.getX() - spawnX,
                    0.0D,
                    target.getZ() - spawnZ
            ).normalize();
            velocity = toTarget.scale(AURA_SPEED);
        } else {
            velocity = new Vec3(dirX * AURA_SPEED, 0.0D, dirZ * AURA_SPEED);
        }
        aura.setDeltaMovement(velocity);
        aura.setNoGravity(true);

        boss.level().addFreshEntity(aura);
        boss.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 0.6F, 1.4F);
    }
}
