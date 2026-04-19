package com.bmt.kaleidoscope_twilight.client;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.client.tooltip.KeepingPouchTooltipComponent;
import com.bmt.kaleidoscope_twilight.item.KeepingPouchItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID, value = Dist.CLIENT)
public class KTClient {

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(KeepingPouchItem.KeepingPouchTooltip.class, KeepingPouchTooltipComponent::new);
    }
}