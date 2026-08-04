package com.bmt.kaleidoscope_twilight.blockentity.trophy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class UmbralSunflowerTrophyBlockEntity extends BlockEntity {

    protected UmbralSunflowerTrophyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return super.getType();
    }
}