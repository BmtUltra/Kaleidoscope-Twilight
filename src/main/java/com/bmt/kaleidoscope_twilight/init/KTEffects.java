package com.bmt.kaleidoscope_twilight.init;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;

import com.bmt.kaleidoscope_twilight.util.AttributeModifierHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import twilightforest.init.TFDataAttachments;
import twilightforest.init.TFMobEffects;
import twilightforest.init.TFParticleType;

public class KTEffects {
    private static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, KaleidoscopeTwilight.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> WITCHCRAFT_PROTECTION = EFFECTS.register("witchcraft_protection",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x8A2BE2) {
                @Override
                public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
                    return duration % 120 == 0;
                }

                @Override
                public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
                    if (!entity.level().isClientSide()) {
                        var shieldsData = entity.getData(TFDataAttachments.FORTIFICATION_SHIELDS);
                        shieldsData.setShields(entity, 3, true);
                    }
                    return true;
                }
            });

    public static final DeferredHolder<MobEffect, MobEffect> MUSHROOM_PERCEPTION = EFFECTS.register("mushroom_perception",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x00FF00) {
            });
    public static final DeferredHolder<MobEffect, MobEffect> PHANTOM = EFFECTS.register("phantom",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x87CEEB) {
            });
    public static final DeferredHolder<MobEffect, MobEffect> STURDY_SCALES = EFFECTS.register("sturdy_scales",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x8B4513) {
            });

    public static final DeferredHolder<MobEffect, MobEffect> YETI_THROW = EFFECTS.register("yeti_throw",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x87CEEB) {
                @Override
                public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
                    return true;
                }

                @Override
                public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
                    if (entity.getLastHurtMob() != null && entity.getLastHurtMobTimestamp() == entity.tickCount - 1) {
                        LivingEntity target = entity.getLastHurtMob();
                        int frostDuration = 120 + (amplifier * 40);
                        target.addEffect(new MobEffectInstance(TFMobEffects.FROSTY, frostDuration, 1, false, true, true));
                    }
                    return true;
                }
            });

    public static final DeferredHolder<MobEffect, MobEffect> FROST_CLOUD = EFFECTS.register("frost_cloud",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x87CEFA) {
                @Override
                public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
                    return true;
                }

                @Override
                public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
                    if (entity instanceof Player player) {
                        boolean changed = false;
                        if (!player.getAbilities().flying) {
                            player.getAbilities().flying = true;
                            changed = true;
                        }
                        if (!player.getAbilities().mayfly) {
                            player.getAbilities().mayfly = true;
                            changed = true;
                        }
                        if (changed) {
                            player.onUpdateAbilities();
                        }
                        player.fallDistance = 0.0F;
                    } else {
                        entity.setNoGravity(true);
                        entity.fallDistance = 0.0F;
                    }
                    spawnSnowParticles(entity, amplifier);
                    return true;
                }

                private void spawnSnowParticles(LivingEntity entity, int amplifier) {
                    if (entity.tickCount % 3 != 0) {
                        return;
                    }
                    Vec3 pos = entity.position();
                    int particleCount = 3 + amplifier;
                    for (int i = 0; i < particleCount; i++) {
                        double px = (entity.getRandom().nextDouble() - 0.5) * 0.8;
                        double py = 0.05 + entity.getRandom().nextDouble() * 0.15;
                        double pz = (entity.getRandom().nextDouble() - 0.5) * 0.8;
                        double vx = (entity.getRandom().nextDouble() - 0.5) * 0.02;
                        double vy = 0.02 + entity.getRandom().nextDouble() * 0.04;
                        double vz = (entity.getRandom().nextDouble() - 0.5) * 0.02;
                        if (entity.level().isClientSide()) {
                            entity.level().addParticle(TFParticleType.SNOW.get(), pos.x + px, pos.y + py, pos.z + pz, vx, vy, vz);
                        } else if (entity.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(TFParticleType.SNOW.get(), pos.x + px, pos.y + py, pos.z + pz, 1, vx, vy, vz, 0.05);
                        }
                    }
                }
            });
    public static final DeferredHolder<MobEffect, MobEffect> FIRE_BREATH = EFFECTS.register("fire_breath",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xFF4500) {
            });
    public static final DeferredHolder<MobEffect, MobEffect> ERUDITION = EFFECTS.register("erudition",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x4B0082) {
            });

    public static final DeferredHolder<MobEffect, MobEffect> GIANT_BLESSING = EFFECTS.register("giant_blessing",
            () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0x8B4513) {
                @Override
                public void addAttributeModifiers(@NotNull AttributeMap attributes, int amplifier) {
                    new AttributeModifierHelper.Builder(attributes, KaleidoscopeTwilight.MODID, "giant_blessing")
                            .add(Attributes.STEP_HEIGHT, 0.5 + amplifier * 0.5, AttributeModifier.Operation.ADD_VALUE)
                            .add(Attributes.ENTITY_INTERACTION_RANGE, 1 + amplifier, AttributeModifier.Operation.ADD_VALUE)
                            .add(Attributes.BLOCK_INTERACTION_RANGE, 1 + amplifier, AttributeModifier.Operation.ADD_VALUE)
                            .add(Attributes.ATTACK_DAMAGE, Math.min(1.5 + amplifier * 0.5, 100.0), AttributeModifier.Operation.ADD_VALUE)
                            .add(Attributes.MAX_HEALTH, Math.min((amplifier + 1) * 10.0, 200.0), AttributeModifier.Operation.ADD_VALUE)
                            .add(Attributes.MOVEMENT_SPEED, Math.min((amplifier + 1) * 0.025, 0.5), AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                            .add(Attributes.JUMP_STRENGTH, Math.min((amplifier + 1) * 0.005, 0.02), AttributeModifier.Operation.ADD_VALUE)
                            .add(Attributes.SAFE_FALL_DISTANCE, (amplifier + 1) * 0.5, AttributeModifier.Operation.ADD_VALUE);
                }

                @Override
                public void removeAttributeModifiers(@NotNull AttributeMap attributes) {
                    new AttributeModifierHelper.Builder(attributes, KaleidoscopeTwilight.MODID, "giant_blessing")
                            .remove(Attributes.STEP_HEIGHT)
                            .remove(Attributes.ATTACK_DAMAGE)
                            .remove(Attributes.MAX_HEALTH)
                            .remove(Attributes.ENTITY_INTERACTION_RANGE)
                            .remove(Attributes.BLOCK_INTERACTION_RANGE)
                            .remove(Attributes.MOVEMENT_SPEED)
                            .remove(Attributes.JUMP_STRENGTH)
                            .remove(Attributes.SAFE_FALL_DISTANCE);
                }
            });

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}
