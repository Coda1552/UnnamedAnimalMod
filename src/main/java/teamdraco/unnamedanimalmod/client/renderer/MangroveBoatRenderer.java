package teamdraco.unnamedanimalmod.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import net.minecraft.client.renderer.entity.BoatRenderer;

public class MangroveBoatRenderer extends BoatRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/boat/mangrove.png");

    public MangroveBoatRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, false);
    }

    @Override
    public ResourceLocation getTextureLocation(Boat entity) {
        return TEXTURE;
    }
}
