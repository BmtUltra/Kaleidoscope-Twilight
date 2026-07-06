package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.blockentity.FieryPotBlockEntity;
import com.bmt.kaleidoscope_twilight.blockentity.FieryStockpotBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class KTBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, KaleidoscopeTwilight.MODID);

    // 炽铁炒锅
    public static final RegistryObject<BlockEntityType<FieryPotBlockEntity>> FIERY_POT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("fiery_pot",
                    () -> BlockEntityType.Builder.of(FieryPotBlockEntity::new, KTBlocks.FIERY_POT.get()).build(null));

    // 炽铁汤锅
    public static final RegistryObject<BlockEntityType<FieryStockpotBlockEntity>> FIERY_STOCKPOT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("fiery_stockpot",
                    () -> BlockEntityType.Builder.of(FieryStockpotBlockEntity::new, KTBlocks.FIERY_STOCKPOT.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}