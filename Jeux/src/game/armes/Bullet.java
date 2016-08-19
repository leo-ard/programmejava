package game.armes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import game.core.GamePane;
import game.mobs.Entity;

public class Bullet extends Entity{
	

	@SuppressWarnings("unused")
	private double angle;
	
	@SuppressWarnings("unused")
	private Gun gun;
	
	private int dx;
	private int dy;
	private double rad;
	private int r;
	private int speedX;
	private int speedY;
	
	/**
	 * constructeur de la classe Bullet
	 * 
	 * @param angle
	 * @param x
	 * @param y
	 * @param speed
	 * @param domage
	 * @param durabilite
	 * @param explosif
	 * @param knockback
	 */
	public Bullet(int x, int y, double angle, Gun gun) {
		super(x,y,2,2);
		this.angle = angle;
		this.gun = gun;
		this.r = 2;
		
		//calcule la distance a parcourir depensdament de langle et de la vitesse
		this.rad = Math.toRadians(angle);
		this.dx = (int)(Math.cos(rad)*gun.getSpeed());
		this.dy = (int)(Math.sin(rad)*gun.getSpeed());
		if(dx > 0){
			speedX = 1;
		}
		else
			speedX = -1;
		if(dy > 0){
			speedY = 1;
		}
		else{
			speedY = -1;
		}
		
	}
	
	public boolean update(){
		x+=dx;
		y+=dy;
		
		//Verifie les collisions avec les murs
		try{
			if(this.collision(x-(int)dx, y-(int)dy, speedX, speedY)){
				return true;
		}
		}catch(NullPointerException e){};
		
		if(this.x < 0||this.y < 0||this.x > GamePane.map.getSizeX()*GamePane.v.blockPixelWidth||this.y > GamePane.map.getSizeY()*GamePane.v.blockPixelHeight)
			return true;
		
		return false;
	}
	
	protected void move(int x, int y){
		//TODO put all movements in move method
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.yellow);
		g.fillOval((int)this.x-this.r, (int)this.y-this.r, this.r*2 , this.r*2);
		/*double b = Math.toRadians(GamePane.getAngle(this.x, this.y, GamePane.player.getX(),GamePane.player.getY()));
		int bx = (int)(Math.cos(b)*10);
		int by = (int)(Math.sin(b)*10);
		g.setColor(Color.red);
		g.fillOval((int)this.x+bx-this.r, (int)this.y+by-this.r, this.r*2 , this.r*2);*/
	}
	
	/**
	 * 
	 * Classe qui permet de savoir si un objet est rentrer dans un objets
	 * 
	 * 
	 * @param xOld ancien x, soit la position avant lupdate
	 * @param yOld 
	 * @param speedX dirrection x, peu etre soit 1, 0 ou -1
	 * @param speedY
	 * @return
	 * @throws NullPointerException
	 */
	public boolean collision(int xOld, int yOld, int speedX, int speedY) throws NullPointerException{
		/*
		 * position de la			position prevu de lentite
		 * derniere update			
		 * 
		 *    a1       a2             b1        b2
		 *                        
		 *         a                       b
		 * 
		 *    a3       a4             b3        b4
		 */
		
		//Point a = new Point(xOld, yOld);
		Point a1 = new Point(xOld-this.WIDTH/2, yOld-this.HEIGHT/2);
		Point a2 = new Point(xOld+this.WIDTH/2, yOld-this.HEIGHT/2);
		Point a3 = new Point(xOld-this.WIDTH/2, yOld+this.HEIGHT/2);
		Point a4 = new Point(xOld+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		//Point b = new Point(this.x, this.y);
		Point b1 = new Point(this.x-this.WIDTH/2, yOld-this.HEIGHT/2);
		Point b2 = new Point(this.x+this.WIDTH/2, yOld-this.HEIGHT/2);
		Point b3 = new Point(this.x-this.WIDTH/2, yOld+this.HEIGHT/2);
		Point b4 = new Point(this.x+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		//va a droite
		if(speedX == 1){
			if(GamePane.getXOfMapByPixel(a1) != GamePane.getXOfMapByPixel(b2)){
				if(GamePane.map.getBlockByPixel(b2).isSolid()){
					return true;
				}
			}
			
			if(GamePane.getXOfMapByPixel(a3) != GamePane.getXOfMapByPixel(b4)){
				if(GamePane.map.getBlockByPixel(b4).isSolid()){
					return true;
				}
			}
		}
		
		//va a gauche
		if(speedX == -1){
			if(GamePane.getXOfMapByPixel(a2) != GamePane.getXOfMapByPixel(b1)){
				if(GamePane.map.getBlockByPixel(b1).isSolid()){
					return true;
				}
			}
			if(GamePane.getXOfMapByPixel(a4) != GamePane.getXOfMapByPixel(b3)){
				if(GamePane.map.getBlockByPixel(b3).isSolid()){
					return true;
				}
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
		
		//va en bas
		if(speedY == 1){
			if(GamePane.getYOfMapByPixel(a1) != GamePane.getYOfMapByPixel(b3)){
				if(GamePane.map.getBlockByPixel(b3).isSolid()){
					return true;
				}
			}
			
			if(GamePane.getYOfMapByPixel(a2) != GamePane.getYOfMapByPixel(b4)){
				if(GamePane.map.getBlockByPixel(b4).isSolid()){
					return true;
				}
			}
		}
		
		// va en haut
		if(speedY == -1){
			if(GamePane.getYOfMapByPixel(a3) != GamePane.getYOfMapByPixel(b1)){
				if(GamePane.map.getBlockByPixel(b1).isSolid()){
					return true;
				}
			}
			
			if(GamePane.getYOfMapByPixel(a4) != GamePane.getYOfMapByPixel(b2)){
				if(GamePane.map.getBlockByPixel(b2).isSolid()){
					return true;
				}
			}
		}
		return false;
		
	}
	
}
