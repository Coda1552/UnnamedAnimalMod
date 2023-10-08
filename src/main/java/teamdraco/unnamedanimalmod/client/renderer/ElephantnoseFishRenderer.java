package teamdraco.unnamedanimalmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.ElephantnoseFishModel;
import teamdraco.unnamedanimalmod.common.entity.ElephantnoseFishEntity;
import net.minecraft.client.renderer.entity.MobRenderer;

public class ElephantnoseFishRenderer extends MobRenderer<ElephantnoseFishEntity, ElephantnoseFishModel<ElephantnoseFishEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/elephantnose_fish.png");

    public ElephantnoseFishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ElephantnoseFishModel<>(renderManagerIn.bakeLayer(UAMModelLayers.ELEPHANTNOSE_FISH)), 0.1F);
    }

    public ResourceLocation getTextureLocation(ElephantnoseFishEntity entity) {
        return TEXTURE;
    }

    protected void setupRotations(ElephantnoseFishEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}