package me.natecb13.DataManager;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;

import me.natecb13.plugin.EvolutionSkill;
import me.natecb13.plugin.EvolutionTree;
import me.natecb13.plugin.Evolutions;

public class Config {

	private static Evolutions main;
	
	public Config(Evolutions main) {
		Config.main = main;
		
		main.getConfig().options().copyDefaults();
		main.saveDefaultConfig();
		
		
	}
	
	public static ChatColor getChatColor(EvolutionTree tree) {
		return ChatColor.valueOf(main.getConfig().getString("trees." + tree.getName() + ".chatcolor"));
	}
	
	public static String getDisplayName(EvolutionTree tree) {
		return main.getConfig().getString("trees." + tree.getName() + ".display-name");
	}
	
	public static List<String> getInfo(EvolutionTree tree) {
		return main.getConfig().getStringList("trees." + tree.getName() + ".info");
	}
	
	public static Material getColor(EvolutionTree tree) {
		return Material.valueOf(main.getConfig().getString("trees." + tree.getName() + ".accent_material"));
	}
	
	public static String getSkullTexture(EvolutionTree tree) {
		return main.getConfig().getString("trees." + tree.getName() + ".skull_texture");
	}
	
	public static List<String> getSkillDescription(EvolutionSkill skill){
		String path = "trees." + skill.getParentTree().getName() + ".skills." + skill.getName() + ".description";
		return main.getConfig().getStringList(path);
	}

	public static String getSkillSkullTexture(EvolutionSkill skill){
		String path = "trees." + skill.getParentTree().getName() + ".skills." + skill.getName() + ".skull_texture";
		return main.getConfig().getString(path);
	}
	
	public static int getSkillEnergy(EvolutionSkill skill) {
		String path = "trees." + skill.getParentTree().getName() + ".skills." + skill.getName() + ".energy";
		return main.getConfig().getInt(path);
	}
	
	public static String getSkillDisplayName(EvolutionSkill skill) {
		String path = "trees." + skill.getParentTree().getName() + ".skills." + skill.getName() + ".displayed_name";
		return main.getConfig().getString(path);
	}
	
	public static boolean getSkillEnabled(EvolutionSkill skill) {
		String path = "trees." + skill.getParentTree().getName() + ".skills." + skill.getName() + ".enabled";
		return main.getConfig().getBoolean(path);
	}
	
	
}

