package com.github.eigenmirai.precisionagriculture;

import com.github.eigenmirai.precisionagriculture.commands.CoordsCommand;
import com.github.eigenmirai.precisionagriculture.commands.PestHighlightCommand;
import com.github.eigenmirai.precisionagriculture.commands.PrecisionAgricultureCommand;
import com.github.eigenmirai.precisionagriculture.commands.YawCommand;
import com.github.eigenmirai.precisionagriculture.features.AdjustYawFeature;
import com.github.eigenmirai.precisionagriculture.features.HoldKeyFeature;
import com.github.eigenmirai.precisionagriculture.garden.PestHighlight;
import com.github.eigenmirai.precisionagriculture.overlay.CatOverlay;
import com.github.eigenmirai.precisionagriculture.overlay.CoordinatesOverlay;
import com.github.eigenmirai.precisionagriculture.overlay.InventoryOverlay;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
@Mod(modid = "PrecisionAgriculture", useMetadata = true)
public class PrecisionAgriculture {
    public static final String MODID = "PrecisionAgriculture";
    public static final String VERSION = "0.0.1";

    public static PestHighlight pestHighlight = new PestHighlight();
    public static AdjustYawFeature adjustYawFeature = new AdjustYawFeature();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Arrays.asList(
                new PrecisionAgricultureCommand(),
                new CoordsCommand(),
                new PestHighlightCommand(),
                new YawCommand()
        ).forEach(ClientCommandHandler.instance::registerCommand);
        Arrays.asList(
                new CatOverlay(),
                new CoordinatesOverlay(),
                new InventoryOverlay(),
                new HoldKeyFeature(),
                adjustYawFeature,
                pestHighlight
                ).forEach(MinecraftForge.EVENT_BUS::register);

        ClientRegistry.registerKeyBinding(HoldKeyFeature.toggleKeyBindingW);
        ClientRegistry.registerKeyBinding(HoldKeyFeature.toggleKeyBindingA);
        ClientRegistry.registerKeyBinding(HoldKeyFeature.toggleKeyBindingS);
        ClientRegistry.registerKeyBinding(HoldKeyFeature.toggleKeyBindingD);
        ClientRegistry.registerKeyBinding(HoldKeyFeature.toggleKeyBindingLMouse);
        ClientRegistry.registerKeyBinding(HoldKeyFeature.toggleKeyBindingRMouse);
        ClientRegistry.registerKeyBinding(AdjustYawFeature.adjust_yaw);


    }
}
