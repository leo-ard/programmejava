package game.mobs;

import java.awt.Point;

import game.core.GamePane;

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
	
	/**
	 * 
	 * Methode qui permet de savoir si un objet est rentrer dans un block solide et dans quelle direction
	 * Hitbox carrees
	 * 
	 * 
	 * @param xOld ancien x, soit la position avant l'update
	 * @param yOld 
	 * @param directionX direction x, peut etre soit 1, 0 ou -1
	 * @param directionY
	 * @return collisionDirection[] {collisionRight, collisionLeft, collisionUp, collisionDown}
	 * @throws NullPointerException
	 */
	public boolean[] wallCollision(int xOld, int yOld, int directionX, int directionY) throws NullPointerException{
		/*
		 * position de la			position prevu de l'entite
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
		
		//avant de verifier les collisions horizontales, on assume que la position prevue est au meme y que la derniere update
		//Point b = new Point(this.x, this.y);
		Point b1 = new Point(this.x-this.WIDTH/2, yOld-this.HEIGHT/2);
		Point b2 = new Point(this.x+this.WIDTH/2, yOld-this.HEIGHT/2);
		Point b3 = new Point(this.x-this.WIDTH/2, yOld+this.HEIGHT/2);
		Point b4 = new Point(this.x+this.WIDTH/2, yOld+this.HEIGHT/2);
		
		//array qui represente ou il y a des collisions
		boolean[] collisionDirection = {false, false, false, false};
		
		//verifie a droite
		if(directionX == 1){
			if(GamePane.getXOfMapByPixel(a1) != GamePane.getXOfMapByPixel(b2)){
				if(GamePane.map.getBlockByPixel(b2).isSolid()){
					collisionDirection[0] = true;
				}
			}
			
			if(GamePane.getXOfMapByPixel(a3) != GamePane.getXOfMapByPixel(b4)){
				if(GamePane.map.getBlockByPixel(b4).isSolid()){
					collisionDirection[0] = true;
				}
			}
		}
		//verifie a gauche
		if(directionX == -1){
			if(GamePane.getXOfMapByPixel(a2) != GamePane.getXOfMapByPixel(b1)){
				if(GamePane.map.getBlockByPixel(b1).isSolid()){
					collisionDirection[1] = true;
				}
			}
			if(GamePane.getXOfMapByPixel(a4) != GamePane.getXOfMapByPixel(b3)){
				if(GamePane.map.getBlockByPixel(b3).isSolid()){
					collisionDirection[1] = true;
				}
			}
		}
		
		//avant de verifier les collisions horizontales, on assume que la position prevue est au meme y que la derniere update
		b1 = new Point(xOld-this.WIDTH/2, this.y-this.HEIGHT/2);
		b2 = new Point(xOld+this.WIDTH/2, this.y-this.HEIGHT/2);
		b3 = new Point(xOld-this.WIDTH/2, this.y+this.HEIGHT/2);
		b4 = new Point(xOld+this.WIDTH/2, this.y+this.HEIGHT/2);
		
		//verifie en bas
		if(directionY == 1){
			if(GamePane.getYOfMapByPixel(a1) != GamePane.getYOfMapByPixel(b3)){
				if(GamePane.map.getBlockByPixel(b3).isSolid()){
					collisionDirection[2] = true;
				}
			}
			
			if(GamePane.getYOfMapByPixel(a2) != GamePane.getYOfMapByPixel(b4)){
				if(GamePane.map.getBlockByPixel(b4).isSolid()){
					collisionDirection[2] = true;
				}
			}
		}
		// verifie en haut
		if(directionY == -1){
			if(GamePane.getYOfMapByPixel(a3) != GamePane.getYOfMapByPixel(b1)){
				if(GamePane.map.getBlockByPixel(b1).isSolid()){
					collisionDirection[3] = true;
				}
			}
			
			if(GamePane.getYOfMapByPixel(a4) != GamePane.getYOfMapByPixel(b2)){
				if(GamePane.map.getBlockByPixel(b2).isSolid()){
					collisionDirection[3] = true;
				}
			}
		}
		return collisionDirection;
	}
	
	/**
	 * 
	 * Methode qui permet de verifier la collision avec les cotes de la map et dans quelle direction
	 * @param x
	 * @param y
	 * @return boolean[] {collisionRight, collisionLeft, collisionUp, collisionDown}
	 */
	public boolean[] boundCollision(int x, int y){
		boolean[] collisionDirection = {false, false, false, false};
		if(this.x > GamePane.map.getSizeX()*GamePane.v.blockPixelWidth){ collisionDirection[0] = true; }
		if(this.x < 0){ collisionDirection[1] = true; }
		if(this.y < 0){ collisionDirection[3] = true; }
		if(this.y > GamePane.map.getSizeY()*GamePane.v.blockPixelHeight){ collisionDirection[4] = true; }
		return collisionDirection;
	}
	
	
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
