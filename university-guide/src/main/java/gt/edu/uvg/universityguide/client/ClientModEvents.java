package gt.edu.uvg.universityguide.client;

import gt.edu.uvg.universityguide.ModContent;
import gt.edu.uvg.universityguide.UniversityGuideMod;
import gt.edu.uvg.universityguide.client.renderer.GuideRenderer;
import gt.edu.uvg.universityguide.client.renderer.TourStopRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = UniversityGuideMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModContent.GUIDE.get(), GuideRenderer::new);
        event.registerBlockEntityRenderer(ModContent.TOUR_STOP_ENTITY.get(), TourStopRenderer::new);
    }
}
