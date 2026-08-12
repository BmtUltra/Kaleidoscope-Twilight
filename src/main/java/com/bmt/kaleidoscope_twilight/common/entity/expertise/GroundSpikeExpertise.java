package com.bmt.kaleidoscope_twilight.common.entity.expertise;

import com.bmt.kaleidoscope_twilight.common.entity.GroundSpikeEntity;
import com.bmt.kaleidoscope_twilight.common.entity.boss.UmbralSunflower;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public class GroundSpikeExpertise extends AbstractSunflowerExpertise {
    public static final int DURATION = 70;
    public static final int SLAM_TICK = 24;
    public static final int RING_INTERVAL = 10;
    public static final float[] RING_RADII = {2.5F, 5.0F, 7.5F, 10.0F, 12.5F, 15.0F, 17.5F};
    private static final int COOLDOWN_BASE = 350;
    private static final double RANGE = 8.0;

    public GroundSpikeExpertise(EntityDataAccessor<Integer> animTimeData) {
        super("GroundSpike", DURATION, 180, animTimeData);
    }

    @Override
    protected int rollCooldown(RandomSource random) {
        return COOLDOWN_BASE + random.nextInt(150);
    }

    @Override
    protected boolean canUse(UmbralSunflower boss, LivingEntity target) {
        return boss.distanceToSqr(target) <= RANGE * RANGE;
    }

    @Override
    protected void onStart(UmbralSunflower boss, LivingEntity target) {
        boss.getNavigation().stop();
        boss.setDeltaMovement(0, boss.getDeltaMovement().y, 0);
        boss.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 0.5F);
    }

    @Override
    protected void tick(UmbralSunflower boss, int remaining) {
        int elapsed = this.elapsed(remaining);
        boss.getNavigation().stop();
        boss.setDeltaMovement(0, boss.getDeltaMovement().y, 0);

        if (elapsed < SLAM_TICK) {
            if (elapsed % 3 == 0) {
                this.spawnWarningParticles(boss, elapsed / (float) SLAM_TICK);
            }
        } else if (elapsed == SLAM_TICK) {
            boss.playSound(SoundEvents.ANVIL_LAND, 1.0F, 0.9F);
            this.spawnSpikeRings(boss);
        }
    }

    private void spawnWarningParticles(UmbralSunflower boss, float chargeProgress) {
        if (boss.level().isClientSide()) return;
        Vec3 center = boss.position();

        float radius = RING_RADII[RING_RADII.length - 1] * chargeProgress;
        int particleCount = Math.max(8, (int) (radius * 6.0F));
        for (int i = 0; i < particleCount; i++) {
            double angle = i * Math.PI * 2.0D / particleCount;
            boss.level().addParticle(ParticleTypes.CRIT,
                    center.x + Math.cos(angle) * radius,
                    center.y + 0.15D,
                    center.z + Math.sin(angle) * radius,
                    0.0D, 0.08D, 0.0D);
        }
    }

    private void spawnSpikeRings(UmbralSunflower boss) {
        if (boss.level().isClientSide()) return;
        Vec3 center = boss.position();

        for (int i = 0; i < RING_RADII.length; i++) {
            GroundSpikeEntity.spawn(boss.level(), center, boss,
                    new ItemStack(this.randomSword(boss.getRandom())),
                    RING_RADII[i], i * RING_INTERVAL);
        }
    }

    private Item randomSword(RandomSource random) {
        Item[] swords = {
                Items.WOODEN_SWORD,
                Items.STONE_SWORD,
                Items.IRON_SWORD,
                Items.GOLDEN_SWORD,
                Items.DIAMOND_SWORD,
                Items.NETHERITE_SWORD
        };
        return swords[random.nextInt(swords.length)];
    }
}
