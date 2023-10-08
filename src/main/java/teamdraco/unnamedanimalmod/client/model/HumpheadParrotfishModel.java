package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class HumpheadParrotfishModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart finTail;
	private final ModelPart finBottomLeft;
	private final ModelPart finBottomRight;
	private final ModelPart finPectoralLeft;
	private final ModelPart finPectoralRight;

	public HumpheadParrotfishModel(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = body.getChild("tail");
		this.finTail = tail.getChild("finTail");
		this.finBottomLeft = body.getChild("finBottomLeft");
		this.finBottomRight = body.getChild("finBottomRight");
		this.finPectoralLeft = body.getChild("finPectoralLeft");
		this.finPectoralRight = body.getChild("finPectoralRight");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.5F, -11.0F, 6.0F, 11.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.5F, 0.0F));

		PartDefinition finAnal = body.addOrReplaceChild("finAnal", CubeListBuilder.create().texOffs(0, 23).addBox(0.0F, -1.0F, -4.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.5F, 6.0F));

		PartDefinition finBottomRight = body.addOrReplaceChild("finBottomRight", CubeListBuilder.create().texOffs(0, 37).addBox(0.0F, 0.0F, -0.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.5F, -3.0F));

		PartDefinition finDorsal = body.addOrReplaceChild("finDorsal", CubeListBuilder.create().texOffs(38, 17).addBox(0.0F, -5.0F, -6.5F, 0.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.5F, 1.5F));

		PartDefinition finPectoralRight = body.addOrReplaceChild("finPectoralRight", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, 0.0F, -3.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 3.0F, -1.0F));

		PartDefinition finBottomLeft = body.addOrReplaceChild("finBottomLeft", CubeListBuilder.create().texOffs(0, 37).mirror().addBox(0.0F, 0.0F, -0.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, 5.5F, -3.0F));

		PartDefinition finPectoralLeft = body.addOrReplaceChild("finPectoralLeft", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(0.0F, 0.0F, -3.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 3.0F, -1.0F));

		PartDefinition hump = body.addOrReplaceChild("hump", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, -8.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(38, 0).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

		PartDefinition finTail = tail.addOrReplaceChild("finTail", CubeListBuilder.create().texOffs(21, 26).addBox(0.0F, -6.5F, 0.0F, 0.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 104, 52);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 0.8f;
		float degree = 0.7f;
		this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
		this.tail.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount;
		this.finTail.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount;
		this.finBottomLeft.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount - 0.5f;
		this.finBottomRight.zRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.5f;
		this.finPectoralLeft.zRot = Mth.cos(2.0F + 20 * speed * 0.4F) * 1.2F * 0.4F * limbSwingAmount + 0.4F;
		this.finPectoralRight.zRot = Mth.cos(2.0F + 20 * speed * 0.4F) * degree * -0.4F * limbSwingAmount - 0.4F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}