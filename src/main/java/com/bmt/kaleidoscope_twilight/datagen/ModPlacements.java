package com.bmt.kaleidoscope_twilight.datagen;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModPlacements {

    public static final ResourceKey<PlacedFeature> TWILIGHT_FERN_CROP = createKey("twilight_fern_crop");

    public static ResourceKey<PlacedFeature> createKey(String key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, KaleidoscopeTwilight.id(key));
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);


        PlacementUtils.register(
                context,
                TWILIGHT_FERN_CROP,
                holdergetter.getOrThrow(ModFeatures.TWILIGHT_FERN_CROP),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(50),
                BiomeFilter.biome()
        );
    }
}
