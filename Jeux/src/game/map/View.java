package game.map;

import game.core.GamePane;

public class View {
	
	private int zoom;
	private final int baseBlockPixelHeight = 100;
	private final int baseBlockPixelWidth = 94;  
	
	public static int chuncksBlockWidth = 24;
	public static int blockPixelHeight = 100;
	public static int blockPixelWidth = 94;
	
	public static int x = 0;
	public static int y = 0;
	
	
	public View() {
		zoom = 10;
	}
	
	public void update(){
		blockPixelHeight = (int) (double)(baseBlockPixelHeight*zoom/10);
		blockPixelWidth = (int) (double)(baseBlockPixelWidth*zoom/10);
	}

	public double getZoom() {
		return zoom;
	}

	public void zoom(int nb) {
		/*int xx = 0;
		int yy = 0;
		
		this.zoom -= nb;
		x+=xx*nb;
		y+=yy*nb;
		
		if(zoom > 20){
			zoom = 20;
			x+=xx;
			y+=yy;
		}
		if(zoom < 5){
			zoom = 5;
			x-=xx;
			y-=yy;
		}*/
	}

	public static void setX(int x) {
		View.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		View.y = y;
	}

}
