package client;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import java.util.Random;

public class Robot implements Serializable {

	private String name;
	public int x, y, x1, y1, x2, y2, R=0, G=0, B=0, size;
	private Point p;
	private Calendar calendar;
	private Date d;
	private boolean w = false;
	
	Random rand = new Random();
	
	public Robot(String name, int size) {
		this.name = name;
		this.size = size;
		Point p = new Point(x,y);
		p.x = startXLocation();
		p.y = startYLocation();
		
		this.x = p.x;
		this.y = p.y;
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
		this.x2 = this.x1;
		this.y2 = this.y1;
		this.x1 = this.x;
		this.y1 = this.y;
		this.x = x;
		this.y = y;
	}
	
	public void color() {
		this.R = rand.nextInt(254) + 1;
		this.G = rand.nextInt(254) + 1;
		this.B = rand.nextInt(254) + 1;
	}
	
	public Point getCorrds() {
		return p;
	}
	
	public void setTime() {
		this.calendar = Calendar.getInstance();
		this.d = this.calendar.getTime();
	}
	
	public String getTime() {
		return this.d.toString();
	}
	
	public void setWarning(boolean w) {
		this.w = w;
	}
	
	public boolean getWarning() {
		return w;
	}
}
