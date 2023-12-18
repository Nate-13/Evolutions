package SkillListeners;

import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.natecb13.DataManager.Settings;
import me.natecb13.plugin.TreeManager;

public class NetherDamage implements Listener{

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) return;

		Player player = (Player) e.getDamager();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Blaze Shield")) {
			if(player.getWorld().getEnvironment() == Environment.NETHER)
			e.setDamage(e.getDamage() * (1 + Settings.blazeShieldResistance()));

		}
		
	}
	
	@EventHandler
	public void onPlayerDamaged(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof Player)) return;

		Player player = (Player) e.getEntity();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Wither Spirit")) {
			if(player.getWorld().getEnvironment() == Environment.NETHER)
			e.setDamage(e.getDamage() * (1 - Settings.witherShieldDamage()));
		}
		
	}
	
}
