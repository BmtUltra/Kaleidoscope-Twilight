package com.bmt.kaleidoscope_twilight.datagen.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTBrews;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public final class BrewsHandler {

    private static final ResourceLocation TAVERN_DRINK_BE = ResourceLocation.tryBuild("kaleidoscope_tavern", "drink");

    @SubscribeEvent
    public static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        if (!ModList.get().isLoaded("kaleidoscope_tavern")) {
            return;
        }
        BuiltInRegistries.BLOCK_ENTITY_TYPE.getOptional(TAVERN_DRINK_BE).ifPresent(drinkType -> {
            event.modify(drinkType, KTBrews.CAVE_FIREFLY_BREW.get());
            event.modify(drinkType, KTBrews.TWILIGHT_DEW.get());
            event.modify(drinkType, KTBrews.WITCHCRAFT_SECRET_BREW.get());
            event.modify(drinkType, KTBrews.SNAKE_SKIN_LIQUOR.get());
            event.modify(drinkType, KTBrews.ICE_CRYSTAL_FROST_DEW.get());
            event.modify(drinkType, KTBrews.MAGIC_BEAN_BREW.get());
            event.modify(drinkType, KTBrews.EMBER_EYE.get());
            event.modify(drinkType, KTBrews.DEER_SONG.get());
            event.modify(drinkType, KTBrews.THORN_HEART.get());
            event.modify(drinkType, KTBrews.DRUID_SECRET_BREW.get());
            event.modify(drinkType, KTBrews.GLOWING_NIGHT_BIRD_SONG.get());
            event.modify(drinkType, KTBrews.GLACIER_FROST_DEW.get());
            event.modify(drinkType, KTBrews.GIANT_SPIRIT.get());
            event.modify(drinkType, KTBrews.NATURE_SPIRIT.get());
        });
    }
}