package com.bmt.kaleidoscope_twilight.common.block;

import com.bmt.kaleidoscope_twilight.common.blockentity.UmbralSunflowerSpawnerBlockEntity;
import com.mojang.serialization.MapCodec;
import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class UmbralSunflowerSpawnerBlock extends BaseEntityBlock {
    public static final MapCodec<UmbralSunflowerSpawnerBlock> CODEC = simpleCodec(UmbralSunflowerSpawnerBlock::new);

    public UmbralSunflowerSpawnerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new UmbralSunflowerSpawnerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, KTBlockEntities.UMBRAL_SUNFLOWER_SPAWNER.get(), twilightforest.block.entity.spawner.BossSpawnerBlockEntity::tick);
    }

    @Override
    public boolean canEntityDestroy(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        return false;
    }
}
