package com.bmt.kaleidoscope_twilight.common.item;

import com.bmt.kaleidoscope_twilight.common.block.trophy.UmbralSunflowerTrophyBlock;
import com.bmt.kaleidoscope_twilight.common.block.trophy.WallUmbralSunflowerTrophyBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import org.jetbrains.annotations.Nullable;

public class UmbralSunflowerTrophyItem extends StandingAndWallBlockItem {

    public UmbralSunflowerTrophyItem(UmbralSunflowerTrophyBlock floorTrophyBlock, WallUmbralSunflowerTrophyBlock wallTrophyBlock, Item.Properties properties) {
        super(floorTrophyBlock, wallTrophyBlock, properties, Direction.DOWN);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, LivingEntity entity) {
        return armorType == EquipmentSlot.HEAD;
    }

    @Override
    public @Nullable EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.HEAD;
    }
}