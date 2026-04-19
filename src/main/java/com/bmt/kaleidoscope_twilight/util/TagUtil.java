package com.bmt.kaleidoscope_twilight.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("all")
public class TagUtil {
    public static class Items {
    }

    public static class Blocks {
        public static final TagKey<Block> CHEST_LIKE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("kaleidoscope_twilight", "chest_like"));
        public static final TagKey<Block> FERN_PLANTABLE = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("kaleidoscope_twilight", "fern_plantable"));
    }
}