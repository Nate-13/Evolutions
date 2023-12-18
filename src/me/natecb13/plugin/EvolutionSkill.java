package me.natecb13.plugin;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.natecb13.DataManager.Lang;
import me.natecb13.DataManager.Settings;
import me.natecb13.utils.ItemBuilder;

public class EvolutionSkill {

	int xp;
	List<String> description;
	ItemStack icon;
	String name;
	String displayName;
	EvolutionTree parentTree;
	String skullURL;
	boolean isEnabled;
	
	public EvolutionSkill(EvolutionTree parentTree, String name, int xp, List<String> description, String skullURL) {
		this.xp = xp;
		this.description = description;
		this.name = name;
		this.parentTree = parentTree;
		this.skullURL = skullURL;
		this.displayName = name;
		this.isEnabled = true;
			
	}
	
	private List<String> getDescription(Player player) {
		
		if(!Settings.hideUnlockedSkills()) {
			return description;
		}
		
		if(TreeManager.hasUnlockedSkill(player, this.name)) {
			return description;
		}
		List<String> locked = Arrays.asList("&7???");
		return locked;
		
		
	}
	
	public ItemStack getIcon(Player player){
		icon = new ItemBuilder(Material.PLAYER_HEAD).name(parentTree.getChatColor() + displayName).lore(getDescription(player)).lore(" ").lore(Lang.requiredEnergy().replace("%energy%", parentTree.getDisplayName() + parentTree.getChatColor() + " " + Lang.energyIcon()).replace("%amount%", "&a" + xp)).makeSkull(skullURL).build();

		
		
		return this.icon;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public int getEnergyRequirement() {
		
		return this.xp;
	}
	
	public EvolutionTree getParentTree() {
		return this.parentTree; 
	}
	
	public List<String> getDescription(){
		return this.description;
	}
	
	public String getSkullTexture() {
		return this.skullURL;
	}
	
	public void setSkullTexture(String skullURL) {
		this.skullURL = skullURL;
	}
	
	public void setEnergyRequirement(int amount) {
		this.xp = amount;
	}
	
	public void setDescription(List<String> description) {
		this.description = description;
	}
	
	public void setDisplayName(String name) {
		this.displayName = name;
	}
	
	
	public void setIsEnabled(boolean value) {
		this.isEnabled = value;
	}
	
	public boolean getIsEnabled() {
		return this.isEnabled;
	}
	
}






