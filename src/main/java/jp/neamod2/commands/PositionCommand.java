package jp.neamod2.commands;

import jp.neamod2.features.PositionFeature;
import jp.neamod2.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

import java.util.List;

public class PositionCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "Position";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            ChatUtils.addChatMessage("プレイヤー名が未入力です。");
            return;
        }
        for (EntityPlayer entityPlayer : Minecraft.getMinecraft().theWorld.playerEntities) {
            if (entityPlayer.getName().contains(args[0])) {
                double x = entityPlayer.posX;
                double y = entityPlayer.posY;
                double z = entityPlayer.posZ;
                ChatUtils.addChatMessage(String.format("%s さんは x:%.0f, y:%.0f, z:%.0f にいます。", entityPlayer.getName(), x, y, z));
                PositionFeature.target.name = entityPlayer.getName();
                PositionFeature.target.pos = entityPlayer.getPosition();
            }
        }
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames()) : null;
    }
}
