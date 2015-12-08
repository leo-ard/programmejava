package game.map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Map {
	
	public static Random randomNum;
	ArrayList<ArrayList<Chunck>> chuncksXY = new ArrayList<ArrayList<Chunck>>();
	ArrayList<ArrayList<Chunck>> chuncksMXY = new ArrayList<ArrayList<Chunck>>();
	ArrayList<ArrayList<Chunck>> chuncksXMY = new ArrayList<ArrayList<Chunck>>();
	ArrayList<ArrayList<Chunck>> chuncksMXMY = new ArrayList<ArrayList<Chunck>>();
	
	final int rayon = 4;
	long seed;
	
	public Map(long seed){
		this.seed = seed;
		randomNum = new Random(seed);
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		
	}
	
	public void Generate(){
		for(int i = -rayon; i <= rayon; i++){
			for(int j = -rayon; j <= rayon; j++){
				System.out.println(i+" "+j);
				this.add(new Chunck(i, j), i, j);
				this.get(i, j).generate();
			}
		}
	}
	
	public void add(Chunck c, int x, int y){
		if(x==0 || y == 0){
			return;
		}
		if(x < 0 && y < 0){
			if(chuncksMXMY.isEmpty()){
				chuncksMXMY.add(-x, new ArrayList<Chunck>());
			}
			chuncksMXMY.get(x*-1).add(y*-1, c);
		}
		else if(x > 0 && y < 0){
			if(chuncksXMY.get(x) == null){
				chuncksXMY.add(x, new ArrayList<Chunck>());
			}
			chuncksXMY.get(x).add(y*-1, c);
		}
		else if(x < 0 && y > 0){
			if(chuncksMXY.get(x*-1) == null){
				chuncksMXY.add(x*-1, new ArrayList<Chunck>());
			}
			chuncksMXY.get(x*-1).add(y, c);
		}
		else if(x > 0 && y > 0){
			if(chuncksXY.get(x) == null){
				chuncksXY.add(x, new ArrayList<Chunck>());
			}
			chuncksXY.get(x).add(y, c);
		}
	}
	
	public Chunck get(int x, int y){
		if(x < 0 && y < 0){
			return chuncksMXMY.get(x*-1).get(y*-1);

		}
		else if(x > 0 && y < 0){
			return chuncksXMY.get(x).get(y*-1);
		}
		else if(x < 0 && y > 0){
			chuncksMXY.get(x*-1).get(y);
		}
		else if(x > 0 && y > 0){
			return chuncksXY.get(x).get(y);
		}
		return null;
	}

	public Random getRandomNum() {
		return randomNum;
	}

}
