package com.adrian.entity;

import java.util.UUID;

public abstract class Entity {
	protected String name;
	protected final String ID = UUID.randomUUID().toString();
	protected float MAX_HP;
	protected final float MIN_HP = 0;
	protected float HP;
	protected float MP;
	protected float movementSpeed;
	protected float damage;
	
	public abstract void move();
	
	/**
	 * @return the hP
	 */
	public float getHP() {
		return HP;
	}
	
	public float getMinHP() {
		return MIN_HP;
	}

	/**
	 * @param hP the hP to set
	 */
	public void setHP(float hP) {
		if(hP < MIN_HP) {
			HP = MIN_HP;
			return;
		}
		if(hP > MAX_HP) {
			HP = MAX_HP;
			return;
		}
		HP = hP;
	}

	/**
	 * @return the mP
	 */
	public float getMP() {
		return MP;
	}

	/**
	 * @param mP the mP to set
	 */
	public void setMP(int mP) {
		MP = mP;
	}

	/**
	 * @return the movementSpeed
	 */
	public float getMovementSpeed() {
		return movementSpeed;
	}

	/**
	 * @param movementSpeed the movementSpeed to set
	 */
	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	/**
	 * @return the damage
	 */
	public float getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}
}


