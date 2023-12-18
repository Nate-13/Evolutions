package me.natecb13.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabComplete implements TabCompleter{

	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (args.length == 1){
            List<String> base = new ArrayList<>();
            List<String> filter = new ArrayList<>();

            base.add("open");
            base.add("menu");
            base.add("addenergy");
            base.add("unlockall");
            base.add("reset");
            base.add("reload");
            
            for(String s: base) {
            	if(s.toUpperCase().startsWith(args[0].toUpperCase())|| args[0] == null) {
            		filter.add(s);
            	}
            }
            
            return filter;
            
        }else if (args.length == 2){
        	if(args[0].equalsIgnoreCase("open")) {
            List<String> arguments = new ArrayList<>();
            List<String> filter = new ArrayList<>();
          
            for(EvolutionTree tree : TreeManager.evolutionTrees) {
            	arguments.add(tree.name);
            }
            
            for(String s: arguments) {
            	if(s.toUpperCase().startsWith(args[1].toUpperCase()) || args[1] == null) {
            		filter.add(s);
            	}
            }
            return filter;
        	}
            
        }
		
        else if (args.length == 3){
        	if(args[0].equalsIgnoreCase("addenergy")) {
            List<String> arguments = new ArrayList<>();
            List<String> filter = new ArrayList<>();
          
            for(EvolutionTree tree : TreeManager.evolutionTrees) {
            	arguments.add(tree.name);
            }
            
            for(String s: arguments) {
            	if(s.toUpperCase().startsWith(args[2].toUpperCase()) || args[2] == null) {
            		filter.add(s);
            	}
            }
            return filter;
        	}
            
        }
		
        return null;
	}

	
}
