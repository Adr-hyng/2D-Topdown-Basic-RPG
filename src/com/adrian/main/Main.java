package com.adrian.main;

import java.util.Random;
import java.util.Scanner;

import com.adrian.entity.Enemy;
import com.adrian.entity.Player;
import com.adrian.inventory.Inventory;
import com.adrian.item.Item;
import com.adrian.item.ItemType;
import com.adrian.random.Weighted;



class Menu {
	private Scanner sc;
	private Battle battle = new Battle();
	private Random rand = new Random();
	
	private Enemy enemy;
	private Player user = new Player(
			"Adrian",
			100,
			100
			);
	private Inventory inventory = user.getInventory();
	
	public Menu(Scanner sc) {
		this.sc = sc;
		this.inventory.fillItems();
		this.user.setEarnedCoins(1000);
	}
	
	public boolean startWandering() throws InterruptedException {
		Weighted<Boolean> encounterChance = new Weighted<>();
		encounterChance.addEntry(false, 60);
		encounterChance.addEntry(true, 40);
		boolean isFound = false;
		System.out.println("\n\n\n\n");
		while(true) {
			Thread.sleep(1000);
			System.out.println("Adventuring..");
			if(encounterChance.getRandom()) {
				System.out.println("\n\n\n\nFound opponent");
				isFound = true;
				break;
			}
		}
		
		if(isFound) {
			System.out.println("Battle on!");
			enemy = new Enemy("Troll", 100, 0);
			boolean onBattle = true;
			int battleSelection = 0;
			while(onBattle) {
				System.out.println("PLAYER MAIN SELECTION:");
				System.out.println("1. Attack ");
				System.out.println("2. Item:");
				System.out.println("3. Run:\n\n\n");
				System.out.println("Select Choice: ");
				
				if(enemy.getHP() <= enemy.getMinHP()) {
					onBattle = false;
					int coinsWon = rand.nextInt(60-5) + 5;
					System.out.println("You've Won! with +" + coinsWon + " coins");
					user.setEarnedCoins(user.getEarnedCoins() + coinsWon);
					System.out.println("Total Coins: " + user.getEarnedCoins());
					break;
				}
				if(user.getHP() <= user.getMinHP()) {
					onBattle = false;
					System.out.println("You Lost!");
					return false;
				}
				battleSelection = this.sc.nextInt();
				
				switch(battleSelection) {
				case 1:
					
					enemy = battle.attackFrom(user, enemy);
					battle.displayInformation(user, enemy);
					break;
					
				case 2:
					System.out.println("\n\n\n\nBACKPACK");
					while(true) {
						System.out.println("View Inventory: ");
						inventory.showInfo();
						System.out.println("\n\n\n");
						System.out.println("Select an item to use: ");
						int bagSelection = this.sc.nextInt();
						bagSelection--;
						if(bagSelection > inventory.size) {
							System.out.println("There's no item further.");
						}
						Item selectedItem = (inventory.getItem(bagSelection));
						System.out.println("Used a " + selectedItem.type.name);
						if(selectedItem.count > 0) {
							user.setHP(user.getHP() + selectedItem.type.healAmount);
							inventory.removeItem(bagSelection);
						}
						inventory.update();
						break;
					}
					break;
					
				case 3:
					onBattle = false;
					System.out.println("Fleed away the battle.");
					break;
					
				default:
					break;
				}
			}
		}
		return true;
	}
	
	public void openShop() {
		boolean onShop = true;
		int shopSelection;
		this.inventory = this.user.getInventory();
		while(onShop) {
			System.out.println("Coins: " + this.user.getEarnedCoins());
			System.out.println("1. BUY");
			System.out.println("2. EXIT");
			System.out.println("\n\n\n\n");
			System.out.println("Shop Selection: ");
			
			shopSelection = this.sc.nextInt();
			
			switch(shopSelection) {
			case 1:
				System.out.println("Size: " + this.inventory.size);
				System.out.println("1. " + ItemType.Potion.oldPotion.name + " | 10 coins");
				System.out.println("2. " + ItemType.Potion.fullRestore.name + " | 30 coins");
				System.out.println("3. Go back");
				System.out.println("\n\n\n\n");
				System.out.println("What would you like to buy?: ");
				
				int buySelection = this.sc.nextInt();
				Item boughtItem;
				int price = 0;
				
				switch(buySelection) {
				case 1:
					if(this.inventory.size >= this.inventory.backpackLimit && this.inventory.getItem(this.inventory.size - 1).count >= this.inventory.stackLimit) {
						System.out.println("You don't have enough backpack space.");
						return;
					}
					price = 10;
					if(this.user.getEarnedCoins() < price) {
						System.out.println("You don't have enough gold!");
						return;
					}
					boughtItem = new Item(ItemType.Potion.oldPotion, 1);
					this.user.setEarnedCoins( this.user.getEarnedCoins() - price );
					
					for(int currentSlot = 0; currentSlot < this.inventory.backpackLimit; currentSlot++) {
						Item currentItem = this.inventory.getItem(currentSlot);
						System.out.println("Size: " + this.inventory.size);
						
						if(boughtItem.type == currentItem.type && currentItem.count < this.inventory.stackLimit) {
							currentItem.count += 1;
							this.inventory.setItem(new Item(boughtItem.type, currentItem.count), currentSlot);
							break;
						}
						else if(currentItem.count == 0) {
							this.inventory.setItem(boughtItem, currentSlot);
							break;
						}
					}
					System.out.println("You just bought 1 " + boughtItem.type.name
							+ ".\nCheck in your inventory.\n\n\n");
					break;
				case 2:
					if(this.inventory.size >= this.inventory.backpackLimit && this.inventory.getItem(this.inventory.size - 1).count >= this.inventory.stackLimit) {
						System.out.println("You don't have enough backpack space.");
						return;
					}
					price = 30;
					if(this.user.getEarnedCoins() < price) {
						System.out.println("You don't have enough gold!");
						return;
					}
					boughtItem = new Item(ItemType.Potion.fullRestore, 1);
					this.user.setEarnedCoins( this.user.getEarnedCoins() - price );
					
					for(int currentSlot = 0; currentSlot < this.inventory.backpackLimit; currentSlot++) {
						Item currentItem = this.inventory.getItem(currentSlot);
						System.out.println("Size: " + this.inventory.size);
						
						if(boughtItem.type == currentItem.type && currentItem.count < this.inventory.stackLimit) {
							currentItem.count += 1;
							this.inventory.setItem(new Item(boughtItem.type, currentItem.count), currentSlot);
							break;
						}
						else if(currentItem.count == 0) {
							this.inventory.setItem(boughtItem, currentSlot);
							break;
						}
					}
					System.out.println("You just bought 1 " + boughtItem.type.name
							+ ".\nCheck in your inventory.\n\n\n");
					break;
				case 3:
					break;
				default:
					break;
				}
				break;
			case 2:
				onShop = false;
				break;
			default:
				break;			
			}
		}
		
	}
	
	public void viewInventory() {
		boolean onShowInventory = true;
		int inventorySelection;
		
		while(onShowInventory) {
			
			System.out.println("\nInventory Menu");
			System.out.println("1. Show Items");
			System.out.println("2. Go Back");
			System.out.println("\n\n");
			System.out.println("Inventory Selection: ");
			inventorySelection = this.sc.nextInt();
			
			switch(inventorySelection) {
			case 1:
				System.out.println("YOUR INVENTORY");
				System.out.println(this.inventory);
				break;
			case 2:
				onShowInventory = false;
				break;
			default:
				break;
			}
			
		}
		
	}
	
	public void viewQuest() {
		
	}
	
	public boolean exit() {
		System.out.println("Program is Finished");
		return false;
	}
}


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu(sc);
		
		int choice;
		boolean running = true;
		
		while(running) {
			System.out.println("PLAYER MAIN SELECTION:");
			System.out.println("1. Start Wandering ");
			System.out.println("2. Open Shop");
			System.out.println("3. View Quest (WIP)");
			System.out.println("4. View Inventory");
			System.out.println("5. Exit:\n\n\n");
			System.out.println("Select Option: ");
			
			choice = sc.nextInt();
			System.out.println("\n\n\n");
			
			switch(choice) {
			case 1:
				try{
					running = menu.startWandering();
				} catch (InterruptedException e) {
					System.out.println(e);
				}
				break;
			case 2:
				menu.openShop();
				break;
			case 3:
				menu.viewQuest();
				break;
			case 4:
				menu.viewInventory();
				break;
			case 5:
				running = menu.exit();
				break;
			default:
				break;
			}
			
		}
		
		sc.close();
	}
}
