package SkillListeners;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.natecb13.plugin.TreeManager;

public class DoubleDebris implements Listener{

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(!e.isCancelled()) {
		Player player = e.getPlayer();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Flaming Debris")) {
			if(e.getBlock().getType() == Material.ANCIENT_DEBRIS) {
			if(!player.getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
			
				e.getBlock().getLocation().getWorld().spawnParticle(Particle.FLAME, e.getBlock().getLocation().clone().add(0.5, 0.5, 0.5), 5, 0.4, 0.4, 0.4, 0);
				
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.NETHERITE_SCRAP, 2));
				player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 0.1f, 1.5f);

				
					
				}
				
			}
			
		}
		}
		
	}
	
}
