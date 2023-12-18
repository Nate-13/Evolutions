package me.natecb13.utils;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemBuilder {

	
	private ItemStack is;

	public ItemBuilder(Material mat) {
		is = new ItemStack(mat);
	}

	public ItemBuilder(ItemStack is) {
		this.is = is;
	}

	public ItemBuilder amount(int amount) {
		is.setAmount(amount);
		return this;

	}

	public ItemBuilder name(String name) {
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		is.setItemMeta(meta);
		return this;
	}

	public ItemBuilder lore(String name) {
		ItemMeta meta = is.getItemMeta();
		List<String> lore = meta.getLore();

		if (lore == null) {
			lore = new ArrayList<>();
		}

		lore.add(ChatColor.translateAlternateColorCodes('&', name));
		meta.setLore(lore);

		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder lore(String... lore) {
		List<String> toSet = new ArrayList<>();
		ItemMeta meta = is.getItemMeta();

		for (String string : lore) {
			toSet.add(ChatColor.translateAlternateColorCodes('&', string));
		}

		meta.setLore(toSet);
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder lore(List<String> lore) {
		List<String> toSet = new ArrayList<>();
		ItemMeta meta = is.getItemMeta();

		for (String string : lore) {
			toSet.add(ChatColor.translateAlternateColorCodes('&', string));
		}

		meta.setLore(toSet);
		is.setItemMeta(meta);

		return this;
	}

	@SuppressWarnings("deprecation")
	public ItemBuilder durability(int durability) {
		is.setDurability((short) durability);
		return this;
	}

	public ItemBuilder enchantment(Enchantment enchantment, int level) {
		is.addUnsafeEnchantment(enchantment, level);
		return this;
	}

	public ItemBuilder enchantment(Enchantment enchantment) {
		is.addUnsafeEnchantment(enchantment, 1);
		return this;
	}

	public ItemBuilder type(Material material) {
		is.setType(material);
		return this;
	}

	public ItemBuilder clearLore() {
		ItemMeta meta = is.getItemMeta();

		meta.setLore(new ArrayList<>());
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder clearEnchantments() {
		for (Enchantment e : is.getEnchantments().keySet()) {
			is.removeEnchantment(e);
		}

		return this;
	}

	public ItemBuilder localisedName(String ln) {
		ItemMeta meta = is.getItemMeta();
		meta.setLocalizedName(ln);
		is.setItemMeta(meta);
		return this;
	}

	public ItemStack build() {
		return is;
	}

	@SuppressWarnings("deprecation")
	public ItemBuilder color(DyeColor color) {
		this.durability(color.getWoolData());

		return this;
	}

	public ItemBuilder dyeColor(Color color) {
		LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
		meta.setColor(color);
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder dyeColorFromRGB(int R, int G, int B) {
		LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
		meta.setColor(Color.fromRGB(R, G, B));
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder addGlow() {
		this.enchantment(Enchantment.WATER_WORKER);
		ItemMeta meta = is.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder setUnbreakable() {

		ItemMeta meta = is.getItemMeta();
		meta.setUnbreakable(true);
		is.setItemMeta(meta);

		return this;
	}

	public ItemBuilder addItemFlag(ItemFlag flag) {
		ItemMeta meta = is.getItemMeta();
		meta.addItemFlags(flag);
		is.setItemMeta(meta);
		return this;
	}

	public ItemBuilder addAttributeModifier(Attribute attribute, String name, Double amount, Operation operation,
			EquipmentSlot slot) {
		ItemMeta meta = is.getItemMeta();
		meta.addAttributeModifier(attribute, new AttributeModifier(UUID.randomUUID(), name, amount, operation, slot));
		is.setItemMeta(meta);
		return this;
	}

	public ItemBuilder makeSkull(String url) {

		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);

		if (url.isEmpty())
			return this;

		SkullMeta headMeta = (SkullMeta) is.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);

		profile.getProperties().put("textures", new Property("textures", url));

		try {
			Field profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		item.setItemMeta(headMeta);
		is = item;
		return this;
	}

	public ItemBuilder setPotionType(PotionType type) {

		ItemMeta meta = is.getItemMeta();
		PotionMeta pmeta = (PotionMeta) meta;
		PotionData pdata = new PotionData(type);
		pmeta.setBasePotionData(pdata);
		is.setItemMeta(meta);
		return this;
	}

	public ItemBuilder hideUnbreakable() {
		ItemMeta meta = is.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		is.setItemMeta(meta);
		return this;
	};
	

	@SuppressWarnings("deprecation")
	public ItemBuilder setSkullOwner(String owner) {
		SkullMeta meta = (SkullMeta)is.getItemMeta();
		meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
		is.setItemMeta(meta);
		return this;
	}
	
}
