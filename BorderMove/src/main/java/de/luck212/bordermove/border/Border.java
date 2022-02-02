package de.luck212.bordermove.border;

import de.luck212.bordermove.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;

public class Border {

    WorldBorder border;
    double borderSize = 0;
    double centerX = 0;
    double centerY = 0;
    boolean isRunning;
    int taskID;
    Main plugin;

    public Border(double borderSize, double centerX, double centerY, Main plugin){
        this.centerX = centerX;
        this.centerY = centerY;
        this.border = Bukkit.getWorld("world").getWorldBorder();
        this.borderSize = borderSize;
        this.plugin = plugin;
        isRunning = false;
    }

    public Border(){
        this.border = Bukkit.getWorld("world").getWorldBorder();
        isRunning = false;
    }

    public void setBorder(){
        border.setCenter(centerX, centerY);
        border.setSize(borderSize);
        border.setDamageAmount(20);
        border.setDamageBuffer(0);
    }

    public void startBorder(){
        isRunning = true;
        plugin.blockChange = 0.5;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                border.setCenter((centerX - 0.5), centerY);
                centerX = centerX - 0.5;
            }
        }, 20, 20);
    }

    public void stopBorder(){
        isRunning = false;
        plugin.blockChange = 0.5;
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public void resetBorder(){
        border.reset();
    }

    public boolean borderIsRunning(){
        return isRunning;
    }
}