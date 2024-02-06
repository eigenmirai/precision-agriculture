package com.github.eigenmirai.precisionagriculture.util;

public class MathUtil {
    public static float normalizeAngle(float angle) {
        while (angle <= -180) {
            angle += 360;
        }
        while (angle > 180) {
            angle -= 360;
        }
        return angle;
    }

}
