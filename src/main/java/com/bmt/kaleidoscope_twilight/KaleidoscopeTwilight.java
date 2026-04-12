package com.bmt.kaleidoscope_twilight;

import com.bmt.kaleidoscope_twilight.init.KTBlocks;
import com.bmt.kaleidoscope_twilight.init.KTCreativeTabs;
import com.bmt.kaleidoscope_twilight.init.KTEffects;
import com.bmt.kaleidoscope_twilight.init.KTItems;

import net.minecraft.resources.ResourceLocation;
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
    }
    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(MODID, name);
    }
}