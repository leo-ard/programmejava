package game.map;

import java.awt.Graphics2D;
import java.util.Random;

public class Map {
	
	public static int chuncksBlockWidth = 24;
	public static int blockPixelHeight = 100;
	public static int blockPixelWidth = 94;
	
	public static Random randomNum;
	Chunck[][] chuncksXY;
	Chunck[][] chuncksMXY;
	Chunck[][] chuncksXMY;
	Chunck[][] chuncksMXMY;
	
	Chunck[] chuncksX;
	Chunck[] chuncksY;
	Chunck[] chuncksMX;
	Chunck[] chuncksMY;
	
	Chunck chunck0;
	
	long seed;
	
	public Map(long seed, int max){
		this.seed = seed;
		randomNum = new Random(seed);
		
		chuncksXY = new Chunck[max][max];  
		chuncksMXY = new Chunck[max][max]; 
		chuncksXMY = new Chunck[max][max]; 
		chuncksMXMY = new Chunck[max][max];
		
		chuncksX = new Chunck[max]; 
		chuncksY = new Chunck[max]; 
		chuncksMX = new Chunck[max];
		chuncksMY = new Chunck[max];
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g, int x, int y){
		int rayon = 1;
		for(int i = -rayon; i < rayon; i+=2){
			for(int j = -rayon; j < rayon; j+=2){
				this.get(i, j).draw(g, x, y);
				x += blockPixelWidth*chuncksBlockWidth;
			}
			y += blockPixelHeight*chuncksBlockWidth;
			x = 0;
		}
	}
	
	public void firstGenerate(int rayon){
		int x1 = 0;
		int y1 =0;
		for(int i = -rayon; i <= rayon; i++){
			for(int j = -rayon; j <= rayon; j++){
				this.add(new Chunck(i, j), i, j);
				this.get(i, j).generate();
			}
		}
	}
	
	public void add(Chunck c, int x, int y){
		if(x==0 || y == 0){
			if(x == 0&& y == 0){
				chunck0 = c;
			}
			else if(x > 0){
				chuncksX[x] = c;
			}
			else if(x < 0){
				chuncksMX[-x] = c;
			}
			else if(y > 0){
				chuncksY[y] = c;
			}
			else if(y < 0){
				chuncksMY[-y] = c;
			}
		}
		else if(x < 0 && y < 0){
			chuncksMXMY[-x][-y] = c;
		}
		else if(x > 0 && y < 0){
			chuncksXMY[x][-y] = c;
		}
		else if(x < 0 && y > 0){
			chuncksMXY[-x][y] = c;
		}
		else if(x > 0 && y > 0){
			chuncksXY[x][y] = c;
		}
	}
	
	public Chunck get(int x, int y){
		if(x==0 || y == 0){
			if(x == 0&& y == 0){
				return chunck0;
			}
			else if(x > 0){
				return chuncksX[x];
			}
			else if(x < 0){
				return chuncksMX[-x];
			}
			else if(y > 0){
				return chuncksY[y];
			}
			else if(y < 0){
				return chuncksMY[-y];
			}
		}
		else if(x < 0 && y < 0){
			return chuncksMXMY[-x][-y];
		}
		else if(x > 0 && y < 0){
			return chuncksXMY[x][-y];
		}
		else if(x < 0 && y > 0){
			return chuncksMXY[-x][y];
		}
		else if(x > 0 && y > 0){
			return chuncksXY[x][y];
		}
		return null;
	}

	public Random getRandomNum() {
		return randomNum;
	}

}
