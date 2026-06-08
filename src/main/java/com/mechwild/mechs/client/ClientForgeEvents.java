package com.mechwild.mechs.client;

import com.mechwild.mechs.MechwildMechs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = MechwildMechs.MODID, value = Dist.CLIENT)
public class ClientForgeEvents {
    private static boolean isPilotingMech() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return false;
        Entity vehicle = mc.player.getVehicle();
        if (vehicle == null) return false;
        ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(vehicle.getType());
        return id != null && id.equals(new ResourceLocation(MechwildMechs.MODID, "raptor_mech"));
    }

    @SubscribeEvent
    public static void hidePilotHands(RenderHandEvent event) {
        if (isPilotingMech()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void renderCockpitOverlay(RenderGuiOverlayEvent.Post event) {
        if (!isPilotingMech()) return;

        GuiGraphics gui = event.getGuiGraphics();
        Minecraft mc = Minecraft.getInstance();
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();

        // Dark navy/black cockpit frame edges.
        gui.fill(0, 0, w, 18, 0xD80A1018);
        gui.fill(0, h - 26, w, h, 0xD80A1018);
        gui.fill(0, 0, 26, h, 0xC8070B12);
        gui.fill(w - 26, 0, w, h, 0xC8070B12);

        // Angled cockpit panel blocks.
        gui.fill(26, 18, 92, 38, 0xAA101A22);
        gui.fill(w - 92, 18, w - 26, 38, 0xAA101A22);
        gui.fill(26, h - 58, 120, h - 26, 0xBB101A22);
        gui.fill(w - 120, h - 58, w - 26, h - 26, 0xBB101A22);

        // Green screen tint and targeting lines.
        int green = 0x8835FF8A;
        int faintGreen = 0x4435FF8A;
        gui.fill(w / 2 - 1, 42, w / 2 + 1, h - 54, faintGreen);
        gui.fill(64, h / 2 - 1, w - 64, h / 2 + 1, faintGreen);
        gui.fill(w / 2 - 18, h / 2 - 1, w / 2 + 18, h / 2 + 1, green);
        gui.fill(w / 2 - 1, h / 2 - 18, w / 2 + 1, h / 2 + 18, green);

        // Small screen strips.
        gui.fill(42, 28, 84, 31, green);
        gui.fill(42, 35, 72, 38, faintGreen);
        gui.fill(w - 84, 28, w - 42, 31, green);
        gui.fill(w - 72, 35, w - 42, 38, faintGreen);
        gui.fill(44, h - 48, 110, h - 45, green);
        gui.fill(w - 110, h - 48, w - 44, h - 45, green);

        gui.drawString(mc.font, Component.literal("MECHLINK // HEAD COCKPIT"), 34, 7, 0x55FF99, false);
        gui.drawString(mc.font, Component.literal("CAMERA FEED"), w - 104, 7, 0x55FF99, false);
    }
}
