package com.example.examplemod.entity.model;


import com.example.examplemod.entity.custom.PigeonEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class PigeonModel<T extends PigeonEntity> extends EntityModel<T> {
    private final ModelRenderer legs;
    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer rightWing;
    private final ModelRenderer cube_r1;
    private final ModelRenderer leftWing;
    private final ModelRenderer cube_r2;

    public PigeonModel() {
        texWidth = 32;
        texHeight = 32;

        legs = new ModelRenderer(this);
        legs.setPos(0.0F, 24.0F, 0.0F);
        legs.texOffs(0, 8).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
        legs.texOffs(0, 0).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setPos(0.0F, 24.0F, 0.0F);
        body.texOffs(0, 8).addBox(-2.0F, -4.0F, -1.0F, 3.0F, 2.0F, 5.0F, 0.0F, false);
        body.texOffs(16, 0).addBox(-2.0F, -3.0F, 4.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
        body.texOffs(0, 0).addBox(-3.0F, -5.0F, -2.0F, 5.0F, 2.0F, 5.0F, 0.0F, false);
        body.texOffs(12, 8).addBox(-2.0F, -6.0F, -2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setPos(-0.5F, 18.25F, -0.75F);
        head.texOffs(6, 16).addBox(-0.5F, -1.25F, -2.25F, 1.0F, 1.0F, 1.0F, 0.0F, false);
        head.texOffs(9, 21).addBox(-1.0F, -2.25F, -1.25F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        rightWing = new ModelRenderer(this);
        rightWing.setPos(-2.9509F, 20.0419F, -0.8627F);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setPos(2.9509F, 3.9581F, 0.8627F);
        rightWing.addChild(cube_r1);
        setRotationAngle(cube_r1, -0.3491F, 0.0873F, 0.1309F);
        cube_r1.texOffs(23, 16).addBox(-4.0F, -3.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r1.texOffs(18, 21).addBox(-4.0F, -4.0F, 0.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
        cube_r1.texOffs(0, 16).addBox(-4.0F, -5.0F, -3.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);

        leftWing = new ModelRenderer(this);
        leftWing.setPos(2.0491F, 20.0419F, -0.8627F);


        cube_r2 = new ModelRenderer(this);
        cube_r2.setPos(0.0F, 0.5F, 1.1667F);
        leftWing.addChild(cube_r2);
        setRotationAngle(cube_r2, -0.3491F, -0.0873F, -0.1309F);
        cube_r2.texOffs(14, 13).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 3.0F, 0.0F, false);
        cube_r2.texOffs(23, 11).addBox(-0.5F, 0.0F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
        cube_r2.texOffs(22, 4).addBox(-0.5F, -1.0F, 1.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.rightWing.yRot = MathHelper.cos(limbSwing * 2.6662F + (float) Math.PI) * 2.4F * limbSwingAmount;
        this.leftWing.yRot = MathHelper.cos(limbSwing * 2.6662F) * 2.4F * limbSwingAmount;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        legs.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}
