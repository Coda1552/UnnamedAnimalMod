package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class PacmanFrogModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart frontRightLeg;
	private final ModelPart frontLeftLeg;
	private final ModelPart backRightLeg;
	private final ModelPart backLeftLeg;

	public PacmanFrogModel(ModelPart root) {
		this.body = root.getChild("body");
		this.frontRightLeg = body.getChild("frontRightLeg");
		this.frontLeftLeg = body.getChild("frontLeftLeg");
		this.backRightLeg = body.getChild("backRightLeg");
		this.backLeftLeg = body.getChild("backLeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -2.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 19.5F, 0.0F));

		PartDefinition frontRightLeg = body.addOrReplaceChild("frontRightLeg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-0.5F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.2F, 2.5F, -1.5F, -1.0472F, 0.1745F, 0.0F));

		PartDefinition backLeftLeg = body.addOrReplaceChild("backLeftLeg", CubeListBuilder.create().texOffs(18, 13).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 3.0F, 2.5F, 0.0F, -0.0873F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -1.0F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -3.0F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create().texOffs(0, 22).addBox(-4.0F, 16.5F, -2.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -19.5F, 0.0F));

		PartDefinition frontLeftLeg = body.addOrReplaceChild("frontLeftLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.2F, 2.5F, -1.5F, -1.0472F, 0.0F, 0.0F));

		PartDefinition backRightLeg = body.addOrReplaceChild("backRightLeg", CubeListBuilder.create().texOffs(18, 13).mirror().addBox(-0.5F, -0.5F, -2.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 3.0F, 2.5F, 0.0F, 0.0873F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.0f;
		float degree = 1.0f;
		this.body.yRot = Mth.cos(1.5F + f * speed * 0.4F) * degree * 0.4F * f1;
		this.frontRightLeg.xRot = Mth.cos(f * speed * 0.4F) * degree * 0.8F * f1 - 0.2F;
		this.frontLeftLeg.xRot = Mth.cos(f * speed * 0.4F) * degree * -0.8F * f1 - 0.2F;
		this.backLeftLeg.xRot = Mth.cos(f * speed * 0.4F) * degree * 0.8F * f1 + 0.2F;
		this.backRightLeg.xRot = Mth.cos(f * speed * 0.4F) * degree * -0.8F * f1 + 0.2F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}