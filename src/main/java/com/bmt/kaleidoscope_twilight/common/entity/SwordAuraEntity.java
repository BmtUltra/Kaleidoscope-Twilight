package com.bmt.kaleidoscope_twilight.common.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SwordAuraEntity extends ThrowableItemProjectile {

    private static final EntityDataAccessor<Boolean> DATA_PLAYER_AURA =
            SynchedEntityData.defineId(SwordAuraEntity.class, EntityDataSerializers.BOOLEAN);

    private static final float BASE_DAMAGE = 5.0F;
    private static final float MAX_HEALTH_PERCENT = 0.15F;
    private static final int MAX_LIFE_TICKS = 20;

    private int life = 0;
    private float customDamage = -1.0F;
    private final List<UUID> attackedEntityUUID = new ArrayList<>();

    public SwordAuraEntity(EntityType<? extends SwordAuraEntity> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        this.noPhysics = true;
    }

    public void setDamage(float damage) {
        this.customDamage = damage;
    }

    public void setPlayerAura(boolean playerAura) {
        this.entityData.set(DATA_PLAYER_AURA, playerAura);
    }

    public boolean isPlayerAura() {
        return this.entityData.get(DATA_PLAYER_AURA);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_PLAYER_AURA, false);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Items.AIR;
    }

    @Override
    public void tick() {
        this.setNoGravity(true);
        super.tick();

        Vec3 vel = this.getDeltaMovement();
        double horizDist = Math.sqrt(vel.x * vel.x + vel.z * vel.z);
        if (horizDist > 1.0E-7) {
            float targetYaw = (float) Mth.atan2(vel.x, vel.z) * Mth.RAD_TO_DEG;
            this.setYRot(targetYaw);
            if (!this.isPlayerAura()) {
                this.setXRot(0.0F);
            }
        }

        if (vel.lengthSqr() < 1.0E-4) {
            this.discard();
            return;
        }

        if (life >= MAX_LIFE_TICKS) {
            this.discard();
            return;
        }
        life++;

        if (!this.level().isClientSide) {
            this.tryHitSomething();
        }

        if (this.level().isClientSide) {
            for (int i = 0; i < 3; i++) {
                this.level().addParticle(ParticleTypes.FLAME,
                        this.getRandomX(0.4D), this.getRandomY() + 0.2D, this.getRandomZ(0.4D),
                        0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void tryHitSomething() {
        LivingEntity owner = this.getOwner() instanceof LivingEntity living ? living : null;
        if (owner == null) return;

        AABB box = this.getBoundingBox().inflate(1.0D);
        List<Entity> hits = this.level().getEntities(this, box,
                e -> e instanceof LivingEntity
                        && e.isAlive()
                        && e != owner
                        && !attackedEntityUUID.contains(e.getUUID()));

        for (Entity entity : hits) {
            if (entity instanceof LivingEntity living) {
                living.invulnerableTime = 0;
                float damage = this.customDamage > 0
                        ? this.customDamage
                        : BASE_DAMAGE + living.getMaxHealth() * MAX_HEALTH_PERCENT;
                DamageSource damageSource = this.damageSources().indirectMagic(this, owner);

                ItemStack offhand = living.getOffhandItem();
                boolean hadShield = !offhand.isEmpty()
                        && offhand.getItem() == Items.SHIELD
                        && living.getUseItem() == offhand;
                if (hadShield) {
                    living.stopUsingItem();
                }

                boolean hurt = living.hurt(damageSource, damage);
                if (hurt) {
                    attackedEntityUUID.add(living.getUUID());
                    living.setRemainingFireTicks(6 * 20);
                }
            }
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
    }
}
