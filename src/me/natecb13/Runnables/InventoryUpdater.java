package me.natecb13.Runnables;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.natecb13.DataManager.Lang;
import me.natecb13.plugin.EvolutionTree;
import me.natecb13.plugin.EvolutionsGUI;
import me.natecb13.plugin.Evolutions;
import me.natecb13.plugin.TreeManager;

public class InventoryUpdater {

	public static void runInventoryUpdates() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Evolutions.getPlugin(), new Runnable() {
			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()) {
					if(player.getOpenInventory() != null) {
						if(player.getOpenInventory().getTitle().contains(Lang.guiNameEvolutions().replace("%name%", ""))) {
							if(player.getOpenInventory().getItem(1).hasItemMeta() && player.getOpenInventory().getItem(1).getItemMeta().hasLocalizedName()) {
								EvolutionTree tree = TreeManager.getEvolutionTree(player.getOpenInventory().getItem(1).getItemMeta().getLocalizedName());
								int page = Integer.parseInt(player.getOpenInventory().getItem(9).getItemMeta().getLocalizedName());
								
								List<Integer> slots = Arrays.asList(0,11,13,15,19,20,21,22,23,24,25);
								Inventory newInv = tree.getMenu(player, page);
								
								for(Integer slot : slots) {
									player.getOpenInventory().setItem(slot, newInv.getItem(slot));
								}
								
								
				
								
								

								
							}
						}
						if(player.getOpenInventory().getTitle().equals(Lang.guiNameMainMenu())) {
							List<Integer> slots = Arrays.asList(4,11,15,22,29,33,40);
							
							int page = Integer.parseInt(player.getOpenInventory().getItem(36).getItemMeta().getLocalizedName());

							
							Inventory newInv = EvolutionsGUI.getPageInv(player, page);
							
							for(Integer slot : slots) {
								player.getOpenInventory().setItem(slot, newInv.getItem(slot));
							}
							
						}
	
					
					}
					
					
				}
				
			}


		}, 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
	}

	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	
}
