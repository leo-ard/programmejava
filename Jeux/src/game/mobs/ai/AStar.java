package game.mobs.ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


import game.core.GamePane;
import game.map.Block;
import game.mobs.Entity;

public class AStar {
	
	HashMap<Point, Nodes> open; 
	Entity e;
	boolean found;
	
	public AStar(Entity e){
		open = new HashMap<Point, Nodes>();
		this.e = e;
		found = false;
	}
	/**
	 * 
	 * @param e cette entite qui call ce AI
	 * @param x Valeur x de lendroit desirer
	 * @param y Valeur y de lendroit desirer
	 * @return the point where to go first
	 */
	public Point getIndication(int x, int y){
		Block b = GamePane.map.getBlockByPixel(e.getX(), e.getY());
		this.putInAround(b.getX(), b.getY());
		while(!found){
			
		}
	}
	
	public Nodes getHightestF(){
		Nodes hightest = (Nodes) open.values().toArray()[0];
		Nodes currentValue;
		Iterator i = open.values().iterator();
		while((i.hasNext())){
			currentValue = (Nodes) i.next();
			if(currentValue.getF() > hightest.getF()){
				hightest = currentValue;
			}
		}
		
		return hightest;
	}
	
	public void putInAround(int x, int y){
		int newX = 0,newY = 0;
		
		for(int i = 0; i < 9; i ++){
			switch(i){
			case 0:newX = x; newY = y; break;
			case 1:newX = x+1; newY = y; break;
			case 2:newX = x-1; newY = y; break;
			case 3:newX = x; newY = y+1; break;
			case 4:newX = x+1; newY = y+1; break;
			case 5:newX = x-1; newY = y+1; break;
			case 6:newX = x; newY = y-1; break;
			case 7:newX = x+1; newY = y-1; break;
			case 8:newX = x-1; newY = y-1; break;
			}
			if(GamePane.map.isBlockInPosition(new Point(newX,newY))&&!GamePane.map.getBlockByPosition(newX, newY).isSolid()){
				if(open.containsKey(new Point(newX, newY))){
					//replica thing to do
				}
				else{
					open.put(new Point(newX, newY), new Nodes(newX, newY));
				}
				
			}
			
		}
		open.remove(new Point(x, y));
	}
	
	public void putInAround(int x, int y, Nodes n){
		int newX = 0,newY = 0;
		
		for(int i = 0; i < 9; i ++){
			switch(i){
			case 0:newX = x; newY = y; break;
			case 1:newX = x+1; newY = y; break;
			case 2:newX = x-1; newY = y; break;
			case 3:newX = x; newY = y+1; break;
			case 4:newX = x+1; newY = y+1; break;
			case 5:newX = x-1; newY = y+1; break;
			case 6:newX = x; newY = y-1; break;
			case 7:newX = x+1; newY = y-1; break;
			case 8:newX = x-1; newY = y-1; break;
			}
			if(GamePane.map.isBlockInPosition(new Point(newX,newY))&&!GamePane.map.getBlockByPosition(newX, newY).isSolid()){
				open.put(new Point(newX, newY), new Nodes(newX, newY,n));
			}
			
		}
	}

}