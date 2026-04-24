package com.bmt.kaleidoscope_twilight.datagen;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> TWILIGHT_FERN_CROP = createKey("twilight_fern_crop");


    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, KaleidoscopeTwilight.id(name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {


        FeatureUtils.register(
                context,
                TWILIGHT_FERN_CROP,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        96,
                        7,
                        3,
                        PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                BlockStateProvider.simple(KTBlocks.TWILIGHT_FERN_CROP.get().defaultBlockState().setValue(CropBlock.AGE, 7))
                        ), BlockPredicate.allOf(
                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                BlockPredicate.wouldSurvive(KTBlocks.TWILIGHT_FERN_CROP.get().defaultBlockState(), BlockPos.ZERO)
                        ))
                ));
    }
}
