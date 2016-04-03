package game.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import game.core.GamePane;
import game.core.Listener;

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
			System.out.println("Hi");
			Chunck c = this.getChunckByPixel(View.x, View.y);
			Generate(4, c.getX(), c.getY());
		}
		
		if(Listener.F1){
			System.out.println("  "+this.getSelectedBlock().getHautGauche().getX()+":"+this.getSelectedBlock().getHautGauche().getY()+" "+this.getSelectedBlock().getHautDroit().getX()+":"+this.getSelectedBlock().getHautDroit().getY());
			System.out.println(this.getSelectedBlock().getGauche().getX()+":"+this.getSelectedBlock().getGauche().getY()+" "+this.getSelectedBlock().getX()+":"+this.getSelectedBlock().getY()+" "+this.getSelectedBlock().getDroit().getX()+":"+this.getSelectedBlock().getDroit().getY());
			System.out.println(" " +this.getSelectedBlock().getBasGauche().getX()+":"+this.getSelectedBlock().getBasGauche().getY()+" "+this.getSelectedBlock().getBasDroit().getX()+":"+this.getSelectedBlock().getBasDroit().getY());
		}
		
	}
	
	public void draw(Graphics2D g, int x1, int y1){
		int rayon = 1;
		
		
		for(int i = -rayon+x1; i <= rayon+x1; i++){
			for(int j = -rayon+y1; j <= rayon+y1; j++){
				Chunck c = this.getChunckByPosition(i, j);
				c.draw(g, View.blockPixelWidth*View.chuncksBlockWidth*c.getX(), (int)(View.blockPixelHeight*View.chuncksBlockWidth*0.75*c.getY()));
			}
		}
	}
	
	/*public void firstGenerate(int rayon){
		for(int i = -rayon; i <= rayon; i++){
			for(int j = -rayon; j <= rayon; j++){
				this.add(new Chunck(i, j), i, j);
				this.getChunckByPosition(i, j).generate();
			}
		}
		
		/*for(int i = -rayon; i <= rayon; i++){
			for(int j = -rayon; j <= rayon; j++){
				this.getChunckByPosition(i, j).arondit();
			}
		}
	}*/
	
	/**
	 * 
	 * Generates the map
	 * 
	 * @param rayon
	 * @param x of the chunck for the middle of the generation
	 * @param y of the chunck for the middle of the generation
	 */
	public void Generate(int rayon, int x, int y){
		//ArrayList<Chunck> b = new ArrayList<Chunck>();
		for(int i = -rayon+x; i <= rayon+x; i++){
			for(int j = -rayon+y; j <= rayon+y; j++){
				if(this.getChunckByPosition(i, j) == null){
					this.add(new Chunck(i, j), i, j);
					this.getChunckByPosition(i, j).generate(null);
				}
				
				/*if(this.getChunckByPosition(i, j) == null){
					
					
					b = findEdges(i,j);
					if(b.size() == 0){
						
						this.getChunckByPosition(i, j).generate(Biome.PLAINES1, 1);			
					}
					else{
						int generated = 4;
						int id = 0;
						for(int k = 0; k < b.size(); k++){
							
							if(b.get(k).getGenerateNumber() < generated){
								id = k;
								generated = b.get(k).getGenerateNumber();
							}
						}
						System.out.println(generated);
						if(generated < 4){
							this.getChunckByPosition(i, j).generate(b.get(id).getBiome(), generated+1);
						}
						else{
							this.getChunckByPosition(i, j).generate(Biome.getBiomeById(Map.randomNum.nextInt(Biome.nbBiome)+1), 1);
						}
							
					}
				}*/
			}
		}
		
		/*for(int i = -rayon+x; i <= rayon+x; i++){
			for(int j = -rayon+y; j <= rayon+y; j++){
					this.getChunckByPosition(i, j).arondit();
				
			}
		}*/
		
	}
	
	public ArrayList<Chunck> findEdges(int x, int y){
		ArrayList<Chunck> b = new ArrayList<Chunck>();
		
		if(this.getChunckByPosition(x+1, y) != null)b.add(this.getChunckByPosition(x+1, y));
		if(this.getChunckByPosition(x-1, y) != null)b.add(this.getChunckByPosition(x-1, y));
		if(this.getChunckByPosition(x, y+1) != null)b.add(this.getChunckByPosition(x, y+1));
		if(this.getChunckByPosition(x, y-1) != null)b.add(this.getChunckByPosition(x, y-1));
		
		return b;
	}
	
	public void add(Chunck c, int x, int y){
		map.put(new Dimension(x, y), c);
	}
	
	/**
	 * @param x
	 * @param y
	 * @return The chunck at the position x, y
	 */
	public Chunck getChunckByPosition(int x, int y){
		try{
			return (Chunck) map.get(new Dimension(x, y));
		}
		catch(NullPointerException e){
			return null;
		}
	}
	
	public Block getBlockByPixel(int x, int y){
		 // Find the row and column of the box that the point falls in.
	    int row = (int) (y / (View.blockPixelHeight*.75));
	    int column;

	    boolean rowIsOdd = row < 0?(-row %2 == 0):(row % 2 == 1);
	    if(row == 0&&y < 0){rowIsOdd = true;}
	    

	    // Is the row an odd number?
	    if (rowIsOdd)// Yes: Offset x to match the indent of the row
	        column = (int) ((x - View.blockPixelWidth/2) / View.blockPixelWidth);
	    else// No: Calculate normally
	        column = (int) (x / View.blockPixelWidth);
	    
	    // Work out the position of the point relative to the box it is in
	    double relY = y - (row * View.blockPixelHeight*.75);
	    double relX;

	    if (rowIsOdd)
	        relX = (x - (column * View.blockPixelWidth)) - View.blockPixelWidth/2;
	    else
	        relX = x - (column * View.blockPixelWidth);
	    
	    if(relX < 0){
	    	relX = View.blockPixelWidth+relX;
	    }
	    if(relY < 0){
	    	relY = View.blockPixelHeight*.75+relY;
	    }
	    
	    //System.out.println(relX+":"+relY);
	    
	   /* double c = (((View.blockPixelHeight*.75)/3));
	    double m = (View.blockPixelHeight*.75-c)/(View.blockPixelWidth-View.blockPixelWidth/2);
	   
	    
	    System.out.println(m+" "+c);
	    
		if (relY < (-m * relX) + c) {// LEFT edge
			
			row--;
			if (!rowIsOdd)
				column--;
			
		} else if (relY < (m * relX) - c) { // RIGHT edge

			row--;
			if (rowIsOdd)
				column++;
		}*/

		
	    
	    //see if on the edge
	    
	    //int m = ()/();
	    
	 // Work out if the point is above either of the hexagon's top edges 
	    boolean bx = false;
		boolean by = false;
		if(x < 0){column--;bx = true;}
		if(y < 0){row--;by = true;}
		return getBlockByPosition(column, row);
	    //return this.getByPosition(column<0?(column/24)-1:column/24, row<0?(row/24)-1:row/24).getBlocks()[GamePane.curentLevel][column<0?(24-(column*-1%24)):column%24][row<0?24-(-row%24):row%24];
	    
	}
	
	public Block getBlockByPosition(int x, int y){
		boolean bx = false;
		boolean by = false;
		if(x < 0){x++;bx = true;}
		if(y < 0){y++;by = true;}
		try{
			Block b = map.get(new Dimension(x<0||bx?(x/24)-1:x/24,y<0||by?(y/24)-1:y/24)).getBlocks()[GamePane.curentLevel][x<0||bx?(23+((x)%24)):x%24][y<0||by?(23+(y%24)):y%24];
			return b;
		}
		catch(NullPointerException e){
			return new Block();
		}
		
	}
	
	/**	 * @param d
	 * @return the Chunck at the pixel x, y
	 */
	public Chunck getChunckByPixel(int Width, int Height){
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
	
	public Chunck getChunckByBlock(int x, int y){
		//System.out.println(x+" "+y);
		
		return map.get(new Dimension((int)(x<0?(x%24==0?x/24:x/24-1):x/24), (int)(y<0?(y%24==0?y/24:y/24-1):y/24)));
	}

	public Random getRandomNum() {
		return randomNum;
	}

	public boolean isChangingChunck() {
		Chunck c = this.getChunckByPixel(View.x, View.y);
		
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
	
	public Block getSelectedBlock(){
		return this.getBlockByPosition((int)(selectedBlock.getX()), ((int)selectedBlock.getY()));
	}

}
