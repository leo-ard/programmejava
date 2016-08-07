package game.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
//import java.util.Vector;

import game.core.GamePane;
import game.core.Main;

public class Block {
	
	public static int hauteur = 50;
	public static Block VOID0 = new Block(0, "", false, false);
	public static Block GRASS1 = new Block(1, "Grass", false, true);
	public static Block WATER2 = new Block(2, "Water", true, true);
	public static Block DIRT3 = new Block(3, "Dirt", false, true);
	public static Block STONE4 = new Block(4, "Stone", false, true);
	
	public static ArrayList<Block> toDrawAfter = new ArrayList<Block>();
	
	public static int tryingthings  = 200;
	
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
		int b = (int)(1+(Map.randomNum.nextInt(nbBlocks)));
		return Block.getById(b==3?1:b);
	}
	
	private int id;
	private String name;
	private boolean isSolid;
	private boolean isTransparent;
	private boolean isSelected; 
	private int x, y;
	private int GenerateNumber;
	public boolean exist;
	private int show;
	public boolean isPortal;
	private Portal portal;
	
	
	/**
	 * Generate the block
	 * 
	 * @author Léonard
	 */
	public Block(){
		exist = false;
		this.GenerateNumber = 0;
		this.isPortal = false;
	}
	
	public Block(int id, String name, boolean isSolid, boolean isTransparent){
		this.id = id;
		this.name = name;
		this.isSolid = isSolid;
		this.isTransparent = isTransparent;
		this.exist = true;
		this.GenerateNumber = 0;
		this.isPortal = false;
	}
	
	public Block(int x, int y){
		exist = false;
		this.GenerateNumber = 0;
		this.x = x;
		this.y = y;
		this.isPortal = false;
	}
	
	public Block(Block b, int x, int y){
		this.change(b);
		this.x = x;
		this.y = y;
		this.GenerateNumber = 0;
		this.isPortal = false;
	}
	
	public Block change(Block b){
		this.id = b.id;
		this.name = b.name;
		this.isSolid = b.isSolid;
		this.isTransparent = b.isTransparent;
		return this;
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
		if(!this.isSolid){
			if(x > -View.x-View.blockPixelWidth&&x < -View.x+GamePane.WIDTH &&y > -View.y-View.blockPixelHeight&&y < -View.y+GamePane.HEIGHT){
				//System.out.println(this.getId());
				g.drawImage(GamePane.texturesBlock[id], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
			}
			if(this.x == Map.selectedBlock.getX()&&this.y == Map.selectedBlock.getY() && !GamePane.player.isRunning()){
				g.drawImage(GamePane.texturesGUI[0], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
			}
			if(Map.isRedSelection&&this.x == Map.redSelectedBlock.getX()&&this.y == Map.redSelectedBlock.getY() && !GamePane.player.isRunning()){
				g.drawImage(GamePane.texturesGUI[1], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
			}
		}
		else{
			if(x > -View.x-View.blockPixelWidth&&x < -View.x+GamePane.WIDTH &&y > -View.y-View.blockPixelHeight&&y < -View.y+GamePane.HEIGHT){
				g.drawImage(GamePane.texturesBlock[id], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
				Block.toDrawAfter.add(this);
			}
			
		}
	
	}
	
	public void drawAfter(Graphics2D g){
		g.setColor(Color.darkGray);
		//Haut Gauche
		Point HG = this.getPoint(0, 0, this.hauteur);
		g.drawLine(this.x*View.blockPixelWidth, this.y*View.blockPixelHeight,(int)HG.getX(), (int)HG.getY());
		
		//Haut droite
		Point HD = this.getPoint(1, 0,this.hauteur);
		g.drawLine((this.x+1)*View.blockPixelWidth, this.y*View.blockPixelHeight,(int)HD.getX(), (int)HD.getY());
		
		
		//bas gauche
		Point BG = this.getPoint(1, 1,this.hauteur);
		g.drawLine((this.x+1)*View.blockPixelWidth, (this.y+1)*View.blockPixelHeight,(int)BG.getX(), (int)BG.getY());
		
		//bas droite
		Point BD = this.getPoint(0, 1,this.hauteur);
		g.drawLine(this.x*View.blockPixelWidth, (this.y+1)*View.blockPixelHeight,(int)BD.getX(), (int)BD.getY());
		
		Polygon p = new Polygon();
		/*p.addPoint((int)HG.getX(), (int)HG.getY());
		p.addPoint((int)HD.getX(), (int)HD.getY());
		p.addPoint((int)BG.getX(), (int)BG.getY());
		p.addPoint((int)BD.getX(), (int)BD.getY());*/
		
		//multiple de 4
		int nbDePoints = 64;
		double nbParLigne = nbDePoints/4;
		
		/*//----MUR BAS----//
		g.setColor(new Color(100,100,100));
		for(int i = 0; i < nbParLigne; i++){
			Point pt;
			pt = this.getPoint((1.0/nbParLigne)*i, 0,this.hauteur);				
			p.addPoint((int)pt.getX(), (int)pt.getY());
		}
		p.addPoint((int)this.getPoint(1,0,this.hauteur).getX(),(int)this.getPoint(1,0,this.hauteur).getY());
		p.addPoint((this.x+1)*View.blockPixelWidth,(this.y)*View.blockPixelHeight);
		p.addPoint(this.x*View.blockPixelWidth,(this.y)*View.blockPixelHeight);
		g.fillPolygon(p);
		p = new Polygon();
		*/
		//----TOIT----//
		g.setColor(Color.darkGray);
		for(int i = 0; i < nbDePoints; i++){
			Point pt;
			if(i<nbParLigne){
				pt = this.getPoint((1.0/nbParLigne)*i, 0,this.hauteur);
			}
			else if(i<nbParLigne*2){
				pt = this.getPoint(1, (1.0/nbParLigne)*(i-nbParLigne),this.hauteur);
				
			}
			else if(i<nbParLigne*3){
				pt = this.getPoint(1-(1.0/nbParLigne)*(i-nbParLigne*2), 1,this.hauteur);
			}
			else{
				pt = this.getPoint(0, 1-(1.0/nbParLigne)*(i-nbParLigne*3),this.hauteur);
			}
			p.addPoint((int)pt.getX(), (int)pt.getY());
		}
		g.fillPolygon(p);
	}
	
	public Point getPoint(double mx, double my, int hauteur){
		
		double rad = Math.toRadians(GamePane.getAngle((this.x+mx)*View.blockPixelWidth, (this.y+my)*View.blockPixelHeight,GamePane.player.getX(),GamePane.player.getY()));
		double dx = (int)(Math.cos(rad)*hauteur);
		double dy = (int)(Math.sin(rad)*hauteur);
		return new Point((int)((this.x+mx)*View.blockPixelWidth+dx), (int)((this.y+my)*View.blockPixelHeight+dy));
		//g.drawLine((this.x+mx)*View.blockPixelWidth, (this.y+my)*View.blockPixelHeight,(this.x)*View.blockPixelWidth+dx, (this.y)*View.blockPixelHeight+dy);
	}
	
	public void editorDraw(Graphics2D g, int x, int y){
		if(x > -View.x-View.blockPixelWidth&&x < -View.x+GamePane.WIDTH &&y > -View.y-View.blockPixelHeight&&y < -View.y+GamePane.HEIGHT){
			//System.out.println(this.getId());
			g.drawImage(GamePane.texturesBlock[show != 0?show:id], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
		}
		if(show != 0){
			show = 0;
		}
		if(this.x == Map.selectedBlock.getX()&&this.y == Map.selectedBlock.getY() && !GamePane.player.isRunning()){
			g.drawImage(GamePane.texturesGUI[0], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
		}
		if(Map.isRedSelection&&this.x == Map.redSelectedBlock.getX()&&this.y == Map.redSelectedBlock.getY() && !GamePane.player.isRunning()){
			g.drawImage(GamePane.texturesGUI[1], x, y,View.blockPixelWidth, View.blockPixelHeight, null);
		}
		//----SHOW POSITION----///
		if(Main.windows.cShowPosition.isSelected()){
			if(Main.windows.cShowPosition.isSelected()){
				g.setFont(new Font("Arial", 20, 20));
				g.setColor(Color.BLACK);
				g.drawString(this.x+":"+this.y, x+View.blockPixelWidth/2-15, y+View.blockPixelHeight/2+8);
			}
		}
		if(this.isPortal){
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(Color.green);
			g.drawString("P", x, y+15);
		}
	}
	
	public boolean isAtEdge(){
		if(x%24 == 0||x%24==-1||y%24==0||y%24==-1||x%24==23||y%24==23){
			return true;
		}
		return false;
	}
	//----positions----//
	
	
	//----Getters and setters ----//

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

	public boolean isPortal() {
		return isPortal;
	}

	public void setPortal(Portal p) {
		portal = p;
		this.isPortal = true;
	}

	public Portal getPortal() {
		return portal;
	}
}

//TODO reparer le bug de la selection de (0;0) a (-1;0)