package de.luck212.bordermove.main;

import de.luck212.bordermove.commands.DeleteChecpointCommand;
import de.luck212.bordermove.commands.StartBorderCommand;
import de.luck212.bordermove.commands.StartCommand;
import de.luck212.bordermove.listener.BlockPlaceListener;
import de.luck212.bordermove.listener.CheckPointListener;
import jdk.javadoc.internal.tool.Start;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    StartCommand startBorderCommand;
    public double blockChange;

    @Override
    public void onEnable() {
        blockChange = 0.5;
        startBorderCommand = new StartCommand();
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BlockPlaceListener(this), this);
        pluginManager.registerEvents(new CheckPointListener(this), this);

        getCommand("border").setExecutor(new StartBorderCommand(this));
        getCommand("deletecp").setExecutor(new DeleteChecpointCommand(this));
        getCommand("startborderplugin").setExecutor(startBorderCommand);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public StartCommand getStartBorderCommand() {
        return startBorderCommand;
    }
}
