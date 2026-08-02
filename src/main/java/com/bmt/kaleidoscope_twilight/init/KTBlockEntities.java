package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.blockentity.trophy.UmbralSunflowerTrophyBlockEntity;
import com.bmt.kaleidoscope_twilight.blockentity.FieryPotBlockEntity;
import com.bmt.kaleidoscope_twilight.blockentity.FieryStockpotBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class KTBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, KaleidoscopeTwilight.MODID);

    public static final Supplier<BlockEntityType<FieryPotBlockEntity>> FIERY_POT_BLOCK_ENTITY = BLOCK_ENTITIES.register("fiery_pot",
            () -> BlockEntityType.Builder.of(FieryPotBlockEntity::new, KTBlocks.FIERY_POT.get()).build(null));

    public static final Supplier<BlockEntityType<FieryStockpotBlockEntity>> FIERY_STOCKPOT_BLOCK_ENTITY= BLOCK_ENTITIES.register("fiery_stockpot",
            () -> BlockEntityType.Builder.of(FieryStockpotBlockEntity::new, KTBlocks.FIERY_STOCKPOT.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UmbralSunflowerTrophyBlockEntity>> UMBRAL_SUNFLOWER_TROPHY =
            BLOCK_ENTITIES.register("umbral_sunflower_trophy", () ->
                    BlockEntityType.Builder.of(UmbralSunflowerTrophyBlockEntity::new,
                            KTBlocks.UMBRAL_SUNFLOWER_TROPHY.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}