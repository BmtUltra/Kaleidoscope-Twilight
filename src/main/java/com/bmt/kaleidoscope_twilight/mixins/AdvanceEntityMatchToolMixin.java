package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopecookery.loot.AdvanceEntityMatchTool;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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

@Mixin(AdvanceEntityMatchTool.class)
public class AdvanceEntityMatchToolMixin {
    @Shadow
    @Final
    private EquipmentSlot slot;

    @Inject(method = "test*", at = @At("RETURN"), cancellable = true)
    private void onTest(LootContext context, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            return;
        }

        if (context.hasParam(LootContextParams.ATTACKING_ENTITY)) {
            Entity entity = context.getParam(LootContextParams.ATTACKING_ENTITY);
            if (entity instanceof LivingEntity livingEntity) {
                ItemStack stack = livingEntity.getItemBySlot(this.slot);
                if (kaleidoscopeTwilight_1_21_1_NeoForge$hasOilBladeEnchantment(stack, (ServerLevel) entity.level())) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Unique
    private boolean kaleidoscopeTwilight_1_21_1_NeoForge$hasOilBladeEnchantment(ItemStack stack, ServerLevel level) {
        ResourceLocation oilBladeId = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "oil_blade");
        return level.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
                .getHolder(oilBladeId)
                .map(enchantment -> EnchantmentHelper.getTagEnchantmentLevel(enchantment, stack) > 0)
                .orElse(false);
    }
}