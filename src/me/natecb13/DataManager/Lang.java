package me.natecb13.DataManager;

import org.bukkit.ChatColor;

import me.natecb13.plugin.Evolutions;

public class Lang {

	
	public Lang(Evolutions main) {		
		main.getConfig().options().copyDefaults();
		main.saveDefaultConfig();
		
	}
	
	public static String configReloaded() {return color(Evolutions.lang.getConfig().getString("config-reloaded"));}
	public static String environmentNotFound() {return color(Evolutions.lang.getConfig().getString("environment-not-found"));}
	public static String openUsage() {return color(Evolutions.lang.getConfig().getString("open-usage"));}
	public static String addenergyUsage() {return color(Evolutions.lang.getConfig().getString("addenergy-usage"));}
	public static String notANumber() {return color(Evolutions.lang.getConfig().getString("not-a-number"));}
	public static String unlockedAll() {return color(Evolutions.lang.getConfig().getString("unlocked-all"));}
	public static String resetAll() {return color(Evolutions.lang.getConfig().getString("reset-all"));}
	public static String notACommand() {return color(Evolutions.lang.getConfig().getString("not-a-command"));}
	public static String requiredEnergy() {return color(Evolutions.lang.getConfig().getString("required-energy"));}
	public static String addEnergy() {return color(Evolutions.lang.getConfig().getString("add-energy"));}
	public static String cancelled() {return color(Evolutions.lang.getConfig().getString("cancelled"));}
	public static String prepariungLaunch() {return color(Evolutions.lang.getConfig().getString("preparing-launch"));}
	public static String launching() {return color(Evolutions.lang.getConfig().getString("launching"));}
	public static String guiNameMainMenu() {return color(Evolutions.lang.getConfig().getString("gui-name-main-menu"));}
	public static String guiNameEvolutions() {return color(Evolutions.lang.getConfig().getString("gui-name-evolutions"));}
	public static String evolutions() {return color(Evolutions.lang.getConfig().getString("evolutions"));}
	public static String percentComplete() {return color(Evolutions.lang.getConfig().getString("percent-complete"));}
	public static String clickToView() {return color(Evolutions.lang.getConfig().getString("click-to-view"));}
	public static String nextPage() {return color(Evolutions.lang.getConfig().getString("next-page"));}
	public static String previousPage() {return color(Evolutions.lang.getConfig().getString("previous-page"));}
	public static String closeMenu() {return color(Evolutions.lang.getConfig().getString("close-menu"));}
	public static String newEvolutionSkill() {return color(Evolutions.lang.getConfig().getString("new-evolution-skill"));}
	public static String locked() {return color(Evolutions.lang.getConfig().getString("locked"));}
	public static String unlocked() {return color(Evolutions.lang.getConfig().getString("unlocked"));}
	public static String gainEnergyBy() {return color(Evolutions.lang.getConfig().getString("gain-energy-by"));}
	public static String yourEnergy() {return color(Evolutions.lang.getConfig().getString("your-energy"));}
	public static String playerEvolutionEnergy() {return color(Evolutions.lang.getConfig().getString("player-evolution-energy"));}
	public static String maxed() {return color(Evolutions.lang.getConfig().getString("maxed"));}
	public static String nextEvolution() {return color(Evolutions.lang.getConfig().getString("next-evolution"));}
	public static String energy() {return color(Evolutions.lang.getConfig().getString("energy"));}
	public static String noPermission() {return color(Evolutions.lang.getConfig().getString("no-permission"));}
	public static String resetUsage() {return color(Evolutions.lang.getConfig().getString("reset-usage"));}
	public static String playerNotFound() {return color(Evolutions.lang.getConfig().getString("player-not-found"));}
	public static String energyIcon() {return color(Evolutions.lang.getConfig().getString("energy-icon"));}
	public static String treeName() {return color(Evolutions.lang.getConfig().getString("tree-name"));}


	
	
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	
}
