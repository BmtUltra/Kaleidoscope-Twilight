package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.common.crafting.TeaDateAbsorbRecipe;
import com.bmt.kaleidoscope_twilight.common.crafting.TeaDatePackResources;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class KTRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, KaleidoscopeTwilight.MODID);

    public static final Supplier<RecipeSerializer<TeaDateAbsorbRecipe>> TEA_DATE_ABSORB_SERIALIZER =
            RECIPE_SERIALIZERS.register("tea_date_absorb", TeaDateAbsorbRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }

    @EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
    public static class RecipePackRegistrar {
        @SubscribeEvent
        public static void onAddPackFinders(AddPackFindersEvent event) {
            if (event.getPackType() != PackType.SERVER_DATA) {
                return;
            }
            event.addRepositorySource(createRecipePack());
        }

        private static RepositorySource createRecipePack() {
            return consumer -> {
                PackLocationInfo location = new PackLocationInfo(
                        "kaleidoscope_twilight/tea_date_recipes",
                        Component.literal("Kaleidoscope Twilight Tea Date Recipes"),
                        PackSource.BUILT_IN,
                        Optional.empty());
                Pack.Metadata metadata = new Pack.Metadata(
                        Component.literal("Dynamically generated tea date recipes"),
                        PackCompatibility.COMPATIBLE,
                        FeatureFlagSet.of(), java.util.List.of(), false);
                Pack pack = getPack(location, metadata);
                consumer.accept(pack);
            };
        }
    }

    private static @NotNull Pack getPack(PackLocationInfo location, Pack.Metadata metadata) {
        Pack.ResourcesSupplier supply = new Pack.ResourcesSupplier() {
            @Override
            public @NotNull PackResources openPrimary(@NotNull PackLocationInfo location) {
                return new TeaDatePackResources();
            }

            @Override
            public @NotNull PackResources openFull(@NotNull PackLocationInfo location, Pack.@NotNull Metadata metadata) {
                return new TeaDatePackResources();
            }
        };
        return new Pack(location, supply, metadata,
                new PackSelectionConfig(true, Pack.Position.TOP, true));
    }

    public static JsonObject buildRecipeJson() {
        JsonObject root = new JsonObject();
        root.addProperty("type", KaleidoscopeTwilight.MODID + ":tea_date_absorb");

        JsonArray ingredients = new JsonArray();
        JsonObject teaDate = new JsonObject();
        teaDate.addProperty("item", KTItems.TEA_DATE_ITEM.getId().toString());
        ingredients.add(teaDate);

        for (ResourceLocation teaId : TeaDatePackResources.getTeacupIds()) {
            JsonObject tea = new JsonObject();
            tea.addProperty("item", teaId.toString());
            ingredients.add(tea);
        }
        root.add("ingredients", ingredients);

        JsonObject result = new JsonObject();
        result.addProperty("id", KTItems.TEA_DATE_ITEM.getId().toString());
        result.addProperty("count", 1);
        root.add("result", result);
        return root;
    }
}
