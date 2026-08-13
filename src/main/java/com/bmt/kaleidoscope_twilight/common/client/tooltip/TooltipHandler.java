package com.bmt.kaleidoscope_twilight.common.client.tooltip;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import com.bmt.kaleidoscope_twilight.util.TagUtils;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID, value = Dist.CLIENT)
public class TooltipHandler {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        FoodProperties foodProperties = stack.getFoodProperties(event.getEntity());
        if (foodProperties == null) return;
        if (!stack.is(TagUtils.Items.SHOW_FOOD_EFFECTS)) return;
        List<MobEffectInstance> effects = foodProperties.effects().stream()
                .filter(e -> e.probability() >= 1.0F)
                .map(FoodProperties.PossibleEffect::effect)
                .toList();
        if (effects.isEmpty()) return;
        event.getToolTip().add(CommonComponents.space());
        PotionContents.addPotionTooltip(effects, event.getToolTip()::add, 1.0F, 20.0F);
    }

    @SubscribeEvent
    public static void onTeaDateTooltip(ItemTooltipEvent event) {
        if (!event.getItemStack().is(KTItems.TEA_DATE_ITEM.get())) {
            return;
        }
        List<net.minecraft.network.chat.Component> tooltip = event.getToolTip();
        if (tooltip.size() > 2) {
            tooltip.remove(3);
        }
    }
}