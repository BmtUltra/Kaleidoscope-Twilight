package com.bmt.kaleidoscope_twilight.common.crafting;

import com.bmt.kaleidoscope_twilight.common.item.TeaDateItem;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import com.bmt.kaleidoscope_twilight.init.KTRecipes;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.TeacupRegistry;
import com.github.ysbbbbbb.kaleidoscopecookery.item.TeacupItem;
import com.mojang.datafixers.util.Pair;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class TeaDateAbsorbRecipe extends CustomRecipe {
    public TeaDateAbsorbRecipe() {
        super(CraftingBookCategory.MISC);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean hasTeaDate = false;
        boolean hasTeacup = false;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;

            if (!hasTeaDate && stack.is(KTItems.TEA_DATE_ITEM.get())) {
                hasTeaDate = true;
            } else if (!hasTeacup && getTeacupData(stack) != null) {
                hasTeacup = true;
            } else {
                return false;
            }
        }

        return hasTeaDate && hasTeacup;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack teaDate = ItemStack.EMPTY;
        ItemStack teacup = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.is(KTItems.TEA_DATE_ITEM.get())) {
                teaDate = stack;
            } else if (getTeacupData(stack) != null) {
                teacup = stack;
            }
        }
        ItemStack output = teaDate.copyWithCount(1);

        TeacupRegistry.TeacupData data = getTeacupData(teacup);
        if (data != null) {
            List<MobEffectInstance> effects = Lists.newArrayList();
            for (Pair<Supplier<MobEffectInstance>, Float> entry : data.getEffects()) {
                effects.add(entry.getFirst().get());
            }
            TeaDateItem.absorbEffects(output, effects);
        }
        return output;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput input) {
        NonNullList<ItemStack> remaining = NonNullList.withSize(input.size(), ItemStack.EMPTY);
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (getTeacupData(stack) != null) {
                remaining.set(i, new ItemStack(com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems.EMPTY_CUP.get()));
            }
        }
        return remaining;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return new ItemStack(KTItems.TEA_DATE_ITEM.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KTRecipes.TEA_DATE_ABSORB_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Nullable
    public static TeacupRegistry.TeacupData getTeacupData(ItemStack stack) {
        if (!(stack.getItem() instanceof TeacupItem)) {
            return null;
        }
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return TeacupRegistry.TEACUP_DATA_MAP.get(id);
    }

    public static class Serializer implements RecipeSerializer<TeaDateAbsorbRecipe> {
        public static final MapCodec<TeaDateAbsorbRecipe> CODEC = MapCodec.unit(new TeaDateAbsorbRecipe());

        public static final StreamCodec<RegistryFriendlyByteBuf, TeaDateAbsorbRecipe> STREAM_CODEC =
                StreamCodec.of(
                        (buf, recipe) -> {},
                        (buf) -> new TeaDateAbsorbRecipe()
                );

        @Override
        public MapCodec<TeaDateAbsorbRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TeaDateAbsorbRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}