package me.lilfade.faded;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class FadedPM {
	
Faded plugin;
	
public FadedPM(Faded instance) {
		plugin = instance;
}
//player send message
	//player messages admin, all players that can answer this message should get it
	//in php script manage who is a admin and forward to mod mailbox
	//on player join if permissions is mod or above notify on messages
	public void sendPM(String[] args){
		//do something on message send
	}
}