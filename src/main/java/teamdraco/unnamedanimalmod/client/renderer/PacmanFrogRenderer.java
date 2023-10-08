package teamdraco.unnamedanimalmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.PacmanFrogModel;
import teamdraco.unnamedanimalmod.common.entity.PacmanFrogEntity;
import net.minecraft.client.renderer.entity.MobRenderer;

public class PacmanFrogRenderer extends MobRenderer<PacmanFrogEntity, PacmanFrogModel<PacmanFrogEntity>> {
    protected static final ResourceLocation ADULT = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/pacman_frog/adult.png");

    public PacmanFrogRenderer(EntityRendererProvider.Context manager) {
        super(manager, new PacmanFrogModel<>(manager.bakeLayer(UAMModelLayers.PACMAN_FROG)), 0.2f);
    }

    @Override
    public ResourceLocation getTextureLocation(PacmanFrogEntity entity) {
        return ADULT;
    }

    protected void setupRotations(PacmanFrogEntity entity, PoseStack matrix, float var1, float var2, float var3) {
        super.setupRotations(entity, matrix, var1, var2, var3);
        if (!entity.isBaby()) {
            matrix.translate(-0.03,0, 0);
        }
        if (entity.isHidden()) {
            matrix.translate(0, -0.3, 0);
            matrix.mulPose(Vector3f.XP.rotationDegrees(25));
        }
    }
}