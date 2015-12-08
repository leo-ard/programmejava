package game.map;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Classe de block de 16 par 16. 
 * 
 * 
 * @author Léonard
 *
 */
public class Chunck {
	private boolean isDisable;
	
	private ArrayList<ArrayList<Block>> blocks = new ArrayList<ArrayList<Block>>(16);
	private Structure structure;
	
	private int x, y;
	
	public Chunck(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(boolean isDisable){
		this.isDisable = isDisable;
	}
	
	public void draw(Graphics2D g){
		
	}
	
	public void generate(){
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				blocks.get(i).add(Map.randomNum.nextBoolean()?new Block():new Block());
			}
		}
		Map.randomNum.nextBoolean();
	}
	

}
