package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.common.advancement.NagaSlayerTrigger;
import com.bmt.kaleidoscope_twilight.common.advancement.SunflowerSeenTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class KTTriggers {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(Registries.TRIGGER_TYPE, KaleidoscopeTwilight.MODID);

    public static final DeferredHolder<CriterionTrigger<?>, NagaSlayerTrigger> NAGA_SLAYER =
            TRIGGERS.register("naga_slayer", NagaSlayerTrigger::new);

    public static final DeferredHolder<CriterionTrigger<?>, SunflowerSeenTrigger> SUNFLOWER_SEEN =
            TRIGGERS.register("sunflower_seen", SunflowerSeenTrigger::new);
}
