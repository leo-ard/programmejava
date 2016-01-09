package game.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Random;

public class Map {
	
	public static Random randomNum;
	java.util.Map<Dimension, Chunck> map;
	
	private boolean isChangingChunck = false;
	private int oldX = 0;
	private int oldY = 0;
	
	long seed;
	
	public Map(long seed, int max){
		this.seed = seed;
		randomNum = new Random(seed);
		
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
				this.add(new Chunck(j, i), j, i);
				this.getByPosition(j, i).generate();
			}
			System.out.println( );
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
		//TODO a coriger
		for(int i = -rayon+x; i <= rayon+x; i++){
			for(int j = -rayon+y; j <= rayon+y; j++){
				if(this.getByPosition(i, j) == null){
					this.add(new Chunck(i, j), i, j);
					this.getByPosition(i, j).generate();
					
				}
				
			}
		}
	}
	
	public void add(Chunck c, int x, int y){
		map.put(new Dimension(x, y), c);
		System.out.print(x+":"+y+" ");
	}
	
	/**
	 * @param x
	 * @param y
	 * @return The chunck at the position x, y
	 */
	public Chunck getByPosition(int x, int y){
		return (Chunck) map.get(new Dimension(x, y));
	}
	
	/**
	 * 
	 * @param d
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
