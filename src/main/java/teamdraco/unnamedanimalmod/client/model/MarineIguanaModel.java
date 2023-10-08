package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class MarineIguanaModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart armLeft;
	private final ModelPart armRight;
	private final ModelPart legLeft;
	private final ModelPart legRight;
	private final ModelPart head;
	private final ModelPart tailBase;
	private final ModelPart tail;

	public MarineIguanaModel(ModelPart root) {
		this.body = root.getChild("body");
		this.armLeft = body.getChild("armLeft");
		this.armRight = body.getChild("armRight");
		this.legLeft = body.getChild("legLeft");
		this.legRight = body.getChild("legRight");
		this.head = body.getChild("head");
		this.tailBase = body.getChild("tailBase");
		this.tail = tailBase.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.5F, -4.0F, 4.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 21.0F, 0.0F, -0.0349F, 0.0F, 0.0F));

		PartDefinition tailBase = body.addOrReplaceChild("tailBase", CubeListBuilder.create().texOffs(34, 0).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.5F, -0.192F, 0.0F, 0.0F));

		PartDefinition tail = tailBase.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(30, 10).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 6.5F, 0.1396F, 0.0F, 0.0F));

		PartDefinition legLeft = body.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(54, 0).addBox(-1.5F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.5F, 2.0F, 0.6981F, 0.0F, -0.2618F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 11).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -4.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition spinesHead = head.addOrReplaceChild("spinesHead", CubeListBuilder.create().texOffs(24, 16).addBox(0.02F, 19.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.5F, 0.0F));

		PartDefinition spinesBody = body.addOrReplaceChild("spinesBody", CubeListBuilder.create().texOffs(45, 7).addBox(0.0F, -1.0F, -3.5F, 0.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.02F, -1.5F, 0.0F));

		PartDefinition armLeft = body.addOrReplaceChild("armLeft", CubeListBuilder.create().texOffs(46, 0).addBox(-1.5F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -2.0F, -0.1745F, 0.0F, -0.1745F));

		PartDefinition legRight = body.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(54, 0).mirror().addBox(-0.5F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 0.5F, 2.0F, 0.6981F, 0.0F, 0.2618F));

		PartDefinition armRight = body.addOrReplaceChild("armRight", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-0.5F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, -2.0F, -0.1745F, 0.0F, 0.1745F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isInWater()) {
			float speed = 1.0f;
			float degree = 0.8f;
			this.armLeft.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 1.0F;
			this.armLeft.xRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount + 0.8F;
			this.armRight.zRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 1.0F;
			this.armRight.xRot = Mth.cos(0.5F + limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount + 0.8F;
			this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
			this.head.yRot = Mth.cos(1.8F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
			this.body.xRot = 0.0F;
			this.head.xRot = -0.1F;
			this.legLeft.zRot = Mth.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 1.0F;
			this.legLeft.xRot = Mth.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount + 1.2F;
			this.legRight.zRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 1.0F;
			this.legRight.xRot = Mth.cos(1.5F + limbSwing * speed * 0.4F) * degree * -0.5F * limbSwingAmount + 1.2F;
			this.tailBase.xRot = 0.05F;
			this.tailBase.yRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount;
			this.tail.xRot = 0.0F;
			this.tail.yRot = Mth.cos(-0.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
		}

		else {
			limbSwingAmount = Mth.clamp(limbSwingAmount, -0.35f, 0.35f);
			float speed = 5.0f;
			float degree = 3.0f;
			this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
			this.armRight.xRot = Mth.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.2F;
			this.armLeft.xRot = Mth.cos(2.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.2F;
			this.legRight.xRot = Mth.cos(2.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.3F;
			this.legLeft.xRot = Mth.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.3F;
			this.head.yRot = Mth.cos(3.0F + limbSwing * speed * 0.2F) * 1.0F * 0.3F * limbSwingAmount;
			this.head.xRot = Mth.cos(3.0F + limbSwing * speed * 0.4F) * 1.0F * 0.4F * limbSwingAmount;
			this.tailBase.yRot = Mth.cos(limbSwing * speed * 0.2F) * 1.0F * 0.8F * limbSwingAmount;
			this.tailBase.xRot = Mth.cos(limbSwing * speed * 0.4F) * 1.0F * 0.4F * limbSwingAmount - 0.15F;
			this.tail.yRot = Mth.cos(limbSwing * speed * 0.2F) * 1.0F * 0.8F * limbSwingAmount;
			this.tail.xRot = Mth.cos(limbSwing * speed * 0.4F) * 1.0F * 0.4F * limbSwingAmount + 0.2F;
			this.armLeft.zRot = -0.17453292519943295F;
			this.legLeft.zRot = -0.2617993877991494F;
			this.armRight.zRot = 0.17453292519943295F;
			this.legRight.zRot = 0.2617993877991494F;
			this.body.y = 20.0F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}