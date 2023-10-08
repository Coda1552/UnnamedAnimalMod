package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class LeafySeadragonModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart neck;

	public LeafySeadragonModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = body.getChild("tail");
		this.neck = body.getChild("neck");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.5F, -3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 23.5F, -1.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(18, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 2.0F, -1.0472F, 0.0F, 0.0F));

		PartDefinition leafTail = tail.addOrReplaceChild("leafTail", CubeListBuilder.create().texOffs(28, 8).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -1.0F));

		PartDefinition leafLeftBottom = body.addOrReplaceChild("leafLeftBottom", CubeListBuilder.create().texOffs(0, 4).mirror().addBox(0.0F, 0.0F, -2.5F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.5F, -0.5F, 0.0F, 0.0F, -0.2618F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(34, 0).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -3.2F, 0.4363F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(12, 0).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.3F, -0.9739F, 0.0F, 0.0F));

		PartDefinition leafHead = head.addOrReplaceChild("leafHead", CubeListBuilder.create().texOffs(0, 17).addBox(0.0F, 18.5F, -4.2F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -22.5F, 2.2F));

		PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(44, 0).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -2.0F));

		PartDefinition leafRightBottom = body.addOrReplaceChild("leafRightBottom", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, 0.0F, -2.5F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.5F, -0.5F, 0.0F, 0.0F, 0.2618F));

		PartDefinition leafLeftTop = body.addOrReplaceChild("leafLeftTop", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -4.0F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.5F, -0.5F, 0.0F, 0.0F, 0.5236F));

		PartDefinition leafRightTop = body.addOrReplaceChild("leafRightTop", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -4.0F, -2.5F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.5F, -0.5F, 0.0F, 0.0F, -0.5236F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 2.8f;
		float degree = 1.6f;
		this.body.xRot = Mth.cos(f * speed * 0.4F) * degree * 0.2F * f1 + 0.5F;
		this.tail.xRot = Mth.cos(f * speed * 0.4F) * degree * 0.4F * f1 - 1.0F;
		this.neck.xRot = Mth.cos(f * speed * 0.4F) * degree * 0.4F * f1 + 0.6F;
		this.body.yRot = Mth.cos(f * speed * 0.2F) * degree * 0.4F * f1;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}