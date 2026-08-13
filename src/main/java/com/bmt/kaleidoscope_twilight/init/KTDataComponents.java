package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.common.item.KeepingPouchItem;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
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

    public static final Supplier<DataComponentType<List<MobEffectInstance>>> TEA_DATE_EFFECTS =
            DATA_COMPONENT_TYPES.register("tea_date_effects",
                    () -> DataComponentType.<List<MobEffectInstance>>builder()
                            .persistent(Codec.list(MobEffectInstance.CODEC))
                            .networkSynchronized(MobEffectInstance.STREAM_CODEC.apply(ByteBufCodecs.list()))
                            .build());
}