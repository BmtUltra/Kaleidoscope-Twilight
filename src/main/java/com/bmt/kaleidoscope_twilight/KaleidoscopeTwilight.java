package com.bmt.kaleidoscope_twilight;

import com.bmt.kaleidoscope_twilight.init.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KaleidoscopeTwilight.MODID)
public class KaleidoscopeTwilight {
    public static final String MODID = "kaleidoscope_twilight";

    public KaleidoscopeTwilight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        KTItems.register(modEventBus);
        KTCreativeTabs.register(modEventBus);
        KTEffects.register(modEventBus);
        KTBlocks.register(modEventBus);
        KTTeacups.init();
        if (ModList.get().isLoaded("kaleidoscope_tavern")) {
            KTFluids.FLUID_TYPES.register(modEventBus);
            KTFluids.FLUIDS.register(modEventBus);
            KTBrews.BLOCKS.register(modEventBus);
            KTBrewItems.ITEMS.register(modEventBus);
        }
    }
    public static ResourceLocation id(String name) {
        return new ResourceLocation(MODID, name);
    }
    public static ResourceLocation fromNamespaceAndPath(String path, String name) {
        return new ResourceLocation(path, name);
    }
}