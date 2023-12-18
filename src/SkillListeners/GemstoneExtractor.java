package SkillListeners;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.natecb13.DataManager.Settings;
import me.natecb13.plugin.TreeManager;

public class GemstoneExtractor implements Listener{

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(!e.isCancelled()) {
		Player player = e.getPlayer();
		
		
		if(TreeManager.hasUnlockedSkill(player, "Gemstone Extractor")) {
			if(e.getBlock().getType().name().contains("_ORE")) {
			if(!player.getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
			int random = (int) (Math.random() * 100 + 1);
			
			if(random <= Settings.gemstoneExtractorChance()) {
				DustOptions dustOptions = new DustOptions(Color.fromRGB(136, 247, 240), 1);
				e.getBlock().getLocation().getWorld().spawnParticle(Particle.REDSTONE, e.getBlock().getLocation().clone().add(0.5, 0.5, 0.5), 25, 0.2, 0.2, 0.2,
						dustOptions);
				
				for(ItemStack drop : e.getBlock().getDrops()) {
					e.getBlock().getWorld().dropItem(e.getBlock().getLocation().add(0,0.5,0), drop);
				}		
				player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

				
					}
				}
				
				}
			
			}
		}		
	}


	
	
}
