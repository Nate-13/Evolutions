package me.natecb13.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.natecb13.DataManager.Config;
import me.natecb13.DataManager.Settings;

public class TreeManager {

	public static List<EvolutionTree> evolutionTrees = new ArrayList<EvolutionTree>();
	
	
	
	public static void addTree(EvolutionTree tree) {
		evolutionTrees.add(tree);
		tree.reorderSkills();
	}
	
	public static EvolutionTree getEvolutionTree(String name) {
		for(EvolutionTree tree : evolutionTrees) {
			if(name.toUpperCase().equals(tree.getName().toUpperCase())) return tree;
		}
		return null;
	}
	
	public static void addPlayerToTrees(Player player) {
		for(EvolutionTree tree : evolutionTrees) {
			tree.addPlayer(player);
		}
	}
	
	public static EvolutionTree getEvolutionTreeBySkillName(String skillName) {
		for(EvolutionTree tree : evolutionTrees) {
			 if(tree.getSkill(skillName) != null) return tree;
		}
		return null;
	}
	
	public static EvolutionTree getEvolutionTreeBySkill(EvolutionSkill skill) {
		for(EvolutionTree tree : evolutionTrees) {
			 if(tree.getSkill(skill.getName()) != null) return tree;
		}
		return null;
	}
	
	
	public static EvolutionSkill getSkill(String name) {
		for(EvolutionTree tree : evolutionTrees) {
			 if(tree.getSkill(name) != null) return tree.getSkill(name);
		}
		return null;
	}
	
	
	public static boolean hasUnlockedSkill(Player player, String skillName) {
		if(Settings.getBlacklistedWorlds().contains(player.getWorld())) return false;
		if(getSkill(skillName).getIsEnabled() == false) return false;
		int skillReq = getSkill(skillName).getEnergyRequirement();
		int playerEnergy = getEvolutionTreeBySkillName(skillName).getEnergy(player);
		if(playerEnergy >= skillReq) return true;
		return false;
	}
	
	public static void saveData() {
		
	
		      
		  for(EvolutionTree tree: evolutionTrees) {
			for(Map.Entry<UUID, Integer> entry: tree.getEnergyMap().entrySet()) {
				Evolutions.getData().getConfig().set("data." +  tree.getName() + "." + entry.getKey(), entry.getValue());
				}
		  }
		   
		  Evolutions.getData().saveConfig();
		  
		
		
	}
	
	public static void loadData() {
		
		
		
		if(!Evolutions.getData().getConfig().contains("data")) return;
		
		for(EvolutionTree tree: evolutionTrees) {
			
			if(!Evolutions.getData().getConfig().contains("data." + tree.getName())) continue;
			
			Evolutions.getData().getConfig().getConfigurationSection("data." + tree.getName()).getKeys(false).forEach(key->{
				Integer content = (Integer) Evolutions.getData().getConfig().get("data." + tree.getName() + "." + key);
				tree.getEnergyMap().put(UUID.fromString(key), content);
			});
		
		}
		
	}
	
	public static void saveToConfig() {
		FileConfiguration config = Evolutions.getPlugin().getConfig();
		
		
		for(EvolutionTree tree : evolutionTrees) {
			
			String path = "trees." + tree.getName() + ".";
			
			if(!config.contains(path + "display-name")) {
				config.set(path + "display-name", tree.getDisplayName());
			}
			
			if(!config.contains(path + "chatcolor")) {
				config.set(path + "chatcolor", tree.getChatColor().name());
			}
			
			if(!config.contains(path + "info")) {
				config.set(path +"info", tree.getInfo());
			}
			
			if(!config.contains(path + "accent_material")) {
				config.set(path + "accent_material", tree.getColor().name());
			}
			
			if(!config.contains(path + "skull_texture")) {
				config.set(path + "skull_texture", tree.getSkullTexture());
			}
			
			//Skills --------
			
			for(EvolutionSkill skill : tree.getSkills()) {
				
				String skillPath = path + "skills." + skill.getName() + ".";
				
				if(!config.contains(skillPath + "enabled")) {
					config.set(skillPath + "enabled", skill.getIsEnabled());
				}
				
				if(!config.contains(skillPath + "displayed_name")) {
					config.set(skillPath + "displayed_name", skill.getDisplayName());
				}
				
				if(!config.contains(skillPath+ "energy")) {
					config.set(skillPath + "energy", skill.getEnergyRequirement());
				}
				
				if(!config.contains(skillPath + "description")) {
					config.set(skillPath + "description", skill.getDescription());
				}
				
				if(!config.contains(skillPath + "skull_texture")) {
					config.set(skillPath + "skull_texture", skill.getSkullTexture());
				}
				
				
				
				
			}
			
		}
		
		
		Evolutions.getPlugin().saveConfig();
	}

	
	
	public static void resetConfig() {
		
		Evolutions.getPlugin().reloadConfig();
		
		
		for(EvolutionTree tree : evolutionTrees) {
			
			tree.setDisplayName(Config.getDisplayName(tree));
			tree.setChatColor(Config.getChatColor(tree));
			tree.setInfo(Config.getInfo(tree));
			tree.setColor(Config.getColor(tree));
			tree.setSkullTexture(Config.getSkullTexture(tree));
			
			for(EvolutionSkill skill : tree.getSkills()) {
				skill.setDescription(Config.getSkillDescription(skill));
				skill.setSkullTexture(Config.getSkillSkullTexture(skill));
				skill.setEnergyRequirement(Config.getSkillEnergy(skill));
				skill.setDisplayName(Config.getSkillDisplayName(skill));
				skill.setIsEnabled(Config.getSkillEnabled(skill));

			}
			
			
		}
		
		
	}
	
	
	public static void resortAllTrees() {
		for(EvolutionTree tree : evolutionTrees) {
			tree.reorderSkills();
		}
	}
	
	
}










