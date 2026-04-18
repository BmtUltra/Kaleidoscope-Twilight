package com.bmt.kaleidoscope_twilight.datapack;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class DatapackLoader {

    @SubscribeEvent
    public static void onDatapackLoad(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            if (ModList.get().isLoaded("kaleidoscope_tavern")) {
                addDatapack(event);
            }
        }
    }

    private static void addDatapack(AddPackFindersEvent event) {
        ResourceLocation packLocation = ResourceLocation.fromNamespaceAndPath(
                KaleidoscopeTwilight.MODID,
                "packs/" + "kaleidoscope_twilight"
        );

        event.addPackFinders(
                packLocation,
                PackType.SERVER_DATA,
                Component.literal("Kaleidoscope Twilight - " + "kaleidoscope_twilight"),
                PackSource.WORLD,
                true,
                Pack.Position.TOP
        );
    }
}