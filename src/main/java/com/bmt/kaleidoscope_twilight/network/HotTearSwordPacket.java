package com.bmt.kaleidoscope_twilight.network;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.common.item.HotTearSwordItem;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;

public record HotTearSwordPacket() implements CustomPacketPayload {
    public static final Type<HotTearSwordPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "hot_tear_sword"));

    public static final StreamCodec<RegistryFriendlyByteBuf, HotTearSwordPacket> STREAM_CODEC =
            StreamCodec.unit(new HotTearSwordPacket());

    @Override
    public @NotNull Type<HotTearSwordPacket> type() {
        return TYPE;
    }

    public static void handle(HotTearSwordPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                HotTearSwordItem.shootSwordAura(serverPlayer);
            }
        });
    }
}
