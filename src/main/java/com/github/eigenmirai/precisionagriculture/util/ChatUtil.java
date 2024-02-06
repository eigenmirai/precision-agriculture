package com.github.eigenmirai.precisionagriculture.util;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.StringUtils;

public class ChatUtil {
    public static ChatComponentText separator = new ChatComponentText(
            EnumChatFormatting.BLUE + EnumChatFormatting.BOLD.toString() + EnumChatFormatting.STRIKETHROUGH +
                    StringUtils.repeat("-", 45));
}
