package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.init.KTEffects;
import com.bmt.kaleidoscope_twilight.mixins.accessor.ItemCombinerMenuAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilMenu.class)
public class AnvilMenuMixin {

    @Inject(method = "getCost", at = @At("RETURN"), cancellable = true)
    private void kaleidoscope_twilight$modifyAnvilCost(CallbackInfoReturnable<Integer> cir) {
        AnvilMenu menu = (AnvilMenu) (Object) this;
        Player player = ((ItemCombinerMenuAccessor) menu).getPlayer();
        if (player != null && player.hasEffect(KTEffects.ERUDITION.get())) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "mayPickup", at = @At("HEAD"), cancellable = true)
    private void kaleidoscope_twilight$bypassExperienceCheck(Player player, boolean hasStack, CallbackInfoReturnable<Boolean> cir) {
        if (player != null && player.hasEffect(KTEffects.ERUDITION.get())) {
            cir.setReturnValue(true);
        }
    }
}