package com.bmt.kaleidoscope_twilight.api;

import com.bmt.kaleidoscope_twilight.common.client.model.UmbralSunflowerModel;

public interface IAnimation {
    void apply(UmbralSunflowerModel model, float time, float netHeadYaw);
}