package com.bmt.kaleidoscope_twilight.util;

import net.minecraft.util.Mth;

public final class KeyframeUtils {

    public static float[] lerpKeyframes(float time, float[] times, float[][] values) {
        if (time <= times[0]) return values[0];
        if (time >= times[times.length - 1]) return values[times.length - 1];
        for (int i = 0; i < times.length - 1; i++) {
            if (time < times[i + 1]) {
                float t = (time - times[i]) / (times[i + 1] - times[i]);
                return new float[]{
                        Mth.lerp(t, values[i][0], values[i + 1][0]),
                        Mth.lerp(t, values[i][1], values[i + 1][1]),
                        Mth.lerp(t, values[i][2], values[i + 1][2])
                };
            }
        }
        return values[values.length - 1];
    }
}