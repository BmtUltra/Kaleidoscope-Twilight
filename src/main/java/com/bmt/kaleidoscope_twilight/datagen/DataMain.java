package com.bmt.kaleidoscope_twilight.datagen;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class DataMain {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        DatapackBuiltinEntriesProvider builtinEntriesProvider = generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
                generator.getPackOutput(),
                event.getLookupProvider(),
                new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, ModFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE, ModPlacements::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, IMBiomeModifier::bootstrap)

                ,
                Map.of(),
                Set.of(KaleidoscopeTwilight.MODID)
        ));

        if (event.includeClient()) {
            generator.addProvider(true, new ModBlockModelProvider(output, existingFileHelper));
            generator.addProvider(true, new ModBlockStateProvider(output, existingFileHelper));
        }

        if (event.includeServer()) {
            generator.addProvider(true, new ModLootTableProvider(output, lookupProvider));
            var blockTagsProvider = new ModBlockTagsProvider(output, lookupProvider, existingFileHelper);
            generator.addProvider(true, blockTagsProvider);
        }
    }
}