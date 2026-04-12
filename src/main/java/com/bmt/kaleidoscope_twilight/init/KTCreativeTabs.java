package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KTCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KaleidoscopeTwilight.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> KALEIDOSCOPE_TWILIGHT =
            CREATIVE_MODE_TABS.register("kaleidoscope_twilight",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.kaleidoscope_twilight"))
                    .icon(() -> new ItemStack(KTItems.TWILIGHT_BERRY_SALAD_ITEM.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(KTItems.TWILIGHT_BERRY_SALAD_ITEM.get());
                        output.accept(KTItems.TWILIGHT_CATERPILLAR_ITEM.get());
                        output.accept(KTItems.DEER_STEW_POTATO_ITEM.get());
                        output.accept(KTItems.DEER_STEW_POTATO_RICE_BOWL_ITEM.get());
                        output.accept(KTItems.MINOTAUR_ROLL_ITEM.get());
                        output.accept(KTItems.TWILIGHT_WELLINGTON_STEAK_ITEM.get());
                        output.accept(KTItems.SALT_BAKED_NAGA_ITEM.get());
                        output.accept(KTItems.TWILIGHT_GHOST_PASTA_ITEM.get());
                        output.accept(KTItems.HYDRA_BOLOGNESE_ITEM.get());
                        output.accept(KTItems.GLOW_MUSHROOM_POT_SOUP_ITEM.get());
                        output.accept(KTItems.SAUCED_SNAKE_FEAST_ITEM.get());
                        output.accept(KTItems.AURORA_ICE_CREAM_ITEM.get());
                        output.accept(KTItems.MAGIC_BEAN_SOUP_ITEM.get());
                        output.accept(KTItems.GLACIER_CAKE_ITEM.get());
                        output.accept(KTItems.LOTUS_CHICKEN_ITEM.get());
                        output.accept(KTItems.RAW_NAGA_MEAT_ITEM.get());
                        output.accept(KTItems.COOKED_NAGA_MEAT_ITEM.get());
                        output.accept(KTItems.TOMAHAWK_STEAK_ITEM.get());
                        output.accept(KTItems.RAW_YETI_MEAT_ITEM.get());
                        output.accept(KTItems.COOKED_YETI_MEAT_ITEM.get());
                        output.accept(KTItems.STUFFED_PUZZLE_CROISSANT_ITEM.get());
                        output.accept(KTItems.WITCHCRAFT_BONE_ITEM.get());
                        output.accept(KTItems.WITCHCRAFT_BONE_SOUP_ITEM.get());
                        output.accept(KTItems.WITCHCRAFT_CROISSANT_ITEM.get());
                        output.accept(KTItems.EVIL_SOUL_ITEM.get());
                        output.accept(KTItems.EVIL_SOUL_NOODLE_SOUP_ITEM.get());
                        output.accept(KTItems.FROZEN_BUN_ITEM.get());
                        output.accept(KTItems.TORCHBERRY_COOKIE_ITEM.get());
                        output.accept(KTItems.TORCHBERRY_DEER_SANDWICH_ITEM.get());
                        output.accept(KTItems.EXPERIMENT_115_SKEWER_ITEM.get());
                        output.accept(KTItems.STIR_FRIED_FAT_CATERPILLAR_ITEM.get());
                        output.accept(KTItems.STIR_FRIED_FAT_CATERPILLAR_RICE_BOWL_ITEM.get());
                        output.accept(KTItems.TWILIGHT_FERN_ITEM.get());
                        output.accept(KTItems.COLD_TOSSED_FERN_ITEM.get());
                        output.accept(KTItems.FOUR_LEAF_ICE_CRYSTAL_ITEM.get());
                        output.accept(KTItems.ICE_CRYSTAL_HYDRA_STEAK_ITEM.get());
                        output.accept(KTItems.ICE_CRYSTAL_MOUSSE_ITEM.get());
                        output.accept(KTItems.BLAZING_IRON_KITCHEN_KNIFE.get());
                        output.accept(KTItems.KNIGHT_KITCHEN_KNIFE.get());
                        output.accept(KTItems.IRONWOOD_KITCHEN_KNIFE.get());
                        output.accept(KTItems.STEELLEAF_KITCHEN_KNIFE.get());
//                        output.accept(KTItems.IRONWOOD_KITCHEN_KNIFE.get().getDefaultInstance(parameters.holders()));
//                        output.accept(KTItems.STEELLEAF_KITCHEN_KNIFE.get().getDefaultInstance(parameters.holders()));
                        output.accept(KTItems.TWILIGHT_STOVE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}