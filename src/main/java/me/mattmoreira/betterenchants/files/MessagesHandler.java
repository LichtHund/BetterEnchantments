package me.mattmoreira.betterenchants.files;

import me.mattmoreira.betterenchants.BetterEnchants;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static me.mattmoreira.betterenchants.utility.Util.color;

public class MessagesHandler {

    private HashMap<String, String> messages;

    /**
     * Send message to the console saying this is the language selected
     */
    public void initialize() {
        messages = new HashMap<>();
        cacheMessage();
    }

    /**
     * Caches all messages into a HashMap for easier access
     */
    private void cacheMessage() {
        new Thread(() -> {
            File messagesFile;
            FileConfiguration fileConfiguration;

            try {
                messagesFile = new File(BetterEnchants.getInstance().getDataFolder(), "messages.yml");
                fileConfiguration = new YamlConfiguration();

                if (!messagesFile.exists()) BetterEnchants.getInstance().saveResource("messages.yml", false);

                fileConfiguration.load(messagesFile);

                for (String parent : fileConfiguration.getConfigurationSection("messages").getKeys(false)) {
                    for (String child : fileConfiguration.getConfigurationSection("messages." + parent).getKeys(false))
                        messages.put("messages." + parent + "." + child, fileConfiguration.getString("messages." + parent + "." + child));
                }

            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Gets message from the lang file
     *
     * @param path String with the path to the message
     * @return Returns String with colored message from file
     */
    public String getMessage(String path) {
        return color(messages.get(path));
    }

}
