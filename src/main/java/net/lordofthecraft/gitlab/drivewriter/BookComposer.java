package net.lordofthecraft.gitlab.drivewriter;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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

    private Document documentGetter(URL url){
        Document document = null;
        try {
            document = Jsoup.connect(url.toString()).get();
        }catch (IOException error){
            player.sendMessage("Error, IO issue getting the document from the provdied URL");
            safe = false;
        }
        return document;
    }

    private String textGetter(Document document){
        String output;

        Elements text = document.select("p");
        for (Element element : text){
            element.text().toString();
        }
    }
}
