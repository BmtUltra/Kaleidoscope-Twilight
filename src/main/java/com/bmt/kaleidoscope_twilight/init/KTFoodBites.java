package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.FoodBiteRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class KTFoodBites {
    public static ResourceLocation GLOW_MUSHROOM_POT_SOUP;

    public static void init() {
        FoodBiteRegistry registry = new FoodBiteRegistry();
        GLOW_MUSHROOM_POT_SOUP = registry.registerFoodData(KaleidoscopeTwilight.id("glow_mushroom_pot_soup"),
                FoodBiteRegistry.FoodData.create(2, KTFoods.GLOW_MUSHROOM_POT_SOUP_BLOCK, KTFoods.GLOW_MUSHROOM_POT_SOUP_ITEM)
                        .setLootItem(Items.FLOWER_POT)
                        .soupPotAABB()
                        .potSoupAnimateTick()
        );
    }
}