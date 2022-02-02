package de.luck212.bordermove.listener;

import de.luck212.bordermove.main.Main;
import de.luck212.bordermove.utils.ConfigLocationUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CheckPointListener implements Listener {

    Main plugin;
    private double distance;
    private List<String> blocklore = new ArrayList<String>();

    public CheckPointListener(Main plugin){
        this.plugin = plugin;
        blocklore.add("");
    }


    @EventHandler
    public void onCheckPointStep(PlayerMoveEvent event){
        if(!plugin.getStartBorderCommand().getPluginIsActive()) return;
        Player player = event.getPlayer();
        ConfigLocationUtil configLocationUtil = null;
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GOLD_BLOCK){
            configLocationUtil = new ConfigLocationUtil(plugin, "checkpoint." + player.getName(), player.getLocation());
            if(configLocationUtil.loadLocation() != null){
                distance = configLocationUtil.loadLocation().distance(event.getFrom().getBlock().getLocation());
                if(!(distance <= 13)){
                    if(player.getInventory().getItemInMainHand().getItemMeta().getLore() == null){
                        player.getInventory().clear();
                        configLocationUtil.saveLocation();
                        return;
                    }
                    configLocationUtil.saveLocation();
                    player.getInventory().clear();

                    ItemStack blocks = new ItemStack(Material.SANDSTONE);
                    blocks.setItemMeta(null);
                    blocks.setAmount(64);

                    player.getInventory().setItem(0, blocks);
                }
            }else{

                ItemStack blocks = new ItemStack(Material.SANDSTONE);
                ItemMeta blocksMeta = blocks.getItemMeta();
                blocksMeta.setLore(blocklore);
                blocks.setItemMeta(blocksMeta);
                blocks.setAmount(64);
                player.getInventory().setItem(0, blocks);

                configLocationUtil.saveLocation();
            }
        }else if (event.getFrom().getY() <= 40){
            configLocationUtil = new ConfigLocationUtil(plugin, "checkpoint." + player.getName());
                if(configLocationUtil.loadLocation() != null)
                    player.teleport(configLocationUtil.loadLocation());
                player.setHealth(20);
        }

    }

    @EventHandler
    public void handleFoodLose(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
}