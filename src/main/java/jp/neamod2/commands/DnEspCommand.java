package jp.neamod2.commands;


import jp.neamod2.utils.ChatUtils;
import jp.neamod2.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class DnEspCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "dnesp";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Utils.isDungeonEsp = !Utils.isDungeonEsp;
        if (Utils.isDungeonEsp) {
            ChatUtils.addChatMessage("ダンジョンESPが有効になりました。");
        } else {
            ChatUtils.addChatMessage("ダンジョンESPが無効になりました。");
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

}
