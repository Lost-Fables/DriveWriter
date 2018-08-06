package net.lordofthecraft.gitlab.drivewriter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DrivewriterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (label.equalsIgnoreCase("DW") || label.equalsIgnoreCase("DriveWriter")){
            if (!isPlayer(sender)){
                errorIsNotPlayer();
                return true;
            }

            if (args.length < 1){
                errorToFewArgs();
                helpMenu();
                return true;
            }

            if (args.length > 1){
                errorToManyArgs();
                helpMenu();
                return true;
            }

            if (args.length == 1){
                String inputurl = args[0];
                BookComposer composer = new BookComposer(inputurl,(Player) sender);
                if (!composer.isSafe()){
                    Bukkit.getServer().broadcastMessage("NOT SAFE EXITING");
                    return true;
                }
                composer.bookCompose();
                return true;
            }
        }
        return true;
    }

    private void errorToFewArgs(){
        Bukkit.getServer().broadcastMessage("To few Args placeholder");
    }

    private void errorToManyArgs(){
        Bukkit.getServer().broadcastMessage("To many args placeholder");
    }

    private void errorIsNotPlayer(){
        Bukkit.getServer().broadcastMessage("NOT Player placeholder");
    }

    private void helpMenu(){
        Bukkit.getServer().broadcastMessage("HelpMenu Placeholder");
    }

    private boolean isPlayer(CommandSender sender){
        return sender instanceof Player;
    }


}
