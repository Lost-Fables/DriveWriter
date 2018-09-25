package net.lordofthecraft.gitlab.drivewriter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DrivewriterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!isPlayer(sender)) {
            errorIsNotPlayer(sender);
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            errorToFewArgs(player);
            helpMenu(player);
            return true;
        }

        if (args.length > 1) {
            errorToManyArgs(player);
            helpMenu(player);
            return true;
        }

        String inputUrl = args[0];
        BookComposer composer = new BookComposer(inputUrl, (Player) sender);
        if (!composer.isSafe()) {
            return true;
        }
        composer.bookCompose();
        return true;
    }

    private void errorToFewArgs(Player player) {
        player.sendMessage(ChatColor.RED + "Error: Too few arguments");
    }

    private void errorToManyArgs(Player player) {
        player.sendMessage(ChatColor.RED + "Error: Too many arguments entered!");
    }

    private void errorIsNotPlayer(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Error: Only players can use this command!");
    }

    private void helpMenu(Player player) {
        player.sendMessage(ChatColor.GOLD + "To use drive writer you need to first publish your Document. \n" +
                               "Go to File, then publish to web, and final take the publish to web link and pass that into drive writer!");
    }

    private boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }


}
