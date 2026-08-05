package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class KTMapDecorations {
    public static final DeferredRegister<MapDecorationType> MAP_DECORATIONS = DeferredRegister.create(BuiltInRegistries.MAP_DECORATION_TYPE, KaleidoscopeTwilight.MODID);

    public static final Supplier<MapDecorationType> SUNFLOWER_BASE =
            MAP_DECORATIONS.register("sunflower_base",
                    () -> new MapDecorationType(KaleidoscopeTwilight.id("sunflower_base"), false, -1, false, false));
}
