package teamdraco.unnamedanimalmod.common.entity.util;

import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.animal.Animal;

public class FrogMoveControl extends MoveControl {
    private final Animal frog;

    public FrogMoveControl(Animal frog) {
        super(frog);
        this.frog = frog;
    }

    public void tick() {
        if (this.frog.isEyeInFluid(FluidTags.WATER)) {
            this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
        }

        if (this.operation == Operation.MOVE_TO && !this.frog.getNavigation().isDone()) {
            double d0 = this.wantedX - this.frog.getX();
            double d1 = this.wantedY - this.frog.getY();
            double d2 = this.wantedZ - this.frog.getZ();
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d1 = d1 / d3;
            float f = (float) (Math.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
            this.frog.yRot = this.rotlerp(this.frog.yRot, f, 90.0F);
            this.frog.yBodyRot = this.frog.yRot;
            float f1 = (float) (this.speedModifier * this.frog.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
            if (frog.isBaby()) {
                f1 *= 2.8;
            }
            this.frog.setSpeed(Mth.lerp(0.125F, this.frog.getSpeed(), f1));
            this.frog.setDeltaMovement(this.frog.getDeltaMovement().add(0.0D, (double) this.frog.getSpeed() * d1 * 0.1D, 0.0D));
        } else {
            this.frog.setSpeed(0.0F);
        }
    }
}
