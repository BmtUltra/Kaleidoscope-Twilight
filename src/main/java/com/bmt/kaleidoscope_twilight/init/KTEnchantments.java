package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class KTEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.ENCHANTMENT, KaleidoscopeTwilight.MODID);

    public static final RegistryObject<Enchantment> OIL_BLADE = ENCHANTMENTS.register("oil_blade",
            () -> new Enchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}) {
                @Override
                public int getMinCost(int level) {
                    return 15 + level * 9;
                }

                @Override
                public int getMaxCost(int level) {
                    return 65 + level * 9;
                }

                @Override
                public int getMaxLevel() {
                    return super.getMaxLevel();
                }

                @Override
                public int getMinLevel() {
                    return super.getMinLevel();
                }

                @Override
                public boolean isTreasureOnly() {
                    return super.isTreasureOnly();
                }

                @Override
                public boolean isCurse() {
                    return super.isCurse();
                }

                @Override
                public boolean isTradeable() {
                    return super.isTradeable();
                }

                @Override
                public boolean isDiscoverable() {
                    return super.isDiscoverable();
                }
            });

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}