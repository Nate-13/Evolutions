package me.natecb13.DataManager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

import me.natecb13.plugin.Evolutions;

public class Settings {

	public static double blazeShieldResistance() {
		return Evolutions.getSettings().getConfig().getDouble("blaze-shield-resistance");
	}
	
	public static double witherShieldDamage() {
		return Evolutions.getSettings().getConfig().getDouble("wither-shield-damage");
	}
	
	
	public static double mobAssasinResistance() {
		return Evolutions.getSettings().getConfig().getDouble("mob-assasin-resistance");
	}
	
	public static double enhancedEnduranceDamage() {
		return Evolutions.getSettings().getConfig().getDouble("enhanced-endurance-damage");
	}
	
	public static Double greenThumbsStregth() {
		return Evolutions.getSettings().getConfig().getDouble("green-thumbs-strength");
	}
	
	public static double dragonScalesResistance() {
		return Evolutions.getSettings().getConfig().getDouble("dragon-scales-resistance");
	}
	
	public static double dragonBreathDamage() {
		return Evolutions.getSettings().getConfig().getDouble("dragon-breath-damage");
	}
	
	public static double turtleShellResistance() {
		return Evolutions.getSettings().getConfig().getDouble("turtle-shell-resistance");
	}
	
	public static double prismarineSpikesDamage() {
		return Evolutions.getSettings().getConfig().getDouble("prismarine-spikes-damage");
	}
	
	public static double guardianAuraDamage() {
		return Evolutions.getSettings().getConfig().getDouble("guardians-aura-damage");
	}
	
	public static int guardianAuraRange() {
		return Evolutions.getSettings().getConfig().getInt("guardians-aura-range");
	}
	
	
	public static double denserAirResistance() {
		return Evolutions.getSettings().getConfig().getDouble("denser-air-resistance");
	}
	
	public static double turboDexterityDamage() {
		return Evolutions.getSettings().getConfig().getDouble("turbo-dexterity-damage");
	}
	
	public static double gemstoneExtractorChance() {
		return Evolutions.getSettings().getConfig().getDouble("gemstone-extractor-chance");
	}
	
	public static boolean hideUnlockedSkills() {
		return Evolutions.getSettings().getConfig().getBoolean("hide-locked-skills");
	}
	
	public static List<World> getBlacklistedWorlds(){
		List<World> worlds = new ArrayList<World>();
		for(String str : Evolutions.getSettings().getConfig().getStringList("blacklisted-worlds")) {
			if(Bukkit.getWorld(str) != null)
				worlds.add(Bukkit.getWorld(str));
		}
		return worlds;
		
	}
	
}









