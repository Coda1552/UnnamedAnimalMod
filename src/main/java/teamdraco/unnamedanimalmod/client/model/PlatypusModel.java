package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class PlatypusModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart bill;
	private final ModelPart tail;

	public PlatypusModel(ModelPart root) {
		this.body = root.getChild("body");
		this.right_arm = body.getChild("right_arm");
		this.left_arm = body.getChild("left_arm");
		this.right_leg = body.getChild("right_leg");
		this.left_leg = body.getChild("left_leg");
		this.bill = body.getChild("bill");
		this.tail = body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition bill = body.addOrReplaceChild("bill", CubeListBuilder.create().texOffs(15, 17).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, -6.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(0.0F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 1.5F, -2.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 4).addBox(-3.0F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.5F, -2.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 1.5F, 5.0F));

		PartDefinition hat = body.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(26, 25).addBox(-3.0F, -2.0F, -2.5F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.01F, -3.0F, 0.0F, -0.3883F, 0.0F));

		PartDefinition shape9 = hat.addOrReplaceChild("shape9", CubeListBuilder.create().texOffs(18, 0).addBox(-4.0F, 22.0F, -3.5F, 8.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 17).addBox(-2.0F, -1.5F, 0.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.5F, 5.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isInWater()) {
			float speed = 1.0f;
			float degree = 1.0f;
			this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount;
			this.right_arm.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -1.8F * limbSwingAmount - 0.1F;
			this.right_arm.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount - 0.1F;
			this.left_arm.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.8F * limbSwingAmount - 0.1F;
			this.left_arm.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount + 0.1F;
			this.right_leg.yRot = -0.54033285F * limbSwingAmount - 1.3F;
			this.right_leg.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount + 1.5F;
			this.left_leg.yRot = 0.54033285F * limbSwingAmount + 1.3F;
			this.left_leg.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount - 1.5F;
			this.tail.yRot = Mth.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount;
			this.right_arm.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount + 4.0F;
			this.left_arm.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount + 4.0F;
			this.bill.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 0.25F;
			this.body.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
		}
		else if (entity.isBaby() && !entity.isInWater()) {
			float speed = 5.0f;
			float degree = 3.0f;
			limbSwingAmount = Mth.clamp(limbSwingAmount, -0.05f, 0.05f);
			this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
			this.right_arm.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount + 0.1F;
			this.right_arm.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount + 0.1F;
			this.left_arm.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount - 0.1F;
			this.left_arm.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount - 0.1F;
			this.right_leg.yRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount - 0.5F;
			this.right_leg.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount + 0.1F;
			this.left_leg.yRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount + 0.5F;
			this.left_leg.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount - 0.1F;
			this.tail.yRot = Mth.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
			this.right_arm.xRot = 0.0F;
			this.left_arm.xRot = 0.0F;
			this.bill.xRot = 0.2617993877991494F;
			this.body.xRot = 0.0F;
		}
		else if (!entity.isBaby() && !entity.isInWater()) {
			float speed = 5.0f;
			float degree = 3.0f;
			limbSwingAmount = Mth.clamp(limbSwingAmount, -0.35f, 0.35f);
			this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
			this.right_arm.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount + 0.1F;
			this.right_arm.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount + 0.1F;
			this.left_arm.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount - 0.1F;
			this.left_arm.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount - 0.1F;
			this.right_leg.yRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount - 0.5F;
			this.right_leg.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount + 0.1F;
			this.left_leg.yRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount + 0.5F;
			this.left_leg.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount - 0.1F;
			this.tail.yRot = Mth.cos(-1.5F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
			this.right_arm.xRot = 0.0F;
			this.left_arm.xRot = 0.0F;
			this.bill.xRot = 0.2617993877991494F;
			this.body.xRot = 0.0F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}