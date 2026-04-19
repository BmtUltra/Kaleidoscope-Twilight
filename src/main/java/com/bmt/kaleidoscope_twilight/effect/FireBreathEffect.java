package com.bmt.kaleidoscope_twilight.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player &&
                entity.getLastHurtMob() != null &&
                entity.getLastHurtMobTimestamp() == entity.tickCount - 1) {

            long currentTick = player.level().getGameTime();
            UUID playerId = player.getUUID();

            Long lastTick = LAST_TICK.get(playerId);
            if (lastTick != null && currentTick - lastTick < COOLDOWN_TICKS) {
                return;
            }

            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.isEmpty()) {
                shootFireball(player, amplifier);
                LAST_TICK.put(playerId, currentTick);
            }
        }
    }

    private void shootFireball(Player player, int amplifier) {
        Level level = player.level();

        if (!level.isClientSide()) {
            HydraMortar fireball = new HydraMortar(TFEntities.HYDRA_MORTAR.get(), level);

            Vec3 lookVec = player.getLookAngle();

            double x = player.getX() + lookVec.x * 0.5;
            double y = player.getY() + player.getEyeHeight() - 0.5;
            double z = player.getZ() + lookVec.z * 0.5;

            fireball.setPos(x, y, z);
            fireball.setOwner(player);

            float speed = 0.8F + (amplifier * 0.2F);

            fireball.shoot(lookVec.x, lookVec.y, lookVec.z, speed, 0.0F);

            level.addFreshEntity(fireball);
        }
    }
}