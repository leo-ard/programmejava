package game.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Listener implements KeyListener, MouseListener, MouseWheelListener{

	public static boolean WOrUp;
	public static boolean AOrLeft;
	public static boolean SOrDown;
	public static boolean DOrRight;
	
	public static boolean LEFT_CLICK;
	public static boolean RIGHT_CLICK;
	
	public static boolean SHIFT;
	
	public static int whellRotation = 0;
	
	//---- Mouse Wheel Listener ----//
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		whellRotation = e.getWheelRotation();
	}

	//---- Mouse Listener ----//
	@Override
	public void mousePressed(MouseEvent e) {
		//Right Click
		if(e.getButton() == MouseEvent.BUTTON1){
			RIGHT_CLICK = true;
		}
		
		//left click
		if(e.getButton() == MouseEvent.BUTTON3){
			LEFT_CLICK = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Right Click
		if(e.getButton() == MouseEvent.BUTTON1){
			RIGHT_CLICK = false;
		}
		
		//left click
		if(e.getButton() == MouseEvent.BUTTON3){
			LEFT_CLICK = false;
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	//---- Key Listener ----//

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_W||e.getKeyCode() == KeyEvent.VK_UP){
			WOrUp = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A||e.getKeyCode() == KeyEvent.VK_LEFT){
			AOrLeft = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S||e.getKeyCode() == KeyEvent.VK_DOWN){
			SOrDown = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D||e.getKeyCode() == KeyEvent.VK_RIGHT){
			DOrRight = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			SHIFT = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W||e.getKeyCode() == KeyEvent.VK_UP){
			WOrUp = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A||e.getKeyCode() == KeyEvent.VK_LEFT){
			AOrLeft = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S||e.getKeyCode() == KeyEvent.VK_DOWN){
			SOrDown = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D||e.getKeyCode() == KeyEvent.VK_RIGHT){
			DOrRight = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			SHIFT = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public static int getWhellRotation(){
		int x = whellRotation;
		whellRotation = 0;
		return x;
	}

}
