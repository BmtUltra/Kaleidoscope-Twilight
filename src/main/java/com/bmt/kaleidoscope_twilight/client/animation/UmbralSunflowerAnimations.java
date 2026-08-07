package com.bmt.kaleidoscope_twilight.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.entity.boss.UmbralSunflower;

public class UmbralSunflowerAnimations {
    private static final IAnimation STANDBY = new StandbyAnimation();
    private static final IAnimation MOVE = new MoveAnimation();
    private static final IAnimation DIE = new DieAnimation();
    private static final IAnimation RESURRECTION = new PhaseTransitionAnimation();
    private static final IAnimation STAND = new StandAnimation();
    private static final IAnimation FIREBALL = new FireballAnimation();
    private static final IAnimation FLASH = new FlashAnimation();
    private static final IAnimation DEFLECT = new DeflectAnimation();
    private static final IAnimation BOUNCE = new BounceAnimation();
    private static final IAnimation AA = new AaAnimation();
    private static final IAnimation SWORD_AURA = new SwordAuraAnimation();
    private static final IAnimation SHIELD = new ShieldAnimation();

    public static void animate(UmbralSunflowerModel model, UmbralSunflower entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        int deathTime = entity.getDeathAnimationTime();
        if (deathTime > 0) {
            DIE.apply(model, deathTime / 20.0F, netHeadYaw);
            return;
        }

        int resurrectionTime = entity.getResurrectionAnimationTime();
        if (resurrectionTime > 0) {
            RESURRECTION.apply(model, (36 - resurrectionTime) / 20.0F, netHeadYaw);
            return;
        }

        int standTime = entity.getStandAnimationTime();
        if (standTime > 0) {
            STAND.apply(model, (48 - standTime) / 20.0F, netHeadYaw);
            return;
        }

        int swordTime = entity.getSwordAnimationTime();
        if (swordTime > 0) {
            FIREBALL.apply(model, entity.getSwordAnimPhase(), netHeadYaw);
            return;
        }

        int swordAuraTime = entity.getSwordAuraAnimationTime();
        if (swordAuraTime > 0) {
            SWORD_AURA.apply(model, entity.getSwordAuraAnimPhase(), netHeadYaw);
            return;
        }

        int groundSpikeTime = entity.getGroundSpikeAnimationTime();
        if (groundSpikeTime > 0) {
            RESURRECTION.apply(model, entity.getGroundSpikeAnimPhase(), netHeadYaw);
            return;
        }

        int shieldTime = entity.getShieldAnimationTime();
        if (shieldTime > 0) {
            SHIELD.apply(model, entity.getShieldAnimPhase(), netHeadYaw);
            return;
        }

        int flashTime = entity.getFlashAnimationTime();
        if (flashTime > 0) {
            FLASH.apply(model, entity.getFlashAnimPhase(), netHeadYaw);
            return;
        }

        int deflectTime = entity.getDeflectAnimationTime();
        if (deflectTime > 0) {
            DEFLECT.apply(model, 1.0F - (deflectTime / 12.0F) * 0.5958F, netHeadYaw);
            return;
        }

        int bounceTime = entity.getBounceAnimationTime();
        if (bounceTime > 0) {
            BOUNCE.apply(model, (10 - bounceTime) / 20.0F, netHeadYaw);
            return;
        }

        int attackTime = entity.getAttackAnimationTime();
        if (attackTime > 0) {
            AA.apply(model, (10 - attackTime) / 20.0F, netHeadYaw);
            return;
        }

        double dx = entity.getX() - entity.xo;
        double dz = entity.getZ() - entity.zo;
        boolean actuallyMoving = dx * dx + dz * dz > 1.0E-5;
        if (actuallyMoving && entity.walkAnimation.isMoving() && limbSwingAmount > 0.02F) {
            MOVE.apply(model, (ageInTicks % 19.96F) / 20.0F, netHeadYaw);
        } else {
            STANDBY.apply(model, (ageInTicks % 20.0F) / 20.0F, netHeadYaw);
        }
    }
}