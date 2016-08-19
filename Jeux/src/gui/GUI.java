package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JPanel;

import game.core.GamePane;

/**
 * Class that draw all the GUI around the player, his health, stamina, mana, and his guns and everything
 * 
 * 
 * @author Léonard
 *
 */
public class GUI{
	
	public static void draw(Graphics2D g){
		drawHpBarre(g, 20, 20,Color.white, new Color(255, 0, 0,150), GamePane.player.getMaxHP(), GamePane.player.getHp() );
		
	}
	
	private static FontMetrics getFontMetrics(Font font) {
		JPanel p = new JPanel();
		return p.getFontMetrics(font);
	}
	/**
	 * 
	 * Tout pour faire une belle barre de vie :)
	 * 
	 * @param g Graphics
	 * @param x position x de la barre
	 * @param y position y de la barre
	 * @param lon longeur de la barre
	 * @param haut Hauteur de la barre
	 * @param bordure largeur en pixel de la bordure
	 * @param c1 couleur de fond (background)
	 * @param c2 couleur de la barre (souvent rouge pour la vie)
	 * @param max parametre maximum de la bordure, par exemple la vie maximale
	 * @param min parametre minimal de la bordure, par exeplle la vie actuelle
	 */
	private static void drawHpBarre(Graphics2D g, int x, int y, Color c1, Color c2, int max, int min){
		
		int haut = 15;
		int lon = 200;
		
		
		//----BACKGROUND----//
		g.setColor(c1);
		g.fillRect(x+15, y, 170, haut);
		
		Polygon p = new Polygon();
		p.addPoint(x, y);
		p.addPoint(x+15, y);
		p.addPoint(x+15, y+15);
		g.fill(p);
		p.reset();
		
		p.addPoint(x+lon, y+15);
		p.addPoint(x-15+lon, y+15);
		p.addPoint(x-15+lon, y);
		g.fill(p);
		
		
		//----CALCULE DU POURCENTAGE QUE CHAQUE PARTIE----//
		//P1 = premiere patie soit le triangle au debut < (sur 15)
		//p2 = derniere partie soit le triangle a la fin > (sur 15)
		//p3 = la parie au millieu soit le rectangle (sur 170)
		
		double pourcentage = ((double)min/(double)max);
		int p1 = 15;
		int p2 = 15;
		int p3 = 170;
		
		if(pourcentage < 0){
			pourcentage = 0;
		}
		
		if(pourcentage > 0.925){
			p2 = (int) (((1.0-pourcentage)/0.075)*15.0);
		}
		else if(pourcentage < 0.075){
			p1 = (int) (((pourcentage)/0.075)*15.0);
			p3 = 0;
		}
		else{
			p3 = (int) (((pourcentage-.075)/.85)*170);
		}
		
		g.setColor(c2);
		g.fillRect(x+15, y, p3, haut);
		
		p.reset();
		p.addPoint(x, y);
		p.addPoint(x+p1, y);
		p.addPoint(x+p1, y+p1);
		g.fill(p);
		p.reset();
		
		p.addPoint(x+lon-p2, y+15);
		p.addPoint(x-15+lon, y+15);
		p.addPoint(x-15+lon, y);
		p.addPoint(x+lon-p2, y-p2+15);
		g.fill(p);
		
		g.drawImage(GamePane.texturesGUI[3], x, y,lon, haut, null);
		
		//----TEXT----//
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD ,10));
		FontMetrics fm = getFontMetrics(new Font("Arial", Font.BOLD,10));
		g.drawString(min+"/"+max, x+(lon-fm.stringWidth(min+"/"+max))/2, y+fm.getHeight()-2);
	}


}
