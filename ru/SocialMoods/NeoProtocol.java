package ru.SocialMoods;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.List;

public class NeoProtocol extends PluginBase implements Listener {

    private List<String> versions;
    private String kickMessage;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Config config = getConfig();

        versions = config.getStringList("versions");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        Player player = event.getPlayer();
        String protocol = player.getLoginChainData().getGameVersion();
        player.getLoginChainData().getD
        getLogger().info("Версия игрока " + player.getName() + ": " + protocol);

        if (!versions.contains(protocol)) {
            event.setCancelled(true);
            event.setKickMessage(getConfig().getString("kick-message", "У вас несовместивая версия!"));
        }
    }
}
