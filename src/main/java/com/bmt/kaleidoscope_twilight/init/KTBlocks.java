package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.block.FieryPotBlock;
import com.bmt.kaleidoscope_twilight.block.FieryStockPotBlock;
import com.bmt.kaleidoscope_twilight.block.crops.TwilightFernCropBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StoveBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class KTBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, KaleidoscopeTwilight.MODID);

    public static final RegistryObject<StoveBlock> TWILIGHT_STOVE = BLOCKS.register("twilight_stove", StoveBlock::new);
    public static final RegistryObject<TwilightFernCropBlock> TWILIGHT_FERN_CROP = BLOCKS.register("twilight_fern_crop", TwilightFernCropBlock::new);

    // 炽铁炒锅
    public static final RegistryObject<FieryPotBlock> FIERY_POT = BLOCKS.register("fiery_pot", FieryPotBlock::new);

    // 炽铁汤锅
    public static final RegistryObject<FieryStockPotBlock> FIERY_STOCKPOT = BLOCKS.register("fiery_stockpot", FieryStockPotBlock::new);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}