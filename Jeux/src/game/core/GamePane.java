package game.core;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.audio.musicPlayer;
import game.map.*;
import game.mobs.*;

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
	private Graphics2D gGame;
	private Graphics2D gUI;
	private BufferedImage image;
	
	//imported images
	public static Image[] texturesBlock = new Image[10];
	public static Image[] texturesGUI = new Image[2];
	public static int curentLevel = 2;
	
	//music
	public static musicPlayer mp;
	
	//cursor
	// Transparent 16 x 16 pixel cursor image.
	static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	// Create a new blank cursor.
	public static final Cursor BLANK_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
	public static final Cursor DEFAULT_CURSOR = Cursor.getDefaultCursor();

	
	//mobs
	public static Player player;
	public ArrayList<Corrompu> corrompus;

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
		map = new Map((long)2107554563);
		map.Generate(4, 0, 0);
		
		//Graphics
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		gGame = (Graphics2D) image.getGraphics();
		gUI = (Graphics2D) image.getGraphics();
		gGame.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gGame.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gUI.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gUI.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		v = new View();
		
		//TODO change the position by the saved one
		player = new Player(0,0);
		corrompus = new ArrayList<Corrompu>();
		corrompus.add(new Corrompu(0,0));
		
	}
	
	public void textureImport(){
		texturesBlock[1] = new ImageIcon("assets/textures/map/grass.png").getImage();
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
		
		player.update();
		//v.zoom(Listener.getWhellRotation());
		v.update();
		
		//---- Map Update ----//
		Block c = map.getBlockByPixel(mousePosX, mousePosY);
		Map.selectedBlock.setLocation(c.getX(), c.getY());
		map.update();
		
		for(int i = 0; i< corrompus.size(); i++){
			corrompus.get(i).update();
		}
		
		
		//to delete
		//System.out.println(map.getSelectedBlock().getEdge());
		
		//System.out.println(map.selectedBlock.getX()+" "+map.selectedBlock.getX());
	}
	
	public void GameRender(){
		gGame.setColor(Color.gray);
		gGame.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
		gGame.translate(View.x, View.y);
		
		//----MAP----//
		Chunck c = map.getChunckByPixel(View.x, View.y);
		map.draw(gGame, c.getX(),c.getY());
		
		//----INFO----//
		gGame.setColor(Color.black);
		gGame.setFont(new Font("Arial", 0, 20));
		gGame.drawString("Pos: "+View.x+":"+View.y+" Chunck Pos: "+c.getX()+":"+c.getY(), 30, 30);
		
		//----MOBS----//
		player.draw(gGame);
		for(int i = 0; i< corrompus.size(); i++){
			corrompus.get(i).draw(gGame);
		}
		
		
		
		
		gGame.translate(-gGame.getTransform().getTranslateX(), -gGame.getTransform().getTranslateY());
		gUI.drawString("FPS: "+averageFPS, 0, 10);
		
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
