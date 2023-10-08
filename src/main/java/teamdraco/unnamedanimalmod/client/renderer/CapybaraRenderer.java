package teamdraco.unnamedanimalmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.CapybaraModel;
import teamdraco.unnamedanimalmod.client.renderer.layer.CapybaraChestLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import teamdraco.unnamedanimalmod.common.entity.CapybaraEntity;

public class CapybaraRenderer extends MobRenderer<CapybaraEntity, CapybaraModel<CapybaraEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/capybara/capybara.png");
    private static final ResourceLocation MARIO = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/capybara/mario.png");

    public CapybaraRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new CapybaraModel<>(renderManagerIn.bakeLayer(UAMModelLayers.CAPYBARA)), 0.5F);
        addLayer(new CapybaraChestLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(CapybaraEntity entity) {
        if (entity.getName().getString().equals("Mario")) {
            return MARIO;
        }
        return TEXTURE;
    }

    @Override
    protected void setupRotations(CapybaraEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.scale(0.77f, 0.77f, 0.77f);
        if (entityLiving.isInWater() && !entityLiving.isBaby()) {
            matrixStackIn.translate(0, -0.625, 0);
        }
    }
}