package com.bmt.kaleidoscope_twilight.effect;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class GiantBlessingEffect extends MobEffect {

    public GiantBlessingEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    private static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("cb3f55d3-645c-4f38-a497-9c13a33db5cf");
    private static final UUID MAX_HEALTH_MODIFIER = UUID.fromString("fa233e1c-4180-4865-b01b-bcce9785aca3");
    private static final UUID MOVEMENT_SPEED_MODIFIER = UUID.fromString("8b4513e1-4180-4865-b01b-bcce9785aca3");
    private static final UUID JUMP_STRENGTH_MODIFIER = UUID.fromString("4b0082e1-4180-4865-b01b-bcce9785aca3");

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            double attackDamage = Math.min(1.5 + amplifier * 0.5, 100.0);
            updateAttribute(entity, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, attackDamage, AttributeModifier.Operation.ADDITION);

            double maxHealth = (amplifier + 1) * 10.0;
            if (maxHealth > 200.0) maxHealth = 200.0;
            updateAttribute(entity, Attributes.MAX_HEALTH, MAX_HEALTH_MODIFIER, maxHealth, AttributeModifier.Operation.ADDITION);

            double movementSpeed = (amplifier + 1) * (-0.025);
            if (movementSpeed < -0.5) movementSpeed = -0.5;
            updateAttribute(entity, Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED_MODIFIER, movementSpeed, AttributeModifier.Operation.MULTIPLY_BASE);

            double jumpStrength = (amplifier + 1) * (-0.005);
            if (jumpStrength < -0.02) jumpStrength = -0.02;
            updateAttribute(entity, Attributes.JUMP_STRENGTH, JUMP_STRENGTH_MODIFIER, jumpStrength, AttributeModifier.Operation.ADDITION);
        }
    }

    private void updateAttribute(LivingEntity entity, Attribute attribute, UUID id, double amount, AttributeModifier.Operation operation) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null) {
            AttributeModifier existing = instance.getModifier(id);
            if (existing == null || existing.getAmount() != amount) {
                instance.removeModifier(id);
                instance.addTransientModifier(new AttributeModifier(id, "Giant Blessing", amount, operation));
            }
        }
    }

    public static void removeAllBonuses(LivingEntity entity) {
        removeBonus(entity, Attributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER);
        removeBonus(entity, Attributes.MAX_HEALTH, MAX_HEALTH_MODIFIER);
        removeBonus(entity, Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED_MODIFIER);
        removeBonus(entity, Attributes.JUMP_STRENGTH, JUMP_STRENGTH_MODIFIER);
    }

    private static void removeBonus(LivingEntity entity, Attribute attribute, UUID id) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null) {
            instance.removeModifier(id);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        var effect = player.getEffect(KTEffects.GIANT_BLESSING.get());
        if (effect != null) {
            GiantBlessingEffect giantBlessing = (GiantBlessingEffect) KTEffects.GIANT_BLESSING.get();
            giantBlessing.applyEffectTick(player, effect.getAmplifier());
            player.setHealth(player.getMaxHealth());
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}