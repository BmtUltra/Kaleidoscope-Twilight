package com.bmt.kaleidoscope_twilight.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagUtils {
    public static class Items {
        public static final TagKey<Item> SHOW_FOOD_EFFECTS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("kaleidoscope_twilight", "show_food_effects"));
    }

    public static class Blocks {
        public static final TagKey<Block> FERN_PLANTABLE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("kaleidoscope_twilight", "fern_plantable"));
    }
}