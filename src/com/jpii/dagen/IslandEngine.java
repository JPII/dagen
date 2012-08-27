package com.jpii.dagen;

public class IslandEngine extends Engine {

	double[][] waterpoints;
	double rr = 0;
	
	public IslandEngine(int width, int height) {
		super(width, height);
	}
	
	public void generate(MapType type, int seed, double magnitude) {
		super.generate(type, seed, magnitude);
		
		waterpoints = new double[width][height];
		
		rr = rand.nextInt(width/3) + width/3;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (iterate(x,y)){
					waterpoints[x][y] = points[x][y];
				}
				else
					waterpoints[x][y] = 0;
			}
		}
		
		for (int lakes = 0; lakes < r(3,5); lakes++) {
			boolean lakepointfound = false;
			int x = 0;
			int y = 0;
			while (!lakepointfound) {
				x = r(0,width);
				y = r(0,height);
				
				if (waterpoints[x][y] != 0)
					lakepointfound = true;
			}
			
			for (int pass = 0; pass < r(60,100); pass++) {
				int rx = x + r(-5,5);
				int ry = y + r(-5,5);
				for (int c = 0; c < r(5,10); c++){
					int gx = r(5,10);
					int gy = r(5,10);
					gx = ((int)Math.cos(gx * r(2,5)) + gx + r(-3,3)) + rx;
					gy = ((int)Math.sin(gy * r(2,5)) + gy + r(-3,3)) + ry;
					waterpoints[gx][gy] = 0;
				}
			}
		}
		
	}
	
	private boolean iterate(int x, int y) {
		double nx = Math.cos(x * rr) + x + r(-2,2);
		double ny = Math.sin(y * rr) + y + r(-2,2);
		
		double tr = Math.sqrt(Math.pow(nx - (width/2),2) + Math.pow(ny - (height/2),2));
		
		if (tr + 0.000001 + (width/5) > rr)
			return false;
		else
			return true;
	}
	
	
	public double[][] getWaterPoints() {
		if (waterpoints == null) {
			throw new NullPointerException("Generation has not been executed!");
		}
		
		return waterpoints;
	}
}
