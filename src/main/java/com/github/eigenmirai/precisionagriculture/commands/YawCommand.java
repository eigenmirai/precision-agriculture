package com.github.eigenmirai.precisionagriculture.commands;

import com.github.eigenmirai.precisionagriculture.PrecisionAgriculture;
import com.github.eigenmirai.precisionagriculture.overlay.CoordinatesOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class YawCommand extends CommandBase {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName() {
        return "yaw";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/$commandName <angle>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1) {
            if ("jiggle".equals(args[0])) {
                boolean old = PrecisionAgriculture.adjustYawFeature.getJiggle();
                PrecisionAgriculture.adjustYawFeature.setJiggle(!old);
                String newState = !old ? "\u00A7a\u00A7lENABLED" : "\u00A7c\u00A7lDISABLED";
                ChatComponentText message = new ChatComponentText(EnumChatFormatting.GRAY + "Jiggle is now " + newState);
                mc.thePlayer.addChatMessage(message);
                return;
            }
            try {
                float angle = Float.parseFloat(args[0]);
                angle = Math.abs(angle) % 90;
                PrecisionAgriculture.adjustYawFeature.setTargetYaw(angle);
            } catch (NumberFormatException e) {
                mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Failed to parse angle!"));
            }
        } else {
            mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Wrong usage!"));

        }
    }
}
