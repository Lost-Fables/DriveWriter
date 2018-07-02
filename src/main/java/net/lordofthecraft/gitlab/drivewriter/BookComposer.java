package net.lordofthecraft.gitlab.drivewriter;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BookComposer {
    private Player player;
    private URL url;
    private boolean safe;
    private ItemStack composedbook;

    BookComposer(String inputurl, Player player){
        safe = true;
        this.player = player;

        try {url = new URL(inputurl);}
        catch (MalformedURLException error){
           player.sendMessage("Error, BAD url please try again");
           safe = false;
        }
    }

    public boolean isSafe() {
        return safe;
    }

    private ItemStack bookCompose(){

    }

    private String driveReader(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }
        }
    }
}
