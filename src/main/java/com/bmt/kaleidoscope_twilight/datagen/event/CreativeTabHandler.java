package com.bmt.kaleidoscope_twilight.datagen.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.Objects;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class CreativeTabHandler {

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().location().equals(KaleidoscopeTwilight.fromNamespaceAndPath("twilightforest", "items"))) {
            event.accept(KTItems.UMBRAL_SUNFLOWER_SPAWN_EGG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey().location().equals(KaleidoscopeTwilight.fromNamespaceAndPath("twilightforest", "blocks"))) {
            Item bossSpawner = BuiltInRegistries.ITEM.getOptional(
                    ResourceKey.create(Registries.ITEM, KaleidoscopeTwilight.fromNamespaceAndPath("twilightforest", "snow_queen_boss_spawner"))).orElse(null);
            if (bossSpawner != null && event.getParentEntries().stream().anyMatch(stack -> stack.is(bossSpawner))) {
                event.insertAfter(new ItemStack(bossSpawner), new ItemStack(KTItems.UMBRAL_SUNFLOWER_SPAWNER.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            } else {
                event.accept(KTItems.UMBRAL_SUNFLOWER_SPAWNER.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
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
