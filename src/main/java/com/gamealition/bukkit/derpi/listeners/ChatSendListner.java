package com.gamealition.bukkit.derpi.listeners;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class ChatSendListner implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(DERPIPlugin.getDERPI().interredPlayers.contains(e.getPlayer()))
            e.setCancelled(true);
    }
}
