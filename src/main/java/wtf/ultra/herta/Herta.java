package wtf.ultra.herta;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "herta", version = "1.0")
public class Herta {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final ResourceLocation[] images = new ResourceLocation[]{
            new ResourceLocation("herta:spin/spin1.png"),
            new ResourceLocation("herta:spin/spin2.png"),
            new ResourceLocation("herta:spin/spin3.png"),
            new ResourceLocation("herta:spin/spin4.png"),
            new ResourceLocation("herta:spin/spin5.png"),
            new ResourceLocation("herta:spin/spin6.png")
    };
    private long instant = 0;
    private int frame = 0;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Text event) {
        long now;
        if ((now = System.currentTimeMillis()) - instant >= 60) {
            instant = now;
            frame = (frame + 1) % images.length;
        }

        mc.fontRendererObj.drawString(EnumChatFormatting.WHITE.toString(), 0, 0, 0);
        mc.getTextureManager().bindTexture(images[frame]);
        int w = 256, h = 256, u = 0, v = 0;
        int x = event.resolution.getScaledWidth() - w;
        int y = event.resolution.getScaledHeight() - h;
        mc.ingameGUI.drawTexturedModalRect(x, y, u, v, w, h);
    }
}
