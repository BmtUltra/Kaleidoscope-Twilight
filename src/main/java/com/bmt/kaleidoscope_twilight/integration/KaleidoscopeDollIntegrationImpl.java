package com.bmt.kaleidoscope_twilight.integration;

import java.util.LinkedHashMap;
import java.util.Map;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTCreativeTabs;
import com.github.ysbbbbbb.kaleidoscopedoll.KaleidoscopeDoll;
import com.github.ysbbbbbb.kaleidoscopedoll.block.DollBlock;
import com.github.ysbbbbbb.kaleidoscopedoll.event.ModRegisterEvent;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollEntityItem;
import com.github.ysbbbbbb.kaleidoscopedoll.item.DollItem;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class KaleidoscopeDollIntegrationImpl {
    private static final Map<ResourceLocation, Block> DOLL_BLOCKS = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Item> DOLL_ITEMS = new LinkedHashMap<>();
    private static final Map<ResourceLocation, Item> ENTITY_DOLL_ITEMS = new LinkedHashMap<>();

    private static final Map<String, String> SPONSOR_DOLL_DEFINITIONS = Map.of(
            "naga_doll", "twilight_contributor",
            "lich_doll", "twilight_contributor",
            "twilight_doll_2", "twilight_contributor",
            "twilight_doll_3", "twilight_contributor",
            "twilight_doll_4", "twilight_contributor",
            "twilight_doll_5", "twilight_contributor_5"
    );

    private static final Map<String, String> ENTITY_DOLL_DEFINITIONS = Map.ofEntries(
            Map.entry("entity_naga_doll", "naga_doll"),
            Map.entry("entity_lich_doll", "lich_doll"),
            Map.entry("entity_twilight_doll_2", "twilight_doll_2"),
            Map.entry("entity_twilight_doll_3", "twilight_doll_3"),
            Map.entry("entity_twilight_doll_4", "twilight_doll_4"),
            Map.entry("entity_twilight_doll_5", "twilight_doll_5")
    );

    public static void register(IEventBus modEventBus) {
        modEventBus.register(KaleidoscopeDollIntegrationImpl.class);
    }

    private static boolean isDollModLoaded() {
        return !ModList.get().isLoaded(KaleidoscopeDoll.MOD_ID);
    }

    @SubscribeEvent
    public static void registerBlocks(RegisterEvent event) {
        if (isDollModLoaded()) {
            return;
        }

        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            SPONSOR_DOLL_DEFINITIONS.keySet().forEach(dollId -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, dollId);
                DollBlock block = new DollBlock();
                DOLL_BLOCKS.put(id, block);
                event.register(Registries.BLOCK, id, () -> block);

                String tooltipKey = SPONSOR_DOLL_DEFINITIONS.get(dollId);
                ModRegisterEvent.SPECIAL_TOOLTIPS.put(id, tooltipKey);
            });
        }
    }

    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
        if (isDollModLoaded()) {
            return;
        }

        if (event.getRegistryKey().equals(Registries.ITEM)) {
            SPONSOR_DOLL_DEFINITIONS.forEach((dollId, tooltipKey) -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, dollId);
                Block block = DOLL_BLOCKS.get(id);
                if (block != null) {
                    DollItem item = new DollItem(block, tooltipKey);
                    DOLL_ITEMS.put(id, item);
                    event.register(Registries.ITEM, id, () -> item);
                }
            });

            ENTITY_DOLL_DEFINITIONS.forEach((entityDollId, blockDollId) -> {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, entityDollId);
                DollEntityItem item = new DollEntityItem();
                ENTITY_DOLL_ITEMS.put(id, item);
                event.register(Registries.ITEM, id, () -> item);
            });
        }
    }

//    @SubscribeEvent
//    public static void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
//        if (isDollModLoaded()) {
//            return;
//        }
//
//        if (event.getTab() == KTCreativeTabs.KALEIDOSCOPE_TWILIGHT.get()) {
//            SPONSOR_DOLL_DEFINITIONS.keySet().forEach(dollId -> {
//                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, dollId);
//                Item item = DOLL_ITEMS.get(id);
//                if (item != null) {
//                    event.accept(item);
//                }
//            });
//
//            ENTITY_DOLL_DEFINITIONS.forEach((entityDollId, blockDollId) -> {
//                ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, blockDollId);
//                Block block = DOLL_BLOCKS.get(blockId);
//
//                if (block != null) {
//                    ItemStack stack = DollEntityItem.createItemWithBlockState(block.defaultBlockState());
//                    event.accept(stack);
//                }
//            });
//        }
//    }
}