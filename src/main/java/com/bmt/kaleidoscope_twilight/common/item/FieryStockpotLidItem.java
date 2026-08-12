package com.bmt.kaleidoscope_twilight.common.item;

import com.github.ysbbbbbb.kaleidoscopecookery.item.StockpotLidItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class FieryStockpotLidItem extends StockpotLidItem {
    private static final int NORMAL = 0;
    private static final int USING = 1;

    public FieryStockpotLidItem() {
        super();
    }

    @OnlyIn(Dist.CLIENT)
    public static float getTexture(ItemStack stack, @Nullable LivingEntity entity) {
        if (entity != null && entity.isUsingItem() && entity.getUseItem() == stack) {
            return USING;
        }
        return NORMAL;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.kaleidoscope_twilight.fiery_stockpot_lid").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
            stack.setDamageValue(0);
    }
}