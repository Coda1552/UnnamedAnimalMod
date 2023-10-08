package teamdraco.unnamedanimalmod.common.entity;

import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import teamdraco.unnamedanimalmod.common.entity.util.BottomDwellerMoveControl;
import teamdraco.unnamedanimalmod.init.UAMItems;

public class BlackDiamondStingrayEntity extends AbstractFish {

    public BlackDiamondStingrayEntity(EntityType<? extends AbstractFish> type, Level worldIn) {
        super(type, worldIn);
        this.moveControl = new BottomDwellerMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    protected PathNavigation createNavigation(Level world) {
        return new GroundPathNavigation(this, world);
    }

    public int getMaxSpawnClusterSize() {
        return 1;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D);
    }

    public void playerTouch(Player entityIn) {
        if (entityIn instanceof ServerPlayer serverPlayer && serverPlayer.hurt(DamageSource.mobAttack(this), 1)) {
            serverPlayer.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0F));
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
        }
    }

    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return sizeIn.height * 0.95F;
    }

    public ItemStack getBucketItemStack() {
        return new ItemStack(UAMItems.BLACK_DIAMOND_STINGRAY_BUCKET.get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PUFFER_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PUFFER_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.PUFFER_FISH_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.PUFFER_FISH_FLOP;
    }

}