package com.bmt.kaleidoscope_twilight.network;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTEffects;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.init.TFEntities;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record FireBreathPacket() implements CustomPacketPayload {
    public static final Type<FireBreathPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "fire_breath"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FireBreathPacket> STREAM_CODEC =
            StreamCodec.unit(new FireBreathPacket());

    private static final Map<UUID, Long> LAST_SHOOT_TIME = new HashMap<>();
    private static final int COOLDOWN_TICKS = 10;

    @Override
    public @NotNull Type<FireBreathPacket> type() {
        return TYPE;
    }

    public static void handle(FireBreathPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                tryShootFireball(serverPlayer);
            }
        });
    }

    private static void tryShootFireball(ServerPlayer player) {
        if (!player.hasEffect(KTEffects.FIRE_BREATH)) {
            return;
        }
        Level level = player.level();
        UUID id = player.getUUID();
        long currentTime = level.getGameTime();
        Long lastTime = LAST_SHOOT_TIME.get(id);
        if (lastTime != null && currentTime - lastTime < COOLDOWN_TICKS) {
            return;
        }
        Vec3 look = player.getLookAngle();
        Vec3 spawnPos = player.getEyePosition().add(look.scale(0.5));
        HydraMortar fireball = new HydraMortar(TFEntities.HYDRA_MORTAR.get(), level);
        fireball.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
        fireball.setOwner(player);
        fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 0.8F, 0.0F);
        fireball.setNoGravity(true);
        level.addFreshEntity(fireball);
        LAST_SHOOT_TIME.put(id, currentTime);
    }
}