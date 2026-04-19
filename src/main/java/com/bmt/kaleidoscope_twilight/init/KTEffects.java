package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.effect.*;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KTEffects {
    private static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.MOB_EFFECT, KaleidoscopeTwilight.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> WITCHCRAFT_PROTECTION = EFFECTS.register("witchcraft_protection", () -> new WitchcraftProtectionEffect(MobEffectCategory.BENEFICIAL, 0x8A2BE2));
    public static final DeferredHolder<MobEffect, MobEffect> MUSHROOM_PERCEPTION = EFFECTS.register("mushroom_perception", () -> new MushroomPerceptionEffect(MobEffectCategory.BENEFICIAL, 0x00FF00));
    public static final DeferredHolder<MobEffect, MobEffect> PHANTOM = EFFECTS.register("phantom", () -> new PhantomEffect(MobEffectCategory.BENEFICIAL, 0x87CEEB));
    public static final DeferredHolder<MobEffect, MobEffect> STURDY_SCALES = EFFECTS.register("sturdy_scales", () -> new SturdyScalesEffect(MobEffectCategory.BENEFICIAL, 0x8B4513));
    public static final DeferredHolder<MobEffect, MobEffect> YETI_THROW = EFFECTS.register("yeti_throw", () -> new YetiThrowEffect(MobEffectCategory.BENEFICIAL, 0x87CEEB));
    public static final DeferredHolder<MobEffect, MobEffect> FROST_CLOUD = EFFECTS.register("frost_cloud", () -> new FrostCloudEffect(MobEffectCategory.BENEFICIAL, 0x87CEFA));
    public static final DeferredHolder<MobEffect, MobEffect> FIRE_BREATH = EFFECTS.register("fire_breath", () -> new FireBreathEffect(MobEffectCategory.BENEFICIAL, 0xFF4500));
    public static final DeferredHolder<MobEffect, MobEffect> ERUDITION = EFFECTS.register("erudition", () -> new EruditionEffect(MobEffectCategory.BENEFICIAL, 0x4B0082));
    public static final DeferredHolder<MobEffect, MobEffect> GIANT_BLESSING = EFFECTS.register("giant_blessing", () -> new GiantBlessingEffect(MobEffectCategory.BENEFICIAL, 0x8B4513));

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}