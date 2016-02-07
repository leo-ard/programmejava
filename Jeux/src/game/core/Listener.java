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
	
	public static boolean F1;
	public static boolean F2;
	public static boolean F3;
	public static boolean F4;
	public static boolean F5;
	public static boolean F6;
	public static boolean F7;
	public static boolean F8;
	public static boolean F9;
	public static boolean F10;
	public static boolean F11;
	public static boolean F12;
	
	
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
		
		if(e.getKeyCode() == KeyEvent.VK_F1){
			F1 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F2){
			F2 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F3){
			F3 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F4){
			F4 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F5){
			F5 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F6){
			F6 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F7){
			F7 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F8){
			F8 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F9){
			F9 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F10){
			F10 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F11){
			F11 = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F12){
			F12 = true;
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
		
		if(e.getKeyCode() == KeyEvent.VK_F1){
			F1 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F2){
			F2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F3){
			F3 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F4){
			F4 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F5){
			F5 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F6){
			F6 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F7){
			F7 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F8){
			F8 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F9){
			F9 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F10){
			F10 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F11){
			F11 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_F12){
			F12 = false;
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
