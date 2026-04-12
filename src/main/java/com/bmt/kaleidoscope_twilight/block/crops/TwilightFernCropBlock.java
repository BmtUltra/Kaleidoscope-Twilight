package com.bmt.kaleidoscope_twilight.block.crops;

import com.bmt.kaleidoscope_twilight.init.KTItems;
import net.minecraft.world.level.ItemLike;

public class TwilightFernCropBlock extends BaseCropBlock {
    public TwilightFernCropBlock() {
        super(KTItems.TWILIGHT_FERN_ITEM, KTItems.TWILIGHT_FERN_ITEM);
    }

    @Override
    public int getMaxAge() {
        return 7;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return KTItems.TWILIGHT_FERN_ITEM.get();
    }
}