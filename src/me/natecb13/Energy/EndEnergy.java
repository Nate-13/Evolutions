package me.natecb13.Energy;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


import me.natecb13.plugin.TreeManager;

public class EndEnergy implements Listener{

List<EntityType> endMobs = Arrays.asList(EntityType.ENDERMAN, EntityType.ENDERMITE, EntityType.SHULKER);

	@EventHandler
	public void onEntityKill(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
		Player player = (Player) e.getDamager();
		
		
		if(!(e.getEntity() instanceof LivingEntity)) return;
			LivingEntity ent = (LivingEntity) e.getEntity();
	
			if(!((ent.getHealth() - e.getFinalDamage()) <= 0)) return;
	
		
			if(endMobs.contains(ent.getType())) {
				TreeManager.getEvolutionTree("End").addEnergy(player, 10);
			}	
			
				if(ent.getType() == EntityType.ENDER_DRAGON) {
					TreeManager.getEvolutionTree("End").addEnergy(player, 500);
			}	
		}
	}
}
	
	
