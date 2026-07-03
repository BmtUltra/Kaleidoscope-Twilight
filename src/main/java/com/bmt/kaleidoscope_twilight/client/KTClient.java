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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;

import static net.neoforged.neoforge.client.gui.VanillaGuiLayers.CROSSHAIR;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID, value = Dist.CLIENT)
public class KTClient {

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(KeepingPouchItem.KeepingPouchTooltip.class, KeepingPouchTooltipComponent::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(KTBlockEntities.FIERY_POT_BLOCK_ENTITY.get(), FieryPotBlockEntityRender::new);
        BlockEntityRenderers.register(KTBlockEntities.FIERY_STOCKPOT_BLOCK_ENTITY.get(), FieryStockpotBlockEntityRender::new);
        
        event.enqueueWork(() -> ItemProperties.register(
                KTItems.FIERY_STOCKPOT_LID.get(),
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "using"),
                (stack, level, entity, seed) -> FieryStockpotLidItem.getTexture(stack, entity)
        ));
    }

    @SubscribeEvent
    public static void onRegisterGuiOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(CROSSHAIR, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "fiery_pot_overlay"), new FieryPotOverlay());
    }
}
