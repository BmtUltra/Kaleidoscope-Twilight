package com.bmt.kaleidoscope_twilight.datagen;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataMain {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

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