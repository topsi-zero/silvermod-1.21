package net.topsi.silvermod.render.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.topsi.silvermod.entity.custom.SilverTntArrowEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SilverTntArrowRenderer extends ProjectileEntityRenderer<SilverTntArrowEntity> {

    public static final Identifier TEXTURE = Identifier.of("minecraft", "textures/entity/projectiles/arrow.png");

    public SilverTntArrowRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(SilverTntArrowEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        // You can delegate to vanilla arrow rendering if needed
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(SilverTntArrowEntity entity) {
        // Always use normal arrow texture, or make a TNT arrow texture
        return TEXTURE;
    }
}