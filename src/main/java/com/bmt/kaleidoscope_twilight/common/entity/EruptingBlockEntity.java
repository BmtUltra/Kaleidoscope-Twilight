package com.bmt.kaleidoscope_twilight.common.entity;

import com.bmt.kaleidoscope_twilight.init.KTEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

public class EruptingBlockEntity extends Entity {
    private static final EntityDataAccessor<BlockState> BLOCK_STATE =
            SynchedEntityData.defineId(EruptingBlockEntity.class, EntityDataSerializers.BLOCK_STATE);

    private int flightTime = 15;
    private int maxLife = 200;
    @Nullable
    private LivingEntity owner;

    public EruptingBlockEntity(EntityType<? extends EruptingBlockEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.noPhysics = false;
    }

    public EruptingBlockEntity(Level world, Vec3 pos, Vec3 velocity, BlockState blockState, int flightTime, @Nullable LivingEntity owner) {
        this(KTEntities.ERUPTING_BLOCK.get(), world);
        this.setPos(pos.x, pos.y, pos.z);
        this.setDeltaMovement(velocity);
        this.setBlockState(blockState);
        this.flightTime = flightTime;
        this.owner = owner;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(BLOCK_STATE, Blocks.STONE.defaultBlockState());
    }

    public void setBlockState(BlockState state) {
        this.entityData.set(BLOCK_STATE, state);
    }

    public BlockState getBlockState() {
        return this.entityData.get(BLOCK_STATE);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
            }
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
            return;
        }

        if (this.tickCount >= this.flightTime) {
            this.setNoGravity(false);
        }

        boolean shouldRestore =
                (this.tickCount > this.maxLife && this.onGround())
                        || (this.onGround() && this.tickCount > 20);

        if (shouldRestore) {
            this.placeBlockAtCurrentPosition();
            this.discard();
            return;
        }

        if (this.tickCount > this.maxLife) {
            this.discard();
            return;
        }

        List<LivingEntity> hitList = this.level().getEntitiesOfClass(LivingEntity.class,
                this.getBoundingBox().inflate(0.2));
        for (LivingEntity target : hitList) {
            if (!target.isAlive() || target == this.owner) continue;
            if (target.hurt(this.damageSources().fallingBlock(this), 6.0F)) {
                double dx = target.getX() - this.getX();
                double dz = target.getZ() - this.getZ();
                target.knockback(1.2, dx, dz);
                this.placeBlockAtCurrentPosition();
                this.discard();
                return;
            }
        }

        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
        }
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
    }

    private void placeBlockAtCurrentPosition() {
        Level world = this.level();
        BlockState state = this.getBlockState();
        BlockPos pos = BlockPos.containing(this.getX(), this.getY(), this.getZ());

        if (world.getBlockState(pos).canBeReplaced() && state.canSurvive(world, pos)) {
            world.setBlock(pos, state, Block.UPDATE_ALL);
            SoundType sound = state.getSoundType();
            world.playSound(null, pos, sound.getPlaceSound(), this.getSoundSource(),
                    (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
        }
    }

    @Override
    protected void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        CompoundTag blockTag = NbtUtils.writeBlockState(this.getBlockState());
        tag.put("BlockState", blockTag);
        tag.putInt("FlightTime", this.flightTime);
        tag.putInt("MaxLife", this.maxLife);
    }

    @Override
    protected void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        this.setBlockState(NbtUtils.readBlockState(
                this.level().holderLookup(Registries.BLOCK),
                tag.getCompound("BlockState")));
        this.flightTime = tag.getInt("FlightTime");
        this.maxLife = tag.getInt("MaxLife");
    }

    @Override
    public @Nonnull Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity se) {
        return new ClientboundAddEntityPacket(this, se, Block.getId(this.getBlockState()));
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }
}