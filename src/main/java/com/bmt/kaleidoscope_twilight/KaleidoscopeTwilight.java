package com.bmt.kaleidoscope_twilight;

import com.bmt.kaleidoscope_twilight.entity.UmbralSunflower;
import com.bmt.kaleidoscope_twilight.init.*;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(KaleidoscopeTwilight.MODID)
public class KaleidoscopeTwilight {
    public static final String MODID = "kaleidoscope_twilight";

    public KaleidoscopeTwilight(IEventBus modEventBus) {
        KTItems.register(modEventBus);
        KTCreativeTabs.register(modEventBus);
        KTEffects.register(modEventBus);
        KTBlocks.register(modEventBus);
        KTBlockEntities.register(modEventBus);
        KTEntities.ENTITIES.register(modEventBus);
        KTTeacups.init();
        KTFoodBites.init();
        KTDataComponents.DATA_COMPONENT_TYPES.register(modEventBus);
        modEventBus.addListener(KaleidoscopeTwilight::registerEntityAttributes);
        if (ModList.get().isLoaded("kaleidoscope_tavern")) {
            KTFluids.FLUID_TYPES.register(modEventBus);
            KTFluids.FLUIDS.register(modEventBus);
            KTBrews.BLOCKS.register(modEventBus);
            KTBrewItems.ITEMS.register(modEventBus);
        }
//        if (ModList.get().isLoaded("kaleidoscope_doll")) {
//            KaleidoscopeDollIntegrationImpl.register(modEventBus);
//        }
    }
    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name);
    }
    public static ResourceLocation fromNamespaceAndPath(String path, String name) {
        return ResourceLocation.tryBuild(path, name);
    }
    private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(KTEntities.UMBRAL_SUNFLOWER.get(), UmbralSunflower.createAttributes().build());
    }
}