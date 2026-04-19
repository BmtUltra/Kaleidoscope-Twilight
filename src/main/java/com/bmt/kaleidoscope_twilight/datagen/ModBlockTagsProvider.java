package com.bmt.kaleidoscope_twilight.datagen;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, KaleidoscopeTwilight.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.CROPS).add(KTBlocks.TWILIGHT_FERN_CROP.get());
    }
}