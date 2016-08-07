package game.mobs;

public abstract class Mob extends Character{


	protected Mob(int x, int y, int WIDTH, int HEIGHT, int maxHP, int baseSpeed, int baseDamage) {
		super(x, y, WIDTH, HEIGHT, maxHP, baseSpeed, baseDamage);
	}
	
	/**
	 * 
	 * Move the mob to the point(x,y) using AI, to be overriden
	 * 
	 * @param x
	 * @param y
	 */
	protected abstract void move(int x, int y);

}
