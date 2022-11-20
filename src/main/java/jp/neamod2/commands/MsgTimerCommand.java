package jp.neamod2.commands;

import jp.neamod2.utils.ChatUtils;
import jp.neamod2.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class MsgTimerCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "msgtimer";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/msgtimer";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Utils.isMsgTimer = !Utils.isMsgTimer;
        if (Utils.isMsgTimer) {
            ChatUtils.addChatMessage("時間表示が有効になりました。");
        } else {
            ChatUtils.addChatMessage("時間表示が無効になりました。");
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
