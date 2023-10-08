package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class MudskipperModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart pectoralfinleft;
	private final ModelPart pectoralfinright;
	private final ModelPart tailfin;
	private final ModelPart pelvicfins;
	private final ModelPart dorsalfinfirst;

	public MudskipperModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.pectoralfinleft = head.getChild("pectoralfinleft");
		this.pectoralfinright = head.getChild("pectoralfinright");
		this.tailfin = body.getChild("tailfin");
		this.pelvicfins = body.getChild("pelvicfins");
		this.dorsalfinfirst = body.getChild("dorsalfinfirst");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.5F, -4.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.5F, 1.0F));

		PartDefinition pelvicfins = body.addOrReplaceChild("pelvicfins", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, 0.5F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -3.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(18, 9).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition pectoralfinleft = head.addOrReplaceChild("pectoralfinleft", CubeListBuilder.create().texOffs(9, 0).mirror().addBox(-0.5F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 1.0F, 0.0F));

		PartDefinition pectoralfinright = head.addOrReplaceChild("pectoralfinright", CubeListBuilder.create().texOffs(9, 0).addBox(-2.5F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(10, 11).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -2.5F));

		PartDefinition dorsalfinsecond = body.addOrReplaceChild("dorsalfinsecond", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.5F, -1.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.5F));

		PartDefinition tailfin = body.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -2.5F, -0.5F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 4.0F));

		PartDefinition analfin = body.addOrReplaceChild("analfin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -2.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 1.0F));

		PartDefinition dorsalfinfirst = body.addOrReplaceChild("dorsalfinfirst", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, -2.5F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 32, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 1.0f;
		float degree = 1.0f;
		if (entity.isInWater()) {
			this.body.yRot = Mth.cos(ageInTicks * speed * 0.6F) * degree * 0.3F * 0.3F;
			this.body.zRot = Mth.cos(limbSwing * speed * 0.8F) * degree * 0.25F * limbSwingAmount;
			this.head.yRot = Mth.cos(limbSwing * speed * 0.8F) * degree * 0.3F * limbSwingAmount;
			this.head.zRot = Mth.cos(limbSwing * speed * 0.8F) * degree * 0.25F * limbSwingAmount;
			this.tailfin.yRot = Mth.cos(ageInTicks * speed * 0.6F) * degree * 0.4F;
			this.pelvicfins.xRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 1.0F;
			this.pectoralfinright.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
			this.pectoralfinright.xRot = 0.0F;
			this.pectoralfinleft.zRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount;
			this.pectoralfinleft.xRot = 0.0F;
			this.dorsalfinfirst.xRot = limbSwingAmount - 0.5F;
		}
		else {
			this.body.yRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
			this.body.zRot = 0.0F;
			this.pectoralfinright.yRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.25F;
			this.pectoralfinleft.yRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount - 0.25F;
			this.head.yRot = Mth.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
			this.head.zRot = 0.0F;
			this.tailfin.yRot = Mth.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
			this.pectoralfinright.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.25F;
			this.pectoralfinleft.xRot = Mth.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount + 0.25F;
			this.pelvicfins.xRot = limbSwingAmount + 1.2F;
			this.dorsalfinfirst.xRot = 0.0F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}