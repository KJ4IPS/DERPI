package com.gamealition.bukkit.derpi.commands;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import com.gamealition.bukkit.derpi.PunishmentEntry;
import com.gamealition.bukkit.derpi.tasks.StartPunishmentTask;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class CommandQuarry extends DERPICommand {
    @Override
    protected boolean isArgumentsValid(String[] args) {
        if(!args[0].matches("[A-Za-z0-9_]{1,16}")){
            return false;
        }
        if(args.length > 1){
            try{
                Integer.parseInt(args[1]);
            }catch(Exception e){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String commandName, String[] args) {
        List<String> list = new ArrayList<String>();
        switch(args.length){
            case 0:
                break;
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
        /*args
          0 - Playername, target player.
          1 - Balance to set
          2 - Reason for derping
         */
        OfflinePlayer targetPlayer;
        UUID targetUUID;

        //noinspection deprecation
        targetPlayer = Bukkit.getOfflinePlayer(args[0]);

        if(targetPlayer == null){
            sender.sendMessage("Sorry, that player has never logged onto this server.");
            return true;
        }
        targetUUID = targetPlayer.getUniqueId();
        if(PunishmentEntry.getActiveRecordsForPlayer(targetUUID).size() > 0){
            sender.sendMessage("That player has already been commited to DERPI!");
        }

        PunishmentEntry punishmentEntry = new PunishmentEntry(targetUUID,sender instanceof Player ? ((Player) sender).getUniqueId() : null);
        punishmentEntry.setStartingOffset(args.length > 1 ? Integer.parseInt(args[1]) : 0 );
        punishmentEntry.setPunishReasion(args.length > 2 ? args[2] : "No Reason Specified");

        DERPIPlugin.getDERPI().getDatabase().save(punishmentEntry);

        //if the player is online, punish them now
        if(targetPlayer.isOnline()) {
            new StartPunishmentTask(punishmentEntry, targetPlayer.getPlayer()).run();
            sender.sendMessage(String.format("%s has been committed to the DERPI!",targetPlayer.getName()));
        }else{
            sender.sendMessage(String.format("%s is offline, he/she will be commited to DERPI upon next login!",targetPlayer.getName()));
        }
        return true;
    }
}
