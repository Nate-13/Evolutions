package SkillListeners;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.natecb13.plugin.TreeManager;

public class SkyDamage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;

		Player player = (Player) e.getEntity();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Feather Feet") && e.getCause() == DamageCause.FALL) {
			e.setCancelled(true);
			player.getLocation().getWorld().spawnParticle(Particle.CLOUD, player.getLocation().clone().add(0.5, 0.5, 0.5), 25, 0.5, 0.5, 0.5, 0.01);
		}
		
		if(TreeManager.hasUnlockedSkill(player, "Smooth Glider") && e.getCause() == DamageCause.FLY_INTO_WALL) {
			e.setCancelled(true);
		}
		
	}	
	
}
