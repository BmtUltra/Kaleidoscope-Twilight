package com.bmt.kaleidoscope_twilight.datagen;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, KaleidoscopeTwilight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (int i = 0; i < 8; i++) {
            withExistingParent("block/twilight_fern_crop/stage" + i, mcLoc("block/cross"))
                    .texture("cross", KaleidoscopeTwilight.id("block/twilight_fern_crop/stage" + i))
                    .renderType("cutout");
        }
    }
}