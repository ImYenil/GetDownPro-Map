package net.choco.getdownpro;

import lombok.Getter;
import me.mattstudios.mf.base.CommandManager;
import net.choco.getdownpro.command.MapCommand;
import net.choco.getdownpro.manager.FileManager;
import net.choco.getdownpro.manager.GDSettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Getter
    private FileManager fileManager;

    @Getter
    private GDSettings settings;

    private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;

        this.fileManager = new FileManager(this);
        this.loadAllConfigs();

        this.settings = new GDSettings(this);
        this.settings.loadSettings();

        commandManager = new CommandManager(this);
        commandManager.register(new MapCommand());

        if (!Bukkit.getPluginManager().isPluginEnabled("GetDownPro")) {
            info("GetDownPro was not found. Disabling...");
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadAllConfigs() {
        this.fileManager.getConfig("config.yml").copyDefaults(true).save();
        this.fileManager.getConfig("translates.yml").copyDefaults(true).save();
    }

    private void reloadAllConfigs() {
         FileManager.configs.values().stream().forEach(FileManager.Config::reload);
    }

    public static void info(String message) {
        Bukkit.getConsoleSender().sendMessage(Main.getInstance().getSettings().getPrefix() + message);
    }
}
