package features.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryOverlay {

    private final Minecraft mc = Minecraft.getMinecraft();
    @Nonnull private final FontRenderer fontRenderer = mc.fontRendererObj;


    @SubscribeEvent
    public void onRenderGameOverlayText(Text event) {
        if (event.type == Text.ElementType.TEXT && Keyboard.isKeyDown(Keyboard.KEY_LMENU)) {
            EntityPlayer player = mc.thePlayer;

            if (player != null && player.inventory != null && player.inventory.mainInventory != null) {
                drawInventoryOverlay();
            } else {
                System.out.println("Player or inventory is null.");
            }
        }
    }

    public void drawInventoryOverlay() {
        List<String> inventory = getPlayerInventoryContents().entrySet()
                .stream()
                .map(entry -> entry.getValue().toString() + "x " + entry.getKey())
                .collect(Collectors.toList());

        int x = 5;
        int y = 5;
        int paddingX = 5;
        int paddingY = 5;
        int textX = x + paddingX;
        int textY = y + paddingY;
        int backgroundColor = 0x80000000;

        int textWidth = inventory.stream().map(fontRenderer::getStringWidth).max(Integer::compareTo).get();
        int textHeight = inventory.size() * fontRenderer.FONT_HEIGHT;
        int width = textWidth + 2 * paddingX;
        int height = textHeight + 2 * paddingY;

        Gui.drawRect(x, y, x + width, y + height, backgroundColor);

        int offsetY = 0;
        for (String listItem : inventory) {
            fontRenderer.drawString(listItem, textX, textY + offsetY * 10, 0xFFFFFF);
            offsetY++;
        }


    }

    public Map<String, Integer> getPlayerInventoryContents() {
        Map<String, Integer> inventoryContents = new HashMap<>();
        EntityPlayer player = mc.thePlayer;

        for (ItemStack itemStack : player.inventory.mainInventory) {
            if (itemStack != null) {

                String itemName = itemStack.getDisplayName();
                int itemCount = itemStack.stackSize;

                if (inventoryContents.containsKey(itemName)) {
                    // add to existing amount
                    int prev = inventoryContents.get(itemName);
                    inventoryContents.put(itemName, prev + itemCount);
                } else {
                    inventoryContents.put(itemName, itemCount);
                }
            }
        }

        return inventoryContents;
    }
}
