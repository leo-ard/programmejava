package game.mobs;

import java.awt.Color;
import java.awt.Graphics2D;

import game.core.GamePane;
import game.core.Listener;
import game.core.Main;
import game.map.Map;

public class Player extends Mob{
	
	private boolean running;
	private final int RUNNING_SPEED = 50;

	public Player(int x, int y) {
		super(x, y, 30, 30, 100, 100, 5, 10);
		
	}
	
	public void update(){
		if (Listener.SHIFT){
			setRunning(true);
		} else {
			setRunning(false);
		}
		if(isRunning()){
			this.setSpeed(RUNNING_SPEED);
		} else {
			setSpeed(getBaseSpeed());
		}
		if(Listener.AOrLeft){
			x-=this.speed;
		}
		if(Listener.DOrRight){
			x+=this.speed;
		}
		if(Listener.SOrDown){
			y+=this.speed;
		}
		if(Listener.WOrUp){
			y-=this.speed;
		}
		
		if(Listener.RIGHT_CLICK){
			GamePane.map.getSelectedBlock().destroy();
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.fillOval(x-WIDTH/2, y-(HEIGHT)/2, WIDTH, HEIGHT);
		
	}
	
	public boolean isRunning(){
		return this.running;
	}
	
	public void setRunning(boolean bool){
		if (bool){
			this.running = true;
			Main.windows.getContentPane().setCursor(GamePane.BLANK_CURSOR);
		} else {
			this.running = false;
			Main.windows.getContentPane().setCursor(GamePane.DEFAULT_CURSOR);
		}
	}

	

}
