package com.bmt.kaleidoscope_twilight.datagen.subLootTables;

import com.bmt.kaleidoscope_twilight.init.KTBlocks;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), Map.of());
    }

    @Override
    protected void generate() {
        dropSelf(KTBlocks.TWILIGHT_STOVE.get());

        add(KTBlocks.TWILIGHT_FERN_CROP.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(KTItems.TWILIGHT_FERN_ITEM.get()))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(KTBlocks.TWILIGHT_FERN_CROP.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)))
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(KTItems.TWILIGHT_FERN_ITEM.get()))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(KTBlocks.TWILIGHT_FERN_CROP.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)))
                )
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(KTItems.TWILIGHT_CATERPILLAR_ITEM.get()))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(KTBlocks.TWILIGHT_FERN_CROP.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f))
                )
        );
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return KTBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}