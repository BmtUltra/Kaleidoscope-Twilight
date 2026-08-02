package com.bmt.kaleidoscope_twilight.client.animation;

import com.bmt.kaleidoscope_twilight.api.IAnimation;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.entity.UmbralSunflower;

public class UmbralSunflowerAnimations {
    private static final IAnimation STANDBY = new StandbyAnimation();
    private static final IAnimation MOVE = new MoveAnimation();
    private static final IAnimation DIE = new DieAnimation();
    private static final IAnimation RESURRECTION = new ResurrectionAnimation();
    private static final IAnimation STAND = new StandAnimation();
    private static final IAnimation FIREBALL = new FireballAnimation();
    private static final IAnimation FLASH = new FlashAnimation();
    private static final IAnimation DEFLECT = new DeflectAnimation();
    private static final IAnimation BOUNCE = new BounceAnimation();
    private static final IAnimation AA = new AaAnimation();

    public static void animate(UmbralSunflowerModel model, UmbralSunflower entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        int deathTime = entity.getDeathAnimationTime();
        if (deathTime > 0) {
            DIE.apply(model, deathTime / 20.0F, netHeadYaw);
            return;
        }

        int resurrectionTime = entity.getResurrectionAnimationTime();
        if (resurrectionTime > 0) {
            RESURRECTION.apply(model, (56 - resurrectionTime) / 20.0F, netHeadYaw);
            return;
        }

        int standTime = entity.getStandAnimationTime();
        if (standTime > 0) {
            STAND.apply(model, (48 - standTime) / 20.0F, netHeadYaw);
            return;
        }

        int fireballTime = entity.getFireballAnimationTime();
        if (fireballTime > 0) {
            FIREBALL.apply(model, entity.getFireballAnimPhase(), netHeadYaw);
            return;
        }

        int swordTime = entity.getSwordAnimationTime();
        if (swordTime > 0) {
            FIREBALL.apply(model, entity.getSwordAnimPhase(), netHeadYaw);
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