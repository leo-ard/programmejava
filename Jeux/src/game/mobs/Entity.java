package game.mobs;

import java.awt.Point;

import game.core.GamePane;
import game.core.Main;
import game.map.Map;
import game.map.View;

public abstract class Entity {
	
	protected int x, y;
	protected int WIDTH, HEIGHT;
	
	public Entity(int x, int y, int WIDTH, int HEIGHT){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.x = x;
		this.y = y;
	}
	
	

}
