package net.topsi.silvermod.render.custom;

import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.topsi.silvermod.entity.custom.ThrowableSilverTntEntity;

public class ThrowableSilverTntRenderer extends EntityRenderer<ThrowableSilverTntEntity> {

    private final ModelPart cube;
    private static final Identifier TEXTURE = Identifier.of("silvermod", "textures/entity/throwable_silver_tnt.png");

    public ThrowableSilverTntRenderer(EntityRendererFactory.Context context) {
        super(context);

        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        root.addChild("cube",
                ModelPartBuilder.create()
                        .cuboid(-4.0F, -4.0F, -4.0F, 8, 8, 8),
                ModelTransform.NONE
        );

        TexturedModelData texturedModelData = TexturedModelData.of(modelData, 16, 16);
        cube = texturedModelData.createModel().getChild("cube");
    }

    @Override
    public void render(ThrowableSilverTntEntity entity, float yaw, float tickDelta,
                       MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(TEXTURE));
        cube.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(ThrowableSilverTntEntity entity) {
        return TEXTURE;
    }
}