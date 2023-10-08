package teamdraco.unnamedanimalmod.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.BananaSlugModel;
import teamdraco.unnamedanimalmod.common.entity.BananaSlugEntity;
import net.minecraft.client.renderer.entity.MobRenderer;

public class BananaSlugRenderer extends MobRenderer<BananaSlugEntity, BananaSlugModel<BananaSlugEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/banana_slug.png");

    public BananaSlugRenderer(EntityRendererProvider.Context manager) {
        super(manager, new BananaSlugModel<>(manager.bakeLayer(UAMModelLayers.BANANA_SLUG)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(BananaSlugEntity entity) {
        return TEXTURE;
    }
}
