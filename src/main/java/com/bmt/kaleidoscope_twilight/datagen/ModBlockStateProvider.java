package com.bmt.kaleidoscope_twilight.datagen;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, KaleidoscopeTwilight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        cropBlock();
    }

    protected void cropBlock() {
        VariantBlockStateBuilder builder = getVariantBuilder(KTBlocks.TWILIGHT_FERN_CROP.get());
        builder.forAllStates(blockState -> {
            int age = blockState.getValue(CropBlock.AGE);
            ResourceLocation file = modLoc("block/twilight_fern_crop/stage" + age);
            return ConfiguredModel.builder()
                    .modelFile(new ModelFile.UncheckedModelFile(file))
                    .build();
        });
    }
}