package com.bmt.kaleidoscope_twilight.datagen.subLootTables;

import com.bmt.kaleidoscope_twilight.init.KTItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import twilightforest.init.TFEntities;
import twilightforest.init.TFItems;
import twilightforest.loot.MultiplayerBasedAdditionLootFunction;
import twilightforest.loot.MultiplayerBasedNumberProvider;

import java.util.stream.Stream;

public class ModEntityLootTables extends EntityLootSubProvider {
    public ModEntityLootTables(HolderLookup.Provider provider) {
        super(FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    public void generate() {
        this.add(TFEntities.NAGA.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().name("naga_scales").setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TFItems.NAGA_SCALE.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(6.0F, 11.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
                                .apply(MultiplayerBasedAdditionLootFunction.addForAllParticipatingPlayers(UniformGenerator.between(2.0F, 4.0F)))))
                .withPool(LootPool.lootPool().name("naga_trophy").setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TFItems.NAGA_TROPHY.get())))
                .withPool(LootPool.lootPool().name("naga_meat").setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(KTItems.RAW_NAGA_MEAT_ITEM.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))));
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return Stream.of(TFEntities.NAGA.get());
    }
}