package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class BlackDiamondStingrayModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart finRight;
	private final ModelPart finLeft;
	private final ModelPart tail;

	public BlackDiamondStingrayModel(ModelPart root) {
		this.body = root.getChild("body");
		this.finRight = body.getChild("finRight");
		this.finLeft = body.getChild("finLeft");
		this.tail = body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, -2.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 7.0F));

		PartDefinition finRight = body.addOrReplaceChild("finRight", CubeListBuilder.create().texOffs(27, 12).mirror().addBox(0.0F, -1.0F, -5.0F, 3.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 0.0F, 1.0F));

		PartDefinition bodyBottom = body.addOrReplaceChild("bodyBottom", CubeListBuilder.create().texOffs(19, 0).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition finLeft = body.addOrReplaceChild("finLeft", CubeListBuilder.create().texOffs(27, 12).addBox(-3.0F, -1.0F, -5.0F, 3.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 5.0f;
		float degree = 2.0f;

		this.finLeft.zRot = Mth.cos(f * speed * 0.2F) * degree * 0.4F * f1 + 0.1F;
		this.finRight.zRot = Mth.cos(f * speed * 0.2F) * degree * -0.4F * f1 - 0.1F;
		this.tail.yRot = Mth.cos(f * speed * 0.3F) * degree * 0.2F * f1;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}