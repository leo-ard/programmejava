package game.mobs.ai;

import java.awt.Point;
import java.util.ArrayList;

import game.core.GamePane;
import game.map.Block;

public class Nodes {
	Nodes parent;
	int g;
	int f;
	int h;
	int x,y;
	boolean isStarter;
	
	public Nodes(int x, int y){
		this.x = x;
		this.y = y;
		isStarter = true;
	}
	
	public Nodes(int x, int y,Nodes node){
		this.x = x;
		this.y = y;
		parent = node;
		isStarter = false;
	}
	
	public int getG(){
		return g;
	}

	public int getH(){
		return h;
	}
	
	public int getF(){
		return f;
	}
	/**
	 * 
	 * 
	 * 
	 * @param x of the finished point
	 * @param y of the finished point
	 */
	public void calculate(int x, int y){
		//g calcul
		g = 0;
		boolean stop = false;
		Nodes currentNode = this;
		Nodes nextNode = this.parent;
		int storedX = this.x;
		int storedY = this.y;
		
		while(!stop){
			if(nextNode.x!=currentNode.x && nextNode.y != currentNode.y){
				g+=14;
			}
			else{
				g+=10;
			}
			if(nextNode.isStarter){
				stop = true;
			}
			
			currentNode = nextNode;
			nextNode = nextNode.parent;
		}
		
		//H calcul
		int newX = Math.abs(x-this.x);
		int newY = Math.abs(y-this.y);
		
		this.h = Math.abs(newX-newY)*10+((newX>newY)?newY*14:newX*10);
		
		//f Calcul
		f = g+h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Nodes resetF(){
		f = 0;
		return this;
	}
	

}
