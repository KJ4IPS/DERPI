package com.gamealition.bukkit.derpi.tasks;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class DerpiWatchdog implements Runnable {

    @Override
    public void run() {
        for(Player p : DERPIPlugin.getDERPI().interredPlayers){
            if(!p.getWorld().equals(DERPIPlugin.getDERPI().getDerpiTarget().getWorld()))
                p.teleport(DERPIPlugin.getDERPI().getDerpiTarget(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }
}
