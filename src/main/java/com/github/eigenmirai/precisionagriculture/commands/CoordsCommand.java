package com.github.eigenmirai.precisionagriculture.commands;

import com.github.eigenmirai.precisionagriculture.overlay.CoordinatesOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CoordsCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "coords";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/$commandName";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        CoordinatesOverlay.showCoords = !CoordinatesOverlay.showCoords;
        String newState = CoordinatesOverlay.showCoords ? "\u00A7a\u00A7lSHOWN" : "\u00A7c\u00A7lHIDDEN";
        ChatComponentText message = new ChatComponentText(EnumChatFormatting.GRAY + "Coordinates are now " + newState);
        Minecraft.getMinecraft().thePlayer.addChatMessage(message);
    }
}
