package teamdraco.unnamedanimalmod.common.entity.util;

import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.block.Blocks;

public class BottomDwellerMoveControl extends MoveControl {
    private final PathfinderMob fish;

    public BottomDwellerMoveControl(PathfinderMob fish) {
        super(fish);
        this.fish = fish;
    }

    public void tick() {
        if (this.fish.isEyeInFluid(FluidTags.WATER)) {
            this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.0D, 0.0D));
        }

        if (this.fish.horizontalCollision && this.fish.level.getBlockState(this.fish.blockPosition().above()).getBlock() == Blocks.WATER) {
            this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.025D, 0.0D));
        }

        if (this.operation == MoveControl.Operation.MOVE_TO && !this.fish.getNavigation().isDone()) {
            double d0 = this.wantedX - this.fish.getX();
            double d1 = this.wantedY - this.fish.getY();
            double d2 = this.wantedZ - this.fish.getZ();
            double d3 = (double) Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d1 = d1 / d3;
            float f = (float)(Math.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
            this.fish.yRot = this.rotlerp(this.fish.yRot, f, 90.0F);
            this.fish.yBodyRot = this.fish.yRot;
            float f1 = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));
            this.fish.setSpeed(Mth.lerp(0.125F, this.fish.getSpeed(), f1));
            this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * d1 * 0.1D, 0.0D));
        } else {
            this.fish.setSpeed(0.0F);
        }
    }
}

