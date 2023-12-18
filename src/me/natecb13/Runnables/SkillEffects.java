package me.natecb13.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.natecb13.plugin.Evolutions;
import me.natecb13.plugin.TreeManager;

public class SkillEffects {
	
	public static void runSkillEffects() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Evolutions.getPlugin(), new Runnable() {
			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()) {
					
				if(player.isInWater()) {
						
					if(TreeManager.hasUnlockedSkill(player, "Makeshift Gills")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 29, 0));
					}
					
					if(TreeManager.hasUnlockedSkill(player, "Pocket Conduit")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 29, 0));
					}
					
					if(TreeManager.hasUnlockedSkill(player, "Eternal Bond")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 29, 0));
					}
				}	
					
				//Cave
				if(player.getWorld().getEnvironment() == Environment.NORMAL && player.getLocation().getY() < 40) {
					
					if(TreeManager.hasUnlockedSkill(player, "Enhanced Eyesight")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 250, 0));
					}
					
					if(TreeManager.hasUnlockedSkill(player, "Energized Arms")) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 29, 1));
					}
					
					
					
					}
					
				}
				
			}
		}, 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
	}
}
