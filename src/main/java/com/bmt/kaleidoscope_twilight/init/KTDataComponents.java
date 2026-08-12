package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.core.item.KeepingPouchItem;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class KTDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, KaleidoscopeTwilight.MODID);

    public static final Supplier<DataComponentType<KeepingPouchItem.ItemContainer>> KEEPING_POUCH_ITEMS =
            DATA_COMPONENT_TYPES.register("keeping_pouch_items",
                    () -> DataComponentType.<KeepingPouchItem.ItemContainer>builder()
                            .persistent(KeepingPouchItem.ItemContainer.CODEC)
                            .networkSynchronized(KeepingPouchItem.ItemContainer.STREAM_CODEC)
                            .build());
}