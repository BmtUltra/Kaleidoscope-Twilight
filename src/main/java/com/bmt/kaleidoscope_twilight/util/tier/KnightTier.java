package com.bmt.kaleidoscope_twilight.util.tier;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import twilightforest.init.TFItems;

public class KnightTier implements Tier {
    @Override
    public int getUses() {
        return 750;
    }

    @Override
    public float getSpeed() {
        return 6.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 6.0F;
    }

    @Override
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return BlockTags.INCORRECT_FOR_IRON_TOOL;
    }

    @Override
    public int getEnchantmentValue() {
        return 12;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(TFItems.KNIGHTMETAL_INGOT.get());
    }
}