package com.bmt.kaleidoscope_twilight.datagen.subLootTables;

import com.bmt.kaleidoscope_twilight.init.KTBlocks;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(KTBlocks.TWILIGHT_STOVE.get());

        add(KTBlocks.TWILIGHT_FERN_CROP.get(), createCropDrops(
                KTBlocks.TWILIGHT_FERN_CROP.get(),
                KTItems.TWILIGHT_FERN_ITEM.get(),
                KTItems.TWILIGHT_FERN_ITEM.get(),
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(KTBlocks.TWILIGHT_FERN_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7))
        ));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return KTBlocks.BLOCKS.getEntries().stream().map(holder -> (Block) holder.get())::iterator;
    }
}