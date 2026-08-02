package com.bmt.kaleidoscope_twilight.mixins.accessor;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(BlockEntityType.class)
public interface BlockEntityTypeAccessor {
    @Accessor("validBlocks")
    Set<Block> kaleidoscope_twilight$validBlocks();

    @Mutable
    @Accessor("validBlocks")
    void kaleidoscope_twilight$setValidBlocks(Set<Block> validBlocks);
}