package com.mechwild.mechs.item;

import com.mechwild.mechs.MechwildMechs;
import com.mechwild.mechs.entity.RaptorMechEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MechDeploymentCoreItem extends Item {
    public MechDeploymentCoreItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            if (player.getVehicle() instanceof RaptorMechEntity mech) {
                mech.activatePrimaryAbility(player);
                player.getCooldowns().addCooldown(this, 20);
                return InteractionResultHolder.consume(stack);
            }

            if (level instanceof ServerLevel serverLevel) {
                RaptorMechEntity mech = MechwildMechs.RAPTOR_MECH.get().create(serverLevel);
                if (mech != null) {
                    mech.moveTo(player.getX(), player.getY() + 0.1D, player.getZ(), player.getYRot(), 0.0F);
                    mech.configureFromPilot(player);
                    serverLevel.addFreshEntity(mech);
                    player.displayClientMessage(Component.literal("Mech deployed. Right-click the lower hull to climb into the head cockpit."), true);
                    if (!player.getAbilities().instabuild) {
                        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                    }
                }
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
