package game.mobs;

import java.awt.Point;

import game.core.GamePane;
import game.mobs.ai.AStar;

public abstract class Character extends Entity {

	protected int maxHP, hp;
	protected int baseDamage, damage;
	protected int baseSpeed, speed;
	protected boolean isAlive = true;
	protected AStar ai;
	
	public Character(int x, int y, int WIDTH, int HEIGHT, int maxHP, int baseSpeed, int baseDamage) {
		super(x, y, WIDTH, HEIGHT);
		this.maxHP = maxHP;
		this.hp = maxHP;
		this.baseSpeed = baseSpeed;
		this.speed = baseSpeed;
		this.baseDamage = baseDamage;
		this.damage = baseDamage;
		ai = new AStar(this);
	}

	/**
	 * 
	 * Move the entity to the point(x,y) using AI, to be overridden
	 * 
	 * @param x
	 * @param y
	 */
	@Override
	protected abstract void move(int x, int y);
	
	/**
	 * Move the chararter in streit line to eventually go to this place.
	 * 
	 * @param p the wanted point
	 * @return true, the point is reach false the point is not yet reach
	 */
	protected boolean moveInStraitLine(Point p){
		
		double rad = Math.toRadians(GamePane.getAngle(p.x, p.y, this.x, this.y));
		dx = Math.cos(rad)*this.speed;
		dy = Math.sin(rad)*this.speed;
		this.go(dx, dy);
		return false;
		//TODO return something else that always false
	}
	
	//---- GETTERS AND SETTERS----//

	public int getMaxHP() {
		return maxHP;
	}
	public int getHp() {
		return hp;
	}

	public void hit(int damage) {
		hp -= damage;
		if(hp <= 0){
			isAlive = false;
		}
	}
	
	public int getBaseSpeed() {
		return baseSpeed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getBaseDamage(){
		return baseDamage;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
}