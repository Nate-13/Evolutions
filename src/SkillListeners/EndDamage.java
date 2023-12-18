package SkillListeners;

import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.natecb13.DataManager.Settings;
import me.natecb13.plugin.TreeManager;

public class EndDamage implements Listener{

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;

		Player player = (Player) e.getDamager();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Dragon's Breath")) {
			if(player.getWorld().getEnvironment() == Environment.THE_END)
			e.setDamage(e.getDamage() * (1 + Settings.dragonBreathDamage()));

		}
		
	}
	
	@EventHandler
	public void onPlayerDamaged(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) return;

		Player player = (Player) e.getEntity();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Dragon's Scales")) {
			if(player.getWorld().getEnvironment() == Environment.THE_END)
			e.setDamage(e.getDamage() * (1 - Settings.dragonScalesResistance()));
		}
		
	}
	
}
