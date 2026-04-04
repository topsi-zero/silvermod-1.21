package net.topsi.silvermod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.topsi.silvermod.entity.ModEntities;
import net.topsi.silvermod.render.custom.SilverTntArrowRenderer;

public class SilvermodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SILVER_TNT_ARROW, SilverTntArrowRenderer::new);
    }
}
