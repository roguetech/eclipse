package client;

import java.io.*;

public class Robot implements Serializable {

	private String name;
	//public int startx, starty, x, y;
	
	public Robot(String name) {
		this.name = name;
	}
	
	public String getName() { return this.name; }

}
