package com.bmt.kaleidoscope_twilight.datagen.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.util.FoodHelper;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import twilightforest.init.TFItems;

import java.util.Objects;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class ComponentHandler {

    @SubscribeEvent
    public static void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        Item meefStroganoff = TFItems.MEEF_STROGANOFF.get();

        event.modify(meefStroganoff, builder -> {
            builder.set(DataComponents.MAX_STACK_SIZE, 16);
            builder.set(DataComponents.FOOD,
                    FoodHelper.withEffect(
                            Objects.requireNonNull(meefStroganoff.components().get(DataComponents.FOOD)),
                            ModEffects.SATIATED_SHIELD,
                            20 * 80, 0, 1.0F
                    ));
        });
    }
}