package com.bmt.kaleidoscope_twilight.mixins;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twilightforest.util.landmarks.LegacyLandmarkPlacements;

@Mixin(value = LegacyLandmarkPlacements.class, remap = false)
public abstract class LegacyLandmarkPlacementsMixin {
    @Unique
    private static final ResourceKey<Biome> KT_SUNFLOWER_PLAINS = ResourceKey.create(Registries.BIOME, KaleidoscopeTwilight.id("sunflower_plains"));
    @Unique
    private static final ResourceKey<Structure> KT_SUNFLOWER_BASE = ResourceKey.create(Registries.STRUCTURE, KaleidoscopeTwilight.id("sunflower_base"));

    @Inject(method = "pickLandmarkForChunk", at = @At("HEAD"), cancellable = true)
    private static void kaleidoscope_twilight$sunflowerBaseLandmark(int chunkX, int chunkZ, LevelReader level,CallbackInfoReturnable<ResourceKey<Structure>> cir) {
        int x = Math.round(chunkX / 16.0F) * 16;
        int z = Math.round(chunkZ / 16.0F) * 16;
        if (level.getBiome(new BlockPos((x << 4) + 8, 0, (z << 4) + 8)).is(KT_SUNFLOWER_PLAINS)) {
            cir.setReturnValue(KT_SUNFLOWER_BASE);
        }
    }
}
