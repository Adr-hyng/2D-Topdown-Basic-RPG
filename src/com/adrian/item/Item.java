package com.adrian.item;

import java.util.UUID;

// IMPROVEMENT: Better Use of Generic Class to accept any Generic Types or Object in class.

public class Item{
	public ItemType.Potion type;
	private String ID = UUID.randomUUID().toString();
	public int count = 0;
	
	/*
	 * Constructor:
	 * - Specify an itemType and number of Items to be created.
	 */
	public Item(ItemType.Potion item, int quantity) {
		this.count = quantity;
		this.type = item;
	}
	
	@Override
    public String toString() {
        return ("Item(" + this.type + ", " + this.count + ")");
    }
	
//	public Item(ItemType.Miscellaneous item, int quantity) {
//		item.quantity = quantity;
//		this.item = item;
//	}
	
	
	/*
	 * Get item's Information.
	 */
	public void getItemInfo() {
		System.out.println("Name: " + this.type.name);
		System.out.println("ID: " + this.ID);
		System.out.println("Description: " + this.type.description);
		System.out.println("Count: " + this.count);
		System.out.println();
	}
}
