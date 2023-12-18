package me.natecb13.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.natecb13.DataManager.Lang;
import me.natecb13.utils.ItemBuilder;
import me.natecb13.utils.PageUtil;

public class EvolutionsGUI {

	static List<ItemStack> allItems = new ArrayList<>();
	
	

	public static Inventory getPageInv(Player player, int page) {
		
		Inventory inv = Bukkit.createInventory(null, 45, Lang.guiNameMainMenu());
		
		allItems.clear();	
		
		for(EvolutionTree tree : TreeManager.evolutionTrees) {
			allItems.add(new ItemBuilder(Material.PLAYER_HEAD).name(tree.getChatColor() + Lang.treeName().replace("%name%", tree.getDisplayName())).localisedName(tree.getName()).lore(Lang.percentComplete().replace("%percent%", tree.getPercentUnlocked(player)), " ", Lang.clickToView()).makeSkull(tree.getSkullTexture()).build());
		}
		
		
		//Add to allitems;
		
		int[] blackPanes =new int[]{0,1,7,8,9,17,18,19,25,26,27,35,36,37,34,43,44};
		int[] bluePanes =new int[]{2,6,10,16,20,24,28,34,38,42};
		int[] lightPanes =new int[]{3,5,12,13,14,21,22,23,30,31,32,39,41};
		
		for(int slot : blackPanes) {
			inv.setItem(slot, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(" ").build());
		}
		
		for(int slot : bluePanes) {
			inv.setItem(slot, new ItemBuilder(Material.CYAN_STAINED_GLASS_PANE).name(" ").build());
		}
		for(int slot : lightPanes) {
			inv.setItem(slot, new ItemBuilder(Material.LIGHT_GRAY_STAINED_GLASS_PANE).name(" ").build());
		}
		
		List<String> energy = new ArrayList<String>();
		energy.add("&7----------------------");
		for(EvolutionTree tree : TreeManager.evolutionTrees) {
			energy.add(tree.getChatColor() + tree.getDisplayName() + " " + Lang.energy() + " " + Lang.energyIcon() + ": &e" + tree.getEnergy(player));
		}
		energy.add("&7----------------------");

		
		inv.setItem(22, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(player.getName()).name(Lang.playerEvolutionEnergy().replace("%player%", player.getName())).lore(energy).build());
		
				
		
		

	
	
	if(PageUtil.isPageValid(allItems, page -1, 6)) {
		inv.setItem(36, new ItemBuilder(Material.ARROW).name(Lang.previousPage()).localisedName(page + "").build());
		
	}else {
	    inv.setItem(36, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name("&7").localisedName(page + "").build());
	}
		

    if(PageUtil.isPageValid(allItems, page +1, 6)) {
    	inv.setItem(44, new ItemBuilder(Material.ARROW).name(Lang.nextPage()).build());
	}
	else {
		inv.setItem(44, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name("&7").build());
	}

	
	for(ItemStack item: PageUtil.getPageItems(allItems, page, 6)) {
		inv.setItem(inv.firstEmpty(), item);
	}
	
	
	return inv;
	}
	
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
