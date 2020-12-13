package client;

import java.awt.*;
import java.io.*;
import java.util.Random;

public class Robot implements Serializable {

	private String name;
	public int x, y, x1, y1, x2, y2, R=0, G=0, B=0;
	private Point p;
	
	Random rand = new Random();
	
	public Robot(String name) {
		this.name = name;
		Point p = new Point(x,y);
		p.x = startXLocation();
		p.y = startYLocation();
		
		this.x = p.x;
		this.y = p.y;
		System.out.println("Points: x: " + p.x + " y: " + p.y);
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
		//this.x = x;
		//this.y = y;
	}
	
	public void color(int R, int G, int B) {
		this.R = R;
		this.G = G;
		this.B = B;
	}
	
	public void lastLoc(int x, int y) {
		this.x1 = x;
		this.x2 = y;
	}
	
	public void lastlastLoc(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}
	
	public Point getCorrds() {
		return p;
	}
}
