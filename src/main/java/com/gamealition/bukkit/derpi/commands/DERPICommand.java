package com.gamealition.bukkit.derpi.commands;

import com.gamealition.bukkit.derpi.DERPIPlugin;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public abstract class DERPICommand implements CommandExecutor, TabCompleter {
    protected DERPIPlugin plugin;
    protected boolean requiresPlayer;

    public void registerCommand(DERPIPlugin plugin, PluginCommand command){
        command.setExecutor(this);
        command.setTabCompleter(this);
        this.plugin = plugin;
    }

    protected abstract boolean isArgumentsValid(String[] args);

    protected abstract boolean processCommand(CommandSender sender, String alias, String[] args);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(this.requiresPlayer && !(commandSender instanceof Player)) {
            commandSender.sendMessage("This command can only be run as a player");
            return false;
        }
        if(!isArgumentsValid(strings)) return false;
        return processCommand(commandSender,s,strings);
    }


    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> res = new ArrayList<String>();
        commandSender.sendMessage("This command does not support tab completion!");
        return res;
    }
}
