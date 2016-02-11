package game.mobs;

public class Mob {

	protected int maxHP;
	protected int hp;
	protected int baseDommage;
	protected int dommage;


	protected int baseSpeed;
	protected int speed;
	protected boolean isAlive = true;

	protected int x, y;
	protected int WIDTH, HEIGHT;
	

	protected Mob(int x, int y, int WIDTH, int HEIGHT, int maxHP, int baseSpeed, int baseDommage) {
		this.maxHP = maxHP;
		this.hp = maxHP;
		this.baseSpeed = baseSpeed;
		this.speed = baseSpeed;
		this.baseDommage = baseDommage;
		this.dommage = baseDommage;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
	
	/**
	 * 
	 * Move the mob to the point(x,y) using AI, to be overriden
	 * 
	 * @param x
	 * @param y
	 */
	private void move(int x, int y){}
	

	//---- GETTERS AND SETTERS----//
	
	/**
	 * 
	 * @return max Health
	 */
	public int getMaxHP() {
		return maxHP;
	}

	public int getHp() {
		return hp;
	}

	public void hit(int dommage) {
		hp -= dommage;
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
		return baseDommage;
	}

	public int getDommage() {
		return dommage;
	}

	public void setDommage(int dommage) {
		this.dommage = dommage;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

}
