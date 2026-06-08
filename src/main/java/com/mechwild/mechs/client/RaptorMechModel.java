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
    private final ModelPart leftStabiliser;
    private final ModelPart rightStabiliser;

    public RaptorMechModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.tail = root.getChild("tail");
        this.leftStabiliser = root.getChild("left_stabiliser");
        this.rightStabiliser = root.getChild("right_stabiliser");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // v0.9: Small-class bipedal raptor mech shell. The pilot camera sits high inside the head cockpit.
        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-11.0F, -23.0F, -16.0F, 22.0F, 20.0F, 32.0F)      // black internal torso
                .texOffs(0, 53).addBox(-12.0F, -26.0F, -13.0F, 24.0F, 7.0F, 22.0F)       // red upper armour/cockpit shell
                .texOffs(76, 0).addBox(-7.0F, -28.0F, -9.0F, 14.0F, 4.0F, 12.0F)         // raised cockpit canopy
                .texOffs(76, 18).addBox(-4.0F, -29.0F, -12.0F, 8.0F, 3.0F, 5.0F)         // green forward sensor glass
                .texOffs(0, 84).addBox(-14.0F, -18.0F, -8.0F, 4.0F, 10.0F, 16.0F)        // left side armour
                .texOffs(0, 84).mirror().addBox(10.0F, -18.0F, -8.0F, 4.0F, 10.0F, 16.0F),// right side armour
                PartPose.offset(0.0F, 16.0F, 0.0F));

        root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(40, 84).addBox(-7.0F, -10.0F, -14.0F, 14.0F, 11.0F, 15.0F)      // angular head
                .texOffs(96, 34).addBox(-5.0F, -8.0F, -19.0F, 10.0F, 5.0F, 7.0F)         // snout
                .texOffs(96, 48).addBox(-6.0F, -12.0F, -7.0F, 12.0F, 4.0F, 8.0F)         // red brow plate
                .texOffs(100, 62).addBox(-5.5F, -5.5F, -19.5F, 3.0F, 2.0F, 1.0F)         // left green eye
                .texOffs(100, 62).mirror().addBox(2.5F, -5.5F, -19.5F, 3.0F, 2.0F, 1.0F),// right green eye
                PartPose.offset(0.0F, 2.0F, -22.0F));

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                .texOffs(42, 112).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 16.0F, 7.0F)
                .texOffs(70, 112).addBox(-4.5F, 12.0F, -8.0F, 9.0F, 4.0F, 11.0F)         // clawed foot
                .texOffs(108, 80).addBox(-5.0F, 3.0F, -4.0F, 10.0F, 5.0F, 8.0F),        // red knee armour
                PartPose.offset(8.0F, 6.0F, 8.0F));

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                .texOffs(42, 112).mirror().addBox(-3.5F, 0.0F, -3.5F, 7.0F, 16.0F, 7.0F)
                .texOffs(70, 112).mirror().addBox(-4.5F, 12.0F, -8.0F, 9.0F, 4.0F, 11.0F)
                .texOffs(108, 80).mirror().addBox(-5.0F, 3.0F, -4.0F, 10.0F, 5.0F, 8.0F),
                PartPose.offset(-8.0F, 6.0F, 8.0F));

        root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                .texOffs(112, 96).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F)
                .texOffs(100, 116).addBox(-2.5F, 6.0F, -6.0F, 5.0F, 3.0F, 8.0F)    // compact blade forearm
                .texOffs(118, 132).addBox(-1.5F, 8.0F, -9.0F, 3.0F, 2.0F, 5.0F),   // claw/talon point
                PartPose.offset(10.0F, 5.0F, -10.0F));

        root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                .texOffs(112, 96).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F)
                .texOffs(100, 116).mirror().addBox(-2.5F, 6.0F, -6.0F, 5.0F, 3.0F, 8.0F)
                .texOffs(118, 132).mirror().addBox(-1.5F, 8.0F, -9.0F, 3.0F, 2.0F, 5.0F),
                PartPose.offset(-10.0F, 5.0F, -10.0F));

        root.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(0, 112).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 30.0F)
                .texOffs(76, 66).addBox(-4.0F, -5.0F, 20.0F, 8.0F, 8.0F, 8.0F),
                PartPose.offset(0.0F, 6.0F, 16.0F));

        root.addOrReplaceChild("left_stabiliser", CubeListBuilder.create()
                .texOffs(108, 66).addBox(0.0F, -2.0F, -4.0F, 16.0F, 4.0F, 8.0F),
                PartPose.offset(8.0F, -6.0F, 3.0F));

        root.addOrReplaceChild("right_stabiliser", CubeListBuilder.create()
                .texOffs(108, 66).mirror().addBox(-16.0F, -2.0F, -4.0F, 16.0F, 4.0F, 8.0F),
                PartPose.offset(-8.0F, -6.0F, 3.0F));

        return LayerDefinition.create(mesh, 160, 160);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        head.yRot = netHeadYaw * ((float) Math.PI / 180F) * 0.75F;
        head.xRot = headPitch * 0.35F * ((float) Math.PI / 180F);
        rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 0.65F * limbSwingAmount;
        leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.65F * limbSwingAmount;
        rightArm.xRot = -0.35F + Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.12F * limbSwingAmount;
        leftArm.xRot = -0.35F + Mth.cos(limbSwing * 0.6662F) * 0.12F * limbSwingAmount;
        tail.yRot = Mth.sin(ageInTicks * 0.10F) * 0.12F;
        leftStabiliser.zRot = Mth.sin(ageInTicks * 0.06F) * 0.035F;
        rightStabiliser.zRot = -leftStabiliser.zRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
