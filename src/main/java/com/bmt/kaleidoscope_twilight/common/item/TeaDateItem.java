package com.bmt.kaleidoscope_twilight.common.item;

import com.bmt.kaleidoscope_twilight.init.KTDataComponents;
import com.github.ysbbbbbb.kaleidoscopecookery.item.FoodWithEffectsItem;
import com.google.common.collect.Lists;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

import java.util.Collection;
import java.util.List;

public class TeaDateItem extends FoodWithEffectsItem {
    public TeaDateItem(FoodProperties properties) {
        super(properties);
    }

    public static List<MobEffectInstance> getStoredEffects(ItemStack stack) {
        List<MobEffectInstance> effects = stack.get(KTDataComponents.TEA_DATE_EFFECTS);
        return effects == null ? List.of() : effects;
    }

    public static void absorbEffects(ItemStack stack, Collection<MobEffectInstance> added) {
        List<MobEffectInstance> merged = Lists.newArrayList(getStoredEffects(stack));
        for (MobEffectInstance instance : added) {
            MobEffectInstance copy = new MobEffectInstance(instance);
            MobEffectInstance existing = merged.stream()
                    .filter(effect -> effect.getEffect().equals(copy.getEffect())
                            && effect.getAmplifier() == copy.getAmplifier())
                    .findFirst()
                    .orElse(null);
            if (existing != null) {
                int combined = (int) Math.min(Integer.MAX_VALUE,
                        (long) existing.getDuration() + copy.getDuration());
                merged.set(merged.indexOf(existing), new MobEffectInstance(
                        copy.getEffect(), combined, copy.getAmplifier(),
                        copy.isAmbient(), copy.isVisible(), copy.showIcon()));
            } else {
                merged.add(copy);
            }
        }
        stack.set(KTDataComponents.TEA_DATE_EFFECTS, merged.isEmpty() ? null : merged);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide) {
            for (MobEffectInstance instance : getStoredEffects(stack)) {
                entity.addEffect(new MobEffectInstance(instance));
            }
        }
        return super.finishUsingItem(stack, level, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        List<MobEffectInstance> stored = getStoredEffects(stack);
        if (!stored.isEmpty()) {
            tooltip.add(Component.translatable("tooltip.kaleidoscope_twilight.tea_date.tea_effects")
                    .withStyle(ChatFormatting.GRAY));
            PotionContents.addPotionTooltip(stored, tooltip::add, 1.0F, context.tickRate());
        }
    }
}
