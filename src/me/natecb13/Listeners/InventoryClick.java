package me.natecb13.Listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.natecb13.DataManager.Lang;
import me.natecb13.plugin.EvolutionTree;
import me.natecb13.plugin.EvolutionsGUI;
import me.natecb13.plugin.TreeManager;

public class InventoryClick implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(!e.getView().getTitle().contains(Lang.guiNameEvolutions().replace("%name%", ""))) return;
		e.setCancelled(true);
		if(e.getInventory().getItem(1).hasItemMeta() && e.getInventory().getItem(1).getItemMeta().hasLocalizedName()) {
			EvolutionTree tree = TreeManager.getEvolutionTree(e.getInventory().getItem(1).getItemMeta().getLocalizedName());
			
			int page = Integer.parseInt(e.getInventory().getItem(9).getItemMeta().getLocalizedName());
			
			switch (e.getSlot()) {
			case 9:
				
				if(e.getInventory().getItem(9).getType() == Material.ARROW) { 
					player.openInventory(tree.getMenu(player, page - 1));
				}
				
				break;
			case 17:
				
				if(e.getInventory().getItem(17).getType() == Material.ARROW) { 
					player.openInventory(tree.getMenu(player, page + 1));
				}
				break;
			case 31:
				player.openInventory(EvolutionsGUI.getPageInv(player, 1));
				break;
				
			default:
				break;
			}
			
		}
	
		
	}
	
	
	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(!e.getView().getTitle().equals(Lang.guiNameMainMenu())) return;
		e.setCancelled(true);
		
		List<Integer> evoSlots = Arrays.asList(4,11,15,29,33,40); 
		
		int page = Integer.parseInt(e.getInventory().getItem(36).getItemMeta().getLocalizedName());

		

		if(evoSlots.contains(e.getSlot())) {
			EvolutionTree tree = TreeManager.getEvolutionTree(e.getCurrentItem().getItemMeta().getLocalizedName());
			player.openInventory(tree.getMenu(player, 1));
			return;
		}
		
		switch (e.getSlot()) {
		case 36:
			
			if(e.getInventory().getItem(36).getType() == Material.ARROW) { 
				player.openInventory(EvolutionsGUI.getPageInv(player, page - 1));
			}
			
			break;
		case 44:
			
			if(e.getInventory().getItem(44).getType() == Material.ARROW) { 
				player.openInventory(EvolutionsGUI.getPageInv(player, page + 1));
			}
			break;
			
		default:
			break;
		}
		
		
	}
	
	public String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	
}
