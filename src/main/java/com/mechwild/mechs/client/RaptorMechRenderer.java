package com.mechwild.mechs.client;

import com.mechwild.mechs.MechwildMechs;
import com.mechwild.mechs.entity.RaptorMechEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RaptorMechRenderer extends MobRenderer<RaptorMechEntity, RaptorMechModel<RaptorMechEntity>> {
    private static final ResourceLocation RAPTOR = new ResourceLocation(MechwildMechs.MODID, "textures/entity/raptor_mech.png");
    private static final ResourceLocation BEETLE = new ResourceLocation(MechwildMechs.MODID, "textures/entity/beetle_mech.png");
    private static final ResourceLocation STAG = new ResourceLocation(MechwildMechs.MODID, "textures/entity/stag_mech.png");
    private static final ResourceLocation WOLF = new ResourceLocation(MechwildMechs.MODID, "textures/entity/wolf_mech.png");
    private static final ResourceLocation HAWK = new ResourceLocation(MechwildMechs.MODID, "textures/entity/hawk_mech.png");
    private static final ResourceLocation CHIMERA = new ResourceLocation(MechwildMechs.MODID, "textures/entity/chimera_mech.png");

    public RaptorMechRenderer(EntityRendererProvider.Context context) {
        super(context, new RaptorMechModel<>(context.bakeLayer(RaptorMechModel.LAYER_LOCATION)), 2.75F);
    }

    @Override
    protected void scale(RaptorMechEntity entity, PoseStack poseStack, float partialTickTime) {
        String family = entity.getMechFamily();
        if (family.equals("beetle")) poseStack.scale(2.00F, 1.85F, 2.10F);
        else if (family.equals("stag")) poseStack.scale(1.95F, 2.20F, 1.95F);
        else if (family.equals("wolf")) poseStack.scale(1.90F, 1.92F, 2.05F);
        else if (family.equals("hawk")) poseStack.scale(1.85F, 2.10F, 1.95F);
        else if (family.equals("chimera")) poseStack.scale(2.25F, 2.35F, 2.30F);
        else poseStack.scale(1.95F, 2.05F, 2.05F);
    }

    @Override
    public ResourceLocation getTextureLocation(RaptorMechEntity entity) {
        String family = entity.getMechFamily();
        if (family.equals("beetle")) return BEETLE;
        if (family.equals("stag")) return STAG;
        if (family.equals("wolf")) return WOLF;
        if (family.equals("hawk")) return HAWK;
        if (family.equals("chimera")) return CHIMERA;
        return RAPTOR;
    }
}
