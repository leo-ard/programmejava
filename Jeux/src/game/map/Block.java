package game.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;

import game.core.GamePane;

public class Block {
	
	public static Block VOID0 = new Block(0, "", false, false);
	public static Block GRASS1 = new Block(1, "Grass", false, true);
	public static Block WATER2 = new Block(2, "Water", false, true);
	public static Block DIRT3 = new Block(3, "Dirt", false, true);
	public static Block STONE4 = new Block(4, "Stone", false, true);
	
	public static Block getById(int id){
		switch(id){
		case 0: return VOID0;
		case 1: return GRASS1;
		case 2: return WATER2;
		case 3: return DIRT3;
		case 4: return STONE4;
		default: System.out.println("--------------------------------\n====ERREUR Block introuvable====\n--------------------------------"); return VOID0;
		}
	}
	
	private int id;
	private String name;
	private boolean isSolid;
	private boolean isTransparent;
	private boolean isSelected; 
	private int x, y;
	
	
	/**
	 * 
	 * 
	 * @author Léonard
	 */
	public Block(){
		
	}
	
	public Block(int id, String name, boolean isSolid, boolean isTransparent){
		this.id = id;
		this.name = name;
		this.isSolid = isSolid;
		this.isTransparent = isTransparent;
	}
	
	public Block(Block b){
		this.id = b.id;
		this.name = b.name;
		this.isSolid = b.isSolid;
		this.isTransparent = b.isTransparent;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g, int x, int y){
		if(x > -View.x-View.blockPixelWidth&&x < -View.x+GamePane.WIDTH &&y > -View.y-View.blockPixelHeight&&y < -View.y+GamePane.HEIGHT){
			g.drawImage(GamePane.texturesBlock[id], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
			
			
		}
		if(this.x == Map.selectedBlock.getX()&&this.y == Map.selectedBlock.getY() && !GamePane.player.isRunning()){
			g.drawImage(GamePane.texturesGUI[0], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
		}
		
		//----SHOW POSITION----///
		g.setFont(new Font("Arial", 20, 20));
		g.setColor(Color.BLACK);
		
		g.drawString(this.x+" "+this.y, x+View.blockPixelWidth/2, y+View.blockPixelHeight/2);
	}
	
	public Block getHautDroit(){
		if(this.y%2 == 0||this.y == 0){
			return GamePane.map.getBlockByPosition(x, y-1);
		}
		else{
			return GamePane.map.getBlockByPosition(x+1, y-1);
		}
	}
	public Block getBasDroit(){
		if(this.y%2 == 0||this.y == 0){
			return GamePane.map.getBlockByPosition(x, y+1);
		}
		else{
			return GamePane.map.getBlockByPosition(x+1, y+1);
		}
		
	}
	public Block getHautGauche(){
		if(this.y%2 == 0||this.y == 0){
			return GamePane.map.getBlockByPosition(x-1, y-1);
		}
		else{
			return GamePane.map.getBlockByPosition(x, y-1);
		}
		
	}
	public Block getBasGauche(){
		if(this.y%2 == 0||this.y == 0){
			return GamePane.map.getBlockByPosition(x-1, y+1);
		}
		else{
			return GamePane.map.getBlockByPosition(x, y+1);
		}
		
	}
	public Block getDroit(){
		return GamePane.map.getBlockByPosition(x+1, y);
		
	}
	public Block getGauche(){
		return GamePane.map.getBlockByPosition(x-1, y);
	}
	
	
	//----Getters and seeters ----//

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public boolean isTransparent() {
		return isTransparent;
	}

	public void setTransparent(boolean isTransparent) {
		this.isTransparent = isTransparent;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void setXAndY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
