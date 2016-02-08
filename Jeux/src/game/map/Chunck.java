package game.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import game.core.GamePane;

/**
 * Classe de block de 24 par 24. 
 * 
 * 
 * @author Léonard
 *
 */
public class Chunck {
	
	
	private boolean isDisable;
	private boolean isGenerated;
	
	private Block[][][] blocks;
	private Structure structure;
	private Biome biome;
	
	private int x, y;
	
	public Chunck(int x, int y, Biome b){
		this.x = x;
		this.y = y;
		blocks = new Block[5][View.chuncksBlockWidth][View.chuncksBlockWidth];
	}
	
	public void update(boolean isDisable){
		this.isDisable = isDisable;
	}
	
	public void draw(Graphics2D g, int x, int y){	
		int x1 = 0;
		int y1 = 0;
		for(int i = 0; i < View.chuncksBlockWidth; i++){
			for(int j = 0; j < View.chuncksBlockWidth; j+=2){
				blocks[GamePane.curentLevel][i][j].draw(g, x+x1, y+y1);
				y1 += View.blockPixelHeight*1.5;
			}
			x1 += View.blockPixelWidth;
			y1 = 0;
		}
		
		x1 =  View.blockPixelWidth/2;
		y1 =  (int)(View.blockPixelHeight*.75);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 52, 52));
		g.drawString(this.getX()+" "+this.getY(), x, (int)(y));
		
		for(int i = 0; i < View.chuncksBlockWidth; i++){
			for(int j = 1; j < View.chuncksBlockWidth; j+=2){
				blocks[GamePane.curentLevel][i][j].draw(g, x+x1, (int)(y+y1));
				y1 += View.blockPixelHeight*1.5;
			}
			x1 += View.blockPixelWidth;
			y1 = (int)(View.blockPixelHeight*.75);
		}
		
			
		
	}
	
	public void generate(){
		
		int b = Map.randomNum.nextInt(Biome.nbBiome);
		
		this.biome = Biome.getBiomeById(b+1);
		
		for(int h = 0; h < 5; h++){
			for(int i = 0; i < View.chuncksBlockWidth; i++){
				for(int j = 0; j < View.chuncksBlockWidth; j++){
					
					this.blocks[h][i][j] = new Block(this.biome.getRandomBlock(h));
					this.blocks[h][i][j].generate();
					this.blocks[h][i][j].setXAndY(this.x*View.chuncksBlockWidth+i,this.y*View.chuncksBlockWidth+j);
					
				}
			}
		}
		
	}

	public boolean isDisable() {
		return isDisable;
	}

	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	public boolean isGenerated() {
		return isGenerated;
	}

	public Block[][][] getBlocks() {
		return blocks;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
