package SkillListeners;

import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.natecb13.DataManager.Settings;
import me.natecb13.plugin.TreeManager;

public class CaveDamage implements Listener{

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;

		Player player = (Player) e.getDamager();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Turbo Dexterity")) {
			if(player.getWorld().getEnvironment() == Environment.NORMAL && player.getLocation().getY() < 40)
			e.setDamage(e.getDamage() * (1 + Settings.turboDexterityDamage()));

		}
		
	}
	
	@EventHandler
	public void onPlayerDamaged(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) return;

		Player player = (Player) e.getEntity();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Denser Air")) {
			if(player.getWorld().getEnvironment() == Environment.NORMAL && player.getLocation().getY() < 40)
			e.setDamage(e.getDamage() * (1 - Settings.denserAirResistance()));
		}
		
	}
	
}
