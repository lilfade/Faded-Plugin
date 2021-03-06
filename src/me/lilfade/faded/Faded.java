package me.lilfade.faded;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Faded extends JavaPlugin{
	public static Logger logger;
	@Override
	public void onEnable() {
	//	PluginDescriptionFile pdfFile = this.getDescription();
	//	this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has been Enabled!");		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new FadedPlayerListener(this),  this);
		
		final FileConfiguration config = this.getConfig();
		//copy over the default config file
		config.options().copyDefaults(true);
		saveConfig();
		
		logger = getLogger();
	}
	
	@Override
	public void onDisable() {
	//	PluginDescriptionFile pdfFile = this.getDescription();
	//	this.logger.info(pdfFile.getName() + " Has Been Disabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("bug")) {
			//we got a bug here, store it in the db with who reported it
			FadedUtils.sendGetRequest(getConfig().getString("http-server")+getConfig().getString("auth-page"), "action=addbug&player="+player.getName()+"&report="+FadedUtils.convertArgsToString(args));
			player.sendMessage("Bug sent thanks for telling us about it!");
		}else if(commandLabel.equalsIgnoreCase("suggestion")){
			//we got a suggestion, add it to the bd but say who its from as well 
			FadedUtils.sendGetRequest(getConfig().getString("http-server")+getConfig().getString("auth-page"), "action=addsuggestion&player="+player.getName()+"&suggestion="+FadedUtils.convertArgsToString(args));
			player.sendMessage("Suggestion sent thanks!");
		}else if(commandLabel.equalsIgnoreCase("bv")) {
			//simple command to find the server version 
			player.sendMessage("Bukkit Version:" + getServer().getBukkitVersion());
		}else if(commandLabel.equalsIgnoreCase("pm")) {
			//someone wants to send a private message to someone 
			player.sendMessage("Hello: "+ player.getName() + "Your message: " + FadedUtils.convertArgsToString1(args));
		}
		return false;
	}
}
