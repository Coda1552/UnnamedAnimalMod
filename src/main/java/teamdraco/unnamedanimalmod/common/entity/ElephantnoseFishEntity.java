package teamdraco.unnamedanimalmod.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamdraco.unnamedanimalmod.init.UAMItems;

public class ElephantnoseFishEntity extends AbstractSchoolingFish {

    public ElephantnoseFishEntity(EntityType<? extends ElephantnoseFishEntity> fish, Level world) {
        super(fish, world);
    }

    @Override
    public int getMaxSchoolSize() {
        return 8;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(UAMItems.ELEPHANTNOSE_FISH_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }
}