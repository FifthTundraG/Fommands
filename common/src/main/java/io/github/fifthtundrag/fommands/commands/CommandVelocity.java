package io.github.fifthtundrag.fommands.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;

public class CommandVelocity {
    public static void register(CommandDispatcher<CommandSourceStack> commandDispatcher) {
        commandDispatcher.register(
                Commands.literal("velocity")
                        .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                        .then(Commands.argument("targets", EntityArgument.entities())
                                .then(Commands.argument("vector", Vec3Argument.vec3(false)) // this must not be center correct or a value of "0 1 0" will end up as (0.5, 1, 0.5)
                                        .executes(commandContext -> setVelocity(
                                                commandContext.getSource(),
                                                EntityArgument.getEntities(commandContext, "targets"),
                                                Vec3Argument.getVec3(commandContext, "vector")
                                        ))
                                )
                        )
        );
    }

    private static int setVelocity(CommandSourceStack commandSourceStack, Collection<? extends Entity> collection, Vec3 vec3) {
        for (Entity entity : collection) {
            entity.setDeltaMovement(vec3);
            if (entity instanceof Player) {
                entity.hurtMarked = true; // ensure the server updates the player's state to the client
            }
        }

        if (collection.size() == 1) {
            commandSourceStack.sendSuccess(() -> Component.translatable("commands.velocity.success.single", collection.iterator().next().getDisplayName()), true);
        } else {
            commandSourceStack.sendSuccess(() -> Component.translatable("commands.velocity.success.multiple", collection.size()), true);
        }

        return collection.size();
    }
}
