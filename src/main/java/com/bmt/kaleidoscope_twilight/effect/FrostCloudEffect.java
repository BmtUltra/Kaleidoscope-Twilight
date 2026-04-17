package com.bmt.kaleidoscope_twilight.effect;

import com.bmt.kaleidoscope_twilight.mixins.accessor.LivingEntityAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import twilightforest.init.TFParticleType;

import java.util.ArrayList;
import java.util.List;

public class FrostCloudEffect extends MobEffect {

    private static final int SHIELD_COUNT = 7;
    private final List<ShieldPosition> shieldPositions = new ArrayList<>();

    public FrostCloudEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof LivingEntityAccessor accessor) {
            if (accessor.isJumping()) {
                Vec3 motion = entity.getDeltaMovement();
                double motionY = 0.2 + (amplifier * 0.05);

                double motionX = Mth.clamp(motion.x, -0.15, 0.15);
                double motionZ = Mth.clamp(motion.z, -0.15, 0.15);

                entity.setDeltaMovement(motionX, motionY, motionZ);
                entity.fallDistance = 0.0F;

                updateShieldPositions(entity, amplifier);

                spawnSnowParticles(entity, amplifier);
            } else {
                shieldPositions.clear();
            }
        }
        return true;
    }

    private void updateShieldPositions(LivingEntity entity, int amplifier) {
        if (shieldPositions.size() < SHIELD_COUNT) {
            for (int i = 0; i < SHIELD_COUNT; i++) {
                shieldPositions.add(new ShieldPosition());
            }
        }

        for (int i = 0; i < SHIELD_COUNT; i++) {
            ShieldPosition pos = shieldPositions.get(i);

            float angle = getShieldAngle(i, entity.tickCount);
            float distance = 1.0F + (amplifier * 0.2F);

            Vec3 position = getShieldPosition(entity, angle, distance);

            pos.x = position.x();
            pos.y = position.y();
            pos.z = position.z();
            pos.angle = angle;
        }
    }

    private float getShieldAngle(int index, int tickCount) {
        return (360.0F / SHIELD_COUNT) * index + (tickCount * 3.0F);
    }

    private Vec3 getShieldPosition(LivingEntity entity, float angle, float distance) {
        double dx = Math.cos(angle * Math.PI / 180.0D) * distance;
        double dz = Math.sin(angle * Math.PI / 180.0D) * distance;

        return new Vec3(
                entity.getX() + dx,
                entity.getY() + entity.getBbHeight() * 0.5,
                entity.getZ() + dz
        );
    }

    private void spawnSnowParticles(LivingEntity entity, int amplifier) {
        if (entity.tickCount % 5 == 0) {
            Vec3 pos = entity.position();
            int particleCount = 2 + amplifier;

            for (int i = 0; i < particleCount; i++) {
                float px = (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.3F;
                float py = entity.getEyeHeight() + (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.5F;
                float pz = (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.3F;

                if (entity.level().isClientSide()) {
                    entity.level().addParticle(
                            TFParticleType.SNOW.get(),
                            pos.x() + px,
                            pos.y() + py,
                            pos.z() + pz,
                            0.0D, 0.0D, 0.0D
                    );
                }
                else if (entity.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            TFParticleType.SNOW.get(),
                            pos.x() + px,
                            pos.y() + py,
                            pos.z() + pz,
                            1,
                            0.0D, 0.0D, 0.0D,
                            0.0
                    );
                }
            }
        }

        for (ShieldPosition shieldPos : shieldPositions) {
            if (entity.tickCount % 3 == 0) {
                if (entity.level().isClientSide()) {
                    for (int i = 0; i < 2; i++) {
                        float px = (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F;
                        float py = (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F;
                        float pz = (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F;

                        entity.level().addParticle(
                                TFParticleType.SNOW.get(),
                                shieldPos.x + px,
                                shieldPos.y + py,
                                shieldPos.z + pz,
                                0.0D, 0.0D, 0.0D
                        );
                    }
                }
                else if (entity.level() instanceof ServerLevel serverLevel) {
                    for (int i = 0; i < 2; i++) {
                        float px = (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F;
                        float py = (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F;
                        float pz = (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F;

                        serverLevel.sendParticles(
                                TFParticleType.SNOW.get(),
                                shieldPos.x + px,
                                shieldPos.y + py,
                                shieldPos.z + pz,
                                1,
                                0.0D, 0.0D, 0.0D,
                                0.0
                        );
                    }
                }
            }
        }
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        super.onEffectStarted(entity, amplifier);
        updateShieldPositions(entity, amplifier);
    }

    @Override
    public void onMobRemoved(LivingEntity entity, int amplifier, net.minecraft.world.entity.Entity.RemovalReason reason) {
        super.onMobRemoved(entity, amplifier, reason);
        shieldPositions.clear();
    }

    private static class ShieldPosition {
        public double x;
        public double y;
        public double z;
        public float angle;
    }
}