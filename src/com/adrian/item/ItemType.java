package com.adrian.item;

import java.util.HashMap;
import java.util.Map;

public interface ItemType{
	/*
	 * Enums - List of Items 
	 * @param DisplayName
	 * @param Description
	 * @param Heal Amount
	 */
	enum Potion {
		NULL(
				"Air",
				"",
				0
				),
		
		oldPotion(
				"Beginner's Potion",
	    		"Heals the user by 10 HP",
	    		10
				),
		fullRestore(
				"Full Restoration Potion",
	    		"Heals 100% of the user's HP",
	    		100
				);

	    private static final Map<String, Potion> BY_NAME = new HashMap<>();
	    private static final Map<String, Potion> BY_DESCRIPTION = new HashMap<>();
	    private static final Map<Integer, Potion> BY_HEAL_AMOUNT = new HashMap<>();
	    
	    static {
	        for (Potion potion : values()) {
	            BY_NAME.put(potion.name, potion);
	            BY_DESCRIPTION.put(potion.description, potion);
	            BY_HEAL_AMOUNT.put(potion.healAmount, potion);
	        }
	    }

	    public final String name;
	    public final String description;
	    public final int healAmount;

	    private Potion(String name, String description, int healAmount) {
	        this.name = name;
	        this.description = description;
	        this.healAmount = healAmount;
	    }

	    public static Potion valueOfName(String name) {
	        return BY_NAME.get(name);
	    }

	    public static Potion valueOfDescription(String description) {
	        return BY_DESCRIPTION.get(description);
	    }
	    
	    public static Potion valueOfHeal(int healAmount) {
	        return BY_HEAL_AMOUNT.get(healAmount);
	    }
	}
	
	enum Miscellaneous {
		
		egg(
				"Chicken's Egg",
	    		"An egg from a chicken"
				),
		feather(
				"Chicken's Feather",
	    		"A feather from a chicken"
				);

	    private static final Map<String, Miscellaneous> BY_NAME = new HashMap<>();
	    private static final Map<String, Miscellaneous> BY_DESCRIPTION = new HashMap<>();
	    
	    static {
	        for (Miscellaneous misc : values()) {
	            BY_NAME.put(misc.name, misc);
	            BY_DESCRIPTION.put(misc.description, misc);
	        }
	    }

	    public final String name;
	    public final String description;

	    private Miscellaneous(String name, String description) {
	        this.name = name;
	        this.description = description;
	    }

	    public static Miscellaneous valueOfName(String name) {
	        return BY_NAME.get(name);
	    }

	    public static Miscellaneous valueOfDescription(String description) {
	        return BY_DESCRIPTION.get(description);
	    }
	}
}

