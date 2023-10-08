package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class ElephantnoseFishModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart tail;

	public ElephantnoseFishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.5F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.5F, 0.0F));

		PartDefinition dorsalfin = body.addOrReplaceChild("dorsalfin", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -2.0F, -1.5F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 2.0F));

		PartDefinition pectoralfinright = body.addOrReplaceChild("pectoralfinright", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, 0.5F, -1.0F));

		PartDefinition analfin = body.addOrReplaceChild("analfin", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, 0.0F, -1.5F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 2.0F));

		PartDefinition pectoralfinleft = body.addOrReplaceChild("pectoralfinleft", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.5F, -1.0F));

		PartDefinition trunk = body.addOrReplaceChild("trunk", CubeListBuilder.create().texOffs(12, 0).addBox(-0.5F, 0.0F, -2.9F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -3.5F, 0.2618F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(9, 6).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.5F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = 1.0F;
		if (!entity.isInWater()) {
			f = 1.5F;
		}
		this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}