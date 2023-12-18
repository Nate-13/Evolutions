package me.natecb13.DataManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.natecb13.plugin.Evolutions;

public class LangManager {

	private Evolutions plugin;
	private FileConfiguration dataConfig = null;
	private File configFile = null;
	
	
	public LangManager(Evolutions plugin) {
		this.plugin = plugin;
		saveDefaultConfig();
	}
	
	public void reloadConfig() {
		if (this.configFile == null)
			this.configFile = new File(this.plugin.getDataFolder(), "lang.yml");
		
		this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
		
		InputStream defaultStream = plugin.getResource("lang.yml");
		if(defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.dataConfig.setDefaults(defaultConfig);
		}

	}
	
	public FileConfiguration getConfig() {
		if(this.dataConfig == null)
			this.reloadConfig();

		return this.dataConfig;
	}
	
	
	public void saveConfig() {
		if(this.dataConfig == null || this.configFile == null) return;
		
		try {
			this.getConfig().save(this.configFile);
		} catch (IOException e) {
			this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile , e);
		}
	}
	
	
	public void saveDefaultConfig() {
		if(this.configFile == null)
			this.configFile = new File(this.plugin.getDataFolder(), "lang.yml");
		if(!this.configFile.exists())
			this.plugin.saveResource("lang.yml", false);
		
	}
	
}
