package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;

import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.TeacupRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;

public class KTCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KaleidoscopeTwilight.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> KALEIDOSCOPE_TWILIGHT =
            CREATIVE_MODE_TABS.register("kaleidoscope_twilight",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.kaleidoscope_twilight"))
                    .icon(() -> new ItemStack(KTItems.TWILIGHT_FERN_ITEM.get()))
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
                        output.accept(KTItems.SAUCED_SNAKE_FEAST_ITEM.get());
                        output.accept(KTItems.AURORA_ICE_CREAM_ITEM.get());
                        output.accept(KTItems.MAGIC_BEAN_SOUP_ITEM.get());
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
                        output.accept(KTItems.TWILIGHT_CHICKEN_MUSHROOM_STEW_ITEM.get());
                        output.accept(KTItems.MAZE_STUFFED_PANCAKE_ITEM.get());
                        FoodBiteRegistry.FOOD_DATA_MAP.forEach((resourceLocation, foodData) -> {
                            if (resourceLocation.getNamespace().equals(KaleidoscopeTwilight.MODID)) {
                                Item item = BuiltInRegistries.ITEM.get(resourceLocation);
                                output.accept(Objects.requireNonNull(item));
                            }
                        });
                        output.accept(KTItems.PHANTOM_FERN_STEW_ITEM.get());
                        output.accept(KTItems.RAW_TWILIGHT_GHOST_TENTACLE_ITEM.get());
                        output.accept(KTItems.COOKED_TWILIGHT_GHOST_TENTACLE_ITEM.get());
                        output.accept(KTItems.MINOTAUR_SALAD_PASTA_ITEM.get());
                        output.accept(KTItems.TWILIGHT_GHOST_SASHIMI_ITEM.get());
                        output.accept(KTItems.RAINBOW_CANDY_ITEM.get());
                        output.accept(KTItems.RAINBOW_BUN_ITEM.get());
                        output.accept(KTItems.MAGIC_CRISPY_CORNER_ITEM.get());
                        output.accept(KTItems.POCHI_PUDDING_ITEM.get());
                        output.accept(KTItems.KITA_STUFFED_CRISP_ITEM.get());
                        output.accept(KTItems.LIANGSHAN_ICE_CONE_ITEM.get());
                        output.accept(KTItems.NAGA_GREEN_TONGUE_ITEM.get());
                        output.accept(KTItems.TEA_DATE_ITEM.get());
                        output.accept(TeacupRegistry.getItem(KTTeacups.NAGA_TEA));
                        output.accept(TeacupRegistry.getItem(KTTeacups.WITCHCRAFT_TEA));
                        output.accept(TeacupRegistry.getItem(KTTeacups.MINOTAUR_MUSHROOM_TEA));
                        output.accept(TeacupRegistry.getItem(KTTeacups.FIRE_TEA));
                        output.accept(TeacupRegistry.getItem(KTTeacups.PHANTOM_TEA));
                        output.accept(TeacupRegistry.getItem(KTTeacups.HOT_TEARS_TEA));
                        output.accept(TeacupRegistry.getItem(KTTeacups.ICE_CRYSTAL_TEA));

                        if (ModList.get().isLoaded("kaleidoscope_tavern")) {
                            output.accept(KTBrewItems.CAVE_FIREFLY_BREW_ITEM.get());
                            output.accept(KTBrewItems.TWILIGHT_DEW_ITEM.get());
                            output.accept(KTBrewItems.WITCHCRAFT_SECRET_BREW_ITEM.get());
                            output.accept(KTBrewItems.SNAKE_SKIN_LIQUOR_ITEM.get());
                            output.accept(KTBrewItems.ICE_CRYSTAL_FROST_DEW_ITEM.get());
                            output.accept(KTBrewItems.MAGIC_BEAN_BREW_ITEM.get());
                            output.accept(KTBrewItems.EMBER_EYE_ITEM.get());
                            output.accept(KTBrewItems.DEER_SONG_ITEM.get());
                            output.accept(KTBrewItems.THORN_HEART_ITEM.get());
                            output.accept(KTBrewItems.DRUID_SECRET_BREW_ITEM.get());
                            output.accept(KTBrewItems.GLOWING_NIGHT_BIRD_SONG_ITEM.get());
                            output.accept(KTBrewItems.GLACIER_FROST_DEW_ITEM.get());
                            output.accept(KTBrewItems.GIANT_SPIRIT_ITEM.get());
                            output.accept(KTBrewItems.NATURE_SPIRIT_ITEM.get());
                        }

                        output.accept(KTItems.BLAZING_IRON_KITCHEN_KNIFE.get());
                        output.accept(KTItems.KNIGHT_KITCHEN_KNIFE.get());
                        output.accept(KTItems.IRONWOOD_KITCHEN_KNIFE.get());
                        output.accept(KTItems.STEELLEAF_KITCHEN_KNIFE.get());
//                        output.accept(KTItems.IRONWOOD_KITCHEN_KNIFE.get().getDefaultInstance(parameters.holders()));
//                        output.accept(KTItems.STEELLEAF_KITCHEN_KNIFE.get().getDefaultInstance(parameters.holders()));
                        if (ModList.get().isLoaded("kaleidoscope_tavern")) {
                            output.accept(KTBrewItems.TORCHBERRY_BUCKET.get());
                        }
                        output.accept(KTItems.TWILIGHT_STOVE.get());
                        output.accept(KTItems.KEEPING_POUCH_ITEM.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}