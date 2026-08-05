package com.bmt.kaleidoscope_twilight.entity.expertise;

import com.bmt.kaleidoscope_twilight.entity.boss.UmbralSunflower;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;

public class FlashStrikeExpertise extends AbstractSunflowerExpertise {
    public static final int DURATION = 18;
    private static final int COOLDOWN_BASE = 200;
    private float lockedYaw;

    public FlashStrikeExpertise(EntityDataAccessor<Integer> animTimeData) {
        super("Flash", DURATION, 100, animTimeData);
    }

    @Override
    protected int rollCooldown(RandomSource random) {
        return COOLDOWN_BASE + random.nextInt(100);
    }

    @Override
    protected boolean canUse(UmbralSunflower boss, LivingEntity target) {
        double distSq = boss.distanceToSqr(target);
        return distSq > 25.0 && distSq < 512.0;
    }

    @Override
    protected void onStart(UmbralSunflower boss, LivingEntity target) {
        this.sendPortalParticles(boss, boss.getX(), boss.getY() + 1.0, boss.getZ(), 40, 0.5);
        double dx = target.getX() - boss.getX();
        double dz = target.getZ() - boss.getZ();
        double len = Math.sqrt(dx * dx + dz * dz);
        double ux = len > 0.1 ? dx / len : 0.0;
        double uz = len > 0.1 ? dz / len : 0.0;
        boss.teleportTo(target.getX() - ux * 4.0, target.getY(), target.getZ() - uz * 4.0);
        float yaw = (float) (Mth.atan2(dz, dx) * Mth.RAD_TO_DEG) - 90.0F;
        this.lockedYaw = yaw;
        boss.setYRot(yaw);
        boss.yRotO = yaw;
        boss.yBodyRot = yaw;
        boss.yBodyRotO = yaw;
        boss.yHeadRot = yaw;
        boss.yHeadRotO = yaw;
        this.sendPortalParticles(boss, boss.getX(), boss.getY() + 1.0, boss.getZ(), 40, 0.5);
        boss.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
    }

    @Override
    protected void tick(UmbralSunflower boss, int remaining) {
        int elapsed = this.elapsed(remaining);
        boss.setDeltaMovement(0, boss.getDeltaMovement().y, 0);
        boss.getNavigation().stop();
        boss.setYRot(this.lockedYaw);
        boss.yBodyRot = this.lockedYaw;
        boss.yHeadRot = this.lockedYaw;
        if (elapsed == 5) {
            LivingEntity target = boss.getTarget();
            if (target != null && !target.isDeadOrDying() && boss.distanceToSqr(target) < 36.0) {
                target.hurt(boss.damageSources().mobAttack(boss), 25.0F);
                target.knockback(1.2, boss.getX() - target.getX(), boss.getZ() - target.getZ());
                target.hurtMarked = true;
                this.sendPortalParticles(boss, target.getX(), target.getY() + 1.0, target.getZ(), 25, 0.2);
                boss.playSound(SoundEvents.PLAYER_ATTACK_STRONG, 1.0F, 0.8F);
            }
        }
    }

    private void sendPortalParticles(UmbralSunflower boss, double x, double y, double z, int count, double speed) {
        if (boss.level() instanceof ServerLevel slevel) {
            slevel.sendParticles(ParticleTypes.PORTAL, x, y, z, count, 0.5, 1.0, 0.5, speed);
        }
    }
}
