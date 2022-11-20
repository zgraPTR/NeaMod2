package jp.neamod2;

import jp.neamod2.commands.DnEspCommand;
import jp.neamod2.commands.NeaGuiCommand;
import jp.neamod2.commands.MsgTimerCommand;
import jp.neamod2.handlers.ConfigHandler;
import jp.neamod2.handlers.EventHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "neamod2", name = "Nea7s Mod 2", version = "v1.1.2")
public class Main {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        ClientCommandHandler.instance.registerCommand((new DnEspCommand()));
        ClientCommandHandler.instance.registerCommand((new MsgTimerCommand()));
        ClientCommandHandler.instance.registerCommand(new NeaGuiCommand());

        ConfigHandler.loadConfig();
    }
}
