package me.natecb13.plugin;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import SkillListeners.AntiVoid;
import SkillListeners.CaveDamage;
import SkillListeners.DoubleDebris;
import SkillListeners.EarthDamage;
import SkillListeners.ElytraLauncher;
import SkillListeners.EndDamage;
import SkillListeners.FungalForrager;
import SkillListeners.GemstoneExtractor;
import SkillListeners.GreenThumb;
import SkillListeners.GuardianAura;
import SkillListeners.NetherDamage;
import SkillListeners.ObsidianSkin;
import SkillListeners.OceanDamage;
import SkillListeners.ReverseShulker;
import SkillListeners.SkyDamage;
import SkillListeners.Thunderstorm;
import me.natecb13.DataManager.Config;
import me.natecb13.DataManager.DataManager;
import me.natecb13.DataManager.GameSettingsManager;
import me.natecb13.DataManager.Lang;
import me.natecb13.DataManager.LangManager;
import me.natecb13.DataManager.Metrics;
import me.natecb13.Energy.EarthEnergy;
import me.natecb13.Energy.EndEnergy;
import me.natecb13.Energy.MiningEnergy;
import me.natecb13.Energy.NetherEnergy;
import me.natecb13.Energy.OceanEnergy;
import me.natecb13.Energy.SkyEnergy;
import me.natecb13.Listeners.InventoryClick;
import me.natecb13.Listeners.JoinEvent;
import me.natecb13.Listeners.PlayerMove;
import me.natecb13.Runnables.AutoSave;
import me.natecb13.Runnables.InventoryUpdater;
import me.natecb13.Runnables.PassiveEnergy;
import me.natecb13.Runnables.SkillEffects;
import me.natecb13.utils.ChatUtils;

public class Evolutions extends JavaPlugin{
	
	EvolutionTree end = new EvolutionTree("End", new ArrayList<EvolutionSkill>(), Arrays.asList("&7- Surviving in the end &8(1⚡&8/sec) ", "&7- Killing an end mob &8(10⚡)", "&7- Killing the Ender Dragon &8(500⚡)"), Material.MAGENTA_STAINED_GLASS_PANE, ChatColor.LIGHT_PURPLE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDY5OTRkNzFiODc1ZjA4N2U2NGRlYTliNGEwYTVjYjlmNGViOWFiMGU4ZDkwNjBkZmRlN2Y2ODAzYmFhMTc3OSJ9fX0=");
	EvolutionTree nether = new EvolutionTree("Nether", new ArrayList<EvolutionSkill>(), Arrays.asList("&7- Surviving in the nether &8(1⚡&8/sec) ", "&7- Killing a nether mobs &8(10⚡)", "&7- Killing the Wither &8(500⚡)"), Material.RED_STAINED_GLASS_PANE, ChatColor.RED, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDUwMDI5MmY0YWZlNTJkMTBmMjk5ZGZiMjYwMzYzMjI4MzA0NTAzMzFlMDAzMDg0YmIyMjAzMzM1MzA2NjRlMSJ9fX0=");
	EvolutionTree ocean = new EvolutionTree("Ocean", new ArrayList<EvolutionSkill>(), Arrays.asList("&7- Surviving while in water &8(1⚡&8/sec) ", "&7- Killing an aquatic mob &8(10⚡)", "&7- Killing Elder Guardians &8(150⚡)"), Material.CYAN_STAINED_GLASS_PANE, ChatColor.DARK_AQUA, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ2OTNlMzcwNTQ2N2QyYjM0ZjE4NmZlOGFiYzhmNGIyMGIyOGU1MmE5NWI1YTUyNTEwN2VlZmE1ODgxY2ExMCJ9fX0=");
	EvolutionTree earth = new EvolutionTree("Earth", new ArrayList<EvolutionSkill>(), Arrays.asList("&7- Surviving while in the overworld &8(1⚡&8/sec) ", "&7- Killing an overworld mob &8(10⚡)"), Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjEyYTAzYTRjMTFiNGQ0NzI0NzJlN2U0NTkzZDJlMTI2YTYyNTllMzNjYzgxZjQ0ZWIwNWNmMDQyZDA3Njk2NyJ9fX0=");
	EvolutionTree cave = new EvolutionTree("Cave", new ArrayList<EvolutionSkill>(), Arrays.asList("&7- Surviving while underground &8(1⚡&8/sec) ", "&7- Killing a mob while underground &8(10⚡)", "&7- Mining an ore block &8(5⚡)"), Material.GRAY_STAINED_GLASS_PANE, ChatColor.WHITE, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZjODQ0N2E4YjZiMGUwYzdlNzYyOWM2ODk4ZWM5Yzc0OWE3YTBhMmI0NTJiOWMzODUyYzc4NDdiYjRkYzUifX19");
	EvolutionTree sky = new EvolutionTree("Sky", new ArrayList<EvolutionSkill>(), Arrays.asList("&7- Flying with an elytra &8(1⚡&8/sec) ", "&7- Killing any flying mob &8(10⚡)"), Material.LIGHT_BLUE_STAINED_GLASS_PANE, ChatColor.AQUA, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY4NmQ5NmFkOGU1OGE4NmE1YTI4MzI2Yzk5ZmRlOWQ0OTgxZTQ2YzA5ZWFlNTFlN2E5ODYxOTBjZDM2YjBmIn19fQ==");

	  private static Evolutions mainInstance;
	
	  public static DataManager data;
	  public static LangManager lang;
	  public static GameSettingsManager settings;
	  
	@Override
	public void onEnable() {
		
		Evolutions.data = new DataManager(this);
		Evolutions.lang = new LangManager(this);
		Evolutions.settings = new GameSettingsManager(this);
		
		 int pluginId = 12452;
	     Metrics metrics = new Metrics(this, pluginId);
	     
		data.saveDefaultConfig();
		
		
		new Config(this);
		new Lang(this);
		
		Evolutions.mainInstance = this;
		
		TreeManager.addTree(nether);
		TreeManager.addTree(earth);
		TreeManager.addTree(end);
		TreeManager.addTree(ocean);
		TreeManager.addTree(cave);
		TreeManager.addTree(sky);

		this.registerTreeSkills();
		
		//Listeners 
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EndDamage(), this);
		Bukkit.getPluginManager().registerEvents(new AntiVoid(), this);
		Bukkit.getPluginManager().registerEvents(new EndEnergy(), this);
		Bukkit.getPluginManager().registerEvents(new NetherEnergy(), this);
		Bukkit.getPluginManager().registerEvents(new OceanEnergy(), this);
		Bukkit.getPluginManager().registerEvents(new GuardianAura(), this);
		Bukkit.getPluginManager().registerEvents(new NetherDamage(), this);
		Bukkit.getPluginManager().registerEvents(new OceanDamage(), this);
		Bukkit.getPluginManager().registerEvents(new ObsidianSkin(), this);
		Bukkit.getPluginManager().registerEvents(new ReverseShulker(), this);
		Bukkit.getPluginManager().registerEvents(new CaveDamage(), this);
		Bukkit.getPluginManager().registerEvents(new GemstoneExtractor(), this);
		Bukkit.getPluginManager().registerEvents(new EarthDamage(), this);
		Bukkit.getPluginManager().registerEvents(new ElytraLauncher(), this);
		Bukkit.getPluginManager().registerEvents(new SkyDamage(), this);
		Bukkit.getPluginManager().registerEvents(new FungalForrager(), this);
		Bukkit.getPluginManager().registerEvents(new GreenThumb(), this);
		Bukkit.getPluginManager().registerEvents(new MiningEnergy(), this);
		Bukkit.getPluginManager().registerEvents(new EarthEnergy(), this);
		Bukkit.getPluginManager().registerEvents(new SkyEnergy(), this);
		Bukkit.getPluginManager().registerEvents(new DoubleDebris(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
		Bukkit.getPluginManager().registerEvents(new Thunderstorm(), this);
	
		
		//Runnables
		PassiveEnergy.runPassiveEnergy();
		InventoryUpdater.runInventoryUpdates();
		SkillEffects.runSkillEffects();
		AutoSave.runAutoSave();
		PlayerMove.checkPlayerAFK();
		Thunderstorm.runClouds();
		
		//Commands
		getCommand("evo").setTabCompleter(new TabComplete());
		
		//Loading data.yml data
		TreeManager.loadData();
		
		//adding existing players to maps
		for(Player player : Bukkit.getOnlinePlayers()) {
			TreeManager.addPlayerToTrees(player);
		}
		
		//config
		
		TreeManager.saveToConfig();
		TreeManager.resetConfig();
		
		TreeManager.resortAllTrees();
		
		//PAPI
		// Small check to make sure that PlaceholderAPI is installed
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
              new EvolutionsPlaceholders(this).register();
        }
	}

	@Override
	public void onDisable() {
		TreeManager.saveData();
	}
	
	public static DataManager getData() {
		return data;
	}
	
	public static GameSettingsManager getSettings() {
		return settings;
	}
	
	public static Evolutions getPlugin() {
		return mainInstance;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(label.equalsIgnoreCase("evolutions") || label.equalsIgnoreCase("evo")) {
				if(args.length > 0) {
					
					switch (args[0].toUpperCase()) {
					
					
					case "OPEN":
						if(args.length < 2) {
							player.sendMessage(Lang.openUsage());
						}
						else {
						EvolutionTree tree = TreeManager.getEvolutionTree(args[1]);
						if(tree != null) {
						player.openInventory(tree.getMenu(player, 1));
							} else {
								player.sendMessage(Lang.environmentNotFound());
							}
						}
						
						break;
						
						
					case "MENU":

						player.openInventory(EvolutionsGUI.getPageInv(player, 1));
						
						
						break;

					case "ADDENERGY":
						if(sender.hasPermission("evolutions.admin")) {
						try {
						if(args.length < 4) {
							player.sendMessage(Lang.addenergyUsage());
						} else {
							
							// Add null checks!
							
							Player target = Bukkit.getPlayer(args[1]);
							EvolutionTree tree = TreeManager.getEvolutionTree(args[2]);
							tree.forceAddEnergy(target, Integer.parseInt(args[3]));
							
							player.sendMessage(Lang.addEnergy().replace("%amount%", args[3]).replace("%player%", target.getName()).replace("%type%", tree.getName()));
						}
						
						}catch(NumberFormatException x) {
							player.sendMessage(Lang.notANumber());
						}
						} else {
							sender.sendMessage(Lang.noPermission());
						}
						break;
					
					case "UNLOCKALL":
						if(sender.hasPermission("evolutions.admin")) {

						for(EvolutionTree tree : TreeManager.evolutionTrees) {
							if(tree.getHighestSkill() == null) continue;
							tree.forceAddEnergy(player, tree.getHighestSkill().getEnergyRequirement());
						}
						
						player.sendMessage(Lang.unlockedAll().replace("%number%", TreeManager.evolutionTrees.size() + ""));
						} else {
							sender.sendMessage(Lang.noPermission());
						}
						
						break;
					case "RESET":
						if(sender.hasPermission("evolutions.admin")) {
						if(args.length >= 2) {
							try {
								
								Player target = Bukkit.getPlayer(args[1]);
						for(EvolutionTree tree : TreeManager.evolutionTrees) {
							tree.setEnergy(player, 0);
						}
						
						player.sendMessage(Lang.resetAll().replace("%number%", TreeManager.evolutionTrees.size() + ""));
							} catch(NullPointerException x) {
								x.printStackTrace();
								sender.sendMessage(Lang.playerNotFound());
							}
						} else {
							player.sendMessage(Lang.resetUsage());
						}
						}
						else {
							sender.sendMessage(Lang.noPermission());
						}
						break;
						
						
					case "RELOAD":
						if(sender.hasPermission("evolutions.admin")) {
							TreeManager.resetConfig();
							lang.reloadConfig();
							TreeManager.resortAllTrees();
							settings.reloadConfig();
							player.sendMessage(Lang.configReloaded());
							
							
						} else {
						sender.sendMessage(Lang.noPermission());
						}
						break;
						
					default:
						player.sendMessage(Lang.notACommand());
						break;
					}
					
					
					
				} else {
					
					player.sendMessage(" ");
					ChatUtils.sendCenteredMessage(player, "&aEvolutions &6v" + this.getDescription().getVersion() + " &aby Natecb13. Made for PluginJam #2.");
					player.sendMessage(" ");
					player.sendMessage(color("&6&lCommands:"));
					player.sendMessage(color("&b/evo menu &7 - Opens the 'Evolutions' GUI"));
					player.sendMessage(color("&b/evo unlockall &7- unlocks all skills for all environments"));
					player.sendMessage(color("&b/evo addenergy"));
					player.sendMessage(color("&b/evo reset &7- resets all of your evolutions"));
					player.sendMessage(color("&b/evo reload &7- reloads the config and lang files"));
					player.sendMessage(color("&b/evo open &7- opens the specified evolution"));
					

					
				}
				
			}
			
			
		}
		 return false;
	}
	
	
	private void registerTreeSkills() {
		
		/*End Skills
		
		1 - Increased Damage Resistance (10%)
		2 - Increased Damge (10%)
		3 - The Anti-Void (levitates you if going into the void)
		4 - Reverse shulker (gives slow falling not levitation)
		5 - King of the End ()
		
		*/
		
		
		end.addEvolutionSkill(new EvolutionSkill(end, "Dragon's Scales", 350, Arrays.asList(" ", "&7Your body begins to adapt to the", "&7end climate and your skin becomes", "&7tougher, reducing damage by &a15%", "&7while in the end."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTZiYTk5ODdmNzM4ZTZkNzVkM2IwMmMzMGQxNDgwYTM2MDU5M2RkYjQ2NGJkMWM4MWFiYjlkNzFkOWU2NTZjMCJ9fX0="));
		
		end.addEvolutionSkill(new EvolutionSkill(end, "Dragon's Breath", 1000, Arrays.asList(" ","&7Your body continues to adapt to", "&7the ends' climate. You now deal", "&a+15% &7damage while in the end."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJlODcwODlmOTMyOWI1NGM5YTU5NjU2MjUzNTQxMDdjN2Y5NmIzMDU0ZjFkZWY4Y2VlYTJiOTBjZTZmOGQifX19"));
		
		end.addEvolutionSkill(new EvolutionSkill(end, "The Anti-Void", 2500, Arrays.asList(" ","&7Harness the energy of the void.", "&7The voids' energy prevents you", "&7from falling to your death."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2MyYzU5ZmNkOTI2MjVlYzRkNTc4MTU5YTVmZDViZDQyNDdlMzgyZDQ5NDcyODRjZjUwZjk5OWM4NDExNmMwIn19fQ=="));
		
		end.addEvolutionSkill(new EvolutionSkill(end, "Reverse Shulker", 5000, Arrays.asList(" ","&7You adapt to the shulkers' attacks","&7by reversing their energy fields.", "&7You no longer levitate when hit by", "&7a shulker, but intead recive the", "&fSlow Falling &7effect."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjU0OWVkMGQxMTRhNTNkYWM3MzA3YmFhZjBhYTQ0NDEyNzE3NjZjMTIwY2Q0YWNlY2JhMGFiNGVkOGU3OGQxIn19fQ=="));
		
	//	end.addEvolutionSkill(new EvolutionSkill(end, "King of the End", 10000, Arrays.asList(" ","&7???"),
	//			"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOTY0N2VjN2M4ZjM1OWQ4ZDA5NTJiZGJmNzJjYmI0YjU3NDNjZjg0NTVkY2I3NjY0ZTJiZjliZGY4YjcxOCJ9fX0="));
		
		
		
		
		/*Nether Skills
		
		1 - Increased Damage Resistance (10%)
		2 - Increased Damge (10%)
		3 - Obsidian Skin (take no fire damage)
		4 - ...
		5 - ...
		
		*/
		
		nether.addEvolutionSkill(new EvolutionSkill(nether, "Blaze Shield", 350, Arrays.asList(" ","&7Your skin becomes tougher as it","&7adapts to the warm climate. All", "&7damage applied to you is reduced", "&7by &a15% while in the nether."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2QyYTViNGIxMDliZDc4OGVkYmEwMTcxZDBhYWI4YTU1MzA1YWMyZjU2MTg0ZGY3MGEzMTljZDQ4OGEzNmMzZSJ9fX0="));
		
		nether.addEvolutionSkill(new EvolutionSkill(nether, "Wither Spirit", 1000, Arrays.asList(" ","&7Your body absorbs lots of wither", "&7essence, increasing your attack", "&7damage by &a15% &7while in the nether."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ2MGQzNTQ3ZGYzYjQ1M2M1MTMxNDc3MWI4MGI5ZmRiNjhjMDM1OWIxNDQ3ZDhhNmYxYmM5NjU1NTc2OTUyMCJ9fX0="));
		
		nether.addEvolutionSkill(new EvolutionSkill(nether, "Obsidian Skin", 2500, Arrays.asList(" ","&7Your skin grows an extra layer,","&7making you immune to fire and","&7lava damage."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTc5ZDBlOTZjMzUxZTJlMmQ5MDQyYTQ2ODJkZWM0MzBjNGU1MjI1NmVkNzdjZjgwYmQwMjY3ZjdjYWJlMzMwMCJ9fX0="));
		
		nether.addEvolutionSkill(new EvolutionSkill(nether, "Flaming Debris", 10000, Arrays.asList(" ","&7You discover a better technique to", "&7mine netherite; by melting it down", "&7at the source. You now recive two", "&7netherite scraps when mining ancient" , "&7debris."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZhMjYxMDU0NWM1MTkzYjE3NzZmYWU2ZjVkNmYxNzU3OWQ2MDAyYWVhMDMyZjlmNTJiNTRiZDNiZmY1OWE1MSJ9fX0="));
		
		
		
		/*Ocean Skills
		
		1 - Increased Damage Resistance (10%)
		2 - Increased Damage (10%)
		3 - Makeshift Gills (Permanent Water breathing when in water)
		4 - Guardian's Aura (recive x% more damage for every nearby guardian within y blocks of you)
		5 - Pocket Conduit (Conduit power when in water)
		6 - Eternal Bond (Permanent Dolphins Grace when swimming)
		
		*/
		
		ocean.addEvolutionSkill(new EvolutionSkill(ocean, "Turtle Shell", 350, Arrays.asList(" ","&7Your body begins to adapt to the water" , "&7by building up a resistance to attacks." , "&7All damage is reduced by &a15% &7while in" , "&7water."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY0Njk4ZmVhNWVjM2YyZmQ3ZGI3YThlM2Y0ZTk4OWExNzE2MDM1YzJiZDM2NjY0MzRiYTFhZjU4MTU3In19fQ=="));
		
		ocean.addEvolutionSkill(new EvolutionSkill(ocean, "Prismarine Spikes", 1000, Arrays.asList(" ","&7Your body adjusts to the harsh" , "&7ocean waters. Your endurance builds," , "&7increasing all damage by &a15% &7while" , "&7in water."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGJlNjJlZGY1MmMwNDgwZTgxOWEwZWFhOGE1YmRjYTM5ZmQ0MmUzZTA4MWJkNmU1ODQ2Zjg5NTdiNGJkMzA0ZCJ9fX0="));
		
		ocean.addEvolutionSkill(new EvolutionSkill(ocean, "Makeshift Gills", 2500, Arrays.asList(" ","&7Your lungs transform into makeshift", "&7gills, providing you with infinite", "&7oxygen while swimming."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWU0MGZlNmE5ZGIyMmYyYjEyZTYwNWU0OTI5OTViZDQ2YWM5MzY3YjI2YjhhYjg1ZTA3MjY2ODAxYmVjZjcxZCJ9fX0="));
		
		ocean.addEvolutionSkill(new EvolutionSkill(ocean, "Guardian's Aura", 5000, Arrays.asList(" ","&7You start to gain power from nearby", "&7guardians. Your damage increases by", "&7&a10% &7for each guardian within &e30", "&7blocks of you."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBiZjM0YTcxZTc3MTViNmJhNTJkNWRkMWJhZTVjYjg1Zjc3M2RjOWIwZDQ1N2I0YmZjNWY5ZGQzY2M3Yzk0In19fQ=="));
		
		
		ocean.addEvolutionSkill(new EvolutionSkill(ocean, "Pocket Conduit", 10000, Arrays.asList(" ","&7Contact with conduits causes your", "&7body to change with its power.", "&7You now gain permanent &bconduit", "&bpower &7when in water."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjEwZjc5YWE1MGU4MmViM2YxYzYxNDYxYTVlMzQ4YzUxZmViMGI1ODBkYzhlYmZjOTkyYWU0MzY5OWY5ZTkzIn19fQ=="));
		
		ocean.addEvolutionSkill(new EvolutionSkill(ocean, "Eternal Bond", 25000, Arrays.asList(" ","&7The dolphins grow close to you","&7as you spend time in the oceans." , "&7You now gain infinite &bDolphins", "&bGrace &7when in water."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQxNWQyYzU0M2UzNGJiODhlZGU5NGQ3OWI5NDI3NjkxZmM5YmU3MmRhYWQ4ODMxYTllZjI5NzE4MDU0NmUxOCJ9fX0="));
	
	/* Earth Skills
	 1 - Increased Damage Resistance (15%)
	 2 - Increased Damage (15%)
	 3 - Fungal Forrager (break mushroom or fungus with your bare hand to recive a random positive potion effect) 
	 4 - Green Thumbs (double farming drops)
	  
	 
	 */
	
		earth.addEvolutionSkill(new EvolutionSkill(earth, "Mob Assasin", 1500, Arrays.asList(" ","&7Your fighting begins to improve as", "&7you battle more mobs. Damage against " , "&7you is decreased by &a15% &7while in" , "&7the overworld."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmYyY2ZhYWQ2ZjhiNjJjOTkyYzNiNTVjZTMxNjlmZmE0MDA1YWFlZTJkYjM1YTYzZjRiMzc5MGU0YTU5NmMzNSJ9fX0="));
		
		earth.addEvolutionSkill(new EvolutionSkill(earth, "Enhanced Endurance", 5000, Arrays.asList(" ","&7Your body grows stronger as" , "&7you remain active. Your damage" , "&7is increased by &a15% &7while in the" , "&7overworld."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjU5ODE5NDViZDlhOTE3OWZiODI5N2EwNjYxYmViMjMxYmNmZGE4M2E0ZGRlZTZiMjA4N2NmZDdiNGNiZDk5OSJ9fX0="));
		
		earth.addEvolutionSkill(new EvolutionSkill(earth, "Fungal Forrager", 10000, Arrays.asList(" ","&7You learn more about the properties of", "&7the plants you harvest. You are now able", "&7to extract random boosts from harvesting", "&7fungi with your hands."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQzMjIzYjZjMjllZjY1MmMzNjM2YWY3NzZkODk0NjZlOWY2OTdmMjU2NWI5Njc0YWE4ZGUwZTljMzZkNyJ9fX0="));
		
		earth.addEvolutionSkill(new EvolutionSkill(earth, "Green Thumbs", 20000, Arrays.asList(" ","&7You start to perfect your skills in", "&7the farm. You begin to make stronger", "&7food, increaseing the strength of all", "&7food by &a50%&7."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjdjMzNjZDBjMTRiYTgzMGRhMTQ5OTA3ZjdhNmFhZTgzNWI2YTM1YWVhMDFlMGNlMDczZmIzYzU5Y2M0NjMyNiJ9fX0="));
		
		
		
	
	/* Cave Skills
	 1 - Increased Damage Resistance (15%)
	 2 - Increased Damage (15%) 
	 3 - Gem Extractor (chance for extra drops from ores)
	 4 - Night Vision
	 5 - Haste II
	 */

		cave.addEvolutionSkill(new EvolutionSkill(cave, "Denser Air", 4500, Arrays.asList(" ", "&7Your lungs adapt to the dense cave air,", "&7improving your fighting when underground.", "&7All damage is reduced by &a15%&7 when in the", "&7caves."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWEzYWJlZjNhYzlmYjA3Y2NiMTAzNWVmZmNkYWIxMjdmZjI3YmFlYmE2ZDllODc2OTJjZTMxOGI1NjY0MWIyIn19fQ=="));
		
		cave.addEvolutionSkill(new EvolutionSkill(cave, "Turbo Dexterity", 9500, Arrays.asList(" ", "&7Your body continues to alter to the", "&7cave climate. Your attack damage is", "&7increased by &a15%&7."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVkNzZkOTBiMzc4MDgzZDE0Nzc1NjgwNTA1ZGRiMWU2YzJjNmRjZjRkZGU3ZjliMWY1ODgwOWJlYzZjNjVjOCJ9fX0="));
		
		cave.addEvolutionSkill(new EvolutionSkill(cave, "Gemstone Extractor", 15000, Arrays.asList(" ", "&7As you continue to mine, you get better", "&7at extracting the valuables from ore.", "&7All ores now have a &a30% &7chance of", "&7dropping extra ore."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY0ZjI1Y2ZmZjc1NGYyODdhOTgzOGQ4ZWZlMDM5OTgwNzNjMjJkZjdhOWQzMDI1YzQyNWUzZWQ3ZmY1MmMyMCJ9fX0="));
		
		cave.addEvolutionSkill(new EvolutionSkill(cave, "Enhanced Eyesight", 30000, Arrays.asList(" ", "&7Your eyes evolve as you spend time", "&7in the darkness. Your eyes change,", "&7allowing you to see in the dark, when", "&7in the caves."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDhkNWZiYTdiYzhiZGRlODk0NzUwMTczZWEzMTc5NDRiOWEzOWI3YzhkNTQyYjYxNDI1MjBiZDUyNjIxIn19fQ=="));
		
		cave.addEvolutionSkill(new EvolutionSkill(cave, "Energized Arms", 50000, Arrays.asList(" ", "&7All of the time underground allows", "&7you to improve your craft. You now", "&7recive the &6Haste II &7effect when in the", "&7caves."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTAwZDc1YThjYzAzZTU5YzQ3NWNhYzc2ODFiYjRlNmQwOGFjNTkyOTBkNWYyMzlkNjA1Njc2MmI0ZTU1ZjkxNSJ9fX0="));
		
		
		// NOT DONE ABOVE!
	
	/* Sky Skills
	 1 - Resist elytra damage maybe
	 2 - Feather feet (No fall damage)
	 3 - Elytra Launchpad (sneak for 10 seconds to launch into the air)
	*/
		sky.addEvolutionSkill(new EvolutionSkill(sky, "Smooth Glider", 300, Arrays.asList(" ", "&7As you continue to fly, your gliding", "&7skills improve, preventing taking", "&7damage when flying into walls."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjgxY2M2NGIwNDViMThhM2E0MTljNTk1YWQ2NThmNmNlMmM1OTk5NDk0NzdhZmU0MmE2YjU4N2FhMzE1YmYifX19"));
		
		sky.addEvolutionSkill(new EvolutionSkill(sky, "Feather Feet", 1000, Arrays.asList(" ", "&7You master the ability to land on the", "&7ground gracefully. You now take no", "&7fall damage."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2ZmN2RkMmJkZjQxOGI0MDMzNmRhMWVjYmQ5MzE1YjU4YjIwNjI3YTk0YWVlNjlhZGFmMDFmNWQ0ZWFjODBkOSJ9fX0="));
		
		sky.addEvolutionSkill(new EvolutionSkill(sky, "Elytra Launcher", 2500, Arrays.asList(" ", "&7You discover a better way to launch", "&7yourself into the air. Hold down &e[Shift] &7", "&7while on the ground to initiate flight", "&7takeoff."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjJmYzIzODY2NTIzY2FhYThhOTUzNDU2NjEyN2E2ZjgzODlhZjNlNzZiOGUzYzMzYzI0NzNjYmE2ODg5YzQifX19"));
		
		sky.addEvolutionSkill(new EvolutionSkill(sky, "Eye of the Storm", 5000, Arrays.asList(" ", "&7When it's raining, you learn to harness", "&7the energy of the storm. Attacking a", "&7mob releases a powerfull bolt of", "&7lightning which damages mobs around", "&7you."),
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRhNjVlNTcwNWM4ZTk4NDYyOWNhY2Y1YWU3OTAyYjNhNzc2NmY5MDgzNjMwNDhlNmQ3ZTIwMDM5N2Q3N2VhYiJ9fX0="));
		
		
	}
	
	public String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

}
