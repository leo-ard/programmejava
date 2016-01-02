package game.map;

import java.awt.Graphics2D;

import game.core.GamePane;

public class Block {
	
	public static Block GRASS0 = new Block(0, "Grass", false, true, "grass.png");
	public static Block GRASS1 = new Block(1, "Grass 2", false, true, "grass2.png");
	
	private int id;
	private String name;
	private boolean isSolid;
	private boolean isTransparent;
	private String textureName;
	
	public Block(int id, String name, boolean isSolid, boolean isTransparent, String textureName){
		this.id = id;
		this.name = name;
		this.isSolid = isSolid;
		this.isTransparent = isTransparent;
		this.textureName = textureName;
	}
	
	public Block(Block b){
		this.id = b.id;
		this.name = b.name;
		this.isSolid = b.isSolid;
		this.isTransparent = b.isTransparent;
		this.textureName = b.textureName;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g, int x, int y){
		//System.out.println(x+" "+y);
		if(x > -View.blockPixelWidth&&x < GamePane.WIDTH &&y > -View.blockPixelHeight&&y < GamePane.HEIGHT){
			g.drawImage(GamePane.texturesBlock[id], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
		}
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

	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}

}
