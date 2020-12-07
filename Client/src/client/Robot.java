package client;

import java.io.*;
import java.util.Random;

public class Robot implements Serializable {

	private String name;
	public int x, y;
	
	Random rand = new Random();
	
	public Robot(String name) {
		this.name = name;
		x = startXLocation();
		y = startYLocation();
	}
	
	public String getName() { return this.name; }
	public String setName(String name) { 
		this.name = name;
		return this.name; 
	}
	
	public int startXLocation() {
		int x = rand.nextInt(300) + 1;	
		return x;
	}
	
	public int startYLocation() {
		int y = rand.nextInt(300) + 1;	
		return y;
	}
	
	public int getLocation() {
		x = x;
		return x;
	}
	
	public void updateLocation(int x, int y) { 
		this.x = x;
		this.y = y;
	}
}
