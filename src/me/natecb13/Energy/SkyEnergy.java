package me.natecb13.Energy;

import java.util.Arrays;
import java.util.List;

import org.bukkit.World.Environment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.natecb13.plugin.TreeManager;

public class SkyEnergy implements Listener{
	
	List<EntityType> earthMobs = Arrays.asList(EntityType.CHICKEN, EntityType.PHANTOM, EntityType.BAT, EntityType.PARROT, EntityType.ENDER_DRAGON, EntityType.VEX, EntityType.BLAZE, EntityType.BEE, EntityType.GHAST);

	@EventHandler
	public void onEntityKill(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
		Player player = (Player) e.getDamager();
		
		
		if(!(e.getEntity() instanceof LivingEntity)) return;
			LivingEntity ent = (LivingEntity) e.getEntity();
	
			if(!((ent.getHealth() - e.getFinalDamage()) <= 0)) return;

			
			if(player.getWorld().getEnvironment() == Environment.NORMAL && player.getLocation().getY() < 40) {
			
			if(earthMobs.contains(ent.getType())) {
				TreeManager.getEvolutionTree("Sky").addEnergy(player, 10);
			}	
			
			}
		}
	}
}
