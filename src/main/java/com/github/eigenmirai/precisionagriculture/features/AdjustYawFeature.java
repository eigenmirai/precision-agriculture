package com.github.eigenmirai.precisionagriculture.features;

import com.github.eigenmirai.precisionagriculture.util.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class AdjustYawFeature {
    private static float targetYaw = 60.0f;
    private final Minecraft mc = Minecraft.getMinecraft();
    public static final KeyBinding adjust_yaw = new KeyBinding("Adjust yaw", Keyboard.KEY_NUMPAD5, "PrecisionAgriculture");


    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && adjust_yaw.isPressed()) {
            double yaw = MathUtil.normalizeAngle(mc.thePlayer.rotationYaw);

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
    }
}
