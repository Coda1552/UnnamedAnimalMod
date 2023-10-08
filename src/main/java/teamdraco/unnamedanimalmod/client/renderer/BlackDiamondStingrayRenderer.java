package teamdraco.unnamedanimalmod.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.BlackDiamondStingrayModel;
import teamdraco.unnamedanimalmod.client.model.TomatoFrogModel;
import teamdraco.unnamedanimalmod.common.entity.BlackDiamondStingrayEntity;
import teamdraco.unnamedanimalmod.common.entity.TomatoFrogEntity;
import net.minecraft.client.renderer.entity.MobRenderer;

public class BlackDiamondStingrayRenderer extends MobRenderer<BlackDiamondStingrayEntity, BlackDiamondStingrayModel<BlackDiamondStingrayEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/black_diamond_stingray.png");

    public BlackDiamondStingrayRenderer(EntityRendererProvider.Context manager) {
        super(manager, new BlackDiamondStingrayModel<>(manager.bakeLayer(UAMModelLayers.BLACK_DIAMOND_STINGRAY)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(BlackDiamondStingrayEntity entity) {
        return TEXTURE;
    }
}
