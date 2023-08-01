package wtf.ultra.herta;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
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
    private int frame = 0;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void renderOverlay(RenderGameOverlayEvent.Text event) {
        mc.fontRendererObj.drawString(ChatFormatting.WHITE.toString(), 0, 0, 0);
        mc.getTextureManager().bindTexture(images[frame / 8]);
        int w = 256, h = 256, u = 0, v = 0;
        int x = event.resolution.getScaledWidth() - w;
        int y = event.resolution.getScaledHeight() - h;
        mc.ingameGUI.drawTexturedModalRect(x, y, u, v, w, h);
        frame = (frame + 1) % 48;
    }
}
