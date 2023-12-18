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

public class OceanEnergy implements Listener{

List<EntityType> oceanMobs = Arrays.asList(EntityType.GUARDIAN, EntityType.DROWNED, EntityType.PUFFERFISH, EntityType.COD , EntityType.SALMON, EntityType.DOLPHIN, EntityType.TROPICAL_FISH);

	
	@EventHandler
	public void onEntityKill(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
		Player player = (Player) e.getDamager();
		
		
		if(!(e.getEntity() instanceof LivingEntity)) return;
			LivingEntity ent = (LivingEntity) e.getEntity();
	
			if(!((ent.getHealth() - e.getFinalDamage()) <= 0)) return;

			
			if(oceanMobs.contains(ent.getType())) {
				TreeManager.getEvolutionTree("Ocean").addEnergy(player, 10);
			}	
			if(ent.getType() == EntityType.ELDER_GUARDIAN) {
				TreeManager.getEvolutionTree("Ocean").addEnergy(player, 150);
			}
			
				
		}
	}
	
}
