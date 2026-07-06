package com.bmt.kaleidoscope_twilight.datapack;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
        Path resourcePath = ModList.get().getModFileById(KaleidoscopeTwilight.MODID).getFile().findResource("packs/" + "kaleidoscope_twilight");
        Pack pack = Pack.readMetaAndCreate(
                KaleidoscopeTwilight.MODID + ":" + "kaleidoscope_twilight",
                Component.literal("Kaleidoscope Twilight - " + "kaleidoscope_twilight".toUpperCase()),
                true,
                (path) -> new PathPackResources(path, resourcePath, false),
                PackType.SERVER_DATA,
                Pack.Position.TOP,
                PackSource.WORLD
        );
        if (pack != null) {
            event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
        }
    }
}