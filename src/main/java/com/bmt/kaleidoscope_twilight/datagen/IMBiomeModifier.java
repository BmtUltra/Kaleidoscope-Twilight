package com.bmt.kaleidoscope_twilight.datagen;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import twilightforest.data.tags.BiomeTagGenerator;

public class IMBiomeModifier {
    public static final ResourceKey<BiomeModifier> TWILIGHT_FERN_CROP = create("twilight_fern_crop");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        context.register(TWILIGHT_FERN_CROP,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTagGenerator.IS_TWILIGHT),
                        HolderSet.direct(placedFeatures.getOrThrow(ModPlacements.TWILIGHT_FERN_CROP)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );
    }

    private static ResourceKey<BiomeModifier> create(String id) {
        return ResourceKey.create(
                NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                KaleidoscopeTwilight.id(id)
        );
    }
}
