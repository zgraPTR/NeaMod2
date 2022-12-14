package jp.neamod2.commands;

import jp.neamod2.utils.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class NeaGuiCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "neamod2";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Utils.guiToOpen = "Main 1";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

}
