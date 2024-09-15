package ynotnaexists.morecakes.block.customblocks;

import net.minecraft.block.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;

public class FoodCakeBlock extends CakeBlock {

    private final int foodAndCakeNutrition;
    private final float foodAndCakeSaturation;

    public FoodCakeBlock(Item food, Settings settings) {
        super(settings);
        this.foodAndCakeNutrition = Objects.requireNonNull(food.getComponents().get(DataComponentTypes.FOOD)).nutrition() + 2;
        this.foodAndCakeSaturation = Objects.requireNonNull(food.getComponents().get(DataComponentTypes.FOOD)).saturation() + 0.1F;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {

            if (tryEat(world, pos, state, player, foodAndCakeNutrition, foodAndCakeNutrition).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player, foodAndCakeNutrition, foodAndCakeSaturation);
    }

    protected ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player, int foodAndCakeNutrition, float foodAndCakeSaturation) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.incrementStat(Stats.EAT_CAKE_SLICE);
            player.getHungerManager().add(foodAndCakeNutrition, foodAndCakeSaturation);
            int i = (Integer)state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < 6) {
                world.setBlockState(pos, state.with(BITES, Integer.valueOf(i + 1)), Block.NOTIFY_ALL);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

}
