package com.bmt.kaleidoscope_twilight.common.crafting;

import com.bmt.kaleidoscope_twilight.common.item.TeaDateItem;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import com.bmt.kaleidoscope_twilight.init.KTRecipes;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.TeacupRegistry;
import com.github.ysbbbbbb.kaleidoscopecookery.item.TeacupItem;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class TeaDateAbsorbRecipe extends CustomRecipe {
    private final List<Ingredient> ingredients;
    private final ItemStack result;

    public TeaDateAbsorbRecipe(List<Ingredient> ingredients, ItemStack result) {
        super(CraftingBookCategory.MISC);
        this.ingredients = ingredients;
        this.result = result;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean hasTeaDate = false;
        boolean hasTeacup = false;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) {
                continue;
            }
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
            if (getTeacupData(input.getItem(i)) != null) {
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
        return this.result.copy();
    }

    public List<Ingredient> ingredients() {
        return this.ingredients;
    }

    public ItemStack result() {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.addAll(this.ingredients);
        return list;
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
        private static final MapCodec<TeaDateAbsorbRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").forGetter(TeaDateAbsorbRecipe::ingredients),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(TeaDateAbsorbRecipe::result)
        ).apply(inst, TeaDateAbsorbRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, TeaDateAbsorbRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), TeaDateAbsorbRecipe::ingredients,
                ItemStack.STREAM_CODEC, TeaDateAbsorbRecipe::result,
                TeaDateAbsorbRecipe::new
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
