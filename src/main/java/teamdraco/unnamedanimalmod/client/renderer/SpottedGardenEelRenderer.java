package teamdraco.unnamedanimalmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.SpottedGardenEelHidingModel;
import teamdraco.unnamedanimalmod.client.model.SpottedGardenEelModel;
import teamdraco.unnamedanimalmod.common.entity.SpottedGardenEelEntity;

public class SpottedGardenEelRenderer extends MobRenderer<SpottedGardenEelEntity, EntityModel<SpottedGardenEelEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/spotted_garden_eel.png");
    private static SpottedGardenEelModel<SpottedGardenEelEntity> MODEL;
    private static SpottedGardenEelHidingModel<SpottedGardenEelEntity> HIDING_MODEL;

    public SpottedGardenEelRenderer(EntityRendererProvider.Context p_i48864_1_) {
        super(p_i48864_1_, new SpottedGardenEelModel<>(p_i48864_1_.bakeLayer(UAMModelLayers.SPOTTED_GARDEN_EEL)), 0.1F);
        MODEL = new SpottedGardenEelModel<>(p_i48864_1_.bakeLayer(UAMModelLayers.SPOTTED_GARDEN_EEL));
        HIDING_MODEL = new SpottedGardenEelHidingModel<>(p_i48864_1_.bakeLayer(UAMModelLayers.SPOTTED_GARDEN_EEL_HIDING));
    }

    @Override
    public ResourceLocation getTextureLocation(SpottedGardenEelEntity p_110775_1_) {
        return TEXTURE;
    }

    @Override
    public void render(SpottedGardenEelEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        if (entityIn.isHidden()) {
            model = HIDING_MODEL;
        }
        else {
            model = MODEL;
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}