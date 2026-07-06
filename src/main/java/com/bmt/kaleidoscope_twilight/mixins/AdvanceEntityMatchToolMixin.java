package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopecookery.loot.AdvanceEntityMatchTool;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(value = AdvanceEntityMatchTool.class, remap = false)
public class AdvanceEntityMatchToolMixin {
    @Shadow
    @Final
    private EquipmentSlot slot;

    @Inject(method = "test*", at = @At("RETURN"), cancellable = true, remap = false)
    private void onTest(LootContext context, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            return;
        }

        if (context.hasParam(LootContextParams.KILLER_ENTITY)) {
            Entity entity = context.getParam(LootContextParams.KILLER_ENTITY);
            if (entity instanceof LivingEntity livingEntity) {
                ItemStack stack = livingEntity.getItemBySlot(this.slot);
                if (kaleidoscopeTwilight$hasOilBladeEnchantment(stack)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Unique
    private boolean kaleidoscopeTwilight$hasOilBladeEnchantment(ItemStack stack) {
        ResourceLocation oilBladeId = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "oil_blade");
        return EnchantmentHelper.getEnchantments(stack).keySet().stream()
                .anyMatch(enchantment -> Objects.equals(BuiltInRegistries.ENCHANTMENT.getKey(enchantment), oilBladeId));
    }
}