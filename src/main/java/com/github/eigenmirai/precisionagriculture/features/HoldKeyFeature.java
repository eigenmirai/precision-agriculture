package com.github.eigenmirai.precisionagriculture.features;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class HoldKeyFeature {

    private static boolean[] toggled = {false, false, false, false, false, false};
    public static KeyBinding toggleKeyBindingW = new KeyBinding("Toggle W key", Keyboard.KEY_NUMPAD8, "PrecisionAgriculture");
    public static KeyBinding toggleKeyBindingA = new KeyBinding("Toggle A key", Keyboard.KEY_NUMPAD4, "PrecisionAgriculture");
    public static KeyBinding toggleKeyBindingS = new KeyBinding("Toggle S key", Keyboard.KEY_NUMPAD2, "PrecisionAgriculture");
    public static KeyBinding toggleKeyBindingD = new KeyBinding("Toggle D key", Keyboard.KEY_NUMPAD6, "PrecisionAgriculture");
    public static KeyBinding toggleKeyBindingLMouse = new KeyBinding("Toggle left click", Keyboard.KEY_NUMPAD7, "PrecisionAgriculture");
    public static KeyBinding toggleKeyBindingRMouse = new KeyBinding("Toggle right click", Keyboard.KEY_NUMPAD9, "PrecisionAgriculture");

    public static final KeyBinding FORWARD = Minecraft.getMinecraft().gameSettings.keyBindForward;
    public static final KeyBinding LEFT = Minecraft.getMinecraft().gameSettings.keyBindLeft;
    public static final KeyBinding RIGHT = Minecraft.getMinecraft().gameSettings.keyBindRight;
    public static final KeyBinding BACK = Minecraft.getMinecraft().gameSettings.keyBindBack;
    public static final KeyBinding LMOUSE = Minecraft.getMinecraft().gameSettings.keyBindAttack;
    public static final KeyBinding RMOUSE = Minecraft.getMinecraft().gameSettings.keyBindUseItem;



    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (toggleKeyBindingW.isPressed()) {
            toggled[0] = !toggled[0];

            KeyBinding.setKeyBindState(FORWARD.getKeyCode(), toggled[0]);
        } else if (toggleKeyBindingA.isPressed()) {
            toggled[1] = !toggled[1];

            KeyBinding.setKeyBindState(LEFT.getKeyCode(), toggled[1]);
        } else if (toggleKeyBindingS.isPressed()) {
            toggled[2] = !toggled[2];

            KeyBinding.setKeyBindState(BACK.getKeyCode(), toggled[2]);
        } else if (toggleKeyBindingD.isPressed()) {
            toggled[3] = !toggled[3];

            KeyBinding.setKeyBindState(RIGHT.getKeyCode(), toggled[3]);
        } else if (toggleKeyBindingLMouse.isPressed()) {
            toggled[4] = !toggled[4];

            KeyBinding.setKeyBindState(LMOUSE.getKeyCode(), toggled[4]);
        } else if (toggleKeyBindingRMouse.isPressed()) {
            toggled[5] = !toggled[5];

            KeyBinding.setKeyBindState(RMOUSE.getKeyCode(), toggled[5]);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) ||
                Keyboard.isKeyDown(Keyboard.KEY_W) ||
                Keyboard.isKeyDown(Keyboard.KEY_A) ||
                Keyboard.isKeyDown(Keyboard.KEY_S) ||
                Keyboard.isKeyDown(Keyboard.KEY_D) ||
                Mouse.isButtonDown(0) || // left = 0
                Mouse.isButtonDown(1)    // right = 1
        ) {
            toggled = new boolean[] {false, false, false, false, false, false};
        }
    }
}
