package game.armes;

import java.awt.Color;
import java.awt.Graphics2D;

import game.core.GamePane;
import game.mobs.Entity;
import game.mobs.Mob;
import game.mobs.Character;

/**
 * 
 * class that create the slash when attaking with a mele weapon
 * 
 * 
 * @author Léonard
 *
 */
public class Slash {
	int x, y, startingDegree, startingDegreeForHitBox;
	//temps passe
	long startingTime;
	double pourcentage;
	boolean goRight;
	
	Mele m;
	Character e;
	
	/**
	 * 
	 * Constructeur de slash
	 * 
	 * @param x origine x du demi-cercle
	 * @param y origine y du demi-cercle
	 * @param width longeur du slash en degre (un width de 180 va faire un demi-cercle devant lui)
	 * @param haight hauteur de slash, epaisseur, etc en pixels
	 * @param temps en cb de temps le slah sera executer
	 * @param degree ou le slash commence, encore sur 360
	 */
	public Slash(Character e, boolean goRight, Mele m) {
		this.x = e.getX();
		this.y = e.getY();
		this.startingDegree = (int) (GamePane.getAngleOfTheMouseAndThePlayer());
		this.startingDegreeForHitBox = (int) (GamePane.getAngleOfTheMouseAndThePlayer()+m.width/2);
		this.startingTime = System.currentTimeMillis();
		this.m = m;
		this.goRight = goRight;
		this.e = e;
	}
	
	public boolean update(){
		long difference = System.currentTimeMillis() - startingTime;
		pourcentage = (double)difference/(double)m.tempsDExecusion;
		
		x = this.e.getX();
		y = this.e.getY();
		if(pourcentage > 1){
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.red);
		g.fillArc(x-m.height/2, y-m.height/2, m.height, m.height,goRight?(int) (-(startingDegree+m.width/2)+pourcentage*m.width):(int) (-(startingDegree-m.width/2)+pourcentage*-m.width), 2);
		//g.drawArc(x-m.height/2, y-m.height/2, m.height, m.height, -this.startingDegreeForHitBox, m.width);
	}
	

}
