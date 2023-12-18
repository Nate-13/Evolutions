package SkillListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.natecb13.DataManager.Settings;
import me.natecb13.plugin.TreeManager;

public class OceanDamage implements Listener{

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;

		Player player = (Player) e.getDamager();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Prismarine Spikes")) {
			if(player.isInWater())
			e.setDamage(e.getDamage() * (1 + Settings.prismarineSpikesDamage()));

		}
		
	}
	
	@EventHandler
	public void onPlayerDamaged(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) return;

		Player player = (Player) e.getEntity();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Turtle Shell")) {
			if(player.isInWater())
			e.setDamage(e.getDamage() * (1 - Settings.turtleShellResistance()));
		}
		
	}
	
}
