package teamdraco.unnamedanimalmod.common.entity.item;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.unnamedanimalmod.common.entity.MarineIguanaEntity;
import teamdraco.unnamedanimalmod.init.UAMEntities;
import teamdraco.unnamedanimalmod.init.UAMItems;

public class MarineIguanaEggEntity extends ThrowableItemProjectile {

    public MarineIguanaEggEntity(EntityType<? extends MarineIguanaEggEntity> p_i50154_1_, Level p_i50154_2_) {
        super(p_i50154_1_, p_i50154_2_);
    }

    public MarineIguanaEggEntity(Level worldIn, LivingEntity throwerIn) {
        super(UAMEntities.MARINE_IGUANA_EGG.get(), throwerIn, worldIn);
    }

    public MarineIguanaEggEntity(Level worldIn, double x, double y, double z) {
        super(UAMEntities.MARINE_IGUANA_EGG.get(), x, y, z, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            for(int i = 0; i < 6; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }

    }

    protected void onHitEntity(EntityHitResult p_213868_1_) {
        super.onHitEntity(p_213868_1_);
        p_213868_1_.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 0.0F);
    }

    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }

                for(int j = 0; j < i; ++j) {
                    MarineIguanaEntity entity = UAMEntities.MARINE_IGUANA.get().create(this.level);
                    entity.setAge(-24000);
                    entity.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                    this.level.addFreshEntity(entity);
                }
            }

            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }

    protected Item getDefaultItem() {
        return UAMItems.MARINE_IGUANA_EGG.get();
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(UAMItems.MARINE_IGUANA_EGG.get());
    }
}
