package teamdraco.unnamedanimalmod.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import teamdraco.unnamedanimalmod.init.UAMBlocks;
import teamdraco.unnamedanimalmod.init.UAMEntities;
import teamdraco.unnamedanimalmod.init.UAMItems;

public class MangroveBoatEntity extends Boat {
    public MangroveBoatEntity(EntityType<? extends MangroveBoatEntity> type, net.minecraft.world.level.Level world) {
        super(type, world);
    }

    public MangroveBoatEntity(Level worldIn, double x, double y, double z) {
        super(UAMEntities.MANGROVE_BOAT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        //this.lastYd = this.getMotion().y;
        if (!this.isPassenger()) {
            if (onGroundIn) {
                if (this.fallDistance > 3.0F) {
//                    if (this.status != BoatEntity.Status.ON_LAND) {
//                        this.fallDistance = 0.0F;
//                        return;
//                    }

                    this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
                    if (!this.level.isClientSide && isAlive()) {
                        this.discard();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            for (int i = 0; i < 3; ++i) {
                                this.spawnAtLocation(UAMBlocks.MANGROVE_PLANKS.get());
                            }

                            for (int j = 0; j < 2; ++j) {
                                this.spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }

                this.fallDistance = 0.0F;
            } else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
                this.fallDistance = (float) ((double) this.fallDistance - y);
            }
        }
    }

    @Override
    public Item getDropItem() {
        return UAMItems.MANGROVE_BOAT.get();
    }

}