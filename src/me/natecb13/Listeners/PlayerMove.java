package me.natecb13.Listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.natecb13.plugin.Evolutions;

public class PlayerMove implements Listener{

	static List<UUID> moved = new ArrayList<UUID>();
	static Map<UUID, Float> yaw = new HashMap<UUID, Float>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		
		
		if(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {
		if(!hasPlayerMoved(player)) {
		
			if(e.getTo().getYaw() != yaw.get(player.getUniqueId())) {
				if(!moved.contains(player.getUniqueId())) {
					moved.add(player.getUniqueId());
					}
				}
			}
		}
	}
	
	
	public static void checkPlayerAFK() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Evolutions.getPlugin(), new Runnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					
					if(!yaw.containsKey(player.getUniqueId())) {
						yaw.put(player.getUniqueId(), player.getLocation().getYaw());
						continue;
						
					} else {
						
						float pastYaw = yaw.get(player.getUniqueId());
						float currentYaw = player.getLocation().getYaw();
						
						if(pastYaw == currentYaw) {
							if(moved.contains(player.getUniqueId())) {
								moved.remove(player.getUniqueId());
							}
							
						} else {
							if(!moved.contains(player.getUniqueId())) {
							moved.add(player.getUniqueId());
							}
						}
						
						yaw.put(player.getUniqueId(), player.getLocation().getYaw());

					}
					
				}
				
			}
			
			
		}, 0L, 1200L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
		
	}
	
	public static boolean hasPlayerMoved(Player player) {
		if(moved.contains(player.getUniqueId())) return true;
		return false;
	}
	
	public static void setPlayerMoved(Player player) {
		if(!moved.contains(player.getUniqueId())) {
			moved.add(player.getUniqueId());
			}
	}
	

	
}
