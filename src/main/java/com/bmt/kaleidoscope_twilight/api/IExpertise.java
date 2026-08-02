package com.bmt.kaleidoscope_twilight.api;

import com.bmt.kaleidoscope_twilight.entity.UmbralSunflower;
import net.minecraft.nbt.CompoundTag;

public interface IExpertise {
    boolean isActive(UmbralSunflower boss);
    void serverTick(UmbralSunflower boss);
    void interrupt(UmbralSunflower boss);
    void save(CompoundTag tag);
    void load(CompoundTag tag);
}