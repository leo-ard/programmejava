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
	HashMap<Point, Nodes> closed;
	Entity e;
	boolean found;
	int finishX,finishY;
	Nodes finisher;
	
	public AStar(Entity e){
		open = new HashMap<Point, Nodes>();
		closed = new HashMap<Point, Nodes>();
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
		System.out.println("-----------");
		//System.out.println(x+" "+y);
		open = new HashMap<Point, Nodes>();
		closed = new HashMap<Point, Nodes>();
		
		Block b1 = GamePane.map.getBlockByPixel(new Point(x, y));
		finishX = b1.getX();
		finishY = b1.getY();
		int i = 0;
		finisher = null;
		
		Block b = GamePane.map.getBlockByPixel(new Point(e.getX(), e.getY()));
		if(b.getX() == finishX&&b.getY()==finishY)
			return new Point(finishX,finishY);
		
		
		
		
		open.put(new Point(b.getX(), b.getY()), new Nodes(b.getX(), b.getY()));
		this.putInAround(new Nodes(b.getX(), b.getY()));
		while(!found){
			System.out.println("1");
			Nodes n = this.getHightestF();
			
			this.putInAround(n);
			//System.out.println(open.size());
			//System.out.println(n.getX()+" "+n.getY());
		}
		//Nodes finisher = open.containsKey(new Point(finishX,finishY))?open.get(new Point(finishX,finishY)):closed.get(new Point(finishX,finishY));
		
		Nodes lastOne = null;
		boolean finish = false;
		while(!finish){
			if(finisher.isStarter()){
				finish = true;
			}
			else{
				lastOne = finisher;
				finisher = finisher.parent;
			}
		}
		
		//System.out.println(new Point(lastOne.getX(), lastOne.getY()));
		return new Point(lastOne.getX(), lastOne.getY());
	}
	
	public Nodes getHightestF(){
		Nodes lowestValue = (Nodes) open.values().iterator().next();
		Nodes currentValue;
		Iterator<Nodes> i = open.values().iterator();
		while((i.hasNext())){
			currentValue = i.next();
			if(currentValue.getF() < lowestValue.getF()){
				lowestValue = currentValue;
			}
		}
		return lowestValue;
	}
	
	public void putInAround(Nodes n){
		int newX = 0,newY = 0;
		
		for(int i = 0; i < 9; i ++){
			switch(i){
			case 0:newX = n.getX(); newY = n.getY(); break;
			case 1:newX = n.getX()+1; newY = n.getY(); break;
			case 2:newX = n.getX()-1; newY = n.getY(); break;
			case 3:newX = n.getX(); newY = n.getY()+1; break;
			case 4:newX = n.getX()+1; newY = n.getY()+1; break;
			case 5:newX = n.getX()-1; newY = n.getY()+1; break;
			case 6:newX = n.getX(); newY = n.getY()-1; break;
			case 7:newX = n.getX()+1; newY = n.getY()-1; break;
			case 8:newX = n.getX()-1; newY = n.getY()-1; break;
			}
			if(GamePane.map.isBlockInPosition(new Point(newX,newY))&&!GamePane.map.getBlockByPosition(newX, newY).isSolid()){
				if(newX == finishX&&newY == finishY){
					System.out.println("hello");found = true;
					
					open.put(new Point(newX, newY), new Nodes(newX, newY, n));
					open.get(new Point(newX, newY)).calculate(finishX, finishY);
					this.finisher = open.get(new Point(newX, newY));
					System.out.println(finisher.isStarter());
				}
				else if(open.containsKey(new Point(newX, newY))){
					//replica thing to do
					
				}
				else{
					open.put(new Point(newX, newY), new Nodes(newX, newY, n));
					open.get(new Point(newX, newY)).calculate(finishX, finishY);
				}
				
			}
			closed.put(new Point(n.getX(), n.getY()), open.get(new Point(n.getX(), n.getY())));
			open.remove(new Point(n.getX(), n.getY()));
			
		}
		
	}

}