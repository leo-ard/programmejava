package game.map;

import java.awt.Color;
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
	
	private Block[][] blocks;
	private Structure structure;
	
	private int x, y;
	
	public Chunck(int x, int y){
		this.x = x;
		this.y = y;
		blocks = new Block[Map.chuncksBlockWidth][Map.chuncksBlockWidth];
	}
	
	public void update(boolean isDisable){
		this.isDisable = isDisable;
	}
	
	public void draw(Graphics2D g, int x, int y){
		int x1 = 0;
		int y1 = 0;
		
		for(int i = 0; i < Map.chuncksBlockWidth; i++){
			for(int j = 0; j < Map.chuncksBlockWidth; j+=2){
				blocks[i][j].draw(g, x+x1, y+y1);
				y1 += Map.blockPixelHeight*1.5;
			}
			x1 += Map.blockPixelWidth;
			y1 = 0;
		}
		
		x1 =  Map.blockPixelWidth/2;
		y1 =  (int)(Map.blockPixelHeight*.75);
		
		for(int i = 1; i < Map.chuncksBlockWidth; i++){
			for(int j = 0; j < Map.chuncksBlockWidth; j+=2){
				/*g.setColor(Color.black);
				g.drawString(i+" "+j, x+x1, (int)(y+y1));*/
				blocks[i][j].draw(g, x+x1, (int)(y+y1));
				y1 += Map.blockPixelHeight*1.5;
			}
			x1 += Map.blockPixelWidth;
			y1 = (int)(Map.blockPixelHeight*.75);
		}
		
			
		
	}
	
	public void generate(){
		for(int i = 0; i < Map.chuncksBlockWidth; i++){
			for(int j = 0; j < Map.chuncksBlockWidth; j++){
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
	

}
