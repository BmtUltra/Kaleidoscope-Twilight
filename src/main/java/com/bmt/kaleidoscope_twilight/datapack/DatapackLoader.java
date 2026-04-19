package com.bmt.kaleidoscope_twilight.datapack;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;
import java.nio.file.Paths;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
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
        event.addRepositorySource((consumer) -> {
            String packName = "kaleidoscope_twilight";
            Component packTitle = Component.literal("Kaleidoscope Twilight - " + packName);

            Path packPath = Paths.get("data", KaleidoscopeTwilight.MODID, "packs", packName);

            Pack pack = Pack.readMetaAndCreate(
                    new ResourceLocation(KaleidoscopeTwilight.MODID, packName).toString(),
                    packTitle,
                    true,
                    (path) -> new PathPackResources(path, packPath, false),
                    PackType.SERVER_DATA,
                    Pack.Position.TOP,
                    PackSource.WORLD
            );
            if (pack != null) {
                consumer.accept(pack);
            }
        });
    }
}