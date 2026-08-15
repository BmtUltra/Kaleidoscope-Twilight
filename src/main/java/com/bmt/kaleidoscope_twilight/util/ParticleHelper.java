package com.bmt.kaleidoscope_twilight.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ParticleHelper {

    public static void spawnSnowParticles(LivingEntity entity, int amplifier, ParticleOptions particleType) {
        if (entity.tickCount % 3 != 0) {
            return;
        }

        Vec3 pos = entity.position();
        int particleCount = 3 + amplifier;

        for (int i = 0; i < particleCount; i++) {
            double px = pos.x + (entity.getRandom().nextDouble() - 0.5) * 0.8;
            double py = pos.y + 0.05 + entity.getRandom().nextDouble() * 0.15;
            double pz = pos.z + (entity.getRandom().nextDouble() - 0.5) * 0.8;
            double vx = (entity.getRandom().nextDouble() - 0.5) * 0.02;
            double vy = 0.02 + entity.getRandom().nextDouble() * 0.04;
            double vz = (entity.getRandom().nextDouble() - 0.5) * 0.02;

            if (entity.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(particleType, px, py, pz, 1, vx, vy, vz, 0.05);
            }
        }
    }
}