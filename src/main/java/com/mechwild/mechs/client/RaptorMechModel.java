package com.mechwild.mechs.client;

import com.mechwild.mechs.MechwildMechs;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class RaptorMechModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MechwildMechs.MODID, "raptor_mech"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart tail;

    public RaptorMechModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Larger shell pass: body is deliberately taller/wider so the hidden rider feels inside a cockpit.
        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-9.0F, -18.0F, -13.0F, 18.0F, 18.0F, 26.0F)
                .texOffs(0, 46).addBox(-6.0F, -22.0F, -9.0F, 12.0F, 5.0F, 16.0F),
                PartPose.offset(0.0F, 15.0F, 0.0F));

        root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(62, 0).addBox(-6.0F, -8.0F, -11.0F, 12.0F, 10.0F, 13.0F)
                .texOffs(62, 24).addBox(-4.0F, -10.0F, -13.0F, 8.0F, 4.0F, 5.0F),
                PartPose.offset(0.0F, 6.0F, -14.0F));

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(40, 46).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F),
                PartPose.offset(6.0F, 8.0F, 6.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(40, 46).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 6.0F),
                PartPose.offset(-6.0F, 8.0F, 6.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(68, 46).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 14.0F, 6.0F),
                PartPose.offset(7.0F, 10.0F, -7.0F));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(68, 46).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 14.0F, 6.0F),
                PartPose.offset(-7.0F, 10.0F, -7.0F));
        root.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(0, 68).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 22.0F),
                PartPose.offset(0.0F, 8.0F, 12.0F));

        return LayerDefinition.create(mesh, 128, 96);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        head.xRot = headPitch * 0.45F * ((float) Math.PI / 180F);
        rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.75F * limbSwingAmount;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.75F * limbSwingAmount;
        rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.55F * limbSwingAmount;
        leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.55F * limbSwingAmount;
        tail.yRot = Mth.sin(ageInTicks * 0.10F) * 0.10F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
