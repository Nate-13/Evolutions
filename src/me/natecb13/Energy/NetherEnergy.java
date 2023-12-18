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

public class NetherEnergy implements Listener{

	List<EntityType> netherMobs = Arrays.asList(EntityType.PIGLIN, EntityType.ZOMBIFIED_PIGLIN, EntityType.MAGMA_CUBE, EntityType.BLAZE, EntityType.WITHER_SKELETON);

	
	@EventHandler
	public void onEntityKill(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
		Player player = (Player) e.getDamager();
		
		
		if(!(e.getEntity() instanceof LivingEntity)) return;
			LivingEntity ent = (LivingEntity) e.getEntity();
	
			if(!((ent.getHealth() - e.getFinalDamage()) <= 0)) return;

			
			if(netherMobs.contains(ent.getType())) {
				TreeManager.getEvolutionTree("Nether").addEnergy(player, 10);
			}	
			if(ent.getType() == EntityType.WITHER) {
				TreeManager.getEvolutionTree("Nether").addEnergy(player, 500);
			}
			
				
		}
	}
	
}
