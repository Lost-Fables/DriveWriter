package net.lordofthecraft.gitlab.drivewriter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookComposer {
    private Player player;
    private URL url;
    private boolean safe;


    BookComposer(String inputurl, Player player){
        safe = true;
        this.player = player;

        try {url = new URL(inputurl);}
        catch (MalformedURLException error){
           player.sendMessage(ChatColor.RED + "Error, BAD url please try again");
           safe = false;
        }
    }

    public boolean isSafe() {
        return safe;
    }

    public void bookCompose(){
        if (player.getInventory().getItemInMainHand().getType() == Material.WRITABLE_BOOK){
            String text = textGetter(documentGetter(url));

            player.getInventory().getItemInMainHand().setAmount(0);

            ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
            BookMeta bookMeta = (BookMeta) book.getItemMeta();
            PageHandler pageHandler = new PageHandler(245);
            pageHandler.text(text);
            List<String> pages = pageHandler.toList();
            bookMeta.setPages(pages);
            book.setItemMeta(bookMeta);

            player.getInventory().setItemInMainHand(book);

        }
    }

    private Document documentGetter(URL url){
        Document document = null;
        try {
            document = Jsoup.connect(url.toString()).get();
        }catch (IOException error){
            player.sendMessage("Error, IO issue getting the document from the provided URL");
            safe = false;
        }
        return document;
    }

    private String textGetter(Document document){
        StringBuilder stringBuilder = new StringBuilder();

        Elements text = document.select("p");
        for (Element element : text){
            stringBuilder.append(element.text());
        }
        return stringBuilder.toString();
    }
}
