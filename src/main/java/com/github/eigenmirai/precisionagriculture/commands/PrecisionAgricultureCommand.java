package com.github.eigenmirai.precisionagriculture.commands;

import com.github.eigenmirai.precisionagriculture.PrecisionAgriculture;
import com.github.eigenmirai.precisionagriculture.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;
import java.util.List;

public class PrecisionAgricultureCommand extends CommandBase {
    GuiScreen configScreen = null;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
        if (configScreen != null) {
            Minecraft.getMinecraft().displayGuiScreen(configScreen);
            configScreen = null;
        }
    }

    @Override
    public String getCommandName() {
        return "precisionagriculture";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("pa");
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
        ChatComponentText component = new ChatComponentText("");

        ChatComponentText info = new ChatComponentText("\n" + EnumChatFormatting.DARK_GREEN +
                PrecisionAgriculture.MODID + "® " + PrecisionAgriculture.VERSION + "\n" +
                EnumChatFormatting.DARK_GREEN + "Advanced agricultural tools for a modern farming experience!\n");
        String playerName = Minecraft.getMinecraft().thePlayer.getName();
        ChatComponentText user = new ChatComponentText(EnumChatFormatting.DARK_GREEN +
                "Logged in as: " + EnumChatFormatting.BOLD + EnumChatFormatting.GOLD + playerName + "\n");


        component.appendSibling(ChatUtil.separator);
        component.appendSibling(info);
        component.appendSibling(user);
        component.appendSibling(ChatUtil.separator);
        Minecraft.getMinecraft().thePlayer.addChatMessage(component);

    }
}
