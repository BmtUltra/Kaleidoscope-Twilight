package com.bmt.kaleidoscope_twilight.common.effect;

import com.bmt.kaleidoscope_twilight.init.KTEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import twilightforest.init.TFParticleType;

public class FrostCloudEffect extends MobEffect {

    public FrostCloudEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            boolean changed = false;
            if (!player.getAbilities().mayfly) {
                player.getAbilities().mayfly = true;
                changed = true;
            }
            if (!player.getAbilities().flying) {
                player.getAbilities().flying = true;
                changed = true;
            }
            if (changed) {
                player.onUpdateAbilities();
            }
            player.fallDistance = 0.0F;
        } else {
            entity.setNoGravity(true);
            entity.fallDistance = 0.0F;
        }
        spawnSnowParticles(entity, amplifier);
        return true;
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        super.onEffectStarted(entity, amplifier);
        if (entity instanceof Player player) {
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        } else {
            entity.setNoGravity(true);
        }
    }

    @Override
    public void onMobRemoved(LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        super.onMobRemoved(entity, amplifier, reason);
        if (entity instanceof Player player) {
            if (!player.hasEffect(KTEffects.FROST_CLOUD)) {
                boolean changed = false;
                if (player.getAbilities().flying) {
                    player.getAbilities().flying = false;
                    changed = true;
                }
                if (player.getAbilities().mayfly != player.getAbilities().instabuild) {
                    player.getAbilities().mayfly = player.getAbilities().instabuild;
                    changed = true;
                }
                if (changed) {
                    player.onUpdateAbilities();
                }
                player.fallDistance = 0.0F;
            }
        } else {
            entity.setNoGravity(false);
        }
    }

    private void spawnSnowParticles(LivingEntity entity, int amplifier) {
        if (entity.tickCount % 3 == 0) {
            Vec3 pos = entity.position();
            int particleCount = 3 + amplifier;

            for (int i = 0; i < particleCount; i++) {
                double px = (entity.getRandom().nextDouble() - 0.5) * 0.8;
                double py = 0.05 + entity.getRandom().nextDouble() * 0.15;
                double pz = (entity.getRandom().nextDouble() - 0.5) * 0.8;

                double vx = (entity.getRandom().nextDouble() - 0.5) * 0.02;
                double vy = 0.02 + entity.getRandom().nextDouble() * 0.04;
                double vz = (entity.getRandom().nextDouble() - 0.5) * 0.02;

                if (entity.level().isClientSide()) {
                    entity.level().addParticle(
                            TFParticleType.SNOW.get(),
                            pos.x + px,
                            pos.y + py,
                            pos.z + pz,
                            vx, vy, vz
                    );
                } else if (entity.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            TFParticleType.SNOW.get(),
                            pos.x + px,
                            pos.y + py,
                            pos.z + pz,
                            1,
                            vx, vy, vz,
                            0.05
                    );
                }
            }
        }
    }
}