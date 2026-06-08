package com.mechwild.mechs.client;

import com.mechwild.mechs.MechwildMechs;
import com.mechwild.mechs.entity.RaptorMechEntity;
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

public class RaptorMechModel<T extends RaptorMechEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MechwildMechs.MODID, "mech_variants"), "main");

    private final ModelPart root;
    private final ModelPart raptor;
    private final ModelPart beetle;
    private final ModelPart stag;
    private final ModelPart wolf;
    private final ModelPart hawk;
    private final ModelPart chimera;

    public RaptorMechModel(ModelPart root) {
        this.root = root;
        this.raptor = root.getChild("raptor");
        this.beetle = root.getChild("beetle");
        this.stag = root.getChild("stag");
        this.wolf = root.getChild("wolf");
        this.hawk = root.getChild("hawk");
        this.chimera = root.getChild("chimera");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Raptor Striker: small-class bipedal infantry mech, high head cockpit, long balancing tail.
        root.addOrReplaceChild("raptor", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-8, -20, -10, 16, 15, 24)
                .texOffs(0, 42).addBox(-10, -23, -8, 20, 6, 18)
                .texOffs(70, 0).addBox(-7, -31, -28, 14, 11, 15)
                .texOffs(70, 28).addBox(-5, -27, -36, 10, 5, 10)
                .texOffs(70, 46).addBox(-6, -34, -25, 12, 5, 11)
                .texOffs(112, 0).addBox(-3, -28, -37, 6, 2, 1)
                .texOffs(0, 72).addBox(-4, -5, 3, 8, 20, 8)
                .texOffs(28, 72).addBox(-13, 12, -6, 12, 5, 17)
                .texOffs(0, 72).mirror().addBox(-4, -5, 3, 8, 20, 8)
                .texOffs(28, 72).mirror().addBox(1, 12, -6, 12, 5, 17)
                .texOffs(72, 72).addBox(-2, -8, -24, 4, 13, 4)
                .texOffs(72, 72).mirror().addBox(-2, -8, -24, 4, 13, 4)
                .texOffs(96, 72).addBox(-3, -10, 13, 6, 7, 38)
                .texOffs(96, 118).addBox(-5, -13, 42, 10, 10, 10)
                .texOffs(132, 22).addBox(-16, -24, 0, 8, 4, 16)
                .texOffs(132, 22).mirror().addBox(8, -24, 0, 8, 4, 16),
                PartPose.offset(0, 14, 0));

        // Beetle Bulwark: compact heavy mining tank with horn drill and broad armoured shell.
        root.addOrReplaceChild("beetle", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-16, -18, -18, 32, 16, 36)
                .texOffs(0, 54).addBox(-18, -24, -14, 36, 8, 28)
                .texOffs(90, 0).addBox(-10, -22, -30, 20, 12, 14)
                .texOffs(92, 28).addBox(-4, -20, -44, 8, 7, 18)
                .texOffs(132, 0).addBox(-2, -19, -58, 4, 5, 17)
                .texOffs(0, 96).addBox(-18, -3, -12, 7, 14, 9)
                .texOffs(0, 96).mirror().addBox(11, -3, -12, 7, 14, 9)
                .texOffs(0, 96).addBox(-18, -3, 6, 7, 14, 9)
                .texOffs(0, 96).mirror().addBox(11, -3, 6, 7, 14, 9)
                .texOffs(44, 96).addBox(-21, -15, -4, 6, 11, 18)
                .texOffs(44, 96).mirror().addBox(15, -15, -4, 6, 11, 18),
                PartPose.offset(0, 16, 0));

        // Stag Grovekeeper: taller utility unit with antler sensor arrays and farming arms.
        root.addOrReplaceChild("stag", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-10, -22, -12, 20, 18, 26)
                .texOffs(0, 48).addBox(-12, -26, -10, 24, 7, 20)
                .texOffs(72, 0).addBox(-8, -36, -28, 16, 12, 15)
                .texOffs(72, 30).addBox(-5, -32, -37, 10, 5, 11)
                .texOffs(116, 0).addBox(-18, -42, -26, 16, 4, 4)
                .texOffs(116, 0).mirror().addBox(2, -42, -26, 16, 4, 4)
                .texOffs(0, 82).addBox(-5, -5, 4, 7, 22, 7)
                .texOffs(0, 82).mirror().addBox(-2, -5, 4, 7, 22, 7)
                .texOffs(32, 82).addBox(-14, 13, -4, 11, 5, 16)
                .texOffs(32, 82).mirror().addBox(3, 13, -4, 11, 5, 16)
                .texOffs(90, 82).addBox(-14, -15, -11, 5, 15, 8)
                .texOffs(90, 82).mirror().addBox(9, -15, -11, 5, 15, 8)
                .texOffs(120, 42).addBox(-3, -10, 14, 6, 6, 27),
                PartPose.offset(0, 14, 0));

        // Wolf Nightfang: fast medium-low predator silhouette with forward shoulders and blade jaws.
        root.addOrReplaceChild("wolf", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-10, -19, -15, 20, 14, 30)
                .texOffs(0, 46).addBox(-12, -23, -13, 24, 6, 20)
                .texOffs(74, 0).addBox(-8, -29, -31, 16, 10, 16)
                .texOffs(74, 28).addBox(-5, -25, -43, 10, 5, 13)
                .texOffs(112, 24).addBox(-3, -22, -48, 6, 4, 8)
                .texOffs(0, 76).addBox(-6, -4, -8, 8, 18, 8)
                .texOffs(0, 76).mirror().addBox(-2, -4, -8, 8, 18, 8)
                .texOffs(32, 76).addBox(-12, 11, -13, 10, 5, 16)
                .texOffs(32, 76).mirror().addBox(2, 11, -13, 10, 5, 16)
                .texOffs(88, 76).addBox(-7, -1, 8, 5, 16, 8)
                .texOffs(88, 76).mirror().addBox(2, -1, 8, 5, 16, 8)
                .texOffs(120, 52).addBox(-3, -11, 15, 6, 5, 31),
                PartPose.offset(0, 15, 0));

        // Hawk Stormwing: light aerial frame with tall cockpit neck, wings and rear thrusters.
        root.addOrReplaceChild("hawk", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-8, -21, -11, 16, 14, 24)
                .texOffs(0, 42).addBox(-10, -25, -8, 20, 5, 17)
                .texOffs(68, 0).addBox(-7, -35, -29, 14, 10, 15)
                .texOffs(68, 28).addBox(-4, -31, -39, 8, 5, 12)
                .texOffs(108, 0).addBox(-30, -25, -2, 28, 4, 28)
                .texOffs(108, 0).mirror().addBox(2, -25, -2, 28, 4, 28)
                .texOffs(0, 72).addBox(-4, -4, 3, 7, 19, 7)
                .texOffs(0, 72).mirror().addBox(-3, -4, 3, 7, 19, 7)
                .texOffs(32, 72).addBox(-11, 12, -5, 10, 4, 15)
                .texOffs(32, 72).mirror().addBox(1, 12, -5, 10, 4, 15)
                .texOffs(110, 46).addBox(-5, -12, 13, 10, 8, 17)
                .texOffs(126, 78).addBox(-4, -10, 28, 8, 6, 12),
                PartPose.offset(0, 13, 0));

        // Chimera Titan: late prototype hybrid; raptor head, beetle core, wolf claws, hawk fins.
        root.addOrReplaceChild("chimera", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-14, -24, -17, 28, 20, 36)
                .texOffs(0, 60).addBox(-16, -29, -14, 32, 8, 28)
                .texOffs(90, 0).addBox(-9, -39, -34, 18, 13, 18)
                .texOffs(90, 34).addBox(-6, -34, -46, 12, 6, 14)
                .texOffs(132, 0).addBox(-4, -31, -55, 8, 5, 10)
                .texOffs(0, 92).addBox(-6, -5, 4, 9, 24, 9)
                .texOffs(0, 92).mirror().addBox(-3, -5, 4, 9, 24, 9)
                .texOffs(38, 92).addBox(-15, 15, -7, 13, 6, 20)
                .texOffs(38, 92).mirror().addBox(2, 15, -7, 13, 6, 20)
                .texOffs(110, 64).addBox(-34, -26, 1, 24, 5, 25)
                .texOffs(110, 64).mirror().addBox(10, -26, 1, 24, 5, 25)
                .texOffs(116, 102).addBox(-4, -13, 19, 8, 8, 42),
                PartPose.offset(0, 10, 0));

        return LayerDefinition.create(mesh, 192, 192);
    }

    @Override
    public ModelPart root() { return root; }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        String family = entity.getMechFamily();
        raptor.visible = family.equals("raptor");
        beetle.visible = family.equals("beetle");
        stag.visible = family.equals("stag");
        wolf.visible = family.equals("wolf");
        hawk.visible = family.equals("hawk");
        chimera.visible = family.equals("chimera");

        float bob = Mth.sin(ageInTicks * 0.08F) * 0.018F;
        raptor.xRot = bob; beetle.xRot = bob * 0.35F; stag.xRot = bob; wolf.xRot = bob; hawk.xRot = bob * 1.25F; chimera.xRot = bob * 0.6F;
        raptor.yRot = netHeadYaw * 0.002F; wolf.yRot = netHeadYaw * 0.0015F; hawk.yRot = netHeadYaw * 0.002F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
