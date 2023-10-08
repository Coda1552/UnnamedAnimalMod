package teamdraco.unnamedanimalmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class SouthernRightWhaleModel<T extends LivingEntity> extends EntityModel<T> {
	private final ModelPart body;
	private final ModelPart right_fin;
	private final ModelPart left_fin;
	private final ModelPart tail_stock1;
	private final ModelPart tail_stock2;
	private final ModelPart flukes;
	private final ModelPart head;
	private final ModelPart jaw;

	public SouthernRightWhaleModel(ModelPart root) {
		this.body = root.getChild("body");
		this.left_fin = body.getChild("left_fin");
		this.right_fin = body.getChild("right_fin");
		this.tail_stock1 = body.getChild("tail_stock1");
		this.tail_stock2 = tail_stock1.getChild("tail_stock2");
		this.flukes = tail_stock2.getChild("flukes");
		this.head = body.getChild("head");
		this.jaw = head.getChild("jaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-36.0F, -40.0F, -45.0F, 74.0F, 64.0F, 90.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_stock1 = body.addOrReplaceChild("tail_stock1", CubeListBuilder.create().texOffs(256, 200).addBox(-22.0F, -22.5F, -2.0F, 44.0F, 45.0F, 58.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.5F, 46.0F));

		PartDefinition tail_stock2 = tail_stock1.addOrReplaceChild("tail_stock2", CubeListBuilder.create().texOffs(238, 26).addBox(-11.0F, -13.0F, 0.0F, 22.0F, 26.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 55.0F));

		PartDefinition flukes = tail_stock2.addOrReplaceChild("flukes", CubeListBuilder.create().texOffs(0, 154).addBox(-36.0F, -2.0F, 0.0F, 72.0F, 4.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 31.0F));

		PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(238, 0).addBox(0.0F, -2.0F, -11.0F, 40.0F, 4.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(38.0F, 15.0F, -25.0F));

		PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(238, 0).mirror().addBox(-40.0F, -2.0F, -11.0F, 40.0F, 4.0F, 22.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-36.0F, 15.0F, -25.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(266, 92).addBox(-29.0F, -24.0F, -62.0F, 58.0F, 46.0F, 62.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -8.0F, -44.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 186).addBox(-29.0F, -24.0F, -61.0F, 62.0F, 33.0F, 66.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 16.0F, -3.0F));

		return LayerDefinition.create(meshdefinition, 512, 304);
	}

	@Override
	public void setupAnim(T entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
		float motionY = (float) entity.getDeltaMovement().y;

		this.body.xRot = headPitch * ((float)Math.PI / 180F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);

		if (entity.distanceToSqr(entity.getDeltaMovement()) > 1.0E-7D) {
			if(!entity.isInWater() && !entity.isOnGround()) {
				this.body.zRot = (float) Math.toRadians(-motionY * 180) * 0.8f;
			}
			else {
				float speed = 0.6f;
				float degree = 0.6f;
				this.body.zRot = 0;
				this.body.xRot += Mth.cos(f * speed * 0.4F) * degree * 0.25F * f1 + 0.01F;
				this.right_fin.zRot = Mth.cos(1.0F + f * speed * 0.4F) * degree * 0.6F * f1 + 0.2F;
				this.left_fin.zRot = Mth.cos(1.0F + f * speed * 0.4F) * degree * -0.6F * f1 - 0.2F;
				this.tail_stock1.xRot = Mth.cos(-1.0F + f * speed * 0.4F) * degree * 0.5F * f1;
				this.tail_stock2.xRot = Mth.cos(-2.0F + f * speed * 0.4F) * degree * 0.5F * f1;
				this.flukes.xRot = Mth.cos(-2.0F + f * speed * 0.4F) * degree * 0.8F * f1;
				this.head.xRot = Mth.cos(1.0F + f * speed * 0.4F) * degree * 0.1F * f1;
				this.jaw.xRot = Mth.cos(1.0F + f * speed * 0.4F) * degree * 0.1F * f1 + 0.05F;
			}
		}
		else {
			this.body.zRot = 0;
			this.left_fin.yRot = 0;
			this.left_fin.zRot = 0;
			this.right_fin.yRot = 0;
			this.right_fin.zRot = 0;
			this.tail_stock1.xRot = 0;
			this.tail_stock2 .xRot = 0;
			this.flukes.xRot = 0;
			this.body.xRot = 0;
			this.jaw.xRot = 0;
			this.head.xRot = 0;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}