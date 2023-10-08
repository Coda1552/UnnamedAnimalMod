package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class BananaSlugModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart eyes;

	public BananaSlugModel(ModelPart root) {
		this.body = root.getChild("body");
		this.eyes = body.getChild("eyes");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.5F, -4.0F, 4.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.5F, 0.0F));

		PartDefinition eyes = body.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 3).addBox(-1.5F, -2.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -3.5F));
		PartDefinition feelers = body.addOrReplaceChild("feelers", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -0.5F, -1.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.0f;
		float degree = 4.0f;

		if (entity.isAlive()) {
			this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
			this.eyes.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount + 0.2F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}