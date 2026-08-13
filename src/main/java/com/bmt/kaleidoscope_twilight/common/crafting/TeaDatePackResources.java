package com.bmt.kaleidoscope_twilight.common.crafting;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTRecipes;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.TeacupRegistry;
import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TeaDatePackResources implements PackResources {
    private static final String RECIPE_PATH = "recipe/tea_date_absorb.json";
    private static final ResourceLocation RECIPE_FILE =
            ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, RECIPE_PATH);

    private final PackLocationInfo location = new PackLocationInfo(
            "kaleidoscope_twilight/tea_date_recipes",
            Component.literal("Kaleidoscope Twilight Tea Date Recipes"),
            PackSource.BUILT_IN,
            Optional.empty());

    public static List<ResourceLocation> getTeacupIds() {
        List<ResourceLocation> ids = Lists.newArrayList();
        for (ResourceLocation id : TeacupRegistry.TEACUP_DATA_MAP.keySet()) {
            Item item = BuiltInRegistries.ITEM.get(id);
            if (item != null && item != Items.AIR) {
                ids.add(id);
            }
        }
        return ids;
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getRootResource(String... elements) {
        if (elements.length == 1 && PackResources.PACK_META.equals(elements[0])) {
            String meta = "{\"pack\":{\"pack_format\":" + dataPackVersion()
                    + ",\"description\":\"Tea date recipes\"}}";
            return () -> new ByteArrayInputStream(meta.getBytes(StandardCharsets.UTF_8));
        }
        return null;
    }

    @Nullable
    @Override
    public IoSupplier<InputStream> getResource(PackType packType, ResourceLocation location) {
        if (packType == PackType.SERVER_DATA
                && KaleidoscopeTwilight.MODID.equals(location.getNamespace())
                && RECIPE_PATH.equals(location.getPath())) {
            String json = new GsonBuilder().setPrettyPrinting().create()
                    .toJson(KTRecipes.buildRecipeJson());
            return () -> new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        }
        return null;
    }

    @Override
    public void listResources(PackType packType, String namespace, String path, ResourceOutput resourceOutput) {
        if (packType != PackType.SERVER_DATA
                || !KaleidoscopeTwilight.MODID.equals(namespace)) {
            return;
        }
        String normalized = path.endsWith("/") || path.isEmpty() ? path : path + "/";
        if (RECIPE_PATH.startsWith(normalized)) {
            resourceOutput.accept(RECIPE_FILE,
                    () -> new ByteArrayInputStream(new GsonBuilder().create()
                            .toJson(KTRecipes.buildRecipeJson()).getBytes(StandardCharsets.UTF_8)));
        }
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        return type == PackType.SERVER_DATA ? Set.of(KaleidoscopeTwilight.MODID) : Set.of();
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getMetadataSection(MetadataSectionSerializer<T> deserializer) {
        if (deserializer == PackMetadataSection.TYPE) {
            return (T) new PackMetadataSection(
                    Component.literal("Dynamically generated tea date recipes"),
                    dataPackVersion());
        }
        return null;
    }

    @Override
    public PackLocationInfo location() {
        return this.location;
    }

    @Override
    public void close() {
    }

    private static int dataPackVersion() {
        return SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA);
    }
}
