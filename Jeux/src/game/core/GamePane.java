package game.core;
import java.awt.Dimension;

import javax.swing.JPanel;

import game.map.Map;

public class GamePane extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1204454795981689683L;
	
	//----FIELDS----// 
	
	//essentials
	private Thread thread;
	private int WIDTH = 1280;
	private int HEIGHT = 800;
	
	//frame
	private final int FPS = 30;
	private double averageFPS = 0;
	
	//map
	Map map;

	/**
	 * Constructor of the GamePane class.
	 * 
	 */
	public GamePane(){
		super();
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		
		requestFocus();
	}
	
	public void initiate(){
		//all fields for the game will be initiate here
		map = new Map((long)2107564565);
		map.Generate();
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		//Initiate keylisteners here
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
	
	//---- getter and setter ----//

	public double getAverageFPS() {
		return averageFPS;
	}

	public void setAverageFPS(double averageFPS) {
		this.averageFPS = averageFPS;
	}

}
