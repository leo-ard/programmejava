package game.mobs;

import java.awt.Color;
import java.awt.Graphics2D;


public class Corrompu extends Mob{
	
	private boolean angered;
	private final int angerDamage;
	private final int RUNNING_SPEED = 40;

	public Corrompu(int x, int y) {
		super(x, y, 15, 15, 50, 5, 5);
		this.angerDamage = dommage*2;
	}
	
	//
	public void update(){
		if (hp < maxHP/4){
			setAngered(true);
		} else {
			setAngered(false);
		}
		if(isAngered()){
			this.dommage = angerDamage;
			this.setSpeed(RUNNING_SPEED);
		} else {
			this.dommage = baseDommage;
			setSpeed(baseSpeed);
		}
		x--; 	//TODO AI des mouvements du mob en overridant la methode .move() de Mob
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.fillOval(x-WIDTH/2, y-(HEIGHT)/2, WIDTH, HEIGHT);
		
	}
	
	private void move(int x, int y){
		//TODO seeking player AI
	}
	
	public boolean isAngered(){
		return this.angered;
	}
	
	public void setAngered(boolean bool){
		if (bool){
			this.angered = true;
		} else {
			this.angered = false;
		}
	}

	

}
