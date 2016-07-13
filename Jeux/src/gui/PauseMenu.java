package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import game.core.GamePane;
import game.core.Main;
import game.map.View;

public class PauseMenu extends JPanel{
	
	public PauseMenu(){
		super();
		this.setBounds(0, 0, 1280, 800);
		//this.setOpaque(false);
		//this.setBackground(new Color(100,100,100));
	}
	
	public void paintComponent(Graphics g){
		if(false)
			g.drawImage(Main.windows.gp.image,0,0,null);
		g.setColor(new Color(0,0,0,90));
		g.fillRect(0, 0, 1280, 800);
		g.drawImage(GamePane.texturesGUI[2], 390,100, null);
		
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}

	

}
