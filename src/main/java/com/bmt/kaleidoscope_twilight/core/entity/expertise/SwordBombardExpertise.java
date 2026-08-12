package com.bmt.kaleidoscope_twilight.core.entity.expertise;

import com.bmt.kaleidoscope_twilight.core.entity.ThrownSwordEntity;
import com.bmt.kaleidoscope_twilight.core.entity.boss.UmbralSunflower;
import com.bmt.kaleidoscope_twilight.init.KTEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SwordBombardExpertise extends AbstractSunflowerExpertise {
    public static final int DURATION = 100;
    public static final int RISE_TICKS = 30;
    private static final int COOLDOWN_BASE = 400;
    private static final double RISE_HEIGHT = 2.0;
    private static final double TARGET_RANGE = 25.0;

    private double startY;

    public SwordBombardExpertise(EntityDataAccessor<Integer> animTimeData) {
        super("Sword", DURATION, 200, animTimeData);
    }

    @Override
    protected int rollCooldown(RandomSource random) {
        return COOLDOWN_BASE + random.nextInt(200);
    }

    @Override
    protected boolean canUse(UmbralSunflower boss, LivingEntity target) {
        return !this.findTargets(boss).isEmpty();
    }

    @Override
    protected void onStart(UmbralSunflower boss, LivingEntity target) {
        this.startY = boss.getY();
        boss.setNoGravity(true);
        boss.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 0.7F);
    }

    @Override
    protected void tick(UmbralSunflower boss, int remaining) {
        int elapsed = this.elapsed(remaining);
        if (elapsed < RISE_TICKS) {
            if (boss.getY() < this.startY + RISE_HEIGHT) {
                boss.setDeltaMovement(0, 0.35, 0);
            } else {
                boss.setDeltaMovement(0, 0, 0);
            }
        } else if (remaining <= 15) {
            boss.setDeltaMovement(0, -0.4, 0);
        } else {
            boss.setDeltaMovement(0, 0, 0);
        }
        boss.getNavigation().stop();
        if (elapsed >= RISE_TICKS && remaining > 15 && elapsed % 5 == 0) {
            this.shootSwordVolley(boss);
        }
    }

    @Override
    protected void onEnd(UmbralSunflower boss) {
        boss.setNoGravity(false);
    }

    private List<LivingEntity> findTargets(UmbralSunflower boss) {
        AABB box = boss.getBoundingBox().inflate(TARGET_RANGE);
        List<LivingEntity> entities = boss.level().getEntitiesOfClass(LivingEntity.class, box);
        List<LivingEntity> targets = new ArrayList<>();
        for (LivingEntity e : entities) {
            if (e != boss && e.isAlive() && !e.isRemoved() && !(e instanceof ThrownSwordEntity)) {
                targets.add(e);
            }
        }
        return targets;
    }

    private void shootSwordVolley(UmbralSunflower boss) {
        List<LivingEntity> targets = this.findTargets(boss);
        if (targets.isEmpty()) return;

        int maxTargets = Math.min(targets.size(), 10);
        for (int i = 0; i < maxTargets; i++) {
            this.spawnFlyingSword(boss, targets.get(i));
        }
        boss.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 0.8F, 0.6F + boss.getRandom().nextFloat() * 0.4F);
    }

    private void spawnFlyingSword(UmbralSunflower boss, LivingEntity target) {
        if (boss.level().isClientSide()) return;

        ThrownSwordEntity sword = KTEntities.THROWN_SWORD.get().create(boss.level());
        if (sword == null) return;

        sword.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(this.randomSword(boss.getRandom())));
        sword.setOwner(boss);
        sword.setTargetPos(target.position().add(0, target.getBbHeight() * 0.5, 0));
        sword.setFixedDamage(false);

        if (sword.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            Objects.requireNonNull(sword.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(4.0D);
        }

        sword.launchFromRotation(boss, 1.6F, 0.0F, target);

        Vec3 pos = boss.position().add(
                (boss.getRandom().nextDouble() - 0.5) * 1.5,
                boss.getBbHeight() * 0.5 + (boss.getRandom().nextDouble() - 0.5),
                (boss.getRandom().nextDouble() - 0.5) * 1.5
        );
        sword.setPos(pos.x, pos.y, pos.z);
        boss.level().addFreshEntity(sword);
    }

    private Item randomSword(RandomSource random) {
        Item[] swords = {
                Items.WOODEN_SWORD,
                Items.STONE_SWORD,
                Items.IRON_SWORD,
                Items.GOLDEN_SWORD,
                Items.DIAMOND_SWORD,
                Items.NETHERITE_SWORD
        };
        return swords[random.nextInt(swords.length)];
    }
}