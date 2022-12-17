package com.adrian.entity;

import com.adrian.inventory.Inventory;

public class Player extends Entity {
	private int earnedCoins = 0;
	private Inventory inventory = new Inventory();

	public Player(String name, int healthPoints, int manaPoints) {
		this.name = name;
		this.MAX_HP = healthPoints;
		this.HP = MAX_HP;
		this.MP = manaPoints;
		this.movementSpeed = 5;
		this.damage = 10;
	}

	public void showStats() {
		System.out.println("Name: " + this.name);
		System.out.println("ID: " + this.ID);
		System.out.println("HP: " + this.HP);
		System.out.println("MP: " + this.MP);
		System.out.println("MS: " + this.movementSpeed);
		System.out.println("ATT : " + this.damage);
	}
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return the earnedCoins
	 */
	public int getEarnedCoins() {
		return earnedCoins;
	}

	/**
	 * @param earnedCoins the earnedCoins to set
	 */
	public void setEarnedCoins(int earnedCoins) {
		this.earnedCoins = earnedCoins;
	}

	/**
	 * @return the inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	
}
