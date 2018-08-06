package net.lordofthecraft.gitlab.drivewriter;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class PageHandler { //Credit to Tel for this amazing string handling code I use it everywhere
    private List<String> contents;
    private String format;
    private int max_chars;

    public PageHandler(int limit) {
        this.max_chars = limit;
        this.contents = new ArrayList<>();
        this.format = "";
    }

    public void text(String text) {
        if (text == null || text.length() == 0)
            return;
        String[] words = text.split("\\s+");
        StringBuilder builder = new StringBuilder(format);

        for (String w : words) {
            if (max_chars < 1 || (ChatColor.stripColor(builder.toString()).length() + ChatColor.stripColor(w).length() <= max_chars) || (contents.isEmpty() && builder.length() == 0)) {
                builder.append(w).append(' ');
            } else {
                String val = builder.toString().trim();
                contents.add(val);
                builder = new StringBuilder(ChatColor.getLastColors(val));
                builder.append(w).append(' ');
            }
        }
        String last = builder.toString();
        if (!ChatColor.stripColor(last).isEmpty())
            contents.add(last.trim());
    }

    public List<String> toList() {
        return contents;
    }

}
