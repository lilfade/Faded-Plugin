package me.lilfade.faded;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Faded extends JavaPlugin{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Faded plugin;
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has Been Disabled");
	}
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has been Enabled!");		
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("sendme")){
			if(!(sender instanceof Player)) {
				sender.sendMessage("This command can only be used by players, not the console");
			} else {
				Player player = (Player) sender;
				player.sendMessage(ChatColor.GOLD + "Sent");
				player.sendMessage(ChatColor.AQUA + "Yourname: " + player.getName());
				try {
					URL fgURL = new URL("http://localhost/serverenable.php?cname=" + player.getName());
					URLConnection fgURLConnection = fgURL.openConnection();
					fgURLConnection.connect();
				}
				catch (MalformedURLException e) {
					//new URL() failed
					player.sendMessage(ChatColor.DARK_RED + "Url failed");
				
				}
				catch (IOException e) {
					// openCnnection() failed
					player.sendMessage(ChatColor.DARK_RED + "open connection failed");
				}
			}
			return true;
		}
		return false;
	}

}
