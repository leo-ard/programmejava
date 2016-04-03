package game.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import game.core.GamePane;

public class Block {
	
	public static Block VOID0 = new Block(0, "", false, false, 0);
	public static Block GRASS1 = new Block(1, "Grass", false, true, 100);
	public static Block WATER2 = new Block(2, "Water", false, true, 100000);
	public static Block DIRT3 = new Block(3, "Dirt", false, true, 100);
	public static Block STONE4 = new Block(4, "Stone", false, true, 10);
	
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
	
	private static int nbBlocks = 4;
	
	public static Block getRandomBlock(){
		int b = (int)(1+(Math.random()*nbBlocks));
		return Block.getById(b==3?1:b);
	}
	
	private int id;
	private String name;
	private boolean isSolid;
	private boolean isTransparent;
	private boolean isSelected; 
	private int x, y;
	private int GenerateNumber;
	private Biome b;
	private boolean hasEdges;
	private int durability;
	public boolean exist;
	
	
	/**
	 * Generate the block
	 * 
	 * @author Léonard
	 */
	public Block(){
		exist = false;
		this.GenerateNumber = 0;
	}
	
	public Block(int id, String name, boolean isSolid, boolean isTransparent, int durabi){
		this.id = id;
		this.name = name;
		this.isSolid = isSolid;
		this.isTransparent = isTransparent;
		this.exist = true;
		this.GenerateNumber = 0;
		this.durability = durabi;
	}
	
	public Block(int x, int y){
		exist = false;
		this.GenerateNumber = 0;
		this.x = x;
		this.y = y;
	}
	
	public Block(Block b, int x, int y){
		this.change(b);
		this.x = x;
		this.y = y;
		this.GenerateNumber = 0;
	}
	
	public void generate(Biome b){
		/*
		Block blo = new Block();
		Block maybeBlock = new Block();
		int maybeGen = 0;
		
		for(int i = 0; i < 6; i ++){
			try{
				switch(i){
				case 0: blo = this.getBasDroit();
				case 1: blo = this.getBasGauche();
				case 2: blo = this.getHautDroit();
				case 3: blo = this.getHautGauche();
				case 4: blo = this.getGauche();
				case 5: blo = this.getDroit();
				}
				
			}catch(NullPointerException e ){}
			
			if(blo != null){
				if(maybeGen < blo.getGenerateNumber()){
					maybeGen = blo.getGenerateNumber();
					maybeBlock = blo;
				}
			}
		}

		if(maybeBlock != null){
			this.GenerateNumber = 150;
			this.b = Biome.getBiomeById(Map.randomNum.nextInt(Biome.nbBiome+1));
			this.change(this.b.getRandomBlock(2));
		}
		else{
			this.GenerateNumber = maybeGen-1;
			this.b = blo.getB();
			this.change(this.b.getRandomBlock(2));
		}
		
		/*
		for(int i = 0; i < a.size();i++){
			try{
				if(a.get(i).GenerateNumber > this.GenerateNumber){
					System.out.println(this.GenerateNumber);
					this.GenerateNumber = a.get(i).GenerateNumber-1;
					this.b = a.get(i).b;
				}
			}catch(NullPointerException e){};
		}
		if(this.b == null){
			this.GenerateNumber = 150;
			this.b = Biome.getBiomeById(Map.randomNum.nextInt(Biome.nbBiome+1));
		}
		
		this.change(this.b.getRandomBlock(2));*/
		
	}
	
	public void change(Block b){
		this.id = b.id;
		this.name = b.name;
		this.isSolid = b.isSolid;
		this.isTransparent = b.isTransparent;
		this.durability = b.durability;
	}
	
	public void destroy(){
		durability--;
		if(durability < 0){
			this.erase();
		}
	}
	
	public void erase(){
		this.id = 0;
		this.name = "void";
		this.isSolid = false;
		this.isTransparent = true;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g, int x, int y){
		if(x > -View.x-View.blockPixelWidth&&x < -View.x+GamePane.WIDTH &&y > -View.y-View.blockPixelHeight&&y < -View.y+GamePane.HEIGHT){
			//System.out.println(this.getId());
			g.drawImage(GamePane.texturesBlock[id], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
			
			
		}
		if(this.x == Map.selectedBlock.getX()&&this.y == Map.selectedBlock.getY() && !GamePane.player.isRunning()){
			g.drawImage(GamePane.texturesGUI[0], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
		}
		
		//----SHOW POSITION----///
		g.setFont(new Font("Arial", 20, 20));
		g.setColor(Color.BLACK);
		
		g.drawString(this.x+":"+this.y+" "+this.GenerateNumber, x, y+View.blockPixelHeight/2);
	}
	
	public boolean isAtEdge(){
		if(x%24 == 0||x%24==-1||y%24==0||y%24==-1||x%24==23||y%24==23){
			return true;
		}
		return false;
	}
	
	/**
	 * returns
	 * 1 for haut
	 * 2 for bas
	 * 3 for doite
	 * 4 for gauche
	 * 13 for haut droit
	 * 14 for haut gauche
	 * 23 for bas droit
	 * 24 for bas gauche
	 * 0 for nothing
	 * 
	 * @return 
	 */
	/*public byte getEdge(){
		if(this.isAtEdge() == false){
			return 0;
		}
		
		if(x%24 == 0){
			if(y%24 == 0){
				return 14;
			}
			else if(y%24 == -1||y%24 == 23){
				return 24;
			}
			else
				return 4;
		}
		else if(x%24 == -1||x%24==23){
			if(y%24 == 0){
				return 13;
			}
			else if(y%24 == -1||y%24 == 23){
				return 23;
			}
			else
				return 3;
		}
		else if(y%24 == 0){
			return 1;
		}
		else if(y%24 == -1||y%24==23){
			return 2;
		}
		
		return 0;
	}*/
	
	public void firstBlock(Biome b2) {
		System.out.println(this.x+" "+this.y);
	}
	
	public ArrayList<Block> getEdges(){
		ArrayList<Block> a = new ArrayList<Block>();
		int nb = 0;
		for(int i = 0; i < 6; i++){
			Block bl = null;
			if(i == 0)
				bl = this.getHautDroit();
			
			if(i == 1)
				bl = this.getHautGauche();
				
			if(i == 2)
				bl = this.getBasDroit();
				
			if(i == 3)
				bl = this.getBasGauche();
						
			if(i == 4)
				bl = this.getDroit();
							
			if(i == 5)
				bl = this.getGauche();
			
			//try{
				if(bl != null&&bl.id != 0){
					a.add(bl);
				}
				else
					nb++;
			//}catch(NullPointerException e){}
			
		}
		if(nb == 6)
			this.hasEdges = false;
		else
			this.hasEdges = true;
		
		return a;
	}
	
	public Block getHautDroit(){
		try{
			if(this.y%2 == 0||this.y == 0){
				return GamePane.map.getBlockByPosition(x, y-1);
			}
			else{
				return GamePane.map.getBlockByPosition(x+1, y-1);
			}
		}catch(NullPointerException e){return new Block();}
	}
	public Block getBasDroit(){
		
		try{
			if(this.y%2 == 0||this.y == 0){
				return GamePane.map.getBlockByPosition(x, y+1);
			}
			else{
				return GamePane.map.getBlockByPosition(x+1, y+1);
			}
		}catch(NullPointerException e){e.printStackTrace();return new Block();}
	}
	public Block getHautGauche(){
		try{
			if(this.y%2 == 0||this.y == 0){
				return GamePane.map.getBlockByPosition(x-1, y-1);
			}
			else{
				return GamePane.map.getBlockByPosition(x, y-1);
			}
		}catch(NullPointerException e){return new Block();}
		
	}
	public Block getBasGauche(){
		try{
			if(this.y%2 == 0||this.y == 0){
				return GamePane.map.getBlockByPosition(x-1, y+1);
			}
			else{
				return GamePane.map.getBlockByPosition(x, y+1);
			}
		}catch(NullPointerException e){return new Block();}
		
	}
	public Block getDroit(){
		try{
			return GamePane.map.getBlockByPosition(x+1, y);
		}catch(NullPointerException e){return new Block();}
		
	}
	public Block getGauche(){
		//try{
			return GamePane.map.getBlockByPosition(x-1, y);
		//}catch(NullPointerException e){return new Block();}
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
	
	public int getAbsoluteX(){
		if(this.x < 0){
			return -x;
		}
		return x;
	}
	
	public int getAbsoluteY(){
		if(this.y < 0){
			return -y;
		}
		return y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getGenerateNumber() {
		try{
		return GenerateNumber;
		}
		catch(NullPointerException e){
			return 0;
		}
	}

	public void setGenerateNumber(int generateNumber) {
		GenerateNumber = generateNumber;
	}

	public Biome getBiome() {
		return b;
	}

	public void setBiome(Biome b) {
		this.b = b;
	}

	

}
