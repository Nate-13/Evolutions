package me.natecb13.Runnables;

import org.bukkit.Bukkit;

import me.natecb13.plugin.Evolutions;
import me.natecb13.plugin.TreeManager;

public class AutoSave {

	public static void runAutoSave() {
	
	Bukkit.getScheduler().scheduleSyncRepeatingTask(Evolutions.getPlugin(), new Runnable() {
		@Override
		public void run() {
			TreeManager.saveData();
		}
	}, 0L, 1200L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
	
	}
}
