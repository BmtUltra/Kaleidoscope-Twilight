package com.bmt.kaleidoscope_twilight.util.tier;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import twilightforest.init.TFItems;

public class IronwoodTier implements Tier {
    @Override
    public int getUses() {
        return 500;
    }

    @Override
    public float getSpeed() {
        return 5.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 3.0F;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(TFItems.IRONWOOD_INGOT.get());
    }
}