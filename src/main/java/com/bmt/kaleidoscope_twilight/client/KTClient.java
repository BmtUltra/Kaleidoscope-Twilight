package com.bmt.kaleidoscope_twilight.client;

import com.bmt.kaleidoscope_twilight.KaleidoscopeTwilight;
import com.bmt.kaleidoscope_twilight.client.gui.FieryPotOverlay;
import com.bmt.kaleidoscope_twilight.client.model.SwordAuraModel;
import com.bmt.kaleidoscope_twilight.client.model.UmbralSunflowerModel;
import com.bmt.kaleidoscope_twilight.client.render.*;
import com.bmt.kaleidoscope_twilight.client.tooltip.KeepingPouchTooltipComponent;
import com.bmt.kaleidoscope_twilight.init.KTBlockEntities;
import com.bmt.kaleidoscope_twilight.init.KTEffects;
import com.bmt.kaleidoscope_twilight.init.KTEntities;
import com.bmt.kaleidoscope_twilight.init.KTItems;
import com.bmt.kaleidoscope_twilight.item.FieryStockpotLidItem;
import com.bmt.kaleidoscope_twilight.item.KeepingPouchItem;
import com.bmt.kaleidoscope_twilight.network.FireBreathPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import static net.neoforged.neoforge.client.gui.VanillaGuiLayers.CROSSHAIR;

@EventBusSubscriber(modid = KaleidoscopeTwilight.MODID, value = Dist.CLIENT)
public class KTClient {

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(KeepingPouchItem.KeepingPouchTooltip.class, KeepingPouchTooltipComponent::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(KTBlockEntities.FIERY_POT_BLOCK_ENTITY.get(), FieryPotBlockEntityRender::new);
        BlockEntityRenderers.register(KTBlockEntities.FIERY_STOCKPOT_BLOCK_ENTITY.get(), FieryStockpotBlockEntityRender::new);
        event.enqueueWork(() -> EntityRenderers.register(KTEntities.UMBRAL_SUNFLOWER.get(), UmbralSunflowerRender::new));
        event.enqueueWork(() -> ItemProperties.register(
                KTItems.FIERY_STOCKPOT_LID.get(),
                ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "using"),
                (stack, level, entity, seed) -> FieryStockpotLidItem.getTexture(stack, entity)
        ));
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(UmbralSunflowerModel.LAYER_LOCATION, UmbralSunflowerModel::createBodyLayer);
        event.registerLayerDefinition(SwordAuraModel.LAYER_LOCATION, SwordAuraModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(KTBlockEntities.UMBRAL_SUNFLOWER_TROPHY.get(),
                TrophyRender::new);
        event.registerBlockEntityRenderer(KTBlockEntities.WALL_UMBRAL_SUNFLOWER_TROPHY.get(),
                TrophyRender::new);
        event.registerEntityRenderer(KTEntities.THROWN_SWORD.get(), ThrownSwordRender::new);
        event.registerEntityRenderer(KTEntities.SWORD_AURA.get(), SwordAuraRender::new);
        event.registerEntityRenderer(KTEntities.GROUND_SPIKE.get(), GroundSpikeRender::new);
        event.registerEntityRenderer(KTEntities.GIANT_SWORD.get(), GiantSwordRender::new);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            private TrophyItemRender renderer;

            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null) {
                    this.renderer = new TrophyItemRender();
                }
                return this.renderer;
            }
        }, KTItems.UMBRAL_SUNFLOWER_TROPHY.get());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;
        if (!player.hasEffect(KTEffects.FIRE_BREATH)) return;
        if (mc.options.keyAttack.isDown()) {
            PacketDistributor.sendToServer(new FireBreathPacket());
        }
    }

    @SubscribeEvent
    public static void onRegisterGuiOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(CROSSHAIR, ResourceLocation.fromNamespaceAndPath(KaleidoscopeTwilight.MODID, "fiery_pot_overlay"), new FieryPotOverlay());
    }
}