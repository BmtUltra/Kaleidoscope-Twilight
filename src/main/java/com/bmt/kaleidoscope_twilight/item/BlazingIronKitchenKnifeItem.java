package com.bmt.kaleidoscope_twilight.item;

import com.github.ysbbbbbb.kaleidoscopecookery.item.KitchenKnifeItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlazingIronKitchenKnifeItem extends KitchenKnifeItem {
    public BlazingIronKitchenKnifeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        target.setSecondsOnFire(5);
        return result;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.translatable("item.kaleidoscope_twilight.blazing_iron_kitchen_knife.tooltip")
                .withStyle(ChatFormatting.GRAY));
    }
}