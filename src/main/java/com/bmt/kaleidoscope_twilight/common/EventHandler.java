package com.bmt.kaleidoscope_twilight.common;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.common.entity.ThrownSwordEntity;
import com.bmt.kaleidoscope_twilight.common.entity.boss.UmbralSunflower;
import com.bmt.kaleidoscope_twilight.common.item.HotTearSwordItem;
import com.bmt.kaleidoscope_twilight.init.*;
import com.bmt.kaleidoscope_twilight.mixins.accessor.BlockEntityTypeAccessor;
import com.bmt.kaleidoscope_twilight.network.FireBreathPacket;
import com.bmt.kaleidoscope_twilight.network.HotTearSwordPacket;
import com.bmt.kaleidoscope_twilight.util.FoodHelper;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModCreativeTabs;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModEffects;
import com.github.ysbbbbbb.kaleidoscopetavern.KaleidoscopeTavern;
import com.github.ysbbbbbb.kaleidoscopetavern.init.ModBlocks;
import com.google.common.collect.Sets;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import twilightforest.entity.boss.HydraMortar;
import twilightforest.entity.boss.Naga;
import twilightforest.init.*;

import java.util.*;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID)
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
            if (event.getSource().is(DamageTypes.IN_WALL)) {
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
        if (!event.isWasDeath() || event.getEntity().level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY)) return;
        for (ItemStack item : event.getOriginal().getInventory().items) {
            if (item.getItem() == KTItems.KEEPING_POUCH_ITEM.get()) {
                event.getEntity().getInventory().add(item);
            }
        }
    }

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Remove event) {
        if (event.getEffect() == KTEffects.FROST_CLOUD) {
            cleanupFrostCloud(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onEffectExpire(MobEffectEvent.Expired event) {
        MobEffectInstance instance = event.getEffectInstance();
        if (instance != null && instance.getEffect().value() == KTEffects.FROST_CLOUD.get()) {
            cleanupFrostCloud(event.getEntity());
        }
    }

    private static void cleanupFrostCloud(LivingEntity entity) {
        if (entity instanceof Player player) {
            if (!player.hasEffect(KTEffects.FROST_CLOUD)) {
                player.getAbilities().flying = false;
                player.getAbilities().mayfly = player.getAbilities().instabuild;
                player.onUpdateAbilities();
                player.fallDistance = 0.0F;
            }
        } else {
            entity.setNoGravity(false);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Naga)) return;
        if (!(event.getSource().getEntity() instanceof ServerPlayer player)) return;
        int kills = player.getStats().getValue(Stats.ENTITY_KILLED, event.getEntity().getType()) + 1;
        if (kills >= 20) KTTriggers.NAGA_SLAYER.get().trigger(player);
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getTarget() instanceof UmbralSunflower)) {
            return;
        }
        if (event.getEntity() instanceof ServerPlayer player) {
            KTTriggers.SUNFLOWER_SEEN.get().trigger(player);
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!HotTearSwordItem.isHotTearSword(player.getMainHandItem())) return;
        HotTearSwordItem.shootSwordAura(player);
    }

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == TFCreativeTabs.ITEMS.getKey()) {
            event.accept(KTItems.UMBRAL_SUNFLOWER_SPAWN_EGG.get(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == TFCreativeTabs.BLOCKS.getKey()) {
            Item bossSpawner = TFBlocks.SNOW_QUEEN_BOSS_SPAWNER.asItem();

            if (event.getParentEntries().stream().anyMatch(stack -> stack.is(bossSpawner))) {
                event.insertAfter(new ItemStack(bossSpawner),
                        new ItemStack(KTItems.UMBRAL_SUNFLOWER_SPAWNER.get()),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            } else {
                event.accept(KTItems.UMBRAL_SUNFLOWER_SPAWNER.get(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }

        if (event.getTabKey() == BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(ModCreativeTabs.COOKERY_FOOD_TAB.get()).orElseThrow()) {
            for (ItemStack itemStack : event.getParentEntries().toArray(ItemStack[]::new)) {
                if (Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(itemStack.getItem()))
                        .getNamespace().equals(KaleidoscopeTwilight.MODID)) {
                    event.remove(itemStack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(KaleidoscopeTwilight.MODID);
        registrar.playToServer(FireBreathPacket.TYPE, FireBreathPacket.STREAM_CODEC, FireBreathPacket::handle);
        registrar.playToServer(HotTearSwordPacket.TYPE, HotTearSwordPacket.STREAM_CODEC, HotTearSwordPacket::handle);
    }

    @SubscribeEvent
    private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(KTEntities.UMBRAL_SUNFLOWER.get(), UmbralSunflower.createAttributes().build());
        event.put(KTEntities.THROWN_SWORD.get(), ThrownSwordEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        Item meefStroganoff = TFItems.MEEF_STROGANOFF.get();
        event.modify(meefStroganoff, builder -> {
            builder.set(DataComponents.MAX_STACK_SIZE, 16);
            builder.set(DataComponents.FOOD,
                    FoodHelper.withEffect(Objects.requireNonNull(meefStroganoff.components().get(DataComponents.FOOD)),
                            ModEffects.SATIATED_SHIELD, 1600, 0, 1.0F
                    ));
        });
    }

    @SubscribeEvent
    public static void registerGenericItemHandlers(RegisterCapabilitiesEvent event) {
        if (!ModList.get().isLoaded(KaleidoscopeTavern.MOD_ID)) return;
        event.registerItem(Capabilities.FluidHandler.ITEM,
                (stack, ctx) -> new FluidBucketWrapper(stack),
                KTBrewItems.TORCHBERRY_BUCKET.get());
    }

    @SubscribeEvent
    public static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        if (!ModList.get().isLoaded(KaleidoscopeTavern.MOD_ID)) return;
        Arrays.stream(KTBrews.ALL_BREWS).forEach(brew ->
                event.modify(ModBlocks.DRINK_BE.get(), brew.get())
        );
    }
}