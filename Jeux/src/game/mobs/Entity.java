package game.mobs;

public abstract class Entity {
	
	protected int x, y;
	protected int WIDTH, HEIGHT;
	
	public Entity(int x, int y, int WIDTH, int HEIGHT){
		this.x = x;
		this.y = y;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
	}
	
	/**
	 * 
	 * Move the entity to the point(x,y), to be overriden
	 * 
	 * @param x
	 * @param y
	 */
	protected abstract void move(int x, int y);
	
	
	//----GETTERS AND SETTERS----//
	
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
	
	public int getWidth(){
		return this.WIDTH;
	}
	public int getHeight(){
		return this.HEIGHT;
	}
	
}
