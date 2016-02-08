package game.mobs;

public class Mob {

	protected int maxHP;
	protected int Hp;
	protected int domage;


	protected int baseSpeed;
	protected int speed;
	protected boolean isAlive = true;

	protected int x, y;
	protected int WIDTH, HEIGHT;
	

	protected Mob(int x, int y, int WIDTH, int HEIGHT, int maxHP, int hp, int baseSpeed, int domage) {
		this.maxHP = maxHP;
		this.Hp = hp;
		this.baseSpeed = baseSpeed;
		this.speed = baseSpeed;
		this.domage = domage;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
	
	/**
	 * 
	 * Move the mob to the point(x,y) using AI
	 * 
	 * @param x
	 * @param y
	 */
	public void move(int x, int y){
		
	}
	

	//---- GETTERS AND SETTERS----//
	
	/**
	 * 
	 * @return max Health
	 */
	public int getMaxHP() {
		return maxHP;
	}

	public int getHp() {
		return Hp;
	}

	public void hit(int domage) {
		Hp -= domage;
		if(Hp <= 0){
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

	public int getDomage() {
		return domage;
	}

	public void setDomage(int domage) {
		this.domage = domage;
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
		Hp = hp;
	}

}
