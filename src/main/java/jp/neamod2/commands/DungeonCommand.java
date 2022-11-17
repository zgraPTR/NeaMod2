package jp.neamod2.commands;


import jp.neamod2.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class DungeonCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "dnesp";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/"+ getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Utils.isDungeon = !Utils.isDungeon;
        if (Utils.isDungeon) {
            Utils.addChatMessage("ダンジョンESPが有効になりました。");
        } else {
            Utils.addChatMessage("ダンジョンESPが無効になりました。");
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

}
