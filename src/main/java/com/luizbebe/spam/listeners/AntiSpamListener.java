package com.luizbebe.spam.listeners;

import com.luizbebe.spam.Main;
import com.luizbebe.spam.registry.EventRegistry;
import lombok.val;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;

public class AntiSpamListener extends EventRegistry {

    public AntiSpamListener(Main main) {
        super(main, main.getConfig());
    }

    @EventHandler
    void playerKick(PlayerKickEvent event) {
        val player = event.getPlayer();
        val reason = event.getReason();
        if (!reason.equals("disconnect.spam"))
            return;

        if (config.getBoolean("Custom-Message.Enable")) {
            val customMessage = config.getString("Custom-Message.Message").replace("&", "§");
            event.setLeaveMessage(customMessage);
            return;

        }
        if (config.getBoolean("Anti-Spam.Warn.Enable")) {
            val titleLine1 = config.getString("Anti-Spam.Warn.Title.Line1").replace("&", "§");
            val titleLine2 = config.getString("Anti-Spam.Warn.Title.Line2").replace("&", "§");
            val sound = Sound.valueOf(config.getString("Anti-Spam.Warn.Sound").toUpperCase());

            player.sendTitle(titleLine1, titleLine2);
            player.playSound(player.getLocation(), sound, 1F, 1F);
        }
        event.setCancelled(true);
    }

}
