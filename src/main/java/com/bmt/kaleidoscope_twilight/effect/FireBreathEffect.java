package com.bmt.kaleidoscope_twilight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.init.TFEntities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FireBreathEffect extends MobEffect {

    private static final Map<UUID, Long> LAST_TICK = new HashMap<>();
    private static final int COOLDOWN_TICKS = 10;

    public FireBreathEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player &&
                entity.getLastHurtMob() != null &&
                entity.getLastHurtMobTimestamp() == entity.tickCount - 1) {

            long currentTick = player.level().getGameTime();
            UUID playerId = player.getUUID();

            Long lastTick = LAST_TICK.get(playerId);
            if (lastTick != null && currentTick - lastTick < COOLDOWN_TICKS) {
                return true;
            }

            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.isEmpty()) {
                shootFireball(player, amplifier);
                LAST_TICK.put(playerId, currentTick);
            }
        }
        return true;
    }

    private void shootFireball(Player player, int amplifier) {
        Level level = player.level();

        if (!level.isClientSide()) {
            HydraMortar fireball = new HydraMortar(TFEntities.HYDRA_MORTAR.get(), level);

            double x = player.getX();
            double y = player.getY() + player.getEyeHeight() - 0.5;
            double z = player.getZ();

            float yRot = player.getYRot();
            float xRot = player.getXRot();
            float speed = 0.5F + (amplifier * 0.1F);
            float xRotRad = xRot * ((float) Math.PI / 180F);
            float yRotRad = -yRot * ((float) Math.PI / 180F);

            double motionX = Math.sin(yRotRad) * Math.cos(xRotRad);
            double motionY = -Math.sin(xRotRad);
            double motionZ = Math.cos(yRotRad) * Math.cos(xRotRad);

            fireball.setPos(x + motionX, y, z + motionZ);

            fireball.setOwner(player);

            fireball.shoot(motionX, motionY, motionZ, speed, 1.0F);

            level.addFreshEntity(fireball);
        }
    }
}