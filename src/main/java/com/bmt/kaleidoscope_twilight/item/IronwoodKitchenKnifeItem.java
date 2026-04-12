package com.bmt.kaleidoscope_twilight.item;

import com.github.ysbbbbbb.kaleidoscopecookery.item.KitchenKnifeItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantments;

public class IronwoodKitchenKnifeItem extends KitchenKnifeItem {
    public IronwoodKitchenKnifeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public ItemStack getDefaultInstance(HolderLookup.Provider holders) {
        ItemStack stack = getDefaultInstance();
        stack.enchant(holders.holderOrThrow(Enchantments.SMITE), 2);
        return stack;
    }
}