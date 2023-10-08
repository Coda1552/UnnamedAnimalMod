package teamdraco.unnamedanimalmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.HumpheadParrotfishModel;
import teamdraco.unnamedanimalmod.common.entity.HumpheadParrotfishEntity;
import net.minecraft.client.renderer.entity.MobRenderer;

public class HumpheadParrotfishRenderer extends MobRenderer<HumpheadParrotfishEntity, HumpheadParrotfishModel<HumpheadParrotfishEntity>> {
    private static final ResourceLocation ADULT = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/humphead_parrotfish/adult.png");

    public HumpheadParrotfishRenderer(EntityRendererProvider.Context manager) {
        super(manager, new HumpheadParrotfishModel<>(manager.bakeLayer(UAMModelLayers.HUMPHEAD_PARROTFISH)), 0.5f);
    }

    public ResourceLocation getTextureLocation(HumpheadParrotfishEntity entity) {
        return ADULT;
    }

    protected void setupRotations(HumpheadParrotfishEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * Mth.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }

    }
}