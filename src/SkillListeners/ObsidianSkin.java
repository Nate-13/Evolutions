package SkillListeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.natecb13.plugin.TreeManager;

public class ObsidianSkin implements Listener{

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			
			List<DamageCause> causes = Arrays.asList(DamageCause.LAVA, DamageCause.FIRE, DamageCause.FIRE_TICK, DamageCause.HOT_FLOOR);
			if(TreeManager.hasUnlockedSkill(player, "Obsidian Skin")) {
			if(causes.contains(e.getCause())) {
				e.setCancelled(true);
			}
			
			}
			
		}
		
		
	}
	
}
