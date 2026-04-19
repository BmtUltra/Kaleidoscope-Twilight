package com.bmt.kaleidoscope_twilight.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.Objects;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class CreativeTabHandler {

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().location().equals(KaleidoscopeTwilight.fromNamespaceAndPath("kaleidoscope_cookery", "cookery_food"))) {
            for (ItemStack itemStack : event.getParentEntries().toArray(ItemStack[]::new)) {
                if (Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(itemStack.getItem()))
                    .getNamespace().equals(KaleidoscopeTwilight.MODID)) {
                    event.remove(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }
    }
}