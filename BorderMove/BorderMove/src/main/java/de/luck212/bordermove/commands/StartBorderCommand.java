package de.luck212.bordermove.commands;

import de.luck212.bordermove.border.Border;
import de.luck212.bordermove.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class StartBorderCommand implements CommandExecutor {

    Border border;
    Main plugin;

    public StartBorderCommand(Main plugin){
        this.plugin = plugin;
        border = new Border();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!plugin.getStartBorderCommand().getPluginIsActive()) return false;
        if(!(sender instanceof Player))return false;
        Player player = (Player) sender;
        if(args.length <= 0){
            player.sendMessage("§cBitte benutze §6/border <start | set <BORDERSIZE> <CENTERX> <CENTERY> | stop | reset>");
            return false;
        }
        switch (args[0].toLowerCase()){
            case "start":
                if(border.borderIsRunning()){
                    player.sendMessage("§cDie Border wurde bereits gestartet");
                    break;
                }
                border.startBorder();
                player.sendMessage("§aDie Border wurde gestartet");
                break;
            case "stop":
                if(!border.borderIsRunning()){
                    player.sendMessage("§cDie Border wurde bereits gestopt");
                    break;
                }
                border.stopBorder();
                player.sendMessage("§aDie Border wurde gestopt");
                break;
            case "set":
                if(args.length == 4){
                    if(border.borderIsRunning()){
                        player.sendMessage("§cDie Border wurde bereits gestartet");
                        break;
                    }
                    try{
                        border = new Border(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), plugin);
                        border.setBorder();
                        player.sendMessage("§aDie Border wurde erstellt");
                    }catch (Exception ex){
                        player.sendMessage("§cFalsche Parameter angegeben!");
                    }
                }else
                    player.sendMessage("§cBitte benutze §6/border <start | set <BORDERSIZE> <CENTER X> <CENTER Y> | stop | reset>!");
                break;
            case "reset":
                    border.resetBorder();
                    player.sendMessage("§aDie Border wurde geresetet");
                break;
            case "blockspeed":
                try {
                    //plugin.blockChange = Double.parseDouble(args[1]);
                    player.sendMessage("Diese Einstellung ist deaktiviert");
                }catch (Exception e){
                    player.sendMessage("§cFalsche Parameter angegeben!");
                }
                break;
            default:
                player.sendMessage("§cBitte benutze §6/border <start | set <BORDERSIZE> <CENTER X> <CENTER Y> | stop | reset | blockspeed>");
                break;
        }
        return false;
    }
}