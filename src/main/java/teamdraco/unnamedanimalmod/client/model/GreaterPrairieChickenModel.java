package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class GreaterPrairieChickenModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart wingLeft;
	private final ModelPart wingRight;
	private final ModelPart legLeft;
	private final ModelPart legRight;
	private final ModelPart tail;

	public GreaterPrairieChickenModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.wingLeft = body.getChild("wingLeft");
		this.wingRight = body.getChild("wingRight");
		this.legLeft = body.getChild("legLeft");
		this.legRight = body.getChild("legRight");
		this.tail = body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition legRight = body.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(37, 0).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 3.0F, 1.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(21, 1).addBox(-1.5F, -7.0F, -1.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -4.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition beak = head.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, -1.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition earLeft = head.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(14, -2).addBox(0.5F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -7.0F, 1.5F, 0.0F, -0.6109F, 0.1745F));

		PartDefinition cheeks = head.addOrReplaceChild("cheeks", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.5F, 1.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition earRight = head.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(14, -2).addBox(-0.5F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -7.0F, 1.5F, 0.0F, 0.6109F, -0.1745F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 3.6F, 1.1345F, 0.0F, 0.0F));

		PartDefinition legLeft = body.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(37, 0).mirror().addBox(-1.0F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, 3.0F, 1.0F));

		PartDefinition wingLeft = body.addOrReplaceChild("wingLeft", CubeListBuilder.create().texOffs(28, 13).addBox(0.0F, 0.0F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -3.0F, 0.0F));

		PartDefinition wingRight = body.addOrReplaceChild("wingRight", CubeListBuilder.create().texOffs(28, 13).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -3.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.0f;
		float degree = 1.0f;
		this.body.xRot = Mth.cos(f * speed * 0.4F) * degree * 0.2F * f1;
		this.head.xRot = Mth.cos(1.0F + f * speed * 0.4F) * degree * 0.2F * f1 + 0.5F;
		this.legRight.xRot = Mth.cos(1.0F + f * speed * 0.4F) * degree * 1.0F * f1;
		this.legLeft.xRot = Mth.cos(1.0F + f * speed * 0.4F) * degree * -1.0F * f1;
		this.tail.xRot = Mth.cos(1.0F + f * speed * 0.4F) * degree * 0.4F * f1 + 1.1F;
		this.wingRight.zRot = ageInTicks;
		this.wingLeft.zRot = -ageInTicks;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}