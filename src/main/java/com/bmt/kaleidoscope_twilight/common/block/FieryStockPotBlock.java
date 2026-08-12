package com.bmt.kaleidoscope_twilight.common.block;

import com.bmt.kaleidoscope_twilight.common.blockentity.FieryStockpotBlockEntity;
import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StockpotBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FieryStockPotBlock extends StockpotBlock {
    public static final MapCodec<FieryStockPotBlock> CODEC = simpleCodec(p -> new FieryStockPotBlock());

    public FieryStockPotBlock() {
        super();
    }

    @Override
    protected @NotNull MapCodec<? extends StockpotBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new FieryStockpotBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return createTickerHelper(blockEntityType, KTBlockEntities.FIERY_STOCKPOT_BLOCK_ENTITY.get(),
                    (lvl, blockPos, blockState, pot) -> pot.clientTick());
        }
        return createTickerHelper(blockEntityType, KTBlockEntities.FIERY_STOCKPOT_BLOCK_ENTITY.get(),
                (lvl, blockPos, blockState, pot) -> pot.tick(lvl));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.kaleidoscope_twilight.fiery_stockpot").withStyle(ChatFormatting.GRAY));
    }
}