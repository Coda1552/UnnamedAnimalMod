package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class FiddlerCrabModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart leg_right_front;
	private final ModelPart leg_right_mid1;
	private final ModelPart leg_right_mid2;
	private final ModelPart leg_right_back;
	private final ModelPart leg_left_front;
	private final ModelPart leg_left_mid1;
	private final ModelPart leg_left_mid2;
	private final ModelPart leg_left_back;
	private final ModelPart claw_left;
	private final ModelPart claw_right;
	private final ModelPart eye_left;
	private final ModelPart eye_right;

	public FiddlerCrabModel(ModelPart root) {
		this.body = root.getChild("body");
		this.leg_right_front = root.getChild("leg_right_front");
		this.leg_right_mid1 = root.getChild("leg_right_mid1");
		this.leg_right_mid2 = root.getChild("leg_right_mid2");
		this.leg_right_back = root.getChild("leg_right_back");
		this.leg_left_front = root.getChild("leg_left_front");
		this.leg_left_mid1 = root.getChild("leg_left_mid1");
		this.leg_left_mid2 = root.getChild("leg_left_mid2");
		this.leg_left_back = root.getChild("leg_left_back");
		this.claw_left = root.getChild("claw_left");
		this.claw_right = root.getChild("claw_right");
		this.eye_right = body.getChild("eye_right");
		this.eye_left = body.getChild("eye_left");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.5F, -3.0F, 7.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.0F, 0.0F, -0.3883F, 0.0F, 0.0F));

		PartDefinition eye_right = body.addOrReplaceChild("eye_right", CubeListBuilder.create().texOffs(0, 9).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -3.0F, 0.3883F, 0.0F, 0.0F));

		PartDefinition shape17 = body.addOrReplaceChild("shape17", CubeListBuilder.create().texOffs(19, 9).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 0.0F));

		PartDefinition eye_left = body.addOrReplaceChild("eye_left", CubeListBuilder.create().texOffs(0, 9).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -3.0F, 0.3883F, 0.0F, 0.0F));

		PartDefinition leg_right_front = partdefinition.addOrReplaceChild("leg_right_front", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 22.0F, -0.5F, -0.7854F, 0.0F, -0.7854F));

		PartDefinition leg_right_mid1 = partdefinition.addOrReplaceChild("leg_right_mid1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 22.0F, 0.0F, -0.3883F, 0.0F, -0.7854F));

		PartDefinition leg_right_mid2 = partdefinition.addOrReplaceChild("leg_right_mid2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 22.0F, 0.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leg_right_back = partdefinition.addOrReplaceChild("leg_right_back", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 22.0F, 1.0F, 0.7854F, 0.0F, -0.7854F));

		PartDefinition leg_left_front = partdefinition.addOrReplaceChild("leg_left_front", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 22.0F, 0.0F, -0.3883F, 0.0F, 0.7854F));

		PartDefinition leg_left_mid1 = partdefinition.addOrReplaceChild("leg_left_mid1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 22.0F, -0.5F, -0.7854F, 0.0F, 0.7854F));

		PartDefinition leg_left_mid2 = partdefinition.addOrReplaceChild("leg_left_mid2", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 22.0F, 0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leg_left_back = partdefinition.addOrReplaceChild("leg_left_back", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 22.0F, 1.0F, 0.7854F, 0.0F, 0.7854F));

		PartDefinition claw_left = partdefinition.addOrReplaceChild("claw_left", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -1.0F, -2.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 21.0F, -3.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition claw_right = partdefinition.addOrReplaceChild("claw_right", CubeListBuilder.create().texOffs(12, 9).addBox(-3.0F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 21.5F, -3.0F, 0.0F, -0.3883F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isInWater()) {
			float speed = 2.5f;
			float degree = 3.0f;
			limbSwingAmount = Mth.clamp(limbSwingAmount, -0.35F, 0.35F);
			this.claw_right.y = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount + 21.475F;
			this.claw_left.y = Mth.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount + 20.975F;
			this.eye_left.xRot = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 0.4F;
			this.eye_right.xRot = Mth.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 0.4F;
			this.body.y = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount + 21.0F;
			this.leg_right_front.yRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.1F;
			this.leg_right_front.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.8F;
			this.leg_right_mid1.yRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.1F;
			this.leg_right_mid1.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.8F;
			this.leg_right_mid2.yRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.1F;
			this.leg_right_mid2.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.8F;
			this.leg_right_back.yRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.1F;
			this.leg_right_back.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.4F;
			this.leg_left_front.yRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.1F;
			this.leg_left_front.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.8F;
			this.leg_left_mid1.yRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.1F;
			this.leg_left_mid1.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.8F;
			this.leg_left_mid2.yRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.1F;
			this.leg_left_mid2.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.8F;
			this.leg_left_back.yRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.1F;
			this.leg_left_back.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.4F;
		}
		else {
			float speed = 1.5f;
			float degree = 1.0f;
			this.claw_right.y = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount + 21.475F;
			this.claw_left.y = Mth.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount + 20.975F;
			this.eye_left.xRot = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 0.4F;
			this.eye_right.xRot = Mth.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 0.4F;
			this.body.y = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount + 21.0F;
			this.leg_right_front.yRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.1F;
			this.leg_right_front.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.8F;
			this.leg_right_mid1.yRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.1F;
			this.leg_right_mid1.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.8F;
			this.leg_right_mid2.yRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.1F;
			this.leg_right_mid2.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.8F;
			this.leg_right_back.yRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.1F;
			this.leg_right_back.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.4F;
			this.leg_left_front.yRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.1F;
			this.leg_left_front.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.8F;
			this.leg_left_mid1.yRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.1F;
			this.leg_left_mid1.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.8F;
			this.leg_left_mid2.yRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.1F;
			this.leg_left_mid2.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.8F;
			this.leg_left_back.yRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.1F;
			this.leg_left_back.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.4F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_right_front.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_right_mid1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_right_mid2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_right_back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_left_front.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_left_mid1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_left_mid2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_left_back.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		claw_left.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		claw_right.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}