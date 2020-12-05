package client;

import java.io.*;
//import java.util.Random;

public class Robot implements Serializable {

	private String name;
	//public int startx, starty, x, y;
	
	//Random rand = new Random();
	
	public Robot(String name) {
		this.name = name;
		//startLocation();
	}
	
	public String getName() { return this.name; }
	
	//public void startLocation() {
	//	startx = rand.nextInt(300) + 1;	
	//	starty = rand.nextInt(300) + 1;
	//}
	
	//public void setLocation(int x, int y) { 
	//	this.x = x;
	//	this.y = y;
	//}

}
