package com.bmt.kaleidoscope_twilight.common.item;

import com.bmt.kaleidoscope_twilight.common.entity.SwordAuraEntity;
import com.bmt.kaleidoscope_twilight.init.KTEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HotTearSwordItem extends SwordItem {

    private static final Map<UUID, Long> LAST_SHOOT_TICK = new HashMap<>();

    public HotTearSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, properties
                .attributes(SwordItem.createAttributes(tier, attackDamage, attackSpeed))
        );
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        target.igniteForSeconds(25);
        return result;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.translatable("item.kaleidoscope_twilight.hot_tear_sword.tooltip")
                .withStyle(ChatFormatting.GRAY));
    }

    public static void shootSwordAura(ServerPlayer player) {
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof HotTearSwordItem)) return;

        long tick = player.level().getGameTime();
        UUID id = player.getUUID();
        if (LAST_SHOOT_TICK.getOrDefault(id, -1L) == tick) return;

        if (player.getAttackStrengthScale(0.5F) <= 0.9F) return;
        LAST_SHOOT_TICK.put(id, tick);

        Level level = player.level();
        SwordAuraEntity aura = KTEntities.SWORD_AURA.get().create(level);
        if (aura == null) return;

        aura.setPos(player.getX(), player.getEyeY() - 0.3D, player.getZ());
        aura.setYRot(player.getYHeadRot());
        aura.setXRot(player.getXRot());
        aura.setOwner(player);
        aura.setDamage((float) player.getAttributeValue(Attributes.ATTACK_DAMAGE));
        aura.setPlayerAura(true);
        aura.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.0F, 0.0F);
        level.addFreshEntity(aura);

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS,
                1.0F, 1.0F);

        stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
    }

    public static boolean isHotTearSword(ItemStack stack) {
        return stack.getItem() instanceof HotTearSwordItem;
    }
}
