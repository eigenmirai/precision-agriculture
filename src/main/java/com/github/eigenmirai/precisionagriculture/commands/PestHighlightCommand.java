package com.github.eigenmirai.precisionagriculture.commands;

import com.github.eigenmirai.precisionagriculture.garden.PestHighlight;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class PestColorCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "pestcolor";
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
        if (args.length > 0) {
            String hex = args[0];
            if (!hex.startsWith("#")) {
                hex = "#" + hex;
            }
            PestHighlight.setColor(hex);
        }
    }
}
