package features.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.stream.Stream;

public class CoordinatesOverlay {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final FontRenderer fontRenderer = mc.fontRendererObj;
    public static boolean showCoords = false;

    @SubscribeEvent
    public void onRenderGameOverlayText(Text event) {
        // Check if it's the event you want (e.g., POTION_ICONS or EXPERIENCE)
        if (showCoords) {
            if (event.type == Text.ElementType.TEXT) {
                // Draw your text on the screen (adjust coordinates as needed)
                drawCoordinates();
            }
        }
    }

    private void drawCoordinates() {
        // Get player's coordinates
        String playerX = String.format("\u00A72X: \u00A76%d", (int) mc.thePlayer.posX);
        String playerY = String.format("\u00A72Y: \u00A76%d", (int) mc.thePlayer.posY);
        String playerZ = String.format("\u00A72Z: \u00A76%d", (int) mc.thePlayer.posZ);
        String yaw = String.format("\u00A72Yaw: \u00A76%.2f°", mc.thePlayer.rotationYaw);
        String pitch = String.format("\u00A72Pitch: \u00A76%.2f°", mc.thePlayer.rotationPitch);

        int paddingX = 5;
        int paddingY = 5;

       int textWidth = Stream.of(playerX, playerY, playerZ, yaw, pitch)
                .map(fontRenderer::getStringWidth)
                .max(Integer::compareTo)
                .get();
        int textHeight = fontRenderer.FONT_HEIGHT * 5; // 5 lines

        // draw background
        int x = 5;
        int y = 5;
        int width = textWidth + 2 * paddingX;
        int height = textHeight + 2 * paddingY;
        int backgroundColor = 0x80000000; // semi-transparent black

        Gui.drawRect(x, y, x + width, y + height, backgroundColor);

        int textX = x + paddingX;
        int textY = y + paddingY;

        // Draw the text directly on the screen
        fontRenderer.drawString(playerX, textX, textY, 0xFFFFFF);
        fontRenderer.drawString(playerY, textX, textY + 10, 0xFFFFFF);
        fontRenderer.drawString(playerZ, textX, textY + 20, 0xFFFFFF);
        fontRenderer.drawString(yaw, textX, textY + 30, 0xFFFFFF);
        fontRenderer.drawString(pitch, textX, textY + 40, 0xFFFFFF);


    }
}
