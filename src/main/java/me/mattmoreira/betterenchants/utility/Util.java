package me.mattmoreira.betterenchants.utility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

public class Util {

    /**
     * Utility to use color codes easierly
     *
     * @param msg The message String
     * @return returns the string with color
     */
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * Simplified way for sending console messages
     *
     * @param msg the message to be sent to the console
     */
    public static void info(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(msg);
    }

    /**
     * Gets arguments from each command for the tab completion
     *
     * @return Returns 2d string array with arguments for tab completion
     */
    public static String[][] getTabCompleteArgs() {
        String[][] argComplete = new String[5][];

        argComplete[0] = getEnchantsList();
        argComplete[1] = new String[]{"0", "1", "2", "3", "4", "5"};

        return argComplete;
    }

    /**
     * @param str String to check if it is a number or not
     * @return Returns true if it is a number false if it is a string or contains any non numeric character
     */
    public static boolean notInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }
        return false;
    }

    private static String[] getEnchantsList() {
        Enchantment[] enchantments = Enchantment.values();
        String[] enchantmentsString = new String[enchantments.length];

        for (int i = 0; i < enchantments.length; i++) {
            enchantmentsString[i] = enchantments[i].getName().toLowerCase();
        }

        return enchantmentsString;
    }


}
