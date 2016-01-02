package game.mobs;

public class Mob {

	private int maxHP;
	private int Hp;
	private int speed;
	private int domage;
	private boolean isAlive = true;

	protected Mob(int maxHP, int hp, int speed, int domage) {
		super();
		this.maxHP = maxHP;
		this.Hp = hp;
		this.speed = speed;
		this.domage = domage;
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
	
	
	/**
	 * 
	 * Go to a point in a strait line. 
	 *
	 * x = 0 --> UP (W)
	 * x = 1 --> RGIHT (D)
	 * x = 2 --> DOWN (S)
	 * x = 3 --> LEFT (A)
	 * 
	 * @param x
	 */
	public void go(byte x){
		
	}
	

	//---- GETTERS AND SETTERS----//
	
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

}
