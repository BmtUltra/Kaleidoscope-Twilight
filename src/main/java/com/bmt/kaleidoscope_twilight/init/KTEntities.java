package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.entity.GiantSwordEntity;
import com.bmt.kaleidoscope_twilight.entity.GroundSpikeEntity;
import com.bmt.kaleidoscope_twilight.entity.SwordAuraEntity;
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

    public static final Supplier<EntityType<SwordAuraEntity>> SWORD_AURA =
            ENTITIES.register("sword_aura",
                    () -> EntityType.Builder.of(SwordAuraEntity::new, MobCategory.MISC)
                            .sized(1.0F, 0.5F)
                            .clientTrackingRange(10)
                            .build("sword_aura")
            );

    public static final Supplier<EntityType<GroundSpikeEntity>> GROUND_SPIKE =
            ENTITIES.register("ground_spike",
                    () -> EntityType.Builder.of(GroundSpikeEntity::new, MobCategory.MISC)
                            .sized(42.0F, 2.0F)
                            .clientTrackingRange(10)
                            .build("ground_spike")
            );

    public static final Supplier<EntityType<GiantSwordEntity>> GIANT_SWORD =
            ENTITIES.register("giant_sword",
                    () -> EntityType.Builder.of(GiantSwordEntity::new, MobCategory.MISC)
                            .sized(8.0F, 8.0F)
                            .clientTrackingRange(10)
                            .build("giant_sword")
            );
}