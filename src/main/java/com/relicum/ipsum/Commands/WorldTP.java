package com.relicum.ipsum.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Name: WorldTP.java Created: 05 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@CmdInfo(description = "Teleport to different worlds",
        usage = "/<command> <world>", label = "worldtp", permission = "worldtp.use", minArgs = 1, maxArgs = 1, playerOnly = true, subCommand = false)
public class WorldTP extends SimpleCommand {

    private final Plugin plugin;

    public WorldTP(String name, List<String> aliasess, Plugin plugin) {
        super(name, aliasess, plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        Player player = (Player) sender;

        String wname = args[0];
        World world1;
        world1 = Bukkit.getWorld(wname);

        if (world1 != null) {
            world1.getSpawnLocation().getChunk().load();
            player.sendMessage(ChatColor.GREEN + "Please wait you are being teleported to " + wname);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () ->
                    player.teleport(world1.getSpawnLocation()), 10l);

            return true;

        } else {
            player.sendMessage(ChatColor.RED + "That world does not exist can teleport you there");
            return true;
        }

    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param alias   The alias used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
