package me.natecb13.plugin;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class EvolutionsPlaceholders extends PlaceholderExpansion {

    private final Evolutions plugin;
    
    public EvolutionsPlaceholders(Evolutions plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public String getAuthor() {
        return "Natecb13";
    }
    
    @Override
    public String getIdentifier() {
        return "evo";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }
    
    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }
    
    @Override
    public String onRequest(OfflinePlayer player, String params) {
    	
    	for(EvolutionTree tree : TreeManager.evolutionTrees) {
    		if(params.equalsIgnoreCase(tree.getName() + "_energy")){
            return String.valueOf(tree.getEnergy((Player) player));
        }
    		if(params.equalsIgnoreCase(tree.getName() + "_next")) {
    			return tree.getNextUnlockedSkill((Player)player).getDisplayName();
    		}
    		
    		
    }
        
        
     
        
        return null; // Placeholder is unknown by the Expansion
    }
	
}
