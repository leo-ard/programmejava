package game.core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.map.Map;

public class GamePane extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1204454795981689683L;
	
	//----FIELDS----// 
	
	//to delete
	int x = 0;
	int y = 0;
	
	//essentials
	private Thread thread;
	public static int WIDTH = 1280;
	public static int HEIGHT = 800;
	private Listener l;
	
	//frame
	private final int FPS = 30;
	private double averageFPS = 0;
	
	//map
	Map map;
	
	//image
	private Graphics2D g;
	private BufferedImage image;
	
	//impoted images
	public static Image[] texturesBlock = new Image[10];

	/**
	 * Constructor of the GamePane class.
	 * 
	 */
	public GamePane(){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		l = new Listener();
		
		requestFocus();
	}
	
	public void initiate(){
		//import images
		textureImport();
		
		//all fields for the game will be initiate here
		map = new Map((long)2107564565, 1_000);
		map.firstGenerate(4);
		
		//Graphics
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
	}
	
	public void textureImport(){
		texturesBlock[0] = new ImageIcon("assets/textures/map/green3.png").getImage();
		texturesBlock[1] = new ImageIcon("assets/textures/map/waterFrame1.png").getImage();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		//Initiate keylisteners here
		this.addKeyListener(l);
		this.addMouseListener(l);
		this.addMouseWheelListener(l);
	}
	
	

	/**
	 * run the game thread
	 */
	public void run() {
		
		//---- Innitiation ----//
		initiate();
		
		
		//---- field for the fps system ----//
		long startTime = 0;
		long URDTimeMillis;
		long waitTime;
		long totalTime = 0;
		
		int frameCount = 0;
		int maxFrameCount = 30;
		
		long targetTime = 1000/FPS;
		
		//----Thread----//
		while(true){
			
			GameUpdate();
			GameRender();
			GameDraw();
			
			
			if(Listener.AOrLeft){
				x+=10;
			}
			if(Listener.DOrRight){
				x-=10;
			}
			if(Listener.SOrDown){
				y-=10;
			}
			if(Listener.WOrUp){
				y+=10;
			}
			
			//---- System for the fps ----//
			
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			
			waitTime = targetTime - URDTimeMillis;
			if(waitTime <0){waitTime = 0;}
			
			
				
			
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {}
			
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			
			if(frameCount == maxFrameCount){
				averageFPS = 1000.0 / ((totalTime / frameCount)/1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
		
	}
	
	public void GameUpdate(){
		
	}
	
	public void GameRender(){
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//map
		map.draw(g, x, y);
	}
	
	public void GameDraw(){
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0,0, null);
		
		g2.dispose();
		
	}
	
	//---- getter and setter ----//

	public double getAverageFPS() {
		return averageFPS;
	}

	public void setAverageFPS(double averageFPS) {
		this.averageFPS = averageFPS;
	}

}
