package com.bmt.kaleidoscope_twilight.core.blockentity;

import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import com.github.ysbbbbbb.kaleidoscopecookery.blockentity.kitchen.StockpotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class FieryStockpotBlockEntity extends StockpotBlockEntity {

    private static final double COOKING_TIME_MULTIPLIER = 0.4;
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

    public static int getCookingTime(int originalTime) {
        return (int) (originalTime * COOKING_TIME_MULTIPLIER);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("LidTicks", this.lidTicks);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.lidTicks = tag.getInt("LidTicks");
    }
}