package me.natecb13.plugin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.base.Strings;

import me.natecb13.DataManager.Lang;
import me.natecb13.DataManager.Settings;
import me.natecb13.Listeners.PlayerMove;
import me.natecb13.utils.ChatUtils;
import me.natecb13.utils.ItemBuilder;
import me.natecb13.utils.PageUtil;

public class EvolutionTree {

	String name;
	List<EvolutionSkill> skills;
	Material color;
	ChatColor chatColor;
	Map<UUID, Integer> energy = new HashMap<UUID, Integer>();
	String skullTexture;
	List<String> info;
	String displayName;
	
	static List<ItemStack> allItems = new ArrayList<>();
	
	public EvolutionTree(String name, List<EvolutionSkill> skills, List<String> info, Material color, ChatColor chatColor, String skullTexture) {
		this.name = name;
		this.color = color;
		this.skills = skills;
		this.chatColor = chatColor;
		this.skullTexture = skullTexture;
		this.info = info;
		this.displayName = name;
		this.reorderSkills();
	}
	
	public Inventory getMenu(Player player, int page) {
		
				allItems.clear();	
				
				for(EvolutionSkill skill : this.skills) {
					ItemStack item = new ItemBuilder(skill.getIcon(player).clone()).lore(" ").lore(this.getLockedString(player, skill)).build();
					if(skill.getIsEnabled() == true)
					allItems.add(item);
				}
							
				Inventory inv = Bukkit.createInventory(null, 36, Lang.guiNameEvolutions().replace("%name%", chatColor + displayName));
				ItemStack playerInfoBook = new ItemBuilder(Material.BOOK).name(Lang.gainEnergyBy().replace("%energy%", this.displayName + chatColor + " " + Lang.energyIcon())).lore(info).lore(" ").lore(Lang.yourEnergy().replace("%name%", displayName).replace("%amount%", ""+this.getEnergy(player))).build();
				
				inv.setItem(0, playerInfoBook);
				inv.setItem(10, new ItemBuilder(color).name(" ").build());
				inv.setItem(12, new ItemBuilder(color).name(" ").build());
				inv.setItem(14, new ItemBuilder(color).name(" ").build());
				inv.setItem(16, new ItemBuilder(color).name(" ").build());
				
				EvolutionSkill sk = this.getNextUnlockedSkill(player);
				
				
				ItemStack grey;
				
				if(sk == null) {
					grey = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).name(Lang.nextEvolution() + Lang.maxed())
							.lore("&7------------------------------", ("&a" + this.getEnergy(player) + "/" + Lang.maxed()) + chatColor + " " + Lang.energyIcon() +" &a■■■■■■■■■■■■■■■" ).build();
				} else {
					String bar = this.getProgressBar(this.getEnergy(player), sk.getEnergyRequirement(), 15, '■', ChatColor.GREEN, ChatColor.GRAY);

					grey = new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).name(Lang.nextEvolution() + chatColor + sk.getDisplayName())
					.lore("&7------------------------------", ("&a" + this.getEnergy(player) + "/" + sk.getEnergyRequirement()) + chatColor + " " + Lang.energyIcon() +" " + bar ).build();
				}
				
				
				
				inv.setItem(19, grey);
				inv.setItem(20, grey);
				inv.setItem(21, grey);
				inv.setItem(22, grey);
				inv.setItem(23, grey);
				inv.setItem(24, grey);
				inv.setItem(25, grey);

				
				inv.setItem(page, playerInfoBook);
				
				inv.setItem(1, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(" ").localisedName(name).build());
				
				List<Integer> blank = Arrays.asList(2,3,4,5,6,7,8,18,26,27,28,29,30,32,33,34,35);
				for(Integer i : blank) {
					inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name(" ").build());
				}

			
			
			if(PageUtil.isPageValid(allItems, page -1, 3)) {
				inv.setItem(9, new ItemBuilder(Material.ARROW).name(Lang.previousPage()).localisedName(page + "").build());
				
			}else {
			    inv.setItem(9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name("&7").localisedName(page + "").build());
			}
				

		    if(PageUtil.isPageValid(allItems, page +1, 3)) {
		    	inv.setItem(17, new ItemBuilder(Material.ARROW).name(Lang.nextPage()).build());
			}
			else {
				inv.setItem(17, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).name("&7").build());
			}

			
			for(ItemStack item: PageUtil.getPageItems(allItems, page, 3)) {
				inv.setItem(inv.firstEmpty(), item);
			}
			
			
			
			inv.setItem(31, new ItemBuilder(Material.BARRIER).name(Lang.closeMenu()).build());
			
			
			return inv;
			}
			
	public void addPlayer(Player player) {
		if(energy.containsKey(player.getUniqueId())) return;
		energy.put(player.getUniqueId(), 0);
	}
	
	public int getEnergy(Player player) {
		if(energy.containsKey(player.getUniqueId())) {
			return energy.get(player.getUniqueId());
		}
		return 0;
	}
	
	public void addEnergy(Player player, int amount) {
		if(Settings.getBlacklistedWorlds().contains(player.getWorld())) return;
		if(PlayerMove.hasPlayerMoved(player)) {
		if(energy.containsKey(player.getUniqueId())) {
		if(player.getGameMode() == GameMode.SURVIVAL) {
		energy.put(player.getUniqueId(), energy.get(player.getUniqueId()) + amount);
		checkNewUnlocks(player, amount);
				}
			}
		}
	}
	
	public void forceAddEnergy(Player player, int amount) {
		if(energy.containsKey(player.getUniqueId())) {
			energy.put(player.getUniqueId(), energy.get(player.getUniqueId()) + amount);
			checkNewUnlocks(player, amount);			
		}
	}
	
	public Map<UUID, Integer> getEnergyMap(){
		return this.energy;
	}
	
	private void checkNewUnlocks(Player player, int amount) {
		boolean playSound = true;
		for(EvolutionSkill skill : skills) {
			if(skill.getIsEnabled()) {
			if(getEnergy(player) - amount < skill.getEnergyRequirement() && getEnergy(player) >= skill.getEnergyRequirement()) {
				ChatUtils.sendCenteredMessage(player, this.chatColor + "&m-----------------------------------------------------");
				ChatUtils.sendCenteredMessage(player, Lang.newEvolutionSkill().replace("%name%", chatColor + skill.getDisplayName()));
				
				
				for(String s : skill.getDescription()) {
					ChatUtils.sendCenteredMessage(player, s);
				}
				ChatUtils.sendCenteredMessage(player, this.chatColor + "&m-----------------------------------------------------");
				
				
				if(playSound) {
				player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.1f, 1f);
				playSound = false;
					}
				}
			}
		}
		
	}

	public void removeEnergy(Player player, int amount) {
		if(energy.containsKey(player.getUniqueId())) {
			energy.put(player.getUniqueId(), energy.get(player.getUniqueId()) - amount);
		}
	}
	
	public void setEnergy(Player player, int amount) {
		if(player == null) return;
		energy.put(player.getUniqueId(), amount);
	}
	
	public void addEvolutionSkill(EvolutionSkill skill) {
		this.skills.add(skill);
	}
			
	
	public EvolutionSkill getSkill(String name) {
		for(EvolutionSkill skill : skills) {
			if(skill.getName().equals(name)) return skill;
		}
		return null;
	}
	
	
	public EvolutionSkill getNextUnlockedSkill(Player player) {
		for(EvolutionSkill skill : this.skills) {
			if(skill.getIsEnabled()) {
			if(skill.getEnergyRequirement() > this.getEnergy(player)) return skill;
			}
		}
		
		return null;
	}
			
	public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
            ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + completedColor + symbol, progressBars)
                + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars);
    }
		
	public String getLockedString(Player player, EvolutionSkill skill) {
		if(this.getEnergy(player) >= skill.getEnergyRequirement()) return Lang.unlocked();
		return Lang.locked();
	}

	public ChatColor getChatColor() {
		return chatColor;
	}
				
				
	public List<EvolutionSkill> getSkills(){
		return this.skills;
	}
	
	public List<String> getInfo(){
		return this.info;
	}
	
	public String getName() {
		return this.name;
	}

	public String getDisplayName() {
		return this.displayName;
	}
	
	public Material getColor() {
		return this.color;
	}
	
	public String getSkullTexture() {
		return this.skullTexture;
	}
	
	public String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public EvolutionSkill getHighestSkill() {
		if(skills.size() == 0) return null;
		EvolutionSkill highestSkill = skills.get(0);
		for(EvolutionSkill skill : this.skills) {
			if(skill.getIsEnabled()) {
			if(skill.getEnergyRequirement() > highestSkill.getEnergyRequirement()) {
				highestSkill = skill;
				}
			}
		}
		return highestSkill;
	}
	
	public String getPercentUnlocked(Player player) {
		if(this.getHighestSkill() == null) return "100";
		float percent = (float) (this.getEnergy(player) * 100 / this.getHighestSkill().getEnergyRequirement());
		
		if(percent >= 100) return "100";
		DecimalFormat df = new DecimalFormat("#.##");
		
		return df.format(percent);
	}

	public void setChatColor(ChatColor color) {
		this.chatColor = color;
	}
	
	public void setColor(Material color) {
		this.color = color;
	}
	
	public void setSkullTexture(String texture) {
		this.skullTexture = texture;
	}
	
	public void setInfo(List<String> info) {
		this.info = info;
	}
	
	public void setDisplayName(String name) {
		this.displayName = name;
	}
	
	public void reorderSkills() {
		List<EvolutionSkill> ordered = new ArrayList<EvolutionSkill>();
		while(skills.size() > 0) {
			ordered.add(getLowestSkill(skills));
			skills.remove(getLowestSkill(skills));
		}
		skills = ordered;
		
	}
	
	public EvolutionSkill getLowestSkill(List<EvolutionSkill> skillList) {
		if(skills.size() == 0) return null;
		EvolutionSkill lowestSkill = skillList.get(0);
		for(EvolutionSkill skill : skillList) {
			if(skill.getEnergyRequirement() < lowestSkill.getEnergyRequirement()) {
				lowestSkill = skill;
			}
		}
		return lowestSkill;
	}
	
	public void removeSkill(String skillName) {
		EvolutionSkill skill = TreeManager.getSkill(skillName);
		if(skills.contains(skill)) {
			skills.remove(skill);
			this.reorderSkills();
		}
	}
	
}
