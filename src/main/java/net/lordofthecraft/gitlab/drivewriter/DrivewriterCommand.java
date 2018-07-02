package net.lordofthecraft.gitlab.drivewriter;

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
                errorToFewArgs();
                helpMenu();
                return true;
            }

            if (args.length == 1){
                String inputurl = args[0];
                BookComposer composer =new BookComposer(inputurl,(Player) sender);
                if (!composer.isSafe()) return true;




                return true;
            }
        }
        return true;
    }

    private void errorToFewArgs(){

    }

    private void errorToManyArgs(){

    }

    private void errorIsNotPlayer(){

    }

    private void helpMenu(){

    }

    private boolean isPlayer(CommandSender sender){
        return sender instanceof Player;
    }


}
