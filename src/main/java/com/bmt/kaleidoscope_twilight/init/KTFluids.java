package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopetavern.fluid.JuiceFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight.id;

public class KTFluids {
    public static final ResourceLocation TORCHBERRY_JUICE_ID = id("torchberry_juice");
    public static final ResourceLocation FLOWING_TORCHBERRY_JUICE_ID = id("flowing_torchberry_juice");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, KaleidoscopeTwilight.MODID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, KaleidoscopeTwilight.MODID);

    public static final RegistryObject<FluidType> TORCHBERRY_JUICE_TYPE = FLUID_TYPES.register("torchberry_juice",
            () -> new JuiceFluidType(TORCHBERRY_JUICE_ID, 10));

    public static final RegistryObject<ForgeFlowingFluid.Source> TORCHBERRY_JUICE = FLUIDS.register("torchberry_juice",
            () -> new ForgeFlowingFluid.Source(KTFluids.torchberryJuiceProperties()));

    public static final RegistryObject<ForgeFlowingFluid.Flowing> FLOWING_TORCHBERRY_JUICE = FLUIDS.register("flowing_torchberry_juice",
            () -> new ForgeFlowingFluid.Flowing(KTFluids.torchberryJuiceProperties()));

    private static ForgeFlowingFluid.Properties torchberryJuiceProperties() {
        return new ForgeFlowingFluid.Properties(
                TORCHBERRY_JUICE_TYPE,
                TORCHBERRY_JUICE,
                FLOWING_TORCHBERRY_JUICE
        ).bucket(KTBrewItems.TORCHBERRY_BUCKET);
    }
}