package com.bmt.kaleidoscope_twilight.entity;

import com.bmt.kaleidoscope_twilight.init.KTEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EarthquakeEntity extends Entity {

    private static final double MAX_RADIUS = 50.0D;
    private static final double RADIUS_INCREMENT = 0.5D;
    private static final double LAUNCH_VELOCITY_Y = 0.04D;
    private static final double HORIZONTAL_RANDOM = 0.0D;

    @Nullable
    private LivingEntity owner;

    public EarthquakeEntity(EntityType<? extends EarthquakeEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.noPhysics = true;
        this.setInvulnerable(true);
    }

    public static void summon(@Nullable LivingEntity owner, Vec3 pos, Level level, int duration) {
        if (level.isClientSide) return;
        EarthquakeEntity quake = KTEntities.EARTHQUAKE.get().create(level);
        if (quake == null) return;
        quake.owner = owner;
        quake.setPos(pos);
        int ticks = Math.min(duration, (int)(MAX_RADIUS / RADIUS_INCREMENT));
        quake.entityData.set(DATA_LIFETIME, ticks);
        level.addFreshEntity(quake);
    }

    private static final EntityDataAccessor<Integer> DATA_LIFETIME =
            SynchedEntityData.defineId(EarthquakeEntity.class, EntityDataSerializers.INT);

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        builder.define(DATA_LIFETIME, 0);
    }

    @Override
    public void tick() {
        super.tick();

        int lifeTicks = this.entityData.get(DATA_LIFETIME);
        if (lifeTicks <= 0) {
            this.discard();
            return;
        }

        if (!this.level().isClientSide) {
            int totalTicks = this.tickCount;
            double currentRadius = Math.min(totalTicks * RADIUS_INCREMENT, MAX_RADIUS);
            double previousRadius = Math.max(0, (totalTicks - 1) * RADIUS_INCREMENT);

            this.raiseCircleBlocks(previousRadius, currentRadius);

            this.entityData.set(DATA_LIFETIME, lifeTicks - 1);

            if (this.tickCount == 1) {
                this.playSound(SoundEvents.GENERIC_EXPLODE.value(), 1.2F, 0.9F);
            }
        }
    }

    private void raiseCircleBlocks(double innerRadius, double outerRadius) {
        Level world = this.level();
        Vec3 center = this.position();
        boolean canGrief = world.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);

        int minX = (int)Math.floor(center.x - outerRadius);
        int maxX = (int)Math.ceil(center.x + outerRadius);
        int minZ = (int)Math.floor(center.z - outerRadius);
        int maxZ = (int)Math.ceil(center.z + outerRadius);

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                double dx = x + 0.5 - center.x;
                double dz = z + 0.5 - center.z;
                double distSq = dx * dx + dz * dz;
                if (distSq < innerRadius * innerRadius || distSq >= outerRadius * outerRadius) {
                    continue;
                }

                BlockPos ground = findFirstNonAirBelow(world, new BlockPos(x, (int) center.y, z));
                if (ground == null) continue;

                BlockState state = world.getBlockState(ground);
                if (state.isAir()
                        || state.is(Blocks.BEDROCK)
                        || state.getDestroySpeed(world, ground) < 0) {
                    continue;
                }

                double spawnX = ground.getX() + 0.5;
                double spawnY = ground.getY() + 1.0;
                double spawnZ = ground.getZ() + 0.5;

                Vec3 velocity = new Vec3(
                        (this.random.nextDouble() - 0.5) * HORIZONTAL_RANDOM,
                        LAUNCH_VELOCITY_Y,
                        (this.random.nextDouble() - 0.5) * HORIZONTAL_RANDOM
                );

                int flightTime = 5 + this.random.nextInt(3);
                EruptingBlockEntity block = new EruptingBlockEntity(
                        world,
                        new Vec3(spawnX, spawnY, spawnZ),
                        velocity,
                        state,
                        flightTime,
                        this.owner
                );
                world.addFreshEntity(block);

                if (canGrief) {
                    world.setBlock(ground, Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }
    }

    @Nullable
    private BlockPos findFirstNonAirBelow(Level world, BlockPos start) {
        BlockPos.MutableBlockPos mutable = start.mutable();
        for (int y = start.getY(); y >= world.getMinBuildHeight(); y--) {
            mutable.setY(y);
            if (!world.getBlockState(mutable).isAir()) {
                return mutable.immutable();
            }
        }
        return null;
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {
        this.entityData.set(DATA_LIFETIME, tag.getInt("Lifetime"));
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {
        tag.putInt("Lifetime", this.entityData.get(DATA_LIFETIME));
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity se) {
        return new ClientboundAddEntityPacket(this, se);
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }
}