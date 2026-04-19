package com.bmt.kaleidoscope_twilight.util.tier;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import twilightforest.init.TFItems;

public class BlazingIronTier implements Tier {
    @Override
    public int getUses() {
        return 1561;
    }

    @Override
    public float getSpeed() {
        return 8.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 5.0F;
    }

    @Override
    public int getLevel() {
        return 2;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(TFItems.FIERY_INGOT.get());
    }
}