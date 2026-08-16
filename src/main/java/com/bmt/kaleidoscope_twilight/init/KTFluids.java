package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopetavern.fluid.JuiceFluidType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class KTFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, KaleidoscopeTwilight.MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, KaleidoscopeTwilight.MODID);

    public static final ResourceLocation TORCHBERRY_JUICE_ID = KaleidoscopeTwilight.id("torchberry_juice");

    public static final Supplier<FluidType> TORCHBERRY_JUICE_TYPE = FLUID_TYPES.register("torchberry_juice",
            () -> new JuiceFluidType(TORCHBERRY_JUICE_ID, 10));

    public static final Supplier<BaseFlowingFluid.Source> TORCHBERRY_JUICE = FLUIDS.register("torchberry_juice",
            () -> new BaseFlowingFluid.Source(KTFluids.torchberryJuiceProperties()));

    public static final Supplier<BaseFlowingFluid.Flowing> FLOWING_TORCHBERRY_JUICE = FLUIDS.register("flowing_torchberry_juice",
            () -> new BaseFlowingFluid.Flowing(KTFluids.torchberryJuiceProperties()));

    private static BaseFlowingFluid.Properties torchberryJuiceProperties() {
        return new BaseFlowingFluid.Properties(TORCHBERRY_JUICE_TYPE, TORCHBERRY_JUICE, FLOWING_TORCHBERRY_JUICE)
                .bucket(KTBrewItems.TORCHBERRY_BUCKET);
    }
}