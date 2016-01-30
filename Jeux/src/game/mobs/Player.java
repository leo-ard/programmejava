package game.mobs;

import java.awt.Color;
import java.awt.Graphics2D;

import game.core.Listener;

public class Player extends Mob{
	
	private boolean attackMode;

	public Player(int x, int y) {
		super(x, y, 30, 30, 100, 100, 5, 10);
		setAttackMode(false);
	}
	
	public void update(){
		if(!attackMode){
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
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.fillOval(x-WIDTH/2, y-(HEIGHT)/2, WIDTH, HEIGHT);
		
	}

	public boolean isAttackMode() {
		return attackMode;
	}

	public void setAttackMode(boolean attackMode) {
		this.attackMode = attackMode;
	}
	

}
