package com.gamealition.bukkit.derpi;

import com.gamealition.bukkit.derpi.commands.CommandQuarry;
import com.gamealition.bukkit.derpi.commands.CommandQuarryInfo;
import com.gamealition.bukkit.derpi.commands.CommandUnquarry;
import com.gamealition.bukkit.derpi.listeners.*;
import com.gamealition.bukkit.derpi.tasks.DerpiWatchdog;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by KJ4IPS on 3/8/2015.
 */
public class DERPIPlugin extends JavaPlugin {

    public Collection<Player> interredPlayers;

    private static DERPIPlugin INSTANCE;


    @Override
    public void onEnable() {
        this.interredPlayers = new ArrayList<Player>();
        INSTANCE = this;

        try {
            getDatabase().find(PunishmentEntry.class).findRowCount();
        } catch (PersistenceException e){
            installDDL();
        }
        new CommandQuarry().registerCommand(this,this.getCommand("quarry"));
        new CommandUnquarry().registerCommand(this,this.getCommand("unquarry"));
        new CommandQuarryInfo().registerCommand(this,this.getCommand("quarryinfo"));

        //register the DERPI-derp prevention hooks
        getServer().getPluginManager().registerEvents(new LoginListener(),this);
        getServer().getPluginManager().registerEvents(new CommandListener(),this);
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new PlaceListener(),this);
        getServer().getPluginManager().registerEvents(new ChatSendListner(), this);

        //start the watchdog
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new DerpiWatchdog(),2000, 2000);

    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(PunishmentEntry.class);
        return list;
    }

    public static DERPIPlugin getDERPI(){
        return INSTANCE;
    }

    public Location getDerpiTarget(){
        return Bukkit.getWorld("world_derpi").getSpawnLocation();
    }
}
