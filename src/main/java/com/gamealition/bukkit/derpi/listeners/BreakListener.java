package com.gamealition.bukkit.derpi.listeners;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class BreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(player == null) return;
        if(DERPIPlugin.getDERPI().interredPlayers.contains(player)){
            if(!event.getBlock().getType().equals(Material.STONE))
                event.setCancelled(true);
        }
    }
}
