package com.bmt.kaleidoscope_twilight.event;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.init.KTEffects;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.TickEvent;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.init.TFDamageTypes;

@Mod.EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onLivingDamage(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player) {
            ItemStack activeItem = player.getUseItem();
            if (activeItem.getItem() == KTItems.FIERY_STOCKPOT_LID.get()) {
                if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                    attacker.setRemainingFireTicks(100);
                }
            }
        }

        if (entity.hasEffect(KTEffects.PHANTOM.get())) {
            if (event.getSource().getMsgId().equals("inWall")) {
                event.setCanceled(true);
            }
        }

        if (entity.hasEffect(KTEffects.STURDY_SCALES.get())) {
            var effectInstance = entity.getEffect(KTEffects.STURDY_SCALES.get());
            if (effectInstance != null) {
                int amplifier = effectInstance.getAmplifier();
                float originalDamage = event.getAmount();
                float reducedDamage = originalDamage * 0.7f;
                float flatReduction = 5.0f + (amplifier * 2.0f);
                reducedDamage = Math.max(0, reducedDamage - flatReduction);
                event.setCanceled(true);
                entity.hurt(event.getSource(), reducedDamage);
            }
        }

        if (event.getSource().is(TFDamageTypes.HYDRA_MORTAR)) {
            if (event.getSource().getDirectEntity() instanceof HydraMortar mortar) {
                if (mortar.getOwner() != null && mortar.getOwner().equals(entity) && entity.hasEffect(KTEffects.FIRE_BREATH.get())) {
                    float originalDamage = event.getAmount();
                    float reducedDamage = originalDamage * 0.2f;
                    event.setCanceled(true);
                    entity.hurt(event.getSource(), reducedDamage);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (player.hasEffect(KTEffects.ERUDITION.get())) {
                if (player.tickCount % 20 == 0) {
                    player.giveExperiencePoints(1);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath() || event.getEntity().level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) {
            return;
        }

        for (ItemStack item : event.getOriginal().getInventory().items) {
            if (item.getItem() == KTItems.KEEPING_POUCH_ITEM.get()) {
                event.getEntity().getInventory().add(item);
            }
        }
    }
}