package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class KTFoodBites {
    public static ResourceLocation TWILIGHT_WELLINGTON_STEAK;
    public static ResourceLocation DEER_STEW_POTATO;
    public static ResourceLocation SALT_BAKED_NAGA;
    public static ResourceLocation GLACIER_CAKE;
    public static ResourceLocation WITCHCRAFT_CAKE;
    public static ResourceLocation GLOW_MUSHROOM_POT_SOUP;

    public static void init() {
        FoodBiteRegistry registry = new FoodBiteRegistry();
        TWILIGHT_WELLINGTON_STEAK = registry.registerFoodData(KaleidoscopeTwilight.id("twilight_wellington_steak"),
                FoodBiteRegistry.FoodData.create(4, KTFoods.TWILIGHT_WELLINGTON_STEAK_BLOCK, KTFoods.TWILIGHT_WELLINGTON_STEAK_ITEM)
        );

        DEER_STEW_POTATO = registry.registerFoodData(KaleidoscopeTwilight.id("deer_stew_potato"),
                FoodBiteRegistry.FoodData.create(3, KTFoods.DEER_STEW_POTATO_BLOCK, KTFoods.DEER_STEW_POTATO_ITEM)
        );

        SALT_BAKED_NAGA = registry.registerFoodData(KaleidoscopeTwilight.id("salt_baked_naga"),
                FoodBiteRegistry.FoodData.create(3, KTFoods.SALT_BAKED_NAGA_BLOCK, KTFoods.SALT_BAKED_NAGA_ITEM)
        );

        GLACIER_CAKE = registry.registerFoodData(KaleidoscopeTwilight.id("glacier_cake"),
                FoodBiteRegistry.FoodData.create(6, KTFoods.GLACIER_CAKE_BLOCK, KTFoods.GLACIER_CAKE_ITEM)
                        .setAABB(Block.box(1, 0, 1, 15, 8, 15))
        );

        WITCHCRAFT_CAKE = registry.registerFoodData(KaleidoscopeTwilight.id("witchcraft_cake"),
                FoodBiteRegistry.FoodData.create(6, KTFoods.WITCHCRAFT_CAKE_BLOCK, KTFoods.WITCHCRAFT_CAKE_ITEM)
                        .setAABB(Block.box(1, 0, 1, 15, 8, 15))
        );

        GLOW_MUSHROOM_POT_SOUP = registry.registerFoodData(KaleidoscopeTwilight.id("glow_mushroom_pot_soup"),
                FoodBiteRegistry.FoodData.create(2, KTFoods.GLOW_MUSHROOM_POT_SOUP_BLOCK, KTFoods.GLOW_MUSHROOM_POT_SOUP_ITEM)
                        .setLootItem(Items.FLOWER_POT)
                        .soupPotAABB()
                        .potSoupAnimateTick()
        );
    }
}