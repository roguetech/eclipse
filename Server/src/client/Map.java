package client;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Map extends Canvas implements MouseListener{
	private int x, y, G, B, R;
	public Vector<Robot> RobotVect;
	private Robot aRobot;
	Vector<Point> points = new Vector<Point>(2);
	
	public Map() {
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		points.add(0, new Point(0,0));
	    points.add(1, new Point(0,0));
		//this.x = x;
		//this.y = y;
	}
	
	public void addRobot(int x, int y, int i) {
		//aRobot = o;
		//this.points.addElement(new Point(x, y));
		this.points.set(i, new Point(x, y));
		
	}
	
	public void move(int x, int y) {
		//for(int i=0; i < RobotVect.size(); i++) {
		//	System.out.println(this.RobotVect.get(i));
		//}
		//this.x = x;
		//this.y = y;
		//this.G = G;
		//this.B = B;
		//this.R = R;
		this.repaint();
	}
	
	public void move1() {
		for(int i=0; i < RobotVect.size(); i++) {
			System.out.println(this.RobotVect.get(i));
		}
		//this.x = x;
		//this.y = y;
		//this.G = G;
		//this.B = B;
		//this.R = R;
		//this.repaint();
	}
	
	public int getXLoc() {
		return x;
	}
	
	public int getYLoc() {
		return x;
	}
	
	public void paint(Graphics g) {
		/*
		for(int i=0; i < RobotVect.size(); i++) {
			Robot j = RobotVect.get(i);
			g.setColor(new Color(255,0,0));
			g.fillOval(j.x, j.y, 5, 5);
		}
		*/
		//Color c = new Color();
		for(int i=0; i < points.size(); i++) {
			Point p = points.get(i);
			System.out.println(points);
				g.setColor(new Color(255,0,0));
				g.fillOval(p.x, p.y, 10, 10);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
