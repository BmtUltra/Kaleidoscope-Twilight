package com.bmt.kaleidoscope_twilight.entity.expertise;

import com.bmt.kaleidoscope_twilight.api.IExpertise;
import com.bmt.kaleidoscope_twilight.entity.boss.UmbralSunflower;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractSunflowerExpertise implements IExpertise {
    private final String nbtKey;
    private final int duration;
    private final EntityDataAccessor<Integer> animTimeData;
    private int cooldown;

    protected AbstractSunflowerExpertise(String nbtKey, int duration, int initialCooldown, EntityDataAccessor<Integer> animTimeData) {
        this.nbtKey = nbtKey;
        this.duration = duration;
        this.cooldown = initialCooldown;
        this.animTimeData = animTimeData;
    }

    protected abstract int rollCooldown(RandomSource random);

    protected boolean canUse(UmbralSunflower boss, LivingEntity target) {
        return true;
    }

    protected abstract void onStart(UmbralSunflower boss, LivingEntity target);

    protected abstract void tick(UmbralSunflower boss, int remaining);

    protected void onEnd(UmbralSunflower boss) {
    }

    protected final int elapsed(int remaining) {
        return this.duration - remaining;
    }

    @Override
    public boolean isActive(UmbralSunflower boss) {
        return boss.getEntityData().get(this.animTimeData) > 0;
    }

    @Override
    public void serverTick(UmbralSunflower boss) {
        if (this.cooldown > 0) this.cooldown--;
        int time = boss.getEntityData().get(this.animTimeData);
        if (time == 0) {
            LivingEntity target = boss.getTarget();
            if (this.cooldown == 0 && target != null
                    && boss.canStartSkill() && this.canUse(boss, target)) {
                time = this.duration;
                boss.getEntityData().set(this.animTimeData, time);
                this.cooldown = this.rollCooldown(boss.getRandom());
                this.onStart(boss, target);
            }
        }
        if (time > 0) {
            this.tick(boss, time);
            int next = time - 1;
            boss.getEntityData().set(this.animTimeData, next);
            if (next == 0) {
                this.onEnd(boss);
            }
        }
    }

    @Override
    public void interrupt(UmbralSunflower boss) {
        if (this.isActive(boss)) {
            boss.getEntityData().set(this.animTimeData, 0);
            this.onEnd(boss);
        }
    }

    @Override
    public void save(CompoundTag tag) {
        tag.putInt(this.nbtKey + "Cooldown", this.cooldown);
    }

    @Override
    public void load(CompoundTag tag) {
        if (tag.contains(this.nbtKey + "Cooldown")) {
            this.cooldown = tag.getInt(this.nbtKey + "Cooldown");
        }
    }
}