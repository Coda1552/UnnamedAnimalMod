package teamdraco.unnamedanimalmod.client.renderer;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import teamdraco.unnamedanimalmod.UAMModelLayers;
import teamdraco.unnamedanimalmod.UnnamedAnimalMod;
import teamdraco.unnamedanimalmod.client.model.SouthernRightWhaleModel;
import teamdraco.unnamedanimalmod.common.entity.SouthernRightWhaleEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class SouthernRightWhaleRenderer extends MobRenderer<SouthernRightWhaleEntity, SouthernRightWhaleModel<SouthernRightWhaleEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/southern_right_whale/southern_right_whale_1.png"));
        hashMap.put(1, new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/southern_right_whale/southern_right_whale_2.png"));
        hashMap.put(2, new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/southern_right_whale/southern_right_whale_3.png"));
        hashMap.put(3, new ResourceLocation(UnnamedAnimalMod.MOD_ID, "textures/entity/southern_right_whale/southern_right_whale_4.png"));
    });

    public SouthernRightWhaleRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SouthernRightWhaleModel<>(renderManagerIn.bakeLayer(UAMModelLayers.SOUTHERN_RIGHT_WHALE)), 2.25F);
    }

    public ResourceLocation getTextureLocation(SouthernRightWhaleEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}