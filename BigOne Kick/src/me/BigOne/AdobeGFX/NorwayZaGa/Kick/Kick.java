package me.BigOne.AdobeGFX.NorwayZaGa.Kick;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Kick extends JavaPlugin
{
	public ChatColor red = ChatColor.RED;
	public ChatColor gray = ChatColor.GRAY;
	public ChatColor green = ChatColor.GREEN;
	String console = red + "Denne kommandoen kan bare bli kjørt av en spiller!";
	String permission = red + "Du har ikke permission til dette!";
	String message1 = red + "skriv /kick <spiller <grunn>";

  
  public void onDisable() {
    System.out.println("=========( BigOne Kick )=========");
    System.out.println("[BigOne Kick] is disabled!");
  }

  public void onEnable() {
    System.out.println("=========( BigOne Kick )=========");
    System.out.println("[BigOne Kick] is enabled!");
  }
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
	  if (!(sender instanceof Player)) {
		  sender.sendMessage(console);
		  return true;
	  }
	  Player p = (Player)sender;
      if (!p.hasPermission("BigOne.kick")) {
    	  p.sendMessage(permission);
      } else {
        if (args.length == 0) {
          p.sendMessage(message1);
      } else if (args.length == 1) {
    	  Player tp = Bukkit.getServer().getPlayer(args[0]);
    	  if (tp == null) {
    		  p.sendMessage(ChatColor.RED + args[0] + " er ikke pålogget");
    		  return true;
    	  } else {
    		  Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.DARK_GREEN + " Kicket " + ChatColor.WHITE + tp.getDisplayName() + ChatColor.DARK_GREEN + " fra serveren.");
    		  Bukkit.broadcastMessage(ChatColor.GOLD + "Grunn: Ingen grunn oppgitt, vi trenger ikke en.");
        tp.kickPlayer("Du ble kicket fra serveren." + ChatColor.YELLOW + " [Grunn: Ingen grunn oppgitt, vi trenger ikke en]" + ChatColor.WHITE + ".");
    	  }
      } else if (args.length >= 2) {
    	  Player tp = Bukkit.getServer().getPlayer(args[0]);
    	  if (tp == null) {
    		  p.sendMessage(ChatColor.RED + args[0] + " is offline");
    		  return true;
    	  } else  {
    	Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.DARK_GREEN + " Kicket " + ChatColor.WHITE + tp.getDisplayName() + ChatColor.DARK_GREEN + " fra serveren.");
    	Bukkit.broadcastMessage(ChatColor.GOLD + "Grunn: " + message(args) + ".");
    tp.kickPlayer("Du ble kicket fra serveren." + ChatColor.YELLOW + " [Grunn: " + message(args) + "]" + ChatColor.WHITE + ".");
    return true;
			    }
      }
      }
	return false;
  }
        @EventHandler
        public void onPlayerKick(PlayerKickEvent e) {
        	e.setLeaveMessage("");
        }
	public String message(String[] args) {
 		StringBuilder sb = new StringBuilder();
 		for (int i = 1; i < args.length;i++)
 			sb.append(args[i] + " ");
 		return sb.toString().trim();
	  }
}