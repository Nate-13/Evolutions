package SkillListeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.natecb13.plugin.Evolutions;
import me.natecb13.plugin.TreeManager;

public class ReverseShulker implements Listener{

	@EventHandler
	public void onShulkerBulletHit(ProjectileHitEvent e) {

		if(e.getEntity() instanceof ShulkerBullet) {
			
			if(e.getHitBlock() != null) return;
			if(e.getHitEntity() != null) {
				if(e.getHitEntity() instanceof Player) {
					Player player = (Player) e.getHitEntity();
					if(TreeManager.hasUnlockedSkill(player, "Reverse Shulker")) {
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 0));
					Bukkit.getScheduler().scheduleSyncDelayedTask(Evolutions.getPlugin(), new Runnable() {
						@Override
						public void run() {
							player.removePotionEffect(PotionEffectType.LEVITATION);
						}
					}, 1L); //20 Tick (1 Second) delay before run() is called
					
					}
				}
				
			}
			
		}
		
	}
	
}
