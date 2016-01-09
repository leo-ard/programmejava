package game.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

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
	
	private Block[][] blocks;
	private Structure structure;
	
	private int x, y;
	
	public Chunck(int x, int y){
		this.x = x;
		this.y = y;
		blocks = new Block[View.chuncksBlockWidth][View.chuncksBlockWidth];
	}
	
	public void update(boolean isDisable){
		this.isDisable = isDisable;
	}
	
	public void draw(Graphics2D g, int x, int y){	
		int x1 = 0;
		int y1 = 0;
		for(int i = 0; i < View.chuncksBlockWidth; i++){
			for(int j = 0; j < View.chuncksBlockWidth; j+=2){
				blocks[i][j].draw(g, x+x1, y+y1);
				y1 += View.blockPixelHeight*1.5;
			}
			x1 += View.blockPixelWidth;
			y1 = 0;
		}
		
		x1 =  View.blockPixelWidth/2;
		y1 =  (int)(View.blockPixelHeight*.75);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial", 52, 52));
		g.drawString(this.getX()+" "+this.getY(), x+x1, (int)(y+y1));
		
		for(int i = 0; i < View.chuncksBlockWidth; i++){
			for(int j = 0; j < View.chuncksBlockWidth; j+=2){
				
				blocks[i][j].draw(g, x+x1, (int)(y+y1));
				y1 += View.blockPixelHeight*1.5;
			}
			x1 += View.blockPixelWidth;
			y1 = (int)(View.blockPixelHeight*.75);
		}
		
			
		
	}
	
	public void generate(){
		for(int i = 0; i < View.chuncksBlockWidth; i++){
			for(int j = 0; j < View.chuncksBlockWidth; j++){
				if(Map.randomNum.nextBoolean()){
					blocks[i][j] = new Block(Block.GRASS0);
				}
				else{
					blocks[i][j] = new Block(Block.GRASS1);
				}
				
			}
		}
		Map.randomNum.nextBoolean();
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

	public Block[][] getBlocks() {
		return blocks;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	

}
