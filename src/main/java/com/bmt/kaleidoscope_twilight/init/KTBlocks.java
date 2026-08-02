package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.block.FieryStockPotBlock;
import com.bmt.kaleidoscope_twilight.block.FieryPotBlock;
import com.bmt.kaleidoscope_twilight.block.trophy.UmbralSunflowerTrophyBlock;
import com.bmt.kaleidoscope_twilight.block.trophy.WallUmbralSunflowerTrophyBlock;
import com.bmt.kaleidoscope_twilight.block.crop.TwilightFernCropBlock;
import com.github.ysbbbbbb.kaleidoscopecookery.block.kitchen.StoveBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class KTBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, KaleidoscopeTwilight.MODID);

    public static final DeferredHolder<Block, StoveBlock> TWILIGHT_STOVE = BLOCKS.register("twilight_stove", StoveBlock::new);
    public static final DeferredHolder<Block, TwilightFernCropBlock> TWILIGHT_FERN_CROP = BLOCKS.register("twilight_fern_crop", TwilightFernCropBlock::new);
    public static final DeferredHolder<Block, FieryPotBlock> FIERY_POT = BLOCKS.register("fiery_pot", FieryPotBlock::new);
    public static final DeferredHolder<Block, FieryStockPotBlock> FIERY_STOCKPOT = BLOCKS.register("fiery_stockpot", FieryStockPotBlock::new);

    public static final DeferredHolder<Block, UmbralSunflowerTrophyBlock> UMBRAL_SUNFLOWER_TROPHY =
            BLOCKS.register("umbral_sunflower_trophy", () ->
                    new UmbralSunflowerTrophyBlock(BlockBehaviour.Properties.of().instabreak(),
                            KTBlockEntities.UMBRAL_SUNFLOWER_TROPHY));

    public static final DeferredHolder<Block, WallUmbralSunflowerTrophyBlock> UMBRAL_SUNFLOWER_WALL_TROPHY =
            BLOCKS.register("umbral_sunflower_wall_trophy",
                    () -> new WallUmbralSunflowerTrophyBlock(
                            BlockBehaviour.Properties.of().instabreak(),
                            KTBlockEntities.UMBRAL_SUNFLOWER_TROPHY));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
