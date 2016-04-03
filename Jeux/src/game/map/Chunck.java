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
	private int generateNumber;
	private boolean firstBlock;
	
	private int x, y;
	
	public Chunck(int x, int y){
		this.x = x;
		this.y = y;
		firstBlock = true;
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
	
	public void generate(Biome b){
		if(this.isGenerated == true){return;};
		//int b = Map.randomNum.nextInt(Biome.nbBiome);
		
		//this.biome = b; //Biome.getBiomeById(b+1);
		this.isGenerated = true;
		for(int h = 0; h < 5; h++){
			for(int i = 0; i < View.chuncksBlockWidth; i++){
				for(int j = 0; j < View.chuncksBlockWidth; j++){
					this.blocks[h][i][j] = new Block(this.x*View.chuncksBlockWidth+i,this.y*View.chuncksBlockWidth+j);
					Block maybeBlock = new Block();
					int maybeGen = 0;
					try{
					if(this.blocks[h][i][j].getBasDroit().getGenerateNumber()> maybeGen){
						maybeGen = this.blocks[h][i][j].getBasDroit().getGenerateNumber();
						maybeBlock = this.blocks[h][i][j].getBasDroit();
					}
					}catch(NullPointerException e){}
					try{
					if(this.blocks[h][i][j].getHautDroit().getGenerateNumber() > maybeGen){
						maybeGen = this.blocks[h][i][j].getBasDroit().getGenerateNumber();
						maybeBlock = this.blocks[h][i][j].getBasDroit();
					}
					}catch(NullPointerException e){}
					try{
					if(this.blocks[h][i][j].getBasGauche().getGenerateNumber() > maybeGen){
						maybeGen = this.blocks[h][i][j].getBasDroit().getGenerateNumber();
						maybeBlock = this.blocks[h][i][j].getHautDroit();
					}
					}catch(NullPointerException e){}
					try{
					if(this.blocks[h][i][j].getHautGauche().getGenerateNumber() > maybeGen){
						maybeGen = this.blocks[h][i][j].getBasDroit().getGenerateNumber();
						maybeBlock = this.blocks[h][i][j].getHautGauche();
					}
					}catch(NullPointerException e){}
					try{
					if(this.blocks[h][i][j].getDroit().getGenerateNumber() > maybeGen){
						maybeGen = this.blocks[h][i][j].getBasDroit().getGenerateNumber();
						maybeBlock = this.blocks[h][i][j].getDroit();
					}
					}catch(NullPointerException e){}
					try{
					if(this.blocks[h][i][j].getGauche().getGenerateNumber() > maybeGen){
						maybeGen = this.blocks[h][i][j].getBasDroit().getGenerateNumber();
						maybeBlock = this.blocks[h][i][j].getGauche();
					}
					}catch(NullPointerException e){}
					
					if(maybeGen == 0||!maybeBlock.exist){
						this.blocks[h][i][j] = new Block(Block.getRandomBlock(), this.x*View.chuncksBlockWidth+i,this.y*View.chuncksBlockWidth+j);
						this.blocks[h][i][j].setGenerateNumber(150);
					}
					else{
						System.out.println("hi");
						this.blocks[h][i][j] = maybeBlock.getBiome().getRandomBlock(2);
					}
					
					
					/*try{
						//if(h == 2)
							//System.out.println(this.blocks[h][i-1][j].getId());
					}catch(Exception e){}*/
					
					
					
					//System.out.println(this.blocks[h][i][j].getId());
					
				}
			}
		}
		
	}
	
	/*public void arondit(){
		
			int x = 0;
			int y = 0;
			try{
				if(this.biome.getId() == GamePane.map.getChunckByPosition(this.x, this.y+1).getBiome().getId()&&GamePane.map.getChunckByPosition(this.x, this.y+1).getBiome().getId()!=0){
					System.out.println("1 "+GamePane.map.getChunckByPosition(this.x, this.y+1).getX()+":"+GamePane.map.getChunckByPosition(this.x, this.y+1).getY());
					for(int i = 0; i < View.chuncksBlockWidth; i++){
						x = this.blocks[2][i][0].getX();
						y = this.blocks[2][i][0].getY();
						this.blocks[2][i][0] = new Block(GamePane.map.getChunckByPosition(this.x, this.y+1).getBiome().getRandomBlock(4));
						this.blocks[2][i][0].setXAndY(x, y);
					}
				}
			}catch(NullPointerException e){}
			try{
				if(this.biome.getId() == GamePane.map.getChunckByPosition(this.x, this.y-1).getBiome().getId()&&GamePane.map.getChunckByPosition(this.x, this.y-1).getBiome().getId()!=0){
					//System.out.println("yah2");
					for(int i = 0; i < View.chuncksBlockWidth; i++){
						x = this.blocks[2][i][23].getX();
						y = this.blocks[2][i][23].getY();
						this.blocks[2][i][23] = new Block(GamePane.map.getChunckByPosition(this.x, this.y-1).getBiome().getRandomBlock(4));
						this.blocks[2][i][23].setXAndY(x, y);
					}
				}
			}catch(NullPointerException e){}
			try{
				if(this.biome.getId() == GamePane.map.getChunckByPosition(this.x+1, this.y).getBiome().getId()&&GamePane.map.getChunckByPosition(this.x+1, this.y).getBiome().getId()!=0){
					//System.out.println("yah3");
					for(int i = 0; i < View.chuncksBlockWidth; i++){
						x = this.blocks[2][0][i].getX();
						y = this.blocks[2][0][i].getY();
						this.blocks[2][0][i] = new Block(GamePane.map.getChunckByPosition(this.x+1, this.y).getBiome().getRandomBlock(4));
						this.blocks[2][0][i].setXAndY(x, y);
					}
				}
			}catch(NullPointerException e){}
			try{
				if(this.biome.getId() == GamePane.map.getChunckByPosition(this.x-1, this.y).getBiome().getId()&&GamePane.map.getChunckByPosition(this.x-1, this.y+1).getBiome().getId()!=0){
					//System.out.println("yah4");
					for(int i = 0; i < View.chuncksBlockWidth; i++){
						x = this.blocks[2][23][i].getX();
						y = this.blocks[2][23][i].getY();
						this.blocks[2][23][i] = new Block(GamePane.map.getChunckByPosition(this.x-1, this.y).getBiome().getRandomBlock(4));
						this.blocks[2][23][i].setXAndY(x, y);
					}
				}
			}catch(NullPointerException e){}
		
	}*/

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

	public Biome getBiome() {
		return biome;
	}

	public void setBiome(Biome biome) {
		this.biome = biome;
	}

	public int getGenerateNumber() {
		return generateNumber;
	}

	public void setGenerateNumber(int generateNumber) {
		this.generateNumber = generateNumber;
	}
}
