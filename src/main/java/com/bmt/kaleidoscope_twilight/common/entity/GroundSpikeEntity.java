package com.bmt.kaleidoscope_twilight.common.entity;

import com.bmt.kaleidoscope_twilight.init.KTEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroundSpikeEntity extends Entity {

    public static final int RISE_TICKS = 8;
    public static final int ACTIVE_TICKS = 20;
    public static final int SINK_TICKS = 8;
    private static final int MAX_LIFE = RISE_TICKS + ACTIVE_TICKS + SINK_TICKS;
    private static final float BASE_DAMAGE = 8.0F;
    private static final float MAX_HEALTH_PERCENT = 0.1F;
    private static final double SPIKE_SPACING = 0.85D;

    private static final EntityDataAccessor<ItemStack> DATA_ITEM =
            SynchedEntityData.defineId(GroundSpikeEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Float> DATA_RADIUS =
            SynchedEntityData.defineId(GroundSpikeEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_DELAY =
            SynchedEntityData.defineId(GroundSpikeEntity.class, EntityDataSerializers.INT);

    @Nullable
    private LivingEntity owner;
    private final List<UUID> attackedEntityUUID = new ArrayList<>();
    @Nullable
    private List<Spike> spikes;

    public GroundSpikeEntity(EntityType<? extends GroundSpikeEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.setInvulnerable(true);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        builder.define(DATA_ITEM, new ItemStack(Items.IRON_SWORD));
        builder.define(DATA_RADIUS, 2.5F);
        builder.define(DATA_DELAY, 0);
    }

    public void setOwner(@Nullable LivingEntity owner) {
        this.owner = owner;
    }

    public void setItem(ItemStack stack) {
        this.entityData.set(DATA_ITEM, stack.copy());
    }

    public ItemStack getItem() {
        return this.entityData.get(DATA_ITEM);
    }

    public void setRingRadius(float radius) {
        this.entityData.set(DATA_RADIUS, radius);
        this.spikes = null;
    }

    public float getRingRadius() {
        return this.entityData.get(DATA_RADIUS);
    }

    public void setDelay(int delayTicks) {
        this.entityData.set(DATA_DELAY, delayTicks);
    }

    public int getDelay() {
        return this.entityData.get(DATA_DELAY);
    }

    public double getDamageRadius() {
        return this.getRingRadius() + 0.8D;
    }

    public List<Spike> getSpikes() {
        if (this.spikes == null) {
            this.spikes = this.buildSpikes();
        }
        return this.spikes;
    }

    private List<Spike> buildSpikes() {
        RandomSource rand = RandomSource.create(this.getId() * 31L + 7L);
        float radius = this.getRingRadius();
        List<Spike> list = new ArrayList<>();

        int count = Math.max(5, Mth.ceil(radius * 2.0D * Math.PI / SPIKE_SPACING));
        float angleOffset = rand.nextFloat() * 360.0F;
        for (int i = 0; i < count; i++) {
            float angle = (angleOffset + i * 360.0F / count + (rand.nextFloat() - 0.5F) * 12.0F) * Mth.DEG_TO_RAD;
            float dist = radius * (0.85F + rand.nextFloat() * 0.3F);
            float sx = Mth.cos(angle) * dist;
            float sz = Mth.sin(angle) * dist;
            list.add(new Spike(
                    sx,
                    sz,
                    (float) this.groundOffset(sx, sz),
                    rand.nextFloat() * 360.0F,
                    (rand.nextFloat() - 0.5F) * 24.0F,
                    rand.nextFloat() * 0.3F,
                    0.9F + rand.nextFloat() * 0.3F));
        }

        if (radius <= 2.0F) {
            list.add(new Spike(0.0F, 0.0F, (float) this.groundOffset(0.0F, 0.0F),
                    rand.nextFloat() * 360.0F,
                    (rand.nextFloat() - 0.5F) * 16.0F, 0.0F, 1.0F + rand.nextFloat() * 0.2F));
        }

        return list;
    }

    private double groundOffset(double dx, double dz) {
        BlockPos.MutableBlockPos pos = BlockPos.containing(this.getX() + dx, this.getY(), this.getZ() + dz).mutable();
        int minY = this.level().getMinBuildHeight();
        if (!this.isGround(pos)) {
            while (pos.getY() > minY && !this.isGround(pos)) {
                pos.move(0, -1, 0);
            }
            return (pos.getY() + 1) - this.getY();
        } else {
            int upSteps = 0;
            while (this.isGround(pos) && upSteps < 8) {
                pos.move(0, 1, 0);
                upSteps++;
            }
            return pos.getY() - this.getY();
        }
    }

    private boolean isGround(BlockPos pos) {
        BlockState state = this.level().getBlockState(pos);
        return !state.getCollisionShape(this.level(), pos).isEmpty() || !state.getFluidState().isEmpty();
    }

    @Override
    public void tick() {
        super.tick();

        int age = this.tickCount - this.getDelay();

        if (this.tickCount == 1) {
            this.snapToGround();
        }

        if (age <= 0) {
            return;
        }

        if (this.level().isClientSide) {
            if (age <= RISE_TICKS) {
                for (int i = 0; i < 3; i++) {
                    GroundSpikeEntity.Spike spike = this.getSpikes().get(this.random.nextInt(this.getSpikes().size()));
                    this.level().addParticle(ParticleTypes.POOF,
                            this.getX() + spike.x + (this.random.nextDouble() - 0.5D) * 0.5D,
                            this.getY() + spike.groundY + 0.1D,
                            this.getZ() + spike.z + (this.random.nextDouble() - 0.5D) * 0.5D,
                            0.0D, 0.05D, 0.0D);
                }
            }
            return;
        }

        if (age == 1) {
            this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0F, 0.7F + this.random.nextFloat() * 0.4F);
        }

        if (age >= MAX_LIFE) {
            this.discard();
            return;
        }

        if (age >= RISE_TICKS && age <= RISE_TICKS + ACTIVE_TICKS) {
            this.tryHitEntities();
        }
    }

    private void snapToGround() {
        BlockPos pos = this.blockPosition();
        int minY = this.level().getMinBuildHeight();
        while (pos.getY() > minY && !this.isGround(pos.below())) {
            pos = pos.below();
        }
        this.setPos(this.getX(), pos.getY(), this.getZ());
        this.spikes = null;
    }

    private void tryHitEntities() {
        if (this.owner == null) return;
        double damageRadius = this.getDamageRadius();
        AABB box = new AABB(
                this.getX() - damageRadius, this.getY() - 2.0D, this.getZ() - damageRadius,
                this.getX() + damageRadius, this.getY() + 2.5D, this.getZ() + damageRadius);
        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, box,
                e -> e.isAlive()
                        && e != this.owner
                        && !this.attackedEntityUUID.contains(e.getUUID()));

        for (LivingEntity target : targets) {
            double dx = target.getX() - this.getX();
            double dz = target.getZ() - this.getZ();
            if (dx * dx + dz * dz > damageRadius * damageRadius) continue;

            this.attackedEntityUUID.add(target.getUUID());

            target.invulnerableTime = 0;
            if (this.owner instanceof Player player) {
                target.setLastHurtByPlayer(player);
            }

            float damage = BASE_DAMAGE + target.getMaxHealth() * MAX_HEALTH_PERCENT;
            DamageSource source = this.damageSources().indirectMagic(this, this.owner);

            if (target.hurt(source, damage)) {
                target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.4D, 0.0D));
                target.hasImpulse = true;
            }
        }
    }

    public double getHeightOffset(float partialTick) {
        double age = this.tickCount + partialTick - this.getDelay();
        if (age <= 0.0D) {
            return 1.0D;
        }
        double progress;
        if (age <= RISE_TICKS) {
            progress = smoothStep(age / RISE_TICKS);
        } else if (age <= RISE_TICKS + ACTIVE_TICKS) {
            progress = 1.0D;
        } else {
            progress = 1.0D - smoothStep((age - RISE_TICKS - ACTIVE_TICKS) / SINK_TICKS);
        }
        return Mth.clamp(1.0D - progress, 0.0D, 1.0D);
    }

    private static double smoothStep(double t) {
        t = Mth.clamp(t, 0.0D, 1.0D);
        return t * t * (3.0D - 2.0D * t);
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
        this.entityData.set(DATA_ITEM,
                ItemStack.parse(this.registryAccess(), tag.getCompound("Item"))
                        .orElse(new ItemStack(Items.IRON_SWORD)));
        this.entityData.set(DATA_RADIUS, tag.getFloat("RingRadius"));
        this.entityData.set(DATA_DELAY, tag.getInt("Delay"));
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
        ItemStack item = this.getItem();
        if (!item.isEmpty()) {
            CompoundTag itemTag = new CompoundTag();
            item.save(this.registryAccess(), itemTag);
            tag.put("Item", itemTag);
        }
        tag.putFloat("RingRadius", this.getRingRadius());
        tag.putInt("Delay", this.getDelay());
    }

    @Nullable
    public static GroundSpikeEntity spawn(Level level, Vec3 center, @Nullable LivingEntity owner,
                                          ItemStack sword, float ringRadius, int delayTicks) {
        if (level.isClientSide) return null;

        GroundSpikeEntity spike = KTEntities.GROUND_SPIKE.get().create(level);
        if (spike == null) return null;

        spike.setOwner(owner);
        spike.setItem(sword.isEmpty() ? new ItemStack(Items.IRON_SWORD) : sword);
        spike.setRingRadius(ringRadius);
        spike.setDelay(delayTicks);
        spike.setPos(center.x, center.y, center.z);
        level.addFreshEntity(spike);
        return spike;
    }

    public static class Spike {
        public final float x;
        public final float z;
        public final float groundY;
        public final float yawDeg;
        public final float tiltDeg;
        public final float extraDrop;
        public final float scale;

        public Spike(float x, float z, float groundY, float yawDeg, float tiltDeg, float extraDrop, float scale) {
            this.x = x;
            this.z = z;
            this.groundY = groundY;
            this.yawDeg = yawDeg;
            this.tiltDeg = tiltDeg;
            this.extraDrop = extraDrop;
            this.scale = scale;
        }
    }
}
