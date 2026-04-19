package com.bmt.kaleidoscope_twilight.client;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.client.tooltip.KeepingPouchTooltipComponent;
import com.bmt.kaleidoscope_twilight.item.KeepingPouchItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KTClient {

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(KeepingPouchItem.KeepingPouchTooltip.class, KeepingPouchTooltipComponent::new);
    }
}