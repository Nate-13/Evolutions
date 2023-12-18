package SkillListeners;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import com.google.common.base.Strings;

import me.natecb13.DataManager.Lang;
import me.natecb13.plugin.Evolutions;
import me.natecb13.plugin.TreeManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;


public class ElytraLauncher implements Listener{

	Map<UUID, Integer> inElytraLauncher = new HashMap<UUID, Integer>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		
		
	Player player =	e.getPlayer();
	
			if(!player.isSneaking()) {
				if(player.getEquipment().getChestplate() != null) {
				if(TreeManager.hasUnlockedSkill(player, "Elytra Launcher") && player.isOnGround() && player.getEquipment().getChestplate().getType() == Material.ELYTRA) {
					inElytraLauncher.put(player.getUniqueId(), 0);
			        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(Lang.prepariungLaunch() + getProgressBar(inElytraLauncher.get(player.getUniqueId()), 10, 10, '■', ChatColor.GREEN, ChatColor.GRAY))));
					Bukkit.getScheduler().scheduleSyncDelayedTask(Evolutions.getPlugin(), new Runnable() {
						@Override
						public void run() {
							checkLauncher(player);
						}
					}, 5L); 
					
					}
				}
			}
	if(inElytraLauncher.containsKey(player.getUniqueId()) && player.isSneaking()) {
			inElytraLauncher.remove(player.getUniqueId());
	        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(Lang.cancelled())));

		}
		
		
	}
	


	@SuppressWarnings("deprecation")
	public void checkLauncher(Player player) {
		if(inElytraLauncher.containsKey(player.getUniqueId()) && player.isSneaking() && player.isOnGround()) {
			inElytraLauncher.put(player.getUniqueId(), inElytraLauncher.get(player.getUniqueId()) + 1);
			
			//Action Bar
	        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(Lang.prepariungLaunch() + getProgressBar(inElytraLauncher.get(player.getUniqueId()), 10, 10, '■', ChatColor.GREEN, ChatColor.GRAY))));
			
			if(inElytraLauncher.get(player.getUniqueId()) == 10) {
				player.setVelocity(new Vector(0,20,0));
				player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_SHOOT, 1f, 1f);
		        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color(Lang.launching())));
				player.getLocation().getWorld().spawnParticle(Particle.CLOUD, player.getLocation().clone().add(0.5, 0.5, 0.5), 25, 0.5, 0.5, 0.5, 0.01);
				inElytraLauncher.remove(player.getUniqueId());
			}
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(Evolutions.getPlugin(), new Runnable() {
				@Override
				public void run() {
					checkLauncher(player);
				}
			}, 5L); 
		
		
		}
	}
	
	
	public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
            ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + completedColor + symbol, progressBars)
                + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars);
    }
	
	public String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
