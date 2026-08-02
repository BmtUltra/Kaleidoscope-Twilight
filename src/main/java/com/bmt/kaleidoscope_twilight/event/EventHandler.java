package com.bmt.kaleidoscope_twilight.event;

import com.bmt.kaleidoscope_twilight.init.KTBlocks;
import com.bmt.kaleidoscope_twilight.init.KTEffects;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import com.bmt.kaleidoscope_twilight.mixins.accessor.BlockEntityTypeAccessor;
import com.google.common.collect.Sets;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.init.TFBlockEntities;
import twilightforest.init.TFDamageTypes;

import java.util.Set;

@EventBusSubscriber(modid = "kaleidoscope_twilight")
public class EventHandler {

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BlockEntityTypeAccessor accessor =
                    (BlockEntityTypeAccessor) TFBlockEntities.TROPHY.get();
            Set<Block> validBlocks = Sets.newHashSet(accessor.kaleidoscope_twilight$validBlocks());
            validBlocks.add(KTBlocks.UMBRAL_SUNFLOWER_TROPHY.get());
            validBlocks.add(KTBlocks.UMBRAL_SUNFLOWER_WALL_TROPHY.get());
            accessor.kaleidoscope_twilight$setValidBlocks(validBlocks);
        });
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingIncomingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player) {
            ItemStack activeItem = player.getUseItem();
            if (activeItem.getItem() == KTItems.FIERY_STOCKPOT_LID.get()) {
                if (event.getSource().getEntity() instanceof LivingEntity attacker) {
                    attacker.setRemainingFireTicks(100);
                }
            }
        }

        if (entity.hasEffect(KTEffects.PHANTOM)) {
            if (event.getSource().type().msgId().equals("inWall")) {
                event.setCanceled(true);
            }
        }

        if (entity.hasEffect(KTEffects.STURDY_SCALES)) {
            var effectInstance = entity.getEffect(KTEffects.STURDY_SCALES);
            if (effectInstance != null) {
                int amplifier = effectInstance.getAmplifier();
                float originalDamage = event.getAmount();
                float reducedDamage = originalDamage * 0.7f;
                float flatReduction = 5.0f + (amplifier * 2.0f);
                reducedDamage = Math.max(0, reducedDamage - flatReduction);
                event.setAmount(reducedDamage);
            }
        }

        if (event.getSource().is(TFDamageTypes.HYDRA_MORTAR)) {
            if (event.getSource().getDirectEntity() instanceof HydraMortar mortar) {
                if (mortar.getOwner() != null && mortar.getOwner().equals(entity) && entity.hasEffect(KTEffects.FIRE_BREATH)) {
                    float originalDamage = event.getAmount();
                    float reducedDamage = originalDamage * 0.2f;
                    event.setAmount(reducedDamage);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.hasEffect(KTEffects.ERUDITION)) {
            if (player.tickCount % 20 == 0) {
                player.giveExperiencePoints(1);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelChange(PlayerXpEvent.LevelChange event) {
        Player player = event.getEntity();
        if (event.getLevels() < 0
                && player.hasEffect(KTEffects.ERUDITION)
                && player.containerMenu instanceof AnvilMenu) {
            event.setCanceled(true);
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