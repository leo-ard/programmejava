package game.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Random;

public class Map {
	
	public static Random randomNum;
	public java.util.Map<Dimension, Chunck> map;
	public static Point selectedBlock;
	
	private boolean isChangingChunck = false;
	private int oldX = 0;
	private int oldY = 0;
	
	
	long seed;
	
	public Map(long seed){
		this.seed = seed;
		randomNum = new Random(seed);
		
		selectedBlock = new Point();
		map = new HashMap<Dimension,Chunck>();
	}
	
	public void update(){
		if(this.isChangingChunck()){
			Chunck c = this.getByPixel(View.x, View.y);
			Generate(4, c.getX(), c.getY());
		}
		
	}
	
	public void draw(Graphics2D g, int x1, int y1){
		int rayon = 1;
		
		
		for(int i = -rayon+x1; i <= rayon+x1; i++){
			for(int j = -rayon+y1; j <= rayon+y1; j++){
				Chunck c = this.getByPosition(i, j);
				c.draw(g, View.blockPixelWidth*View.chuncksBlockWidth*c.getX(), (int)(View.blockPixelHeight*View.chuncksBlockWidth*0.75*c.getY()));
			}
		}
	}
	
	public void firstGenerate(int rayon){
		for(int i = -rayon; i <= rayon; i++){
			for(int j = -rayon; j <= rayon; j++){
				this.add(new Chunck(j, i, Biome.PLAINES1), j, i);
				this.getByPosition(j, i).generate();
			}
		}
	}
	
	/**
	 * 
	 * Generates the map
	 * 
	 * @param rayon
	 * @param x of the chunck for the middle of the generation
	 * @param y of the chunck for the middle of the generation
	 */
	public void Generate(int rayon, int x, int y){
		for(int i = -rayon+x; i <= rayon+x; i++){
			for(int j = -rayon+y; j <= rayon+y; j++){
				if(this.getByPosition(i, j) == null){
					this.add(new Chunck(i, j, Biome.PLAINES1), i, j);
					this.getByPosition(i, j).generate();
					
				}
				
			}
		}
	}
	
	public void add(Chunck c, int x, int y){
		map.put(new Dimension(x, y), c);
	}
	
	/**
	 * @param x
	 * @param y
	 * @return The chunck at the position x, y
	 */
	public Chunck getByPosition(int x, int y){
		return (Chunck) map.get(new Dimension(x, y));
	}
	
	public Block getBlockByPosition(int x, int y){
		 // Find the row and column of the box that the point falls in.
	    int row = (int) (y / (View.blockPixelHeight*.75));
	    int column;

	    boolean rowIsOdd = row < 0?(-row %2 == 0):(row % 2 == 1);
	    

	    // Is the row an odd number?
	    if (rowIsOdd)// Yes: Offset x to match the indent of the row
	        column = (int) ((x - View.blockPixelWidth/2) / View.blockPixelWidth);
	    else// No: Calculate normally
	        column = (int) (x / View.blockPixelWidth);
	    
	    boolean IsXM1;
	    /*if(x > -View.blockPixelWidth)*/
	    
	    double relY = y - (row * View.blockPixelHeight*75);
	    double relX;

	    if (rowIsOdd)
	        relX = (x - (column * View.blockPixelWidth)) - View.blockPixelWidth/2;
	    else
	        relX = x - (column * View.blockPixelWidth);
	    
	 // Work out if the point is above either of the hexagon's top edges 
	    return this.getByPosition(column<0?(column/24)-1:column/24, row<0?(row/24)-1:row/24).getBlocks()[3][column<0?(23-(column*-1%24)):column%24][row<0?23-(-row%24):row%24];
	    
	}
	
	/**	 * @param d
	 * @return the Chunck at the pixel x, y
	 */
	public Chunck getByPixel(int Width, int Height){
		int x = 0;
		int y = 0;
		
		if(Width < 0){
			x = Width/(View.chuncksBlockWidth*View.blockPixelWidth);
		}
		else if(Width > 0){
			x = Width/(View.chuncksBlockWidth*View.blockPixelWidth);
			x++;
		}
		
		if(Height < 0){
			y = (int) (Height/(View.chuncksBlockWidth*View.blockPixelHeight*.75));
		}
		else if(Height > 0){
			y = (int) (Height/(View.chuncksBlockWidth*View.blockPixelHeight*.75));
			y++;
		}
		
		return (Chunck) map.get(new Dimension(-x, -y));
	}

	public Random getRandomNum() {
		return randomNum;
	}

	public boolean isChangingChunck() {
		Chunck c = this.getByPixel(View.x, View.y);
		
		if(oldX != c.getX() || oldY != c.getY()){
			this.setChangingChunck(true);
			System.out.println(true);
			oldX = c.getX();
			oldY = c.getY();
		}
		else{
			this.setChangingChunck(false);
		}
		
		return isChangingChunck;
	}

	private void setChangingChunck(boolean isChangingChunck) {
		this.isChangingChunck = isChangingChunck;
	}

}
