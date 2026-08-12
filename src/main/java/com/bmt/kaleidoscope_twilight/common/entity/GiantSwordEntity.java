package com.bmt.kaleidoscope_twilight.common.entity;

import com.bmt.kaleidoscope_twilight.init.KTEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class GiantSwordEntity extends Entity {

    private static final double FALL_SPEED = 2.0D;
    private static final double SPAWN_HEIGHT = 25.0D;
    private static final int HOVER_TICKS = 30;
    private static final double IMPACT_RADIUS = 2.5D;
    private static final int MAX_LIFE = 200;

    private static final EntityDataAccessor<ItemStack> DATA_ITEM =
            SynchedEntityData.defineId(GiantSwordEntity.class, EntityDataSerializers.ITEM_STACK);

    @Nullable
    private LivingEntity owner;

    public GiantSwordEntity(EntityType<? extends GiantSwordEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.setInvulnerable(true);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        builder.define(DATA_ITEM, new ItemStack(Items.NETHERITE_SWORD));
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

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            if (this.tickCount < HOVER_TICKS) {
                BlockPos groundPos = this.findGroundPos();
                for (int i = 0; i < 4; i++) {
                    double angle = (this.tickCount * 0.3D + i * Math.PI / 2.0D) % (Math.PI * 2.0D);
                    this.level().addParticle(ParticleTypes.CRIT,
                            groundPos.getX() + 0.5D + Math.cos(angle) * IMPACT_RADIUS,
                            groundPos.getY() + 1.15D,
                            groundPos.getZ() + 0.5D + Math.sin(angle) * IMPACT_RADIUS,
                            0.0D, 0.05D, 0.0D);
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    this.level().addParticle(ParticleTypes.SWEEP_ATTACK,
                            this.getX() + (this.random.nextDouble() - 0.5D) * 1.5D,
                            this.getY() + this.random.nextDouble() * 2.0D,
                            this.getZ() + (this.random.nextDouble() - 0.5D) * 1.5D,
                            0.0D, 0.0D, 0.0D);
                }
            }
            return;
        }

        if (this.tickCount > MAX_LIFE) {
            this.discard();
            return;
        }

        if (this.tickCount < HOVER_TICKS) {
            return;
        }

        double newY = this.getY() - FALL_SPEED;
        this.setPos(this.getX(), newY, this.getZ());

        boolean hitGround = this.isGround(BlockPos.containing(this.getX(), newY, this.getZ()));
        boolean hitTarget = false;
        if (this.owner instanceof Mob mob && mob.getTarget() != null && mob.getTarget().isAlive()) {
            LivingEntity target = mob.getTarget();
            double dx = target.getX() - this.getX();
            double dz = target.getZ() - this.getZ();
            hitTarget = dx * dx + dz * dz < 2.25D
                    && newY <= target.getY() + target.getBbHeight() * 0.5D;
        }

        if (hitGround || hitTarget || newY <= this.level().getMinBuildHeight()) {
            this.impact();
            this.discard();
        }
    }

    private BlockPos findGroundPos() {
        BlockPos.MutableBlockPos pos = BlockPos.containing(this.getX(), this.getY(), this.getZ()).mutable();
        while (pos.getY() > this.level().getMinBuildHeight() && !this.isGround(pos)) {
            pos.move(0, -1, 0);
        }
        return pos;
    }

    private boolean isGround(BlockPos pos) {
        BlockState state = this.level().getBlockState(pos);
        return !state.getCollisionShape(this.level(), pos).isEmpty() || !state.getFluidState().isEmpty();
    }

    private void impact() {
        this.playSound(SoundEvents.ANVIL_LAND, 1.0F, 0.7F);
        this.playSound(SoundEvents.GENERIC_EXPLODE.value(), 0.6F, 1.2F);

        EarthquakeEntity.summon(this.owner, this.position(), this.level(), 40);

        for (int i = 0; i < 40; i++) {
            double angle = this.random.nextDouble() * Math.PI * 2.0D;
            double dist = this.random.nextDouble() * IMPACT_RADIUS;
            this.level().addParticle(ParticleTypes.POOF,
                    this.getX() + Math.cos(angle) * dist,
                    this.getY() + 0.2D,
                    this.getZ() + Math.sin(angle) * dist,
                    Math.cos(angle) * 0.3D, 0.2D + this.random.nextDouble() * 0.2D, Math.sin(angle) * 0.3D);
        }

        if (this.owner == null) return;

        AABB box = new AABB(
                this.getX() - IMPACT_RADIUS, this.getY() - 1.0D, this.getZ() - IMPACT_RADIUS,
                this.getX() + IMPACT_RADIUS, this.getY() + 3.0D, this.getZ() + IMPACT_RADIUS);
        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, box,
                e -> e.isAlive() && e != this.owner);

        for (LivingEntity target : targets) {
            double dx = target.getX() - this.getX();
            double dz = target.getZ() - this.getZ();
            if (dx * dx + dz * dz > IMPACT_RADIUS * IMPACT_RADIUS) continue;

            target.invulnerableTime = 0;
            if (this.owner instanceof Player player) {
                target.setLastHurtByPlayer(player);
            }

            DamageSource source = this.damageSources().indirectMagic(this, this.owner);
            target.setHealth(0);
            target.die(source);
        }
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
                        .orElse(new ItemStack(Items.NETHERITE_SWORD)));
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
        ItemStack item = this.getItem();
        if (!item.isEmpty()) {
            CompoundTag itemTag = new CompoundTag();
            item.save(this.registryAccess(), itemTag);
            tag.put("Item", itemTag);
        }
    }

    public static void summon(Level level, LivingEntity target, @Nullable LivingEntity owner, ItemStack sword) {
        if (level.isClientSide) return;

        GiantSwordEntity giantSword = KTEntities.GIANT_SWORD.get().create(level);
        if (giantSword == null) return;

        giantSword.setOwner(owner);
        giantSword.setItem(sword.isEmpty() ? new ItemStack(Items.NETHERITE_SWORD) : sword);

        Vec3 targetPos = target.position();
        giantSword.setPos(targetPos.x, targetPos.y + SPAWN_HEIGHT, targetPos.z);
        level.addFreshEntity(giantSword);
    }
}