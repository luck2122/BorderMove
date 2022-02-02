package de.luck212.bordermove.commands;

import de.luck212.bordermove.main.Main;
import de.luck212.bordermove.utils.ConfigLocationUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteChecpointCommand implements CommandExecutor {

    Main plugin;

    public DeleteChecpointCommand(Main plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!plugin.getStartBorderCommand().getPluginIsActive()) return false;
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length == 1){
                ConfigLocationUtil configLocationUtil = new ConfigLocationUtil(plugin, "checkpoint." + args[0]);
                configLocationUtil.deleteLocation();
                player.sendMessage("§aDie Location für §6" + args[0] + " §awurde gelöscht");
            }else
                player.sendMessage("§cBitte benutze §6/deletecp <SPIELER> §c!");
        }

        return false;
    }
}