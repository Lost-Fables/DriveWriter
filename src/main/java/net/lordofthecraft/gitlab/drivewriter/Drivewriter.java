package net.lordofthecraft.gitlab.drivewriter;

import org.bukkit.plugin.java.JavaPlugin;

public final class Drivewriter extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands(){
        getCommand("DriveWriter").setExecutor(new DrivewriterCommand());
    }
}
