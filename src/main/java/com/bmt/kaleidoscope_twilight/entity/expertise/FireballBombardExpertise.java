package com.bmt.kaleidoscope_twilight.entity.expertise;

import com.bmt.kaleidoscope_twilight.entity.boss.UmbralSunflower;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.init.TFEntities;

public class FireballBombardExpertise extends AbstractSunflowerExpertise {
    public static final int DURATION = 100;
    public static final int RISE_TICKS = 30;
    private static final int COOLDOWN_BASE = 600;
    private static final double RISE_HEIGHT = 2.0;

    private double startY;

    public FireballBombardExpertise(EntityDataAccessor<Integer> animTimeData) {
        super("Fireball", DURATION, 200, animTimeData);
    }

    @Override
    protected int rollCooldown(RandomSource random) {
        return COOLDOWN_BASE + random.nextInt(200);
    }

    @Override
    protected void onStart(UmbralSunflower boss, LivingEntity target) {
        this.startY = boss.getY();
        boss.setNoGravity(true);
        boss.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 0.7F);
    }

    @Override
    protected void tick(UmbralSunflower boss, int remaining) {
        int elapsed = this.elapsed(remaining);
        if (elapsed < RISE_TICKS) {
            if (boss.getY() < this.startY + RISE_HEIGHT) {
                boss.setDeltaMovement(0, 0.35, 0);
            } else {
                boss.setDeltaMovement(0, 0, 0);
            }
        } else if (remaining <= 15) {
            boss.setDeltaMovement(0, -0.4, 0);
        } else {
            boss.setDeltaMovement(0, 0, 0);
        }
        boss.getNavigation().stop();
        if (elapsed >= RISE_TICKS && remaining > 15 && elapsed % 6 == 0) {
            this.shootVolley(boss, elapsed);
        }
    }

    @Override
    protected void onEnd(UmbralSunflower boss) {
        boss.setNoGravity(false);
    }

    private void shootVolley(UmbralSunflower boss, int elapsed) {
        RandomSource random = boss.getRandom();
        float pitch = 25.0F * Mth.DEG_TO_RAD;
        float ringOffset = (elapsed * 13.0F) * Mth.DEG_TO_RAD;
        for (int i = 0; i < 8; i++) {
            float a = ringOffset + i * (45.0F * Mth.DEG_TO_RAD) + random.nextFloat() * 0.15F;
            double dx = Mth.cos(a) * Mth.cos(pitch);
            double dy = -Mth.sin(pitch);
            double dz = Mth.sin(a) * Mth.cos(pitch);
            this.spawnFireball(boss, dx, dy, dz, 0.9F);
        }
        LivingEntity target = boss.getTarget();
        if (target != null) {
            for (int j = 0; j < 2; j++) {
                double dx = target.getX() - boss.getX();
                double dy = (target.getY() + target.getBbHeight() * 0.5) - boss.getY();
                double dz = target.getZ() - boss.getZ();
                double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
                if (len > 0.1) {
                    dx /= len;
                    dy /= len;
                    dz /= len;
                }
                dx += (random.nextDouble() - 0.5) * 0.15;
                dy += (random.nextDouble() - 0.5) * 0.15;
                dz += (random.nextDouble() - 0.5) * 0.15;
                this.spawnFireball(boss, dx, dy, dz, 1.1F);
            }
        }
    }

    private void spawnFireball(UmbralSunflower boss, double dx, double dy, double dz, float speed) {
        HydraMortar fireball = new HydraMortar(TFEntities.HYDRA_MORTAR.get(), boss.level());
        fireball.setPos(boss.getX() + dx * 1.6, boss.getY() + 1.0 + dy * 1.6, boss.getZ() + dz * 1.6);
        fireball.setOwner(boss);
        fireball.shoot(dx, dy, dz, speed, 1.0F);
        boss.level().addFreshEntity(fireball);
        boss.playSound(SoundEvents.FIRECHARGE_USE, 0.6F, 0.9F + boss.getRandom().nextFloat() * 0.3F);
    }
}
