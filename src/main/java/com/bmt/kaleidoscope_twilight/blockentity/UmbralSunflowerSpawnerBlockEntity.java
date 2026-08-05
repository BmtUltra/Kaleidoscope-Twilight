package com.bmt.kaleidoscope_twilight.blockentity;

import com.bmt.kaleidoscope_twilight.entity.boss.UmbralSunflower;
import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import com.bmt.kaleidoscope_twilight.init.KTEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import twilightforest.block.entity.spawner.BossSpawnerBlockEntity;

public class UmbralSunflowerSpawnerBlockEntity extends BossSpawnerBlockEntity<UmbralSunflower> {
    public UmbralSunflowerSpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(KTBlockEntities.UMBRAL_SUNFLOWER_SPAWNER.get(), KTEntities.UMBRAL_SUNFLOWER.get(), pos, state);
    }

    @Override
    public @NotNull ParticleOptions getSpawnerParticle() {
        return ParticleTypes.FLAME;
    }
}
