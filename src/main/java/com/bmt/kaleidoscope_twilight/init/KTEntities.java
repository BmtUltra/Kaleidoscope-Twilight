package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.entity.ThrownSwordEntity;
import com.bmt.kaleidoscope_twilight.entity.boss.UmbralSunflower;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class KTEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, KaleidoscopeTwilight.MODID);

    public static final Supplier<EntityType<UmbralSunflower>> UMBRAL_SUNFLOWER =
            ENTITIES.register("umbral_sunflower",
                    () -> EntityType.Builder.of(UmbralSunflower::new, MobCategory.MONSTER)
                            .sized(1.0F, 2.1F)
                            .build("umbral_sunflower")
            );

    public static final Supplier<EntityType<ThrownSwordEntity>> THROWN_SWORD =
            ENTITIES.register("thrown_sword",
                    () -> EntityType.Builder.of(ThrownSwordEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(10)
                            .build("thrown_sword")
            );
}