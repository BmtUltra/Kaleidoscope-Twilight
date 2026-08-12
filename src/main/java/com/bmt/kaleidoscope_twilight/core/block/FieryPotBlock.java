package com.bmt.kaleidoscope_twilight.core.block;

import com.bmt.kaleidoscope_twilight.core.blockentity.FieryPotBlockEntity;
import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.PotBlock;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FieryPotBlock extends PotBlock implements EntityBlock {
    public static final MapCodec<FieryPotBlock> CODEC = simpleCodec(p -> new FieryPotBlock());

    public FieryPotBlock() {
        super();
    }

    @Override
    protected @NotNull MapCodec<? extends PotBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new FieryPotBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return null;
        }
        if (!state.getValue(HAS_OIL)) {
            return null;
        }
        return createTickerHelper(blockEntityType, KTBlockEntities.FIERY_POT_BLOCK_ENTITY.get(),
                (levelIn, pos, stateIn, pot) -> pot.tick(levelIn));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, List<Component> tooltip, @NotNull TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.kaleidoscope_twilight.fiery_pot").withStyle(ChatFormatting.GRAY));
    }
}
