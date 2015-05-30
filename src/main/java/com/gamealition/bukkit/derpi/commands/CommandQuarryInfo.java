package com.gamealition.bukkit.derpi.commands;

import com.gamealition.bukkit.derpi.PunishmentEntry;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class CommandQuarryInfo extends DERPICommand {
    @Override
    protected boolean isArgumentsValid(String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String commandName, String[] args) {
        List<String> list = new ArrayList<String>();
        switch(args.length){
            case 1: //Offline Player Names
                for(OfflinePlayer op : Bukkit.getOfflinePlayers()){
                    if(op.getName().toLowerCase().startsWith(args[0].toLowerCase())){
                        list.add(op.getName());
                    }
                }
                break;
            default:
        }
        return list;
    }

    @Override
    protected boolean processCommand(CommandSender sender, String alias, String[] args) {
        OfflinePlayer targetPlayer;
        Collection<PunishmentEntry> entries;
        //noinspection deprecation
        targetPlayer = Bukkit.getOfflinePlayer(args[0]);
        if(targetPlayer == null){
            sender.sendMessage("That player has never logged onto this server, or does not exist.");
            return true;
        }
        entries = PunishmentEntry.getActiveRecordsForPlayer(targetPlayer.getUniqueId());
        if(entries.size() > 1){
            PunishmentEntry entry = entries.iterator().next();
            sender.sendMessage(String.format("DERPI'd by %s: %s", entry.getActingPlayerUUID() == null ? "the console" :
            Bukkit.getOfflinePlayer(entry.getActingPlayerUUID()).getName(),entry.getPunishReasion()));
        }else{
            sender.sendMessage("That player has not been commited to DERPI");
        }
        return true;
    }
}
