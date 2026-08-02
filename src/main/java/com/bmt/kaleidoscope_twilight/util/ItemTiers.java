package com.bmt.kaleidoscope_twilight.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import twilightforest.init.TFItems;

public class ItemTiers {

    public static final Tier BLAZING_IRON = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1561, 8.0F, 7.0F, 15,
            () -> Ingredient.of(TFItems.FIERY_INGOT.get()));

    public static final Tier IRONWOOD = new SimpleTier(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 500, 5.0F, 5.0F, 10,
            () -> Ingredient.of(TFItems.IRONWOOD_INGOT.get()));

    public static final Tier KNIGHT = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 750, 6.0F, 6.0F, 12,
            () -> Ingredient.of(TFItems.KNIGHTMETAL_INGOT.get()));

    public static final Tier STEELLEAF = new SimpleTier(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 750, 4.0F, 6.0F, 8,
            () -> Ingredient.of(TFItems.STEELEAF_INGOT.get()));
}