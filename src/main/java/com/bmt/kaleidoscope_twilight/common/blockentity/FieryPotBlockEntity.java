package com.bmt.kaleidoscope_twilight.common.blockentity;

import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.PotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FieryPotBlockEntity extends PotBlockEntity {

    public FieryPotBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public net.minecraft.world.level.block.entity.@NotNull BlockEntityType<?> getType() {
        return KTBlockEntities.FIERY_POT_BLOCK_ENTITY.get();
    }
}