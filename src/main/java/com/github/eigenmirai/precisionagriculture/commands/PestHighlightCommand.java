package com.github.eigenmirai.precisionagriculture.commands;

import com.github.eigenmirai.precisionagriculture.PrecisionAgriculture;
import com.github.eigenmirai.precisionagriculture.garden.PestHighlight;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;
import java.util.List;

public class PestHighlightCommand extends CommandBase {
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName() {
        return "pesthighlight";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("ph");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/$commandName <color hex code>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        PestHighlight pestHighlight = PrecisionAgriculture.pestHighlight;
        if (args.length == 1) {
            if ("enable".equals(args[0]) || "on".equals(args[0])) {
                if (pestHighlight.isEnabled()) {
                    mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Feature is already enabled!"));
                } else {
                    mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Pest Highlight is now \u00A7a\u00A7lENABLED"));
                    pestHighlight.enable();
                }
            } else if ("disable".equals(args[0]) || "off".equals(args[0])) {
                if (!pestHighlight.isEnabled()) {
                    mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Feature is already disabled!"));
                } else {
                    mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Pest Highlight is now \u00A7c\u00A7lDISABLED"));
                    pestHighlight.enable();
                }
            } else {
                mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Wrong usage!"));
            }
        } else if (args.length == 2) {
            if ("color".equals(args[0])) {
                String hex = args[1];
                if (!hex.startsWith("#")) {
                    hex = "#" + hex;
                }
                mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY
                        + "Set color to " + EnumChatFormatting.BOLD + hex.toUpperCase()));
                pestHighlight.setColor(hex);
            }
        } else {
            mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Wrong usage!"));
        }
    }
}
