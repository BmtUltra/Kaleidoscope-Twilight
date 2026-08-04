package com.bmt.kaleidoscope_twilight.entity.boss;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.entity.expertise.FireballBombardExpertise;
import com.bmt.kaleidoscope_twilight.entity.expertise.FlashStrikeExpertise;
import com.bmt.kaleidoscope_twilight.entity.expertise.AbstractSunflowerExpertise;
import com.bmt.kaleidoscope_twilight.entity.expertise.SwordBombardExpertise;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import twilightforest.entity.boss.BaseTFBoss;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.init.TFBlocks;

import java.util.List;

public class UmbralSunflower extends BaseTFBoss {

    public static final ResourceKey<Structure> KITCHEN_STRUCTURE =
            ResourceKey.create(Registries.STRUCTURE, KaleidoscopeTwilight.id("1145"));
    private static final EntityDataAccessor<Integer> DATA_STAND_ANIM_TIME =
            SynchedEntityData.defineId(UmbralSunflower.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_SWORD_ANIM_TIME =
            SynchedEntityData.defineId(UmbralSunflower.class, EntityDataSerializers.INT);

    private static final int STAND_ANIMATION_DURATION = 48;
    private boolean spawnAnimPlayed = false;
    private int deflectAnimationTime = 0;
    private int bounceAnimationTime = 0;
    private float deflectYaw;
    private boolean hasDeflectYaw = false;
    private float animLockYaw;
    private int deathAnimationTime = 0;
    private static final int DEATH_ANIMATION_DURATION = 55;
    private int outOfCombatTicks = 0;
    private static final int OUT_OF_COMBAT_THRESHOLD = 200;
    private static final int HEAL_INTERVAL = 20;
    private static final float HEAL_PERCENT = 0.02F;

    private static final EntityDataAccessor<Integer> DATA_FIREBALL_ANIM_TIME =
            SynchedEntityData.defineId(UmbralSunflower.class, EntityDataSerializers.INT);

    private int attackAnimationTime = 0;
    private static final int ATTACK_ANIMATION_DURATION = 10;

    private static final EntityDataAccessor<Integer> DATA_FLASH_ANIM_TIME =
            SynchedEntityData.defineId(UmbralSunflower.class, EntityDataSerializers.INT);

    private final List<AbstractSunflowerExpertise> skills = List.of(
            new FireballBombardExpertise(DATA_FIREBALL_ANIM_TIME),
            new FlashStrikeExpertise(DATA_FLASH_ANIM_TIME),
            new SwordBombardExpertise(DATA_SWORD_ANIM_TIME));

    private static final EntityDataAccessor<Boolean> DATA_PHASE_TWO =
            SynchedEntityData.defineId(UmbralSunflower.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_RESURRECTION_ANIM_TIME =
            SynchedEntityData.defineId(UmbralSunflower.class, EntityDataSerializers.INT);
    private static final int RESURRECTION_ANIMATION_DURATION = 56;
    private static final int PHASE_ONE_BAR_COLOR = 0x4CBB17;
    private static final int PHASE_TWO_BAR_COLOR = 0xFF8C00;
    private static final ResourceLocation PHASE_TWO_SPEED_ID = KaleidoscopeTwilight.id("phase_two_speed");
    private int damageImmunityTicks = 0;

    public UmbralSunflower(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 500;
    }

    @Override
    public @NotNull ResourceKey<Structure> getHomeStructure() {
        return KITCHEN_STRUCTURE;
    }

    @Override
    public @NotNull Block getDeathContainer(@NotNull RandomSource random) {
        return TFBlocks.DARK_CHEST.get();
    }

    @Override
    public @NotNull Block getBossSpawner() {
        return Blocks.SPAWNER;
    }

    @Override
    protected boolean shouldCreateSpawner() {
        return false;
    }

    @Override
    public int getBossBarColor() {
        return this.isPhaseTwo() ? PHASE_TWO_BAR_COLOR : PHASE_ONE_BAR_COLOR;
    }

    @Override
    public int getHomeRadius() {
        return 32;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 500.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.FOLLOW_RANGE, 60.0D)
                .add(Attributes.ARMOR, 6.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 9999.0D);
    }

    @Override
    public void knockback(double strength, double x, double z) {
    }

    @Override
    public boolean canBeAffected(@NotNull MobEffectInstance effectInstance) {
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_STAND_ANIM_TIME, 0);
        builder.define(DATA_FIREBALL_ANIM_TIME, 0);
        builder.define(DATA_FLASH_ANIM_TIME, 0);
        builder.define(DATA_PHASE_TWO, false);
        builder.define(DATA_RESURRECTION_ANIM_TIME, 0);
        builder.define(DATA_SWORD_ANIM_TIME, 0);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("SpawnAnimPlayed", this.spawnAnimPlayed);
        this.skills.forEach(skill -> skill.save(tag));
        tag.putBoolean("PhaseTwo", this.isPhaseTwo());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.spawnAnimPlayed = tag.getBoolean("SpawnAnimPlayed");
        this.skills.forEach(skill -> skill.load(tag));
        this.entityData.set(DATA_PHASE_TWO, tag.getBoolean("PhaseTwo"));
        if (this.isPhaseTwo()) {
            this.getBossBar().updateStyle(PHASE_TWO_BAR_COLOR, this.getBossBarOverlay(), false);
        }
        AttributeInstance speed = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speed != null) {
            speed.removeModifier(PHASE_TWO_SPEED_ID);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SunflowerMeleeGoal());
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (source.getEntity() == this) {
            return false;
        }
        if (source.getDirectEntity() instanceof HydraMortar mortar && mortar.getOwner() == this) {
            return false;
        }

        if (this.getResurrectionAnimationTime() > 0 && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }

        if (this.isPhaseTwo()
                && (source.is(DamageTypeTags.IS_EXPLOSION) || source.is(DamageTypeTags.IS_FIRE))) {
            return false;
        }

        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }

        if (source.is(DamageTypeTags.IS_FALL)) {
            return false;
        }

        if (source.is(DamageTypeTags.IS_DROWNING)) {
            return false;
        }

        if (source.is(DamageTypeTags.IS_FREEZING)) {
            return false;
        }

        if (source.is(DamageTypeTags.IS_LIGHTNING)) {
            return false;
        }

        if (amount >= Float.MAX_VALUE / 2) {
            return false;
        }

        if (this.damageImmunityTicks > 0
                && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }

        amount = Math.min(amount, 10.0F);

        if (!this.level().isClientSide() && !this.isPhaseTwo()
                && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)
                && amount >= this.getHealth()
                && !(this.invulnerableTime > 10 && amount <= this.lastHurt)) {
            this.startResurrection();
            return false;
        }

        if (this.isDeadOrDying()
                || this.getStandAnimationTime() > 0
                || this.getFireballAnimationTime() > 0
                || this.getFlashAnimationTime() > 0
                || this.getSwordAnimationTime() > 0) {
            return this.hurtWithPhaseImmunity(source, amount);
        }

        if (source.is(DamageTypeTags.IS_PROJECTILE) && !this.level().isClientSide()) {
            this.deflectAnimationTime = 15;

            Entity attacker = source.getEntity();
            Vec3 facingPos = attacker != null ? attacker.position() : source.getSourcePosition();
            if (facingPos != null) {
                double dx = facingPos.x - this.getX();
                double dz = facingPos.z - this.getZ();
                if (dx * dx + dz * dz > 1.0E-4) {
                    this.deflectYaw = (float) (Mth.atan2(dz, dx) * Mth.RAD_TO_DEG) - 90.0F;
                    this.hasDeflectYaw = true;
                    this.applyDeflectFacing();
                }
            }

            this.level().broadcastEntityEvent(this, (byte) 64);
            return false;
        }

        if (!this.level().isClientSide()
                && source.getDirectEntity() instanceof LivingEntity attacker
                && this.random.nextFloat() < 0.35F) {
            this.bounceAnimationTime = 10;
            this.animLockYaw = this.getYRot();
            this.level().broadcastEntityEvent(this, (byte) 65);

            this.playSound(SoundEvents.ANVIL_LAND, 1.0F, 1.2F);

            attacker.knockback(1.5D,
                    this.getX() - attacker.getX(),
                    this.getZ() - attacker.getZ());
            attacker.hurtMarked = true;
            return false;
        }
        return this.hurtWithPhaseImmunity(source, amount);
    }

    private boolean hurtWithPhaseImmunity(DamageSource source, float amount) {
        boolean hurt = super.hurt(source, amount);
        if (hurt && !this.level().isClientSide()) {
            this.damageImmunityTicks = this.isPhaseTwo() ? 20 : 10;
        }
        return hurt;
    }

    private void applyDeflectFacing() {
        this.setYRot(this.deflectYaw);
        this.yBodyRot = this.deflectYaw;
        this.yHeadRot = this.deflectYaw;
    }

    private void applyAnimYawLock() {
        this.setYRot(this.animLockYaw);
        this.yBodyRot = this.animLockYaw;
        this.yHeadRot = this.animLockYaw;
    }

    @Override
    public boolean isWithinMeleeAttackRange(@NotNull LivingEntity target) {
        return this.getBoundingBox().inflate(2.5D, 1.0D, 2.5D).intersects(target.getBoundingBox());
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity target) {
        boolean hit = super.doHurtTarget(target);
        if (hit && !this.level().isClientSide()) {
            if (target instanceof LivingEntity living && living.isAlive()) {
                living.invulnerableTime = 0;
                living.hurt(this.damageSources().mobAttack(this), living.getMaxHealth() * 0.1F);
            }
            this.attackAnimationTime = ATTACK_ANIMATION_DURATION;
            this.animLockYaw = this.getYRot();
            this.level().broadcastEntityEvent(this, (byte) 66);
        }
        return hit;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 64) {
            this.deflectAnimationTime = 12;
        } else if (id == 65) {
            this.bounceAnimationTime = 10;
        } else if (id == 66) {
            this.attackAnimationTime = ATTACK_ANIMATION_DURATION;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public void handleDamageEvent(@NotNull DamageSource source) {
        super.handleDamageEvent(source);
        this.hurtTime = 0;
        this.hurtDuration = 0;
    }

    @Override
    protected void tickDeath() {
        ++this.deathAnimationTime;
        this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
        if (this.deathAnimationTime >= DEATH_ANIMATION_DURATION
                && !this.level().isClientSide() && !this.isRemoved()) {
            this.level().broadcastEntityEvent(this, (byte) 60);
            this.remove(RemovalReason.KILLED);
        }
    }

    @Override
    public boolean isDeathAnimationFinished() {
        return this.deathAnimationTime >= DEATH_ANIMATION_DURATION;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide()) {
            if (!this.spawnAnimPlayed) {
                this.spawnAnimPlayed = true;
                this.entityData.set(DATA_STAND_ANIM_TIME, STAND_ANIMATION_DURATION);
            }
            int standTime = this.entityData.get(DATA_STAND_ANIM_TIME);
            if (standTime > 0) {
                this.entityData.set(DATA_STAND_ANIM_TIME, standTime - 1);
            }
            int resTime = this.entityData.get(DATA_RESURRECTION_ANIM_TIME);
            if (resTime > 0) {
                this.entityData.set(DATA_RESURRECTION_ANIM_TIME, resTime - 1);
            }
        }

        if (this.getStandAnimationTime() > 0) {
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
            this.getNavigation().stop();
        }

        if (this.getResurrectionAnimationTime() > 0) {
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
            this.getNavigation().stop();
        }

        if (this.deflectAnimationTime > 0) {
            this.deflectAnimationTime--;
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
            this.getNavigation().stop();
            if (!this.level().isClientSide() && this.hasDeflectYaw) {
                this.applyDeflectFacing();
            }
        } else {
            this.hasDeflectYaw = false;
        }

        if (this.bounceAnimationTime > 0) {
            this.bounceAnimationTime--;
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
            this.getNavigation().stop();
            if (!this.level().isClientSide()) {
                this.applyAnimYawLock();
            }
        }

        if (this.attackAnimationTime > 0) {
            this.attackAnimationTime--;
            if (!this.level().isClientSide()) {
                this.applyAnimYawLock();
            }
        }

        if (this.damageImmunityTicks > 0) {
            this.damageImmunityTicks--;
        }

        if (!this.level().isClientSide()) {
            for (AbstractSunflowerExpertise skill : this.skills) {
                skill.serverTick(this);
            }
        }

        if (!this.level().isClientSide()) {
            LivingEntity target = this.getTarget();
            if (target == null || !target.isAlive()) {
                this.outOfCombatTicks++;
            } else {
                this.outOfCombatTicks = 0;
            }

            if (this.outOfCombatTicks >= OUT_OF_COMBAT_THRESHOLD
                    && this.getHealth() < this.getMaxHealth()
                    && this.outOfCombatTicks % HEAL_INTERVAL == 0) {
                this.heal(this.getMaxHealth() * HEAL_PERCENT);
            }
        }
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState state) {
    }

    public int getDeflectAnimationTime() {
        return this.deflectAnimationTime;
    }

    public int getBounceAnimationTime() {
        return this.bounceAnimationTime;
    }

    public int getAttackAnimationTime() {
        return this.attackAnimationTime;
    }

    public int getDeathAnimationTime() {
        return this.deathAnimationTime;
    }

    public int getStandAnimationTime() {
        return this.entityData.get(DATA_STAND_ANIM_TIME);
    }

    public boolean isPhaseTwo() {
        return this.entityData.get(DATA_PHASE_TWO);
    }

    public int getResurrectionAnimationTime() {
        return this.entityData.get(DATA_RESURRECTION_ANIM_TIME);
    }

    public int getFireballAnimationTime() {
        return this.entityData.get(DATA_FIREBALL_ANIM_TIME);
    }

    public boolean canStartSkill() {
        return !this.isDeadOrDying()
                && this.getStandAnimationTime() == 0
                && this.getResurrectionAnimationTime() == 0
                && this.deflectAnimationTime == 0
                && this.bounceAnimationTime == 0
                && this.skills.stream().noneMatch(skill -> skill.isActive(this));
    }

    public float getFireballAnimPhase() {
        int ft = this.entityData.get(DATA_FIREBALL_ANIM_TIME);
        if (ft <= 0) return 0.0F;
        int elapsed = FireballBombardExpertise.DURATION - ft;
        if (elapsed < FireballBombardExpertise.RISE_TICKS) {
            return (elapsed / (float) FireballBombardExpertise.RISE_TICKS) * 1.75F;
        }
        if (ft > 15) {
            return 1.75F;
        }
        return 1.75F + (1.0F - ft / 15.0F) * (1.8333F - 1.75F);
    }

    public int getFlashAnimationTime() {
        return this.entityData.get(DATA_FLASH_ANIM_TIME);
    }

    public float getFlashAnimPhase() {
        int ft = this.entityData.get(DATA_FLASH_ANIM_TIME);
        if (ft <= 0) return 0.0F;
        int elapsed = FlashStrikeExpertise.DURATION - ft;
        return Math.min(elapsed / 20.0F, 0.7168F);
    }

    public int getSwordAnimationTime() {
        return this.entityData.get(DATA_SWORD_ANIM_TIME);
    }

    public float getSwordAnimPhase() {
        int ft = this.entityData.get(DATA_SWORD_ANIM_TIME);
        if (ft <= 0) return 0.0F;
        int elapsed = SwordBombardExpertise.DURATION - ft;
        if (elapsed < SwordBombardExpertise.RISE_TICKS) {
            return (elapsed / (float) SwordBombardExpertise.RISE_TICKS) * 1.75F;
        }
        if (ft > 15) {
            return 1.75F;
        }
        return 1.75F + (1.0F - ft / 15.0F) * (1.8333F - 1.75F);
    }

    private void startResurrection() {
        this.entityData.set(DATA_PHASE_TWO, true);
        this.setHealth(this.getMaxHealth());
        this.entityData.set(DATA_RESURRECTION_ANIM_TIME, RESURRECTION_ANIMATION_DURATION);

        this.deflectAnimationTime = 0;
        this.hasDeflectYaw = false;
        this.bounceAnimationTime = 0;
        this.attackAnimationTime = 0;
        this.skills.forEach(skill -> skill.interrupt(this));
        this.getNavigation().stop();

        this.getBossBar().updateStyle(PHASE_TWO_BAR_COLOR, this.getBossBarOverlay(), false);

        this.level().broadcastEntityEvent(this, (byte) 35);
        this.playSound(SoundEvents.WITHER_SPAWN, 1.0F, 1.3F);
    }

    private class SunflowerMeleeGoal extends MeleeAttackGoal {
        private int attackCooldown;

        SunflowerMeleeGoal() {
            super(UmbralSunflower.this, 1.0D, true);
        }

        @Override
        public void start() {
            super.start();
            this.attackCooldown = 0;
        }

        @Override
        public void tick() {
            super.tick();
            if (this.attackCooldown > 0) this.attackCooldown--;
        }

        @Override
        protected void checkAndPerformAttack(@NotNull LivingEntity enemy) {
            if (this.attackCooldown <= 0
                    && UmbralSunflower.this.getResurrectionAnimationTime() == 0
                    && this.mob.isWithinMeleeAttackRange(enemy)) {
                this.attackCooldown = this.adjustedTickDelay(20);
                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(enemy);
            }
        }
    }
}