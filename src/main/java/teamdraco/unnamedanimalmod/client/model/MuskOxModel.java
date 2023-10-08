package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class MuskOxModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart hips;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart legRight;
	private final ModelPart legLeft;
	private final ModelPart armRight;
	private final ModelPart armLeft;

	public MuskOxModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.armLeft = body.getChild("armLeft");
		this.armRight = body.getChild("armRight");
		this.hips = body.getChild("hips");
		this.tail = hips.getChild("tail");
		this.legLeft = hips.getChild("legLeft");
		this.legRight = hips.getChild("legRight");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -8.0F, 14.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -7.0F));

		PartDefinition hips = body.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(60, 0).addBox(-5.0F, -6.0F, 0.0F, 10.0F, 12.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 8.0F));

		PartDefinition tail = hips.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 2.0F, -12.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 21.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition shagBody_1 = hips.addOrReplaceChild("shagBody_1", CubeListBuilder.create().texOffs(46, 41).addBox(-5.0F, 0.0F, -7.0F, 10.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 7.0F));

		PartDefinition legLeft = hips.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 6.0F, 10.5F));

		PartDefinition legRight = hips.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 6.0F, 10.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(54, 26).addBox(-4.5F, -4.5F, -6.0F, 9.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -8.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(44, 0).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -6.0F));

		PartDefinition hornBase = head.addOrReplaceChild("hornBase", CubeListBuilder.create().texOffs(0, 53).addBox(-5.5F, -1.5F, -1.5F, 11.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -2.0F));

		PartDefinition hornRight = hornBase.addOrReplaceChild("hornRight", CubeListBuilder.create().texOffs(45, 9).mirror().addBox(-4.5F, 7.0F, -16.5F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -5.5F, 15.0F));

		PartDefinition hornLeft = hornBase.addOrReplaceChild("hornLeft", CubeListBuilder.create().texOffs(45, 9).addBox(-0.5F, 0.0F, -1.5F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 1.5F, 0.0F));

		PartDefinition armLeft = body.addOrReplaceChild("armLeft", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 8.0F, -3.5F));

		PartDefinition armRight = body.addOrReplaceChild("armRight", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, 0.0F, -2.5F, 4.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 8.0F, -3.5F));

		PartDefinition shagBody = body.addOrReplaceChild("shagBody", CubeListBuilder.create().texOffs(0, 32).addBox(-7.0F, 0.0F, -8.0F, 14.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.legRight.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.legLeft.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.armRight.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.armLeft.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.tail.zRot = Mth.cos(limbSwing * 0.6662F) * 0.6F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}