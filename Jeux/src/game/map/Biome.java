package game.map;

import java.util.ArrayList;
import java.util.HashMap;

public class Biome { 
	
	
	
	
	/*
	 * IDs (Block):
	 * 
	 * 1: Grass
	 * 2: Water
	 * 3: Dirt 
	 * 4: stone
	 * 
	 * IDs (Biomes):
	 * 
	 * 1: Plaines
	 * 2: eau
	 * 
	 * 
	 */
	
	//TODO change the 1 in the 2nd add to 3
	public static Biome PLAINES1 = new Biome(1, new ArrayList<Integer>(), 33).add(2, 1, 100).add(1, 1, 100).add(0, 4, 100);
	  public static Biome OCEAN2 = new Biome(2, new ArrayList<Integer>(), 33).add(2, 2, 100).add(1, 2, 100).add(0, 2, 100);
	  public static Biome ROCHE3 = new Biome(3, new ArrayList<Integer>(), 34).add(2, 1, 100).add(1, 1, 100).add(0, 4, 100);
	
	public static Biome getBiomeById(int Id){
		switch(Id){
		case 1: return PLAINES1;
		case 2: return OCEAN2;
		case 3: return ROCHE3;
		default: return PLAINES1;
		}
	}
	
	public static int nbBiome = 3;
	
	
	private ArrayList<Pourcentage> Niv0, Niv1, Niv2, Niv3, Niv4;
	private int id;
	private ArrayList<Integer> Exeptions;
	private int prob;
	
	
	public Biome(int id, ArrayList<Integer> exeptions, int prob) {
		Niv0 = new ArrayList<Pourcentage>();
		Niv1 = new ArrayList<Pourcentage>();
		Niv2 = new ArrayList<Pourcentage>();
		Niv3 = new ArrayList<Pourcentage>();
		Niv4 = new ArrayList<Pourcentage>();
		
		this.prob = prob;
		
		Exeptions = exeptions;
	}
	
	/**
	 * @param p Pourcentage
	 * @param id of the block
	 */
	public Biome add(int Niveau, int Block, int Pourcentage){
		this.getMap(Niveau).add(new Pourcentage(Block, Pourcentage));
		return this;
	}
	
	public ArrayList<Pourcentage> getMap(int level){
		switch(level){
		case 0: return Niv0;
		case 1: return Niv1;
		case 2:  return Niv2;
		case 3:  return Niv3;
		case 4:  return Niv4;
		}
		return new ArrayList<Pourcentage>();
	}
	
	public Block getRandomBlock(int lvl){
		int random;
		random = 1+Map.randomNum.nextInt(100);
		int BlockId = 0;
		
		for(int i = 0; i < this.getMap(lvl).size();i++){
			random -= this.getMap(lvl).get(i).getProb();
			
			if(random <= 0){
				BlockId = this.getMap(lvl).get(i).getId();
			}
		}
		
		
		return Block.getById(BlockId);
	}
	
	

	public int getProb() {
		return prob;
	}

}
