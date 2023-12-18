package SkillListeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.bukkit.Particle;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.natecb13.plugin.TreeManager;

public class AntiVoid implements Listener{

	List<UUID> exitingVoid = new ArrayList<UUID>();
	
	@EventHandler
	public void onFallInVoid(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(TreeManager.hasUnlockedSkill(player, "The Anti-Void")) {
			
			if(player.getWorld().getEnvironment() == Environment.THE_END) {

			if(player.getLocation().getBlockY() < -0) {
				
				player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 40, 39));
				player.getLocation().getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 15, 1, 1 , 1, 0.2);
								
				exitingVoid.add(player.getUniqueId());
				
				}
			}
		}
		
	}
	
	@EventHandler
	public void onFallInVoidDamage(EntityDamageEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		
		if(TreeManager.hasUnlockedSkill(player, "The Anti-Void")) {
			if(player.getWorld().getEnvironment() == Environment.THE_END) {
			if(e.getCause() == DamageCause.VOID) {
				e.setCancelled(true);
				}
			}
			
		}
		if(exitingVoid.contains(player.getUniqueId()) && e.getCause() == DamageCause.FALL) {
			e.setCancelled(true);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(exitingVoid.contains(player.getUniqueId())) {
			if(player.isOnGround()) {
				removeAll(exitingVoid, player.getUniqueId());
			}
		}
		
	}
	
	
	private void removeAll(List<UUID> list, UUID uuid) {
	    for (int i = 0; i < list.size(); i++) {
	        if (Objects.equals(uuid, list.get(i))) {
	            list.remove(i);
	        }
	    }
	}
		
	
}
