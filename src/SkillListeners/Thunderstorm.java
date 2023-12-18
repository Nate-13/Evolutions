package SkillListeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import me.natecb13.plugin.Evolutions;
import me.natecb13.plugin.TreeManager;

public class Thunderstorm implements Listener{

	@EventHandler(ignoreCancelled = true)
	public void onHit(EntityDamageByEntityEvent e) {
		
		if(!(e.getDamager() instanceof Player)) return;
		Player player = (Player) e.getDamager();
			if(!TreeManager.hasUnlockedSkill(player, "Eye of the Storm")) return;
		Entity ent = e.getEntity();
		
		if(player.getWorld().hasStorm()) {
		
		Location loc = player.getLocation().add(0,2.8,0);
		
		List<Monster> nearby = new ArrayList<Monster>();
		
		for(Entity ent2 : player.getNearbyEntities(10,10,10)) {
			if(ent2 instanceof Monster) nearby.add((Monster) ent2);
		}
		
		if(!(nearby.size() > 0)) return;
				
		Monster previous = nearby.get(0);
		
		for(Monster entity : nearby) {
			
			if(!(previous == entity)) {
				
				drawLine(entity.getLocation().add(0,1,0), previous.getLocation().add(0,1,0), 0.5);
				previous = (Monster) entity;
				
			} else {
				
				drawLine(loc, entity.getLocation().add(0,1,0), 0.5);
			
			}
			
				entity.damage(e.getFinalDamage() * 0.3);
			}
		
		}
		
	}
	
	public static void runClouds() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Evolutions.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()) {
					if(!TreeManager.hasUnlockedSkill(player, "Eye of the Storm")) continue;
					if(!player.getWorld().hasStorm()) continue;
					if(!player.isOnGround()) continue;
					
					Location loc = player.getLocation().add(0,3,0);
					
					loc.getWorld().spawnParticle(Particle.CLOUD,loc, 10, 0.3, 0.2, 0.3, 0);

					
				}
				
			}
		}, 0L, 5L); 
		
	}
	
	public void drawLine(Location point1, Location point2, double space) {
	    World world = point1.getWorld();
	    double distance = point1.distance(point2);
	    Vector p1 = point1.toVector();
	    Vector p2 = point2.toVector();
	    Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
	    double length = 0;
	    for (; length < distance; p1.add(vector)) {
	    	
	    	DustOptions dustOptions = new DustOptions(Color.fromRGB(255, 238, 89), 1);
	        world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 1, dustOptions); 
	        
	        length += space;
	    }
	}
	
}
