package teamdraco.unnamedanimalmod.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import teamdraco.unnamedanimalmod.UAMHelper;
import teamdraco.unnamedanimalmod.init.UAMBlocks;
import teamdraco.unnamedanimalmod.init.UAMItems;

import java.util.ArrayList;

public class MudBallItem extends Item {
    public MudBallItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (world.getBlockState(pos).getBlock().equals(Blocks.FARMLAND)) {
            world.setBlockAndUpdate(pos, UAMBlocks.RICH_FARMLAND.get().defaultBlockState());
            if (context.getPlayer() != null && !context.getPlayer().getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }
            if (world.isClientSide) {
                addItemParticles(context.getPlayer(), pos);
            }
            else {
                world.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0f);
                world.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0F, 0.75f);
                world.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0F, 0.5f);
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
    
    public void addItemParticles(Player playerEntity, BlockPos pos) {
        Level world = playerEntity.level;
        for (int i = 0; i < 5; i++) {
            ArrayList<Vec3> particlePositions = UAMHelper.blockOutlinePositions(world, pos);
            particlePositions.forEach(p -> world.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DIRT.defaultBlockState()), p.x, p.y, p.z, 0, world.random.nextFloat() * 0.1f, 0));
        }
        for (int i = 0; i < 10; ++i) {
            double d0 = (double) (-playerEntity.level.random.nextFloat()) * 0.6D - 0.3D;
            Vec3 position = new Vec3(((double) playerEntity.level.random.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
            position = position.xRot(-playerEntity.getXRot() * ((float) Math.PI / 180F));
            position = position.yRot(-playerEntity.getYRot() * ((float) Math.PI / 180F));
            position = position.add(playerEntity.getX(), playerEntity.getEyeY(), playerEntity.getZ());
    
            Vec3 velocity = new Vec3(((double) playerEntity.level.random.nextFloat() - 0.5D) * 0.2D, Math.random() * 0.1D + 0.1D, 0.0D);
            velocity = velocity.xRot(-playerEntity.getXRot() * ((float) Math.PI / 180F));
            velocity = velocity.yRot(-playerEntity.getYRot() * ((float) Math.PI / 180F));
            playerEntity.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, UAMItems.MUD_BALL.get().getDefaultInstance()), position.x, position.y, position.z, velocity.x, velocity.y + 0.05D, velocity.z);
        }
    }
}