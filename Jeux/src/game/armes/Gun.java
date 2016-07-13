package game.armes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import game.core.GamePane;
import game.core.Main;
import game.editeur.ArmeTester;
import game.mobs.Mob;

public class Gun extends Arme{
	
	public static final Gun HANDGUN = new Gun("HandGun","HandGun",10,75,20,50,0,.25,false,true);
	public static ArrayList<Gun> GUNS = new ArrayList<Gun>();
	public static int loaded;
	
	public static void loadGuns(){
		Gun.getGuns().clear();
		Main.windows.armeList.removeAll();
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(new File("assets/Guns").getAbsolutePath()));
			
			String line;
			String[] args;
			while ((line = in.readLine()) != null) {
				args = line.split(" ");
				if(args.length == 10){
					try{
						GUNS.add(new Gun(args[0],args[1],Integer.parseInt(args[2]),Integer.parseInt(args[3]),Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]),Double.parseDouble(args[7]),Boolean.parseBoolean(args[8]),Boolean.parseBoolean(args[9])));
						Main.windows.armeList.add(GUNS.get(GUNS.size()-1).getNom());
					}catch(NumberFormatException e){}	
				}
			}
			
			in.close();
		}catch(IOException e){e.printStackTrace();
			return;
		}
	}

	public static void loadGunIntoPanel() {
		if(Main.windows.armeList.getSelectedIndex() == -1){
			return;
		}
		Gun sel = GUNS.get(Main.windows.armeList.getSelectedIndex());
		loaded = Main.windows.armeList.getSelectedIndex();
		Main.windows.tGunName.setText(sel.getNom());
		Main.windows.tGunType.setText(sel.getType());
		Main.windows.sDomage.setValue(sel.getDomage());
		Main.windows.sPrecision.setValue(sel.getPrecision());
		Main.windows.sSpeed.setValue(sel.getSpeed());
		Main.windows.sVitesseDeTir.setValue(sel.getNbDeBalle());
		Main.windows.cGunExplosif.setSelected(sel.isExplosif());
		Main.windows.cGunAuto.setSelected(sel.isAuto());
		ArmeTester.player.setArme(sel);
	}

	public static ArrayList<Gun> getGuns(){
		return GUNS;
	}
	
	public static void saveGuns(){
		PrintWriter writer;
		try {
			writer = new PrintWriter("assets/Guns", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}
		//Name Type Domage Precision Speed NbDeBalleParSeconde Durabilite Knockback Explosif Auto
		for(int i = 0; i < GUNS.size(); i++){
			Gun sel = GUNS.get(i);
			writer.print(sel.getNom()+" ");
			writer.print(sel.getType()+" ");
			writer.print(sel.getDomage()+" ");
			writer.print(sel.getPrecision()+" ");
			writer.print(sel.getSpeed()+" ");
			writer.print(sel.getNbDeBalle()+" ");
			writer.print(sel.getDurabilite()+" ");
			writer.print(sel.getKnockback()+" ");
			writer.print(sel.isExplosif()+" ");
			writer.println(sel.isAuto());
		}
		
		writer.close();
	}
	
	private int precision;
	private int speed;
	private int nbDeBalleParMilieme;
	private int durabilite;
	private double knockback;
	private boolean explosif;
	private Mob shooter;
	private boolean auto;
	
	public boolean canshoot;
	public long lastTimeShoot;

	/**
	 * @param nom
	 * @param type
	 * @param domage
	 * @param precision
	 * @param speed
	 * @param nbDeBalleParSeconde
	 * @param durabilite
	 * @param knockback
	 * @param explosif
	 * @param automatique
	 */
	private Gun(String nom, String type, int domage, int precision, int speed, int nbDeBalleParMilieme, int durabilite,
			double knockback, boolean explosif,boolean auto) {
		super(nom,type,domage);
		this.precision = precision;
		//int randmSpeed = (int) (Math.random()*speed);
		this.speed = (int) (speed);//+randmSpeed/2-randmSpeed);
		this.nbDeBalleParMilieme = nbDeBalleParMilieme;
		this.durabilite = durabilite;
		this.knockback = knockback;
		this.explosif = explosif;
		this.auto = auto;
	}
	
	public Gun(){
		super("Gun","Gun",0);
		this.precision = 0;
		this.speed = 10;
		this.nbDeBalleParMilieme = 1000;
		this.durabilite = 0;
		this.knockback = 0;
		this.explosif = false;
		this.auto = false;
	}
	
	public void update(){
		if(GamePane.l.RIGHT_CLICK&&canshoot){
			this.shoot();
		}
	}
	
	public void shoot(){
		long difference = System.currentTimeMillis() - lastTimeShoot;
		
		if(difference > nbDeBalleParMilieme){
			
			double pre =(1.0-(double)this.precision/100.0)*30.0- Math.random()*((1.0-(double)this.precision/100.0)*60.0);
			
			GamePane.map.bullets.add(new Bullet(GamePane.player.getX(), GamePane.player.getY(), GamePane.getAngleOfTheMouseAndThePlayer()+pre,this));
			lastTimeShoot = System.currentTimeMillis();
			
			
			if(!this.auto){
				canshoot = false;
			}
		}
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getNbDeBalle() {
		return nbDeBalleParMilieme;
	}

	public void setNbDeBalle(int nbDeBalle) {
		this.nbDeBalleParMilieme = nbDeBalle;
	}

	public int getDurabilite() {
		return durabilite;
	}

	public void setDurabilite(int durabilite) {
		this.durabilite = durabilite;
	}

	public double getKnockback() {
		return knockback;
	}

	public void setKnockback(double knockback) {
		this.knockback = knockback;
	}

	public boolean isExplosif() {
		return explosif;
	}

	public void setExplosif(boolean explosif) {
		this.explosif = explosif;
	}
	
	public boolean isAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}
}
