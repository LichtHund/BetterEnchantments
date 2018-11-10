package me.mattmoreira.betterenchants;

import me.mattmoreira.betterenchants.commands.CMDBenchant;
import me.mattmoreira.betterenchants.files.MessagesHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterEnchants extends JavaPlugin {

    private static BetterEnchants instance;
    private static MessagesHandler messagesHandler;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("benchant").setExecutor(new CMDBenchant());

        messagesHandler = new MessagesHandler();
        messagesHandler.initialize();

    }

    public static BetterEnchants getInstance() {
        return instance;
    }

    public static MessagesHandler getMessagesHandler() {
        return messagesHandler;
    }

}
