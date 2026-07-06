package com.bmt.kaleidoscope_twilight.client;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.client.gui.FieryPotOverlay;
import com.bmt.kaleidoscope_twilight.client.render.FieryPotBlockEntityRender;
import com.bmt.kaleidoscope_twilight.client.render.FieryStockpotBlockEntityRender;
import com.bmt.kaleidoscope_twilight.client.tooltip.KeepingPouchTooltipComponent;
import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import com.bmt.kaleidoscope_twilight.item.FieryStockpotLidItem;
import com.bmt.kaleidoscope_twilight.item.KeepingPouchItem;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTClient {

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(KeepingPouchItem.KeepingPouchTooltip.class, KeepingPouchTooltipComponent::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityRenderers.register(KTBlockEntities.FIERY_POT_BLOCK_ENTITY.get(), FieryPotBlockEntityRender::new);
            BlockEntityRenderers.register(KTBlockEntities.FIERY_STOCKPOT_BLOCK_ENTITY.get(), FieryStockpotBlockEntityRender::new);

            ItemProperties.register(
                    KTItems.FIERY_STOCKPOT_LID.get(),
                    new ResourceLocation(KaleidoscopeTwilight.MODID, "using"),
                    (stack, level, entity, seed) -> FieryStockpotLidItem.getTexture(stack, entity)
            );
        });
    }

    @SubscribeEvent
    public static void onRegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("fiery_pot_overlay", new FieryPotOverlay());
    }
}