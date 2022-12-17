package com.adrian.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.adrian.item.Item;
import com.adrian.item.ItemType;

/*
 * Enum / List of Quests.
 */
enum QuestLog {
	_1("Kill 5 Enemy"),
	_2("Kill 10 Enemy"),
	_3("Kill 15 Enemy"),
	_4("Kill 20 Enemy"),
	_5("Kill 25 Enemy");

    private static final Map<String, QuestLog> BY_QUEST_LIST = new HashMap<>();
    
    static {
        for (QuestLog log: values()) {
            BY_QUEST_LIST.put(log.prompt, log);
        }
    }

    public final String prompt;

    private QuestLog(String prompt) {
        this.prompt = prompt;
    }

    public static QuestLog nameOfQuest(String prompt) {
        return BY_QUEST_LIST.get(prompt);
    }
}

class Quest {
	private List<Item> itemDrops = new LinkedList<Item>();
	private String prompt;
	
	/*
	 * Quest: Content
	 * - prompt
	 * - itemDrops
	 */
	public Quest(String questLog) {
		this.prompt = QuestLog.valueOf("_" + questLog).prompt;
		this.itemDrops.add(new Item(ItemType.Potion.fullRestore, 1));
		this.itemDrops.add(new Item(ItemType.Potion.fullRestore, 3));
	}
	
	/*
	 * display Quest Information or Log
	 */
	public void displayLog() {
		System.out.println("Quest: " + this.prompt);
		System.out.println("Drops are: \n");
		for(Item item: itemDrops) {
			item.getItemInfo();
		}
	}
}


public class Enemy extends Entity{
	public Enemy (String name, int healthPoints, int manaPoints) {
		this.name = name;
		this.MAX_HP = healthPoints;
		this.HP = MAX_HP;
		this.MP = manaPoints;
		this.movementSpeed = 2;
		this.damage = 3;
		
		// Assign Quest Name 
//		Quest quest = new Quest("3");
//		quest.displayLog();
	}	
	

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
}
