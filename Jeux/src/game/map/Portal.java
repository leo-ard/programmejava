package game.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Portal {
	
	private String name;
	private Point pointA;
	private Point pointB;
	
	
	public Portal(Point pointA, Point pointB, String name){
		this.name = name;
		this.pointA = pointA;
		this.pointB = pointB;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Point getPointA() {
		return pointA;
	}


	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}


	public Point getPointB() {
		return pointB;
	}


	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
}
