package com.bmt.kaleidoscope_twilight;

import com.bmt.kaleidoscope_twilight.init.*;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;

@Mod(KaleidoscopeTwilight.MODID)
public class KaleidoscopeTwilight {
    public static final String MODID = "kaleidoscope_twilight";

    public KaleidoscopeTwilight(IEventBus modEventBus) {
        KTItems.register(modEventBus);
        KTCreativeTabs.register(modEventBus);
        KTEffects.register(modEventBus);
        KTBlocks.register(modEventBus);
        if (ModList.get().isLoaded("kaleidoscope_tavern")) {
            KTFluids.FLUID_TYPES.register(modEventBus);
            KTFluids.FLUIDS.register(modEventBus);
            KTBrews.BLOCKS.register(modEventBus);
            KTBrewItems.ITEMS.register(modEventBus);
        }
    }
    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name);
    }
}