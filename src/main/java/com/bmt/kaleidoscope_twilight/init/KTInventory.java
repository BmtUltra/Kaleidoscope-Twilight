package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.common.inventory.KeepingPouchMenu;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class KTInventory {
    private static final ResourceKey<Registry<MenuType<?>>> MENU_TYPE_REGISTRY_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("menu"));

    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(MENU_TYPE_REGISTRY_KEY, KaleidoscopeTwilight.MODID);

    public static final Supplier<MenuType<KeepingPouchMenu>> KEEPING_POUCH =
            MENU_TYPES.register("keeping_pouch", () -> IMenuTypeExtension.create(KeepingPouchMenu::new));

    public static void register(IEventBus modEventBus) {
        MENU_TYPES.register(modEventBus);
    }
}
