package com.gamealition.bukkit.derpi.listeners;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class PlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(player == null) return;
        if(DERPIPlugin.getDERPI().interredPlayers.contains(player))
            event.setCancelled(true);
    }
}
