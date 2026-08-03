package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import com.github.ysbbbbbb.kaleidoscopecookery.init.registry.TeacupRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class KTTeacups {
    public static ResourceLocation NAGA_TEA;
    public static ResourceLocation WITCHCRAFT_TEA;
    public static ResourceLocation MINOTAUR_MUSHROOM_TEA;
    public static ResourceLocation FIRE_TEA;
    public static ResourceLocation PHANTOM_TEA;
    public static ResourceLocation HOT_TEARS_TEA;
    public static ResourceLocation ICE_CRYSTAL_TEA;

    public static void init() {
        TeacupRegistry registry = new TeacupRegistry() {
            @Override
            public ResourceLocation registerTeacupData(String name, TeacupData data) {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, name);
                TEACUP_DATA_MAP.put(id, data);
                return id;
            }
        };

        NAGA_TEA = registry.registerTeacupData("naga_tea",
                TeacupRegistry.TeacupData.create(4)
                        .addEffect(() -> new MobEffectInstance(KTEffects.STURDY_SCALES, 2 * 60 * 20))
        );

        WITCHCRAFT_TEA = registry.registerTeacupData("witchcraft_tea",
                TeacupRegistry.TeacupData.create(4)
                        .addEffect(() -> new MobEffectInstance(KTEffects.WITCHCRAFT_PROTECTION, 2 * 60 * 20))
        );

        MINOTAUR_MUSHROOM_TEA = registry.registerTeacupData("minotaur_mushroom_tea",
                TeacupRegistry.TeacupData.create(4)
                        .addEffect(() -> new MobEffectInstance(KTEffects.MUSHROOM_PERCEPTION, 5 * 60 * 20))
        );

        FIRE_TEA = registry.registerTeacupData("fire_tea",
                TeacupRegistry.TeacupData.create(4)
                        .addEffect(() -> new MobEffectInstance(KTEffects.FIRE_BREATH, 3 * 60 * 20))
                        .addEffect(() -> new MobEffectInstance(ModEffects.WARMTH, 5 * 60 * 20))
        );

        PHANTOM_TEA = registry.registerTeacupData("phantom_tea",
                TeacupRegistry.TeacupData.create(4)
                        .addEffect(() -> new MobEffectInstance(KTEffects.PHANTOM, 2 * 60 * 20))
        );

        HOT_TEARS_TEA = registry.registerTeacupData("hot_tears_tea",
                TeacupRegistry.TeacupData.create(4)
                        .addEffect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 8 * 60 * 20))
                        .addEffect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 5 * 60 * 20))
        );

        ICE_CRYSTAL_TEA = registry.registerTeacupData("ice_crystal_tea",
                TeacupRegistry.TeacupData.create(4)
                        .addEffect(() -> new MobEffectInstance(KTEffects.FROST_CLOUD, 60 * 20))
                        .addEffect(() -> new MobEffectInstance(KTEffects.YETI_THROW,  60 * 20))
        );
    }
}