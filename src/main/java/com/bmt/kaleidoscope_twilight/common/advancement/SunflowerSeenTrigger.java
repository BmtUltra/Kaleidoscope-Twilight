package com.bmt.kaleidoscope_twilight.common.advancement;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SunflowerSeenTrigger extends SimpleCriterionTrigger<SunflowerSeenTrigger.Instance> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "sunflower_seen");

    public void trigger(ServerPlayer player) {
        super.trigger(player, instance -> true);
    }

    @Override
    public @NotNull Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public record Instance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                        EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player))
                .apply(instance, Instance::new));
    }
}
