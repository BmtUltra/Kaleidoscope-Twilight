package com.bmt.kaleidoscope_twilight.blockentity;

import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.StockpotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FieryStockpotBlockEntity extends StockpotBlockEntity {

    private static final int COOKING_TIME = 60;
    private int lidTicks = 0;

    public FieryStockpotBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return KTBlockEntities.FIERY_STOCKPOT_BLOCK_ENTITY.get();
    }

    public void resetLidTicks() {
        this.lidTicks = 0;
    }

    public static int getCookingTime() {
        return COOKING_TIME;
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("LidTicks", this.lidTicks);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.lidTicks = tag.getInt("LidTicks");
    }
}