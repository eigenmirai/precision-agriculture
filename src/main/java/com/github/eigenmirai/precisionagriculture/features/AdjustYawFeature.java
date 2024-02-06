package com.github.eigenmirai.precisionagriculture.features;

import com.github.eigenmirai.precisionagriculture.util.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Random;

public class AdjustYawFeature {
    private float targetYaw = 60.0f;
    private final Minecraft mc = Minecraft.getMinecraft();
    public static final KeyBinding adjust_yaw = new KeyBinding("Adjust yaw", Keyboard.KEY_NUMPAD5, "PrecisionAgriculture");
    public static final KeyBinding turn_180deg = new KeyBinding("Turn around", Keyboard.KEY_NUMPAD1, "PrecisionAgriculture");
    private boolean doJiggle = false;
    private int lastSign = 1;
    private final float YAW_VARIATION = 0.15f;
    private long lastYawChangeTime = 0;

    private static final long INTERVAL_MIN = 2500;
    private static final long INTERVAL_MAX = 4000;
    private final Random random = new Random();


    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && adjust_yaw.isPressed()) {
            float yaw = MathUtil.normalizeAngle(mc.thePlayer.rotationYaw);

            if (yaw >= 0 && yaw < 90) {
                mc.thePlayer.rotationYaw = targetYaw;
            } else if (yaw >= 90 && yaw < 180) {
                mc.thePlayer.rotationYaw = 180.0f - targetYaw;
            } else if (yaw >= -90 && yaw < 0) {
                mc.thePlayer.rotationYaw = -targetYaw;
            } else {
                mc.thePlayer.rotationYaw = -180.0f + targetYaw;
            }
        }

        if (event.phase == TickEvent.Phase.END && doJiggle) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastYawChangeTime >= calculateRandomInterval()) {
                float newYaw = mc.thePlayer.rotationYaw + (lastSign * YAW_VARIATION);
                mc.thePlayer.rotationYaw = newYaw;
                lastYawChangeTime = currentTime;
                lastSign = -1 * lastSign; // toggle between incrementing and decrementing yaw

            }
        }

    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (turn_180deg.isPressed()) {
            float newYaw = MathUtil.normalizeAngle(mc.thePlayer.rotationYaw + 180.0f);
            mc.thePlayer.rotationYaw = newYaw;
        }
    }

    private long calculateRandomInterval() {
        return INTERVAL_MIN + random.nextInt((int) (INTERVAL_MAX - INTERVAL_MIN + 1));
    }

    public void setTargetYaw(float targetYaw) {
        this.targetYaw = targetYaw;
    }

    public void setJiggle(boolean doJiggle) {
        this.doJiggle = doJiggle;
    }

    public boolean getJiggle() {
        return this.doJiggle;
    }
}
