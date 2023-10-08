package teamdraco.unnamedanimalmod.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.CapybaraModel;
import teamdraco.unnamedanimalmod.common.entity.CapybaraEntity;

public class CapybaraChestLayer extends RenderLayer<CapybaraEntity, CapybaraModel<CapybaraEntity>> {
    private static final ResourceLocation SINGLE_CHEST = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/capybara/single_chest.png");
    private static final ResourceLocation DOUBLE_CHEST = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/capybara/double_chest.png");

    public CapybaraChestLayer(RenderLayerParent<CapybaraEntity, CapybaraModel<CapybaraEntity>> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack p_225628_1_, MultiBufferSource bufferProvider, int p_225628_3_, CapybaraEntity entity, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        final int chestCount = entity.getChestCount();
        if (chestCount > 0) {
            CapybaraModel<CapybaraEntity> model = getParentModel();
            model.prepareMobModel(entity, p_225628_5_, p_225628_6_, p_225628_7_);
            model.setupAnim(entity, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);
            model.renderToBuffer(p_225628_1_, bufferProvider.getBuffer(RenderType.entityCutoutNoCull(chestCount > 1 ? DOUBLE_CHEST : SINGLE_CHEST)), p_225628_3_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
