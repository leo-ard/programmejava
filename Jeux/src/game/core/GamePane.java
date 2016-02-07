package game.core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.map.Block;
import game.map.Chunck;
import game.map.Map;
import game.map.View;
import game.audio.*;
import game.mobs.Player;

public class GamePane extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1204454795981689683L;
	
	//----FIELDS----// 
	
	//essentials
	private Thread thread;
	public static int WIDTH = 1280;
	public static int HEIGHT = 800;
	private Listener l;
	private View v;
	public static int mousePosX;
	public static int mousePosY;
	
	//frame
	private final int FPS = 60;
	private double averageFPS = 0;
	
	//map
	public static Map map;
	
	//image
	private Graphics2D g;
	private BufferedImage image;
	
	//impoted images
	public static Image[] texturesBlock = new Image[10];
	public static Image[] texturesGUI = new Image[2];
	public static int curentLevel = 2;
	
	//musique
	public static musicPlayer mp;
	
	
	//player
	public static Player player;

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
		
		//music
		mp = new musicPlayer(new String[]{"assets\\audio\\Concerto.mp3"}, true);
		mp.play();
		
		//all fields for the game will be initiate here
		map = new Map((long)2107554565);
		map.firstGenerate(4);
		
		//Graphics
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		v = new View();
		
		//TODO change the position by the saved one
		player = new Player(0,0);
		
	}
	
	public void textureImport(){
		texturesBlock[1] = new ImageIcon("assets/textures/map/green3.png").getImage();
		texturesBlock[2] = new ImageIcon("assets/textures/map/waterFrame1.png").getImage();
		
		texturesBlock[4] = new ImageIcon("assets/textures/map/rock.png").getImage();
		
		texturesGUI[0] = new ImageIcon("assets/textures/map/Selected.png").getImage();
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
			startTime = System.nanoTime();
			
			try{
				mousePosX = this.getMousePosition().x;
				mousePosY = this.getMousePosition().y;
				mousePosX+=player.getX()-GamePane.WIDTH/2;
				mousePosY+=player.getY()- GamePane.HEIGHT/2;
				}catch(NullPointerException e){}
			
			GameUpdate();
			GameRender();
			GameDraw();
			
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
		
		player.setSpeed(5);
		
		if(Listener.SHIFT){
			player.setSpeed(50);
		}
		
		player.update();
		v.zoom(Listener.getWhellRotation());
		v.update();
		
		//---- Map Update ----//
		Block c = map.getBlockByPixel(mousePosX, mousePosY);
		Map.selectedBlock.setLocation(c.getX(), c.getY());
		map.update();
		
		
		//System.out.println(map.selectedBlock.getX()+" "+map.selectedBlock.getX());
	}
	
	public void GameRender(){
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.translate(View.x, View.y);
		
		//----MAP----//
		Chunck c = map.getByPixel(View.x, View.y);
		map.draw(g, c.getX(),c.getY());
		
		//----INFO----//
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 0, 20));
		g.drawString("Pos: "+View.x+":"+View.y+" Chunck Pos: "+c.getX()+":"+c.getY(), 30, 30);
		
		//----PLAYER----//
		player.draw(g);
		g.translate(-g.getTransform().getTranslateX(), -g.getTransform().getTranslateY());
		
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
