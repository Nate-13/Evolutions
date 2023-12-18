package me.natecb13.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

import me.natecb13.plugin.Evolutions;
import me.natecb13.plugin.TreeManager;

public class PassiveEnergy {

	public static void runPassiveEnergy() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Evolutions.getPlugin(), new Runnable() {
			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()) {
					if(player.getWorld().getEnvironment() == Environment.NETHER) {
						TreeManager.getEvolutionTree("Nether").addEnergy(player, 1);
					}
					if(player.getWorld().getEnvironment() == Environment.THE_END) {
						TreeManager.getEvolutionTree("End").addEnergy(player, 1);
					}
					if(player.isInWater()) {
						TreeManager.getEvolutionTree("Ocean").addEnergy(player, 1);
					}
					
					if(player.getWorld().getEnvironment() == Environment.NORMAL && player.getLocation().getY() > 40 && !player.isGliding() && !player.isInWater()) {
						TreeManager.getEvolutionTree("Earth").addEnergy(player, 1);
					}
					
					if(player.getLocation().getY() < 40) {
						TreeManager.getEvolutionTree("Cave").addEnergy(player, 1);
					}
					
					if(player.isGliding() && !player.isInWater()) {
						TreeManager.getEvolutionTree("Sky").addEnergy(player, 1);
					}
					
				}
				
			}
		}, 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
	}
	
}
