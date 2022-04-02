package com.luizbebe.spam;

import com.luizbebe.spam.listeners.AntiSpamListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        new AntiSpamListener(this);
    }

}
