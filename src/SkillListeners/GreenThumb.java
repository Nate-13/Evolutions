package SkillListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import me.natecb13.DataManager.Settings;
import me.natecb13.plugin.TreeManager;

public class GreenThumb implements Listener{

	@EventHandler
	public void onConsume(FoodLevelChangeEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if(e.getItem() != null) {
				if(TreeManager.hasUnlockedSkill(player, "Green Thumbs")) {
				e.setFoodLevel((int) (e.getFoodLevel() + (e.getFoodLevel() - player.getFoodLevel()) * Settings.greenThumbsStregth()));
				}
			}
			
			
		}
		
	}
	
}
