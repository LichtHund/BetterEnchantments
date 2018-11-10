package me.mattmoreira.betterenchants.commands;

import me.mattmoreira.betterenchants.BetterEnchants;
import me.mattmoreira.betterenchants.utility.Path;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static me.mattmoreira.betterenchants.utility.Util.getTabCompleteArgs;
import static me.mattmoreira.betterenchants.utility.Util.notInteger;

public class CMDBenchant implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length != 3) {
                player.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.WRONG_USAGE));
                return false;
            }

            String playerName = args[0];
            String enchantment = args[1];

            if (notInteger(args[2])) {
                player.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.WRONG_USAGE));
                return false;
            }

            int level = Integer.parseInt(args[2]);

            if (Bukkit.getPlayer(playerName) == null) {
                player.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.WRONG_PLAYER));
                return false;
            }

            Player playerEnchant = Bukkit.getPlayer(playerName);

            if (Enchantment.getByName(enchantment.toUpperCase()) == null) {
                player.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.WRONG_ENCHANT));
                return false;
            }

            Enchantment enchant = Enchantment.getByName(enchantment.toUpperCase());

            if (playerEnchant.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                player.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.EMPTY_HAND));
                return false;
            }

            playerEnchant.getInventory().getItemInMainHand().addUnsafeEnchantment(enchant, level);
            player.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.ENCHANT_ADDED));

        } else {

            if (args.length != 3) {
                sender.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.WRONG_USAGE));
                return false;
            }

            String playerName = args[0];
            String enchantment = args[1];

            if (notInteger(args[2])) {
                sender.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.WRONG_USAGE));
                return false;
            }

            int level = Integer.parseInt(args[2]);

            if (Bukkit.getPlayer(playerName) == null) {
                sender.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.WRONG_PLAYER));
                return false;
            }

            Player playerEnchant = Bukkit.getPlayer(playerName);

            if (Enchantment.getByName(enchantment.toUpperCase()) == null) {
                sender.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.WRONG_ENCHANT));
                return false;
            }

            Enchantment enchant = Enchantment.getByName(enchantment.toUpperCase());

            if (playerEnchant.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                sender.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.EMPTY_HAND));
                return false;
            }

            playerEnchant.getInventory().getItemInMainHand().addUnsafeEnchantment(enchant, level);
            sender.sendMessage(BetterEnchants.getMessagesHandler().getMessage(Path.ENCHANT_ADDED));
        }

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel,
                                      String[] args) {
        if (cmd.getName().equalsIgnoreCase("benchant")) {
            if (args.length == 2) return getCommandNames(args, 1, (Player) sender);
            if (args.length == 3) return getCommandNames(args, 2, (Player) sender);
        }

        return null;
    }

    /**
     * Gets the subcomands to tab complete
     *
     * @param args   Arguments from the command
     * @param arg    Number of arguments to get the correct from the getTabCompleteArgs()
     * @return Returns list with Strings to the tab complete
     */
    private List<String> getCommandNames(String[] args, int arg, Player player) {
        List<String> commandNames = new ArrayList<>();
        String[][] argsComplete = getTabCompleteArgs();

        if (!args[arg - 1].equals("")) {
            for (String commandName : argsComplete[arg - 1]) {
                if (arg + 1 > args.length) break;
                if (!commandName.toLowerCase().startsWith(args[arg].toLowerCase())) continue;
                commandNames.add(commandName);
            }
        } else {
            commandNames = Arrays.asList(argsComplete[arg - 1]);
        }

        Collections.sort(commandNames);

        return commandNames;
    }
}
