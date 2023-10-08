package teamdraco.unnamedanimalmod.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import teamdraco.unnamedanimalmod.common.entity.util.ai.WhaleBreachGoal;
import teamdraco.unnamedanimalmod.init.UAMEntities;
import teamdraco.unnamedanimalmod.init.UAMSounds;

import javax.annotation.Nullable;

public class SouthernRightWhaleEntity extends Animal {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(SouthernRightWhaleEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(SouthernRightWhaleEntity.class, EntityDataSerializers.INT);
    protected boolean noBlow = false;

    public SouthernRightWhaleEntity(EntityType<? extends SouthernRightWhaleEntity> type, Level world) {
        super(type, world);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.1F, 0.5F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2F, true));
        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(5, new WhaleBreachGoal(this, 10));
        this.goalSelector.addGoal(6, new FollowBoatGoal(this));
        this.goalSelector.addGoal(7, new AvoidEntityGoal<>(this, Guardian.class, 8.0F, 1.0D, 1.0D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 120.0D).add(Attributes.MOVEMENT_SPEED, 1.2F).add(Attributes.ATTACK_DAMAGE, 4.0F);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();
            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, entityIn);
        }

        return flag;
    }

    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int p_211137_1_) {
        this.entityData.set(MOISTNESS_LEVEL, p_211137_1_);
    }

    @Override
    protected PathNavigation createNavigation(Level worldIn) {
        return new WaterBoundPathNavigation(this, worldIn);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.setAirSupply(this.getMaxAirSupply());
        this.xRot = 0.0F;
        if (dataTag == null) {
            if (random.nextFloat() > 0.1D) {
                setVariant(random.nextInt(3));
            }
            else {
                setVariant(3);
            }
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(MOISTNESS_LEVEL, 2400);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return UAMEntities.SOUTHERN_RIGHT_WHALE.get().create(this.level);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setVariant(compound.getInt("Variant"));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.SALMON;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.4F;
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos pos = blockPosition().above();

        if (!noBlow && level.getBlockState(pos).getBlock() == Blocks.AIR && wasTouchingWater){
            playBlowAnimation();
            noBlow = true;
        }
        if (wasTouchingWater && level.getBlockState(pos).getBlock() != Blocks.AIR){
            noBlow = false;
        }
        if (this.isInWaterRainOrBubble()) {
            this.setMoisntessLevel(2400);
        } else {
            this.setMoisntessLevel(this.getMoistnessLevel() - 1);
            if (this.getMoistnessLevel() <= 0) {
                this.hurt(DamageSource.DRY_OUT, 1.0F);
            }
        }
    }

    protected void playBlowAnimation(){
/*        int blowHeight = 10;
        int intensity = 40;
        if (this.world.isRemote) {
            double d0 = 0;
            double d1 = 0;
            double d2 = 0;

            for (int i = 0; i < blowHeight + 3; i++) {
                for(int b = 0; b < intensity; ++b) {
                    this.world.addParticle(ParticleTypes.FALLING_WATER,
                            this.getPosX() - MathHelper.sin(-renderYawOffset * 0.017453292F)
                                    + (MathHelper.sin(-renderYawOffset * 0.017453292F) * 2.8F),
                            this.getPosY() + 1.4F + (i * 0.4F), this.getPosZ() - MathHelper.cos(renderYawOffset * 0.017453292F)
                                    + (MathHelper.cos(renderYawOffset * 0.017453292F) * 2.8F),
                            d0, d1, d2);
                }
            }

            for (int i = 0; i < 3; i++) {
                this.world.addParticle(ParticleTypes.CLOUD,
                        this.getPosX() - MathHelper.sin(-renderYawOffset * 0.017453292F)
                                + (MathHelper.sin(-renderYawOffset * 0.017453292F) * 2.8F),
                        this.getPosY() + 1.4F + (i * 0.4F), this.getPosZ() - MathHelper.cos(renderYawOffset * 0.017453292F)
                                + (MathHelper.cos(renderYawOffset * 0.017453292F) * 2.8F),
                        d0, d1, d2);
            }
        }*/
        playSound(UAMSounds.SOUTHERN_RIGHT_WHALE_SONG.get(), 5.0f, 1.0f);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
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
    protected SoundEvent getAmbientSound() {
        return UAMSounds.SOUTHERN_RIGHT_WHALE_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return UAMSounds.SOUTHERN_RIGHT_WHALE_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return UAMSounds.SOUTHERN_RIGHT_WHALE_HURT.get();
    }

}