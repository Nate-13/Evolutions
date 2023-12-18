package SkillListeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.natecb13.plugin.TreeManager;

public class FungalForrager implements Listener{

	@EventHandler(ignoreCancelled = true)
	public void onBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		List<Material> mushrooms = Arrays.asList(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.CRIMSON_FUNGUS, Material.WARPED_FUNGUS);
		if(TreeManager.hasUnlockedSkill(player, "Fungal Forrager")) {
			if(player.getInventory().getItemInMainHand().getType() == Material.AIR) {
		if(mushrooms.contains(e.getBlock().getType())) {
			applyPotionEffectAndParticles(player, e.getBlock().getLocation());
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
					}	
				}
			}
		
	}
	
	
	private void applyPotionEffectAndParticles(Player player, Location loc) {
		int random = getRandomNumber(1, 6);
		
		switch (random) {
		case 1:
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3200, 1));
			
			DustOptions dustOptions = new DustOptions(Color.fromRGB(214, 11, 11), 1);
			loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(0.5, 0.5, 0.5), 25, 0.2, 0.2, 0.2,dustOptions);
			
			break;
		case 2:
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 3200, 1));
			
			DustOptions dustOptions1 = new DustOptions(Color.fromRGB(224, 224, 224), 1);
			loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(0.5, 0.5, 0.5), 25, 0.2, 0.2, 0.2,dustOptions1);
			break;
		case 3:
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3200, 1));
			
			DustOptions dustOptions2 = new DustOptions(Color.fromRGB(23, 125, 145), 1);
			loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(0.5, 0.5, 0.5), 25, 0.2, 0.2, 0.2, dustOptions2);
			break;
		case 4:
			player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 3200, 0));
			
			DustOptions dustOptions3 = new DustOptions(Color.fromRGB(245, 151, 0), 1);
			loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(0.5, 0.5, 0.5), 25, 0.2, 0.2, 0.2, dustOptions3);
			break;
			
		case 5:
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3200, 1));
			
			DustOptions dustOptions4 = new DustOptions(Color.fromRGB(119, 245, 91), 1);
			loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(0.5, 0.5, 0.5), 25, 0.2, 0.2, 0.2, dustOptions4);
			break;

		default:
			break;
		}
		
		player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

	}
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
}
