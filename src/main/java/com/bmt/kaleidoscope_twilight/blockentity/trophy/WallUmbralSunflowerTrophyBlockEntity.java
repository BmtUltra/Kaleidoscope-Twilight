package com.bmt.kaleidoscope_twilight.blockentity.trophy;

import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class WallUmbralSunflowerTrophyBlockEntity extends UmbralSunflowerTrophyBlockEntity {

    public WallUmbralSunflowerTrophyBlockEntity(BlockPos pos, BlockState state) {
        super(KTBlockEntities.WALL_UMBRAL_SUNFLOWER_TROPHY.get(), pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return KTBlockEntities.WALL_UMBRAL_SUNFLOWER_TROPHY.get();
    }
}