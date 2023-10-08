package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class MangroveSnakeModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart head;
	private final ModelPart body1;
	private final ModelPart body2;
	private final ModelPart body3;
	private final ModelPart tail;

	public MangroveSnakeModel(ModelPart root) {
		this.head = root.getChild("head");
		this.body1 = head.getChild("body1");
		this.body2 = body1.getChild("body2");
		this.body3 = body2.getChild("body3");
		this.tail = body3.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -1.0F, -5.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -9.0F));

		PartDefinition body1 = head.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition body2 = body1.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(24, 21).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(24, 9).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition tail = body3.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(13, 24).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 9.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, 0.0F, -5.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition tongue = jaw.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(8, 5).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.5f;
		float degree = 1.5f;
		this.body1.yRot = Mth.cos(limbSwing * speed * 0.3F) * degree * -0.4F * limbSwingAmount;
		this.head.yRot = Mth.cos(2.0F + limbSwing * speed * 0.3F) * degree * 0.3F * limbSwingAmount;
		this.body2.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.3F) * degree * 0.8F * limbSwingAmount;
		this.body3.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.3F) * degree * -0.8F * limbSwingAmount;
		this.tail.yRot = Mth.cos(limbSwing * speed * 0.3F) * degree * 0.8F * limbSwingAmount;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}