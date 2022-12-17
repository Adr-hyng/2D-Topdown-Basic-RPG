package com.adrian.inventory;

import java.util.ArrayList;
import java.util.List;

import com.adrian.item.Item;
import com.adrian.item.ItemType;


public class Inventory {
	public final int backpackLimit = 5;
	public final int stackLimit = 16;
	
	// private
	private List<Item> backpack = new ArrayList<Item>(backpackLimit);
	
	public int size = backpack.size();
	
	public Inventory () {
		
	}
	
	@Override
    public String toString() {
        return ("Inventory: " + this.backpack.toString());
    }
	
	/**
	 * Removes empty slot into air or null.
	 */
	public void update() {
		for(int slot = 0; slot < this.size; slot++) {
			if(this.backpack.get(slot).count <= 0) {
				this.setItem(new Item(ItemType.Potion.NULL, 0), slot);
			}
		}
	}
	
	/*
	 * Create Items in your inventory.
	 */
	public void fillItems() {
		this.addItem(new Item(ItemType.Potion.oldPotion, 16));
		this.addItem(new Item(ItemType.Potion.oldPotion, 16));
		this.addItem(new Item(ItemType.Potion.oldPotion, 16));
		this.addItem(new Item(ItemType.Potion.oldPotion, 16));
		this.addItem(new Item(ItemType.Potion.NULL, 0));
	}
	
	/*
	 * Show Information in Backpack
	 */
	public void showInfo() {
		int i = 0;
		for(Item _item: this.backpack) {
				System.out.println((i+1) + "." + _item.type.name + ", " + _item.count);
				i++;
		}
	}
	
	/*
	 * Debug Show for Debugging
	 */
	public void debugShow() {
		for(Item item: this.backpack) {
			item.getItemInfo();
		}
	}
	
	/*
	 * Add Item to the Inventory
	 * @param Item
	 */
	public int checkAvailable() {
		int i = 0;
		for(i = 0; i < this.size; i++) {
			if(this.getItem(i).type == ItemType.Potion.NULL) {
				return i;
			}
		}
		return i;
	}
	
	public void addItem(Item item) {
		if(this.size >= this.backpackLimit) return;
		if(item.type.name != "Air") this.size++;
		this.backpack.add(item);
		
	}
	
	/*
	 * Set Item to the inventory via the slot of the inventory.
	 */
	public void setItem(Item item, int slot) {
		if(slot > this.backpackLimit) return;
		if(item.type.name != "Air" && item.count == 1) this.size++;
		this.backpack.get(slot).type = item.type;
		this.backpack.set(slot, item);
	}
	
	/**
	 * Removes item quantity by 1.
	 * @param slot - slot or index to remove.
	 */
	public void removeItem(int slot) {
		/**
		 * @return the inventory
		 */
		Item item = this.getItem(slot);
		item.count -= 1;
		if(item.count <= 0) {
			System.out.println("Cannot remove furthermore.");
			return;
		}
		this.size--;
	}
	
	/*
	 * Fetch the item in your inventory slot via slot or index.
	 */
	public Item getItem(int slot) {
		if(slot >= this.backpackLimit) return new Item(ItemType.Potion.NULL, 0);
		return this.backpack.get(slot);
	}
}
