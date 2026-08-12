package com.bmt.kaleidoscope_twilight.common.effect;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class GiantBlessingEffect extends MobEffect {

    public GiantBlessingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    private static final ResourceLocation SCALE_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_scale");
    private static final ResourceLocation STEP_HEIGHT_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_step_height");
    private static final ResourceLocation ATTACK_DAMAGE_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_attack_damage");
    private static final ResourceLocation MAX_HEALTH_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_max_health");
    private static final ResourceLocation ENTITY_INTERACTION_RANGE_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_entity_interaction_range");
    private static final ResourceLocation BLOCK_INTERACTION_RANGE_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_block_interaction_range");
    private static final ResourceLocation MOVEMENT_SPEED_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_movement_speed");
    private static final ResourceLocation JUMP_STRENGTH_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_jump_strength");
    private static final ResourceLocation SAFE_FALL_DISTANCE_MODIFIER = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "giant_blessing_safe_fall_distance");

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            updateAttribute(entity, Attributes.STEP_HEIGHT, STEP_HEIGHT_MODIFIER, 0.5 + amplifier * 0.5, AttributeModifier.Operation.ADD_VALUE);
            updateAttribute(entity, Attributes.ENTITY_INTERACTION_RANGE, ENTITY_INTERACTION_RANGE_MODIFIER, 1 + amplifier, AttributeModifier.Operation.ADD_VALUE);
            updateAttribute(entity, Attributes.BLOCK_INTERACTION_RANGE, BLOCK_INTERACTION_RANGE_MODIFIER, 1 + amplifier, AttributeModifier.Operation.ADD_VALUE);
            double attackDamage = Math.min(1.5 + amplifier * 0.5, 100.0);
            updateAttribute(entity, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, attackDamage, AttributeModifier.Operation.ADD_VALUE);
            double maxHealth = (amplifier + 1) * 10.0;
            if (maxHealth > 200.0) maxHealth = 200.0;
            updateAttribute(entity, Attributes.MAX_HEALTH, MAX_HEALTH_MODIFIER, maxHealth, AttributeModifier.Operation.ADD_VALUE);
            double movementSpeed = (amplifier + 1) * 0.025;
            if (movementSpeed > 0.5) movementSpeed = 0.5;
            updateAttribute(entity, Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED_MODIFIER, movementSpeed, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
            double jumpStrength = (amplifier + 1) * 0.005;
            if (jumpStrength > 0.02) jumpStrength = 0.02;
            updateAttribute(entity, Attributes.JUMP_STRENGTH, JUMP_STRENGTH_MODIFIER, jumpStrength, AttributeModifier.Operation.ADD_VALUE);
            updateAttribute(entity, Attributes.SAFE_FALL_DISTANCE, SAFE_FALL_DISTANCE_MODIFIER, (amplifier + 1) * 0.5, AttributeModifier.Operation.ADD_VALUE);
        }
        return true;
    }

    private void updateAttribute(LivingEntity entity, Holder<Attribute> attribute, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null) {
            AttributeModifier existing = instance.getModifier(id);
            if (existing == null || existing.amount() != amount) {
                instance.removeModifier(id);
                instance.addTransientModifier(new AttributeModifier(id, amount, operation));
            }
        }
    }

    public static void removeAllBonuses(LivingEntity entity) {
        removeBonus(entity, Attributes.SCALE, SCALE_MODIFIER);
        removeBonus(entity, Attributes.STEP_HEIGHT, STEP_HEIGHT_MODIFIER);
        removeBonus(entity, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER);
        removeBonus(entity, Attributes.MAX_HEALTH, MAX_HEALTH_MODIFIER);
        removeBonus(entity, Attributes.ENTITY_INTERACTION_RANGE, ENTITY_INTERACTION_RANGE_MODIFIER);
        removeBonus(entity, Attributes.BLOCK_INTERACTION_RANGE, BLOCK_INTERACTION_RANGE_MODIFIER);
        removeBonus(entity, Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED_MODIFIER);
        removeBonus(entity, Attributes.JUMP_STRENGTH, JUMP_STRENGTH_MODIFIER);
        removeBonus(entity, Attributes.SAFE_FALL_DISTANCE, SAFE_FALL_DISTANCE_MODIFIER);
    }

    private static void removeBonus(LivingEntity entity, Holder<Attribute> attribute, ResourceLocation id) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null) {
            instance.removeModifier(id);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        var effect = player.getEffect(com.bmt.kaleidoscope_twilight.init.KTEffects.GIANT_BLESSING);
        if (effect != null) {
            GiantBlessingEffect giantBlessing = (GiantBlessingEffect) com.bmt.kaleidoscope_twilight.init.KTEffects.GIANT_BLESSING.get();
            giantBlessing.applyEffectTick(player, effect.getAmplifier());
            player.setHealth(player.getMaxHealth());
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}