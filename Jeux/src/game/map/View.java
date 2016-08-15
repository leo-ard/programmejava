package game.map;

import game.core.GamePane;

public class View {
	
	public int zoom;
	private final int baseBlockPixelHeight = 100;
	private final int baseBlockPixelWidth = 100;  
	
	public static int chuncksBlockWidth = 24;
	public static int blockPixelHeight = 100;
	public static int blockPixelWidth = 100;
	
	public static int x = 0;
	public static int y = 0;
	
	
	public View() {
		zoom = 10;
	}
	
	public void update(){
		blockPixelHeight = (int) (double)(baseBlockPixelHeight*zoom/10);
		blockPixelWidth = (int) (double)(baseBlockPixelWidth*zoom/10);
		
		x = -GamePane.player.getX()+GamePane.WIDTH/2;
		y = -GamePane.player.getY()+GamePane.HEIGHT/2;
	}
	
	public void editorUpdate(){
		blockPixelHeight = (int) (double)(baseBlockPixelHeight*zoom/10);
		blockPixelWidth = (int) (double)(baseBlockPixelWidth*zoom/10);
		
		x = -GamePane.player.getX()+GamePane.WIDTH/2-200;
		y = -GamePane.player.getY()+GamePane.HEIGHT/2;
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
	
	public static double putInRationX(int nb){
		return (double)nb/(double)View.blockPixelWidth;
	}
	
	public static double putInRationY(int nb){
		return (double)nb/(double)View.blockPixelHeight;
	}

	
	public static int getFromRationX(double nb){
		return (int) ((double)nb*(double)View.blockPixelWidth);
	}
	
	public static int getFromRationY(double nb){
		return (int) ((double)nb*(double)View.blockPixelHeight);
	}
	
	public static void zoomIn(){
		double rationX = View.putInRationX(GamePane.player.getX());
		double rationY = View.putInRationY(GamePane.player.getY());
		GamePane.v.zoom++;
		GamePane.v.update();
		GamePane.player.setX(View.getFromRationX(rationX));
		GamePane.player.setY(View.getFromRationX(rationY));
	}
	
	public static void zoomOut(){
		double rationX = View.putInRationX(GamePane.player.getX());
		double rationY = View.putInRationY(GamePane.player.getY());
		GamePane.v.zoom--;
		GamePane.v.update();
		GamePane.player.setX(View.getFromRationX(rationX));
		GamePane.player.setY(View.getFromRationX(rationY));
	}
}

//TODO move Cursor from GamePane to View