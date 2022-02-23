package de.luck212.bordermove.listener;

import de.luck212.bordermove.main.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {

    Main plugin;

    public BlockPlaceListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(PlayerInteractEvent event){
        if(!plugin.getStartBorderCommand().getPluginIsActive()) return;
        Player player = event.getPlayer();
        if(player.getGameMode() == GameMode.CREATIVE)
            return;
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(player.getInventory().getItemInMainHand() == null) return;
            if(player.getInventory().getItemInMainHand().getItemMeta().getLore() != null){
                if(event.getClickedBlock().getType() == Material.MOSSY_COBBLESTONE || event.getClickedBlock().getType() == Material.COBBLESTONE){
                    event.setUseItemInHand(Event.Result.DENY);
                    event.setCancelled(true);
                    return;
                }
                player.getInventory().addItem(new ItemStack(Material.SANDSTONE));
            }
        }
    }

    @EventHandler
    public void handleDamage(EntityDamageEvent event){
        if(event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK)
            event.setCancelled(true);
    }
}