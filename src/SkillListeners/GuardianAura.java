package SkillListeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.natecb13.DataManager.Settings;
import me.natecb13.plugin.TreeManager;

public class GuardianAura implements Listener {

	@EventHandler
	public void onEntityKill(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
		Player player = (Player) e.getDamager();
		
		if(!(e.getEntity() instanceof LivingEntity)) return;
	
			if(TreeManager.hasUnlockedSkill(player, "Guardian's Aura")) {
				
				int nearbyGuardians = 0;
				for(Entity mob : player.getWorld().getNearbyEntities(player.getLocation(), Settings.guardianAuraRange(), Settings.guardianAuraRange(), Settings.guardianAuraRange())) {
					if(mob.getType() == EntityType.GUARDIAN) {
						nearbyGuardians++;
					}
				}
				
				double newDamage = e.getDamage() * (1 + (Settings.guardianAuraDamage() * nearbyGuardians));
				e.setDamage(newDamage);
			}
			
				
		}
	}
	

	
}
