package com.mechwild.mechs.client;

import com.mechwild.mechs.MechwildMechs;
import com.mechwild.mechs.entity.RaptorMechEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RaptorMechRenderer extends MobRenderer<RaptorMechEntity, RaptorMechModel<RaptorMechEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MechwildMechs.MODID, "textures/entity/raptor_mech.png");

    public RaptorMechRenderer(EntityRendererProvider.Context context) {
        super(context, new RaptorMechModel<>(context.bakeLayer(RaptorMechModel.LAYER_LOCATION)), 2.25F);
    }

    @Override
    protected void scale(RaptorMechEntity entity, PoseStack poseStack, float partialTickTime) {
        // v0.9: small-class infantry raptor scale, roughly 4-5 metres tall in-world.
        poseStack.scale(1.85F, 1.85F, 1.85F);
    }

    @Override
    public ResourceLocation getTextureLocation(RaptorMechEntity entity) {
        return TEXTURE;
    }
}
