package wtf.ultra.herta;

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
        mc.getTextureManager().bindTexture(images[frame / 8]);
        mc.ingameGUI.drawTexturedModalRect(event.resolution.getScaledWidth() - 256, event.resolution.getScaledHeight() - 256, 0, 0, 256, 256);
        frame = (frame + 1) % 48;
    }
}
