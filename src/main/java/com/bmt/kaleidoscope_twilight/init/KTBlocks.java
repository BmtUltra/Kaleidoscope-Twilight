package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.block.FieryStockPotBlock;
import com.bmt.kaleidoscope_twilight.block.FieryPotBlock;
import com.bmt.kaleidoscope_twilight.block.crops.TwilightFernCropBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StoveBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KTBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, KaleidoscopeTwilight.MODID);

    public static final DeferredHolder<Block, StoveBlock> TWILIGHT_STOVE = BLOCKS.register("twilight_stove", StoveBlock::new);
    public static final DeferredHolder<Block, TwilightFernCropBlock> TWILIGHT_FERN_CROP = BLOCKS.register("twilight_fern_crop", TwilightFernCropBlock::new);

    // 炽铁炒锅
    public static final DeferredHolder<Block, FieryPotBlock> FIERY_POT = BLOCKS.register("fiery_pot", FieryPotBlock::new);

    // 炽铁汤锅
    public static final DeferredHolder<Block, FieryStockPotBlock> FIERY_STOCKPOT = BLOCKS.register("fiery_stockpot", FieryStockPotBlock::new);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
