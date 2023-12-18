package me.natecb13.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.natecb13.plugin.TreeManager;

public class JoinEvent implements Listener{

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		TreeManager.addPlayerToTrees(player);
		PlayerMove.setPlayerMoved(player);
		
	}
	
}
