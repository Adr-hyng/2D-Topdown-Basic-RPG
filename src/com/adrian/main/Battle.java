package com.adrian.main;

import com.adrian.entity.Enemy;
import com.adrian.entity.Player;

public class Battle {
	public Battle() {
		// TODO Auto-generated constructor stub
		
	}
	
	// Use GenericEntity for General Purpose.
	public Enemy attackFrom(Player player, Enemy enemy) {
		enemy.setHP(enemy.getHP() - player.getDamage());
		player.setHP(player.getHP() - enemy.getDamage());
		return enemy;
	}
	
	public void displayInformation(Player player, Enemy enemy) {
		System.out.println("Player: " + player.getHP() + "\t | \t" + "Enemy: " + enemy.getHP());
	}
}
