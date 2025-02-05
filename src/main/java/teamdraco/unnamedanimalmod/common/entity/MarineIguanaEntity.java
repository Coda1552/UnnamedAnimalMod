package teamdraco.unnamedanimalmod.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import teamdraco.unnamedanimalmod.init.UAMItems;
import teamdraco.unnamedanimalmod.init.UAMSounds;

import javax.annotation.Nullable;
import java.util.UUID;

public class MarineIguanaEntity extends Animal {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(MarineIguanaEntity.class, EntityDataSerializers.INT);
    public int timeUntilNextSneeze = this.random.nextInt(8000) + 8000;
    private UUID lightningUUID;

    public MarineIguanaEntity(EntityType<? extends MarineIguanaEntity> type, Level world) {
        super(type, world);
        this.moveControl = new MarineIguanaMoveControl(this);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public float getStepHeight() {
        return 1.0F;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 360;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 2.0D, 1) {
            @Override
            public boolean canUse() {
                return super.canUse() && isInWater();
            }
        });
        this.goalSelector.addGoal(2, new MarineIguanaEntity.WanderGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(3, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.SEAGRASS;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.MOVEMENT_SPEED, 0.15);
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader worldIn) {
        if (worldIn.getFluidState(pos).is(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return 1.5F;
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide && this.isAlive() && !this.isBaby() && --this.timeUntilNextSneeze <= 0) {
            if (!this.isSilent()) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.LLAMA_SPIT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }
            this.spawnAtLocation(new ItemStack(UAMItems.SALT.get(), random.nextInt(4)));
            this.timeUntilNextSneeze = this.random.nextInt(8000) + 6000;
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (dataTag == null) {
            setVariant(random.nextInt(4));
        } else {
            if (dataTag.contains("Variant", 3)){
                this.setVariant(dataTag.getInt("Variant"));
            }
        }
        return spawnDataIn;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
        compound.putInt("SneezeTime", this.timeUntilNextSneeze);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
        if (compound.contains("SneezeTime")) {
            this.timeUntilNextSneeze = compound.getInt("SneezeTime");
        }
   }

    public void thunderHit(ServerLevel p_241841_1_, LightningBolt p_241841_2_) {
        UUID uuid = p_241841_2_.getUUID();
        if (!uuid.equals(this.lightningUUID)) {
            this.setVariant(4);
            this.lightningUUID = uuid;
            this.playSound(UAMSounds.MARINE_IGUANA_TRANSFORMS.get(), 2.0F, 1.0F);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        this.spawnAtLocation(new ItemStack(UAMItems.MARINE_IGUANA_EGG.get(), getRandom().nextInt(1) + 1));
        this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        ((Animal) p_241840_2_).resetLove();
        return null;
    }

    protected SoundEvent getAmbientSound() {
        return UAMSounds.MARINE_IGUANA_AMBIENT.get();
    }

    protected SoundEvent getDeathSound() {
        return UAMSounds.MARINE_IGUANA_DEATH.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return UAMSounds.MARINE_IGUANA_HURT.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.2F;
    }

    static class MarineIguanaMoveControl extends MoveControl {
        private final MarineIguanaEntity iguana;

        MarineIguanaMoveControl(MarineIguanaEntity iguana) {
            super(iguana);
            this.iguana = iguana;
        }

        private void updateSpeed() {
            if (this.iguana.isInWater()) {
                this.iguana.setDeltaMovement(this.iguana.getDeltaMovement().add(0.0D, 0.005D, 0.0D));

                if (this.iguana.isBaby()) {
                    this.iguana.setSpeed(Math.max(this.iguana.getSpeed() / 3.0F, 0.06F));
                }
            } else if (this.iguana.onGround) {
                this.iguana.setSpeed(Math.max(this.iguana.getSpeed() / 1.0F, 0.06F));
            }

        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.iguana.getNavigation().isDone()) {
                double d0 = this.wantedX - this.iguana.getX();
                double d1 = this.wantedY - this.iguana.getY();
                double d2 = this.wantedZ - this.iguana.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 = d1 / d3;
                float f = (float)(Math.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.iguana.yRot = this.rotlerp(this.iguana.yRot, f, 90.0F);
                this.iguana.yBodyRot = this.iguana.yRot;
                float f1 = (float)(this.speedModifier * this.iguana.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.iguana.setSpeed(Mth.lerp(0.125F, this.iguana.getSpeed(), f1));
                this.iguana.setDeltaMovement(this.iguana.getDeltaMovement().add(0.0D, (double)this.iguana.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.iguana.setSpeed(0.0F);
            }
        }
    }

    static class WanderGoal extends RandomStrollGoal {
        private WanderGoal(PathfinderMob iguana, double speedIn, int chance) {
            super(iguana, speedIn, chance);
        }

        public boolean canUse() {
            return !this.mob.isInWater() && super.canUse();
        }
    }

}