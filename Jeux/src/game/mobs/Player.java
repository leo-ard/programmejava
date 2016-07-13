package game.mobs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import game.armes.Arme;
import game.armes.Bullet;
import game.armes.Gun;
import game.core.Frame;
import game.core.GamePane;
import game.core.Main;
import game.map.Block;
import game.map.Map;
import game.map.View;

public class Player extends Mob{
	
	private boolean running;
	private final int RUNNING_SPEED = 20;
	private Arme Arme;

	public Player(int x, int y) {
		super(x, y, 30, 30, 100, 5, 10);
		Arme = Gun.HANDGUN;
	}
	
	public void update(){
		int speedX = 0, speedY = 0;
		
		if (GamePane.l.SHIFT){
			setRunning(true);
		} else {
			setRunning(false);
		}
		if(isRunning()){
			this.setSpeed(RUNNING_SPEED);
		} else {
			setSpeed(getBaseSpeed());
		}
		if(GamePane.l.AOrLeft){
			speedX = -1;
		}
		if(GamePane.l.DOrRight){
			speedX = 1;
		}
		if(GamePane.l.SOrDown){
			speedY = 1;
		}
		if(GamePane.l.WOrUp){
			speedY = -1;
		}
		int xOld = this.x;
		int yOld = this.y;
		
		this.x += this.speed*speedX;
		this.y += this.speed*speedY;
		if(this.x < WIDTH/2){
			this.x = WIDTH/2;
		}
		if(this.y < HEIGHT/2){
			this.y = HEIGHT/2;
		}
		if(this.x > GamePane.map.getSizeX()*View.blockPixelWidth-WIDTH/2){
			this.x = GamePane.map.getSizeX()*View.blockPixelWidth-WIDTH/2;
		}
		if(this.y > GamePane.map.getSizeY()*View.blockPixelHeight-HEIGHT/2){
			this.y = GamePane.map.getSizeY()*View.blockPixelHeight-HEIGHT/2;
		}
		
		if(speedX == 1  || speedY == 1 || speedX == -1||speedY == -1){
			if(GamePane.l.F10){
				GamePane.l.WOrUp = false;
				GamePane.l.AOrLeft = false;
				GamePane.l.SOrDown = false;
				GamePane.l.DOrRight = false;
			}
		}
		try{
			this.collision(xOld, yOld, speedX, speedY);
		}catch(NullPointerException e){
			
		}
		
		Arme.update();
	}
	
	public void editorUpdate(){
		int speedX = 0, speedY = 0;
		
		if (GamePane.l.SHIFT){
			setRunning(true);
		} else {
			setRunning(false);
		}
		if(isRunning()){
			this.setSpeed(RUNNING_SPEED);
		} else {
			setSpeed(getBaseSpeed());
		}
		if(GamePane.l.AOrLeft){
			speedX = -1;
		}
		if(GamePane.l.DOrRight){
			speedX = 1;
		}
		if(GamePane.l.SOrDown){
			speedY = 1;
		}
		if(GamePane.l.WOrUp){
			speedY = -1;
		}
		int xOld = this.x;
		int yOld = this.y;
		
		this.x += this.speed*speedX;
		this.y += this.speed*speedY;
		if(this.x < WIDTH/2){
			this.x = WIDTH/2;
		}
		if(this.y < HEIGHT/2){
			this.y = HEIGHT/2;
		}
		if(this.x > GamePane.map.getSizeX()*View.blockPixelWidth-WIDTH/2){
			this.x = GamePane.map.getSizeX()*View.blockPixelWidth-WIDTH/2;
		}
		if(this.y > GamePane.map.getSizeY()*View.blockPixelHeight-HEIGHT/2){
			this.y = GamePane.map.getSizeY()*View.blockPixelHeight-HEIGHT/2;
		}
		if(speedX == 1  || speedY == 1 || speedX == -1||speedY == -1){
			if(GamePane.l.F10){
				GamePane.l.WOrUp = false;
				GamePane.l.AOrLeft = false;
				GamePane.l.SOrDown = false;
				GamePane.l.DOrRight = false;
			}
		}
		//COLLISION
		if(Main.windows.cColision.isSelected()){
			try{
				collision(xOld, yOld, speedX, speedY);
			}
			catch(NullPointerException e){}
		}
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);	
		g.fillOval(x-WIDTH/2, y-(HEIGHT)/2, WIDTH, HEIGHT);
		/*double rad = Math.toRadians(GamePane.getAngleOfTheMouseAndThePlayer());
		int dx = (int)(Math.cos(rad)*100);
		int dy = (int)(Math.sin(rad)*100);
		g.drawLine(this.x, this.y, this.x+dx, this.y+dy);*/
		
		
	}
	
	public void editorDraw(Graphics2D g){
		g.setColor(new Color(0,0,0, 50));
		g.fillOval(x-WIDTH/2, y-(HEIGHT)/2, WIDTH, HEIGHT);
	}
	
	
	public boolean collision(int xOld, int yOld, int speedX, int speedY) throws NullPointerException{
		/*
		 *    a1       a2             b1         b2
		 *                        
		 *         a                       b
		 * 
		 *    a3       a4             b3         b4
		 */
		
		//Point a = new Point(xOld, yOld);
		Point a1 = new Point(xOld-this.WIDTH/2, yOld-this.HEIGHT/2);
		Point a2 = new Point(xOld+this.WIDTH/2, yOld-this.HEIGHT/2);
		Point a3 = new Point(xOld-this.WIDTH/2, yOld+this.HEIGHT/2);
		Point a4 = new Point(xOld+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		Point b = new Point(this.x, this.y);
		Point b1 = new Point(this.x-this.WIDTH/2, yOld-this.HEIGHT/2);
		Point b2 = new Point(this.x+this.WIDTH/2, yOld-this.HEIGHT/2);
		Point b3 = new Point(this.x-this.WIDTH/2, yOld+this.HEIGHT/2);
		Point b4 = new Point(this.x+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		if(speedX == 1){
			if(GamePane.getXOfMapByPixel(a1) != GamePane.getXOfMapByPixel(b2)){
				if(GamePane.map.getBlockByPixel(b2).isSolid()){
					this.x = GamePane.getXOfMapByPixel(b2)*View.blockPixelWidth-this.WIDTH/2-1;
				}
				portal(b2);
			}
			
			if(GamePane.getXOfMapByPixel(a3) != GamePane.getXOfMapByPixel(b4)){
				if(GamePane.map.getBlockByPixel(b4).isSolid()){
					this.x = GamePane.getXOfMapByPixel(b4)*View.blockPixelWidth-this.WIDTH/2-1;
				}
				portal(b4);
			}
		}
		if(speedX == -1){
			if(GamePane.getXOfMapByPixel(a2) != GamePane.getXOfMapByPixel(b1)){
				if(GamePane.map.getBlockByPixel(b1).isSolid()){
					this.x = (GamePane.getXOfMapByPixel(b1)+1)*View.blockPixelWidth+this.WIDTH/2;
				}
				portal(b1);
			}
			if(GamePane.getXOfMapByPixel(a4) != GamePane.getXOfMapByPixel(b3)){
				if(GamePane.map.getBlockByPixel(b3).isSolid()){
					this.x = (GamePane.getXOfMapByPixel(b3)+1)*View.blockPixelWidth+this.WIDTH/2;
				}
				portal(b3);
			}
		}
		
		a1 = new Point(xOld-this.WIDTH/2, yOld-this.HEIGHT/2);
		a2 = new Point(xOld+this.WIDTH/2, yOld-this.HEIGHT/2);
		a3 = new Point(xOld-this.WIDTH/2, yOld+this.HEIGHT/2);
		a4 = new Point(xOld+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		b1 = new Point(xOld-this.WIDTH/2, this.y-this.HEIGHT/2);
		b2 = new Point(xOld+this.WIDTH/2, this.y-this.HEIGHT/2);
		b3 = new Point(xOld-this.WIDTH/2, this.y+this.HEIGHT/2);
		b4 = new Point(xOld+this.WIDTH/2, this.y+this.HEIGHT/2);
		
		if(speedY == 1){
			if(GamePane.getYOfMapByPixel(a1) != GamePane.getYOfMapByPixel(b3)){
				if(GamePane.map.getBlockByPixel(b3).isSolid()){
					this.y = GamePane.getYOfMapByPixel(b3)*View.blockPixelHeight-this.HEIGHT/2-1;
				}
				portal(b3);
			}
			
			if(GamePane.getYOfMapByPixel(a2) != GamePane.getYOfMapByPixel(b4)){
				if(GamePane.map.getBlockByPixel(b4).isSolid()){
					this.y = GamePane.getYOfMapByPixel(b4)*View.blockPixelHeight-this.HEIGHT/2-1;
				}
				portal(b4);
			}
		}
		if(speedY == -1){
			if(GamePane.getYOfMapByPixel(a3) != GamePane.getYOfMapByPixel(b1)){
				if(GamePane.map.getBlockByPixel(b1).isSolid()){
					this.y = (GamePane.getYOfMapByPixel(b1)+1)*View.blockPixelHeight+this.HEIGHT/2;
				}
				portal(b1);
			}
			
			if(GamePane.getYOfMapByPixel(a4) != GamePane.getYOfMapByPixel(b2)){
				if(GamePane.map.getBlockByPixel(b2).isSolid()){
					this.y = (GamePane.getYOfMapByPixel(b2)+1)*View.blockPixelHeight+this.HEIGHT/2;
				}
				portal(b2);
			}
		}
		return false;
		
	}
	
	public void portal(Point b){
		if(GamePane.map.getBlockByPixel(b).isPortal){
			if(Main.windows.edi.isShowing())
				Main.windows.edi.map.save();
			this.tp(GamePane.map.getBlockByPixel(b).getPortal().getPointB().x*View.blockPixelWidth+View.blockPixelWidth/2, GamePane.map.getBlockByPixel(b).getPortal().getPointB().y*View.blockPixelHeight+View.blockPixelHeight/2, GamePane.map.getBlockByPixel(b).getPortal().getName());
		}
	}
	
	public void tp(int x, int y, String str){
		this.x = x;
		this.y = y;
		GamePane.map = new Map(str);
	}
	
	public void tp(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	private void move(int x, int y){
		//TODO move if(Main.l.) to move
		//TODO make mouvements more fluid (pas juste les lignes droites, ameliorer les tournants)
	}
	
	public boolean isRunning(){
		return this.running;
	}
	
	public void setRunning(boolean bool){
		if (bool){
			this.running = true;
			Main.windows.frame.getContentPane().setCursor(GamePane.BLANK_CURSOR);
		} else {
			this.running = false;
			Main.windows.frame.getContentPane().setCursor(GamePane.DEFAULT_CURSOR);
		}
	}

	public Arme getArme() {
		return Arme;
	}

	public void setArme(Arme arme) {
		Arme = arme;
	}
	
	public Gun getGun() {
		return (Gun)Arme;
	}

	public void setGun(Arme arme) {
		Arme = arme;
	}

	

}
