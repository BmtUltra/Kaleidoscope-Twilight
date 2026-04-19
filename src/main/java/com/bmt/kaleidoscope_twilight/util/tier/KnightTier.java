package com.bmt.kaleidoscope_twilight.util.tier;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
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
        return 4.0F;
    }

    @Override
    public int getLevel() {
        return 2;
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