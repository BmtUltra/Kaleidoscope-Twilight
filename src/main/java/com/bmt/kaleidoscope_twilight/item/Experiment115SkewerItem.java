package com.bmt.kaleidoscope_twilight.item;

import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Experiment115SkewerItem extends FoodWithEffectsItem {
    private static final int COOLDOWN_TICKS = 40;

    public Experiment115SkewerItem(FoodProperties food) {
        super(food);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, @NotNull Level world, @NotNull LivingEntity entity) {
        if (stack.has(DataComponents.FOOD)) {
            entity.eat(world, stack.copy());
            addCooldown(entity);
        }
        return stack;
    }

    private void addCooldown(LivingEntity entity) {
        if (entity instanceof Player player) {
            player.getCooldowns().addCooldown(this, Experiment115SkewerItem.COOLDOWN_TICKS);
        }
    }
}