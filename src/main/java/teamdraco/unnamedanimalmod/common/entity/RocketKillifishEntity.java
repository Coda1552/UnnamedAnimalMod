package teamdraco.unnamedanimalmod.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamdraco.unnamedanimalmod.init.UAMItems;

import javax.annotation.Nullable;

public class RocketKillifishEntity extends AbstractSchoolingFish {
    public RocketKillifishEntity(EntityType<? extends AbstractSchoolingFish> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(UAMItems.ROCKET_KILLIFISH_BUCKET.get());
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    @Override
    public int getMaxSchoolSize() {
        return 15;
    }

}
