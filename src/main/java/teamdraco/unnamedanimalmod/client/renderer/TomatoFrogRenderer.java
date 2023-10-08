package teamdraco.unnamedanimalmod.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.TomatoFrogModel;
import teamdraco.unnamedanimalmod.common.entity.TomatoFrogEntity;
import net.minecraft.client.renderer.entity.MobRenderer;

public class TomatoFrogRenderer extends MobRenderer<TomatoFrogEntity, TomatoFrogModel<TomatoFrogEntity>> {
    protected static final ResourceLocation ADULT = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/tomato_frog/adult.png");

    public TomatoFrogRenderer(EntityRendererProvider.Context manager) {
        super(manager, new TomatoFrogModel<>(manager.bakeLayer(UAMModelLayers.TOMATO_FROG)), 0.2f);
    }

    @Override
    public ResourceLocation getTextureLocation(TomatoFrogEntity entity) {
        return ADULT;
    }
}