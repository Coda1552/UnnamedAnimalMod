package teamdraco.unnamedanimalmod.common.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import teamdraco.unnamedanimalmod.init.UAMEntities;
import teamdraco.unnamedanimalmod.init.UAMItems;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HumpheadParrotfishEntity extends Animal {
    private BlockPos target;

    public HumpheadParrotfishEntity(EntityType<? extends Animal> entity, Level world) {
        super(entity, world);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 100));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }

    @Override
    public void travel(Vec3 p_213352_1_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.01F, p_213352_1_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(p_213352_1_);
        }
    }

    protected PathNavigation createNavigation(Level p_175447_1_) {
        return new WaterBoundPathNavigation(this, p_175447_1_);
    }

    protected void updateAir(int air) {
        if (this.isAlive() && !this.isInWaterOrBubble()) {
            this.setAirSupply(air - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(DamageSource.DROWN, 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }
    }

    public void baseTick() {
        int lvt_1_1_ = this.getAirSupply();
        super.baseTick();
        this.updateAir(lvt_1_1_);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide) {
            if (target == null && tickCount % 12000 == 0) {
                selectTarget();
            }

            if (target != null) {
                if (navigation.isDone()) navigation.moveTo(target.getX(), target.getY(), target.getZ(), 0.3);
                else if (distanceToSqr(target.getX(), target.getY(), target.getZ()) <= 4) breakBlock();
            }
        }
    }

    private void breakBlock() {
        if (wasEyeInWater) {
            BlockState state = level.getBlockState(target);
            if (state.canOcclude()) {
                level.levelEvent(2001, target, Block.getId(state));
                for (ItemStack drop : state.getDrops(new LootContext.Builder((ServerLevel) level).withRandom(random).withParameter(LootContextParams.TOOL, ItemStack.EMPTY))) {
                    spawnAtLocation(drop, (float) (target.getY() - getY()));
                }
                level.removeBlock(target, false);
            }
        }
    }

    private void selectTarget() {
        List<BlockPos> possible = new ArrayList<>();
        BlockPos start = blockPosition();
        BlockPos.betweenClosedStream(start.offset(-16, -16, -16), start.offset(16, 16, 16)).forEach(pos -> {
            if (level.getBlockState(pos).is(BlockTags.CORAL_BLOCKS)) {
                possible.add(pos.immutable());
            }
        });
        if (!possible.isEmpty())
            target = possible.get(random.nextInt(possible.size()));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 14.0D).add(Attributes.MOVEMENT_SPEED, 2.5D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return new HumpheadParrotfishEntity(UAMEntities.HUMPHEAD_PARROTFISH.get(), this.level);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if (dataTag != null) {
            setAge(dataTag.getInt("Age"));
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public void aiStep() {
        if (!this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), 0.4000000059604645D, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.onGround = false;
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        animationSpeed = 2.0f;

        super.aiStep();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (Block.byItem(heldItem.getItem()) instanceof CoralBlock) {
            spawnAtLocation(new ItemStack(Items.SAND, getRandom().nextInt(10)));
            heldItem.shrink(1);
            return InteractionResult.SUCCESS;
        } else if (isBaby() && heldItem.getItem() == Items.WATER_BUCKET && this.isAlive()) {
            this.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0F, 1.0F);
            heldItem.shrink(1);
            ItemStack bucket = new ItemStack(UAMItems.BABY_HUMPHEAD_PARROTFISH_BUCKET.get());
            if (this.hasCustomName()) {
                bucket.setHoverName(this.getCustomName());
            }
            if (!this.level.isClientSide) {
                bucket.getOrCreateTag().putInt("Age", getAge());
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucket);
            }

            if (heldItem.isEmpty()) {
                player.setItemInHand(hand, bucket);
            } else if (!player.getInventory().add(bucket)) {
                player.drop(bucket, false);
            }

            this.discard();
            return InteractionResult.sidedSuccess(this.level.isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return Block.byItem(stack.getItem()).defaultBlockState().is(BlockTags.CORAL_PLANTS);
    }

    public boolean isPushedByFluid() {
        return false;
    }
}