package com.gamealition.bukkit.derpi.listeners;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by KJ4IPS on 3/8/2015.
 */

public class CommandListener implements Listener {
    private Collection<String> allowedCommands = new ArrayList<String>();

    public CommandListener() {
        this.allowedCommands.add("/shack");
        this.allowedCommands.add("/money");
    }

    @EventHandler
    public void onPreprocessesCommand(PlayerCommandPreprocessEvent event){
        if(!DERPIPlugin.getDERPI().interredPlayers.contains(event.getPlayer())) return;
        for(String str:allowedCommands){
            if(str.equals(event.getMessage().split(" ")[0])){
                return;
            }
        }
        event.setCancelled(true);
        return;
    }
}
