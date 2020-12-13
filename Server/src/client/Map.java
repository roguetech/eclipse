package client;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Map extends Canvas implements MouseListener{
	private int x, y, G, B, R;
	public Vector<Robot> RobotVect;
	private Robot aRobot;
	ThreadedServer ts;
	Vector<Point> points = new Vector<Point>(2);
	Robot[] RC;
	private ArrayList<Robot> RobotArray;
	private Point p;
	
	public Map(ThreadedServer ts) {
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		points.add(0, new Point(0,0));
	    points.add(1, new Point(0,0));
	    this.ts = ts;
	    RobotArray = new ArrayList<Robot>();
	    //Point p = new Point(x,y);
		//this.x = x;
		//this.y = y;
	}
	
	public void addRobot(int x, int y, int i) {
		//aRobot = o;
		//this.points.addElement(new Point(x, y));
		//this.points.set(i, new Point(x, y));
		
	}
	
	public void move(ArrayList<Robot> RobotArray) {
		this.RobotArray = RobotArray;
		//for(int i=0; i < RobotVect.size(); i++) {
		//	System.out.println(this.RobotVect.get(i));
		//}
		//this.x = x;
		//this.y = y;
		//this.points.set(i, new Point(x, y));
		//this.RC = RC;
		//this.ts.updateStatus("test");
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
		for(int i=0; i < RobotArray.size(); i++) {
			//int R = RC[0].R;
			System.out.println("test ");
			Robot r = RobotArray.get(i);
			System.out.println("map name: " + r.getName());
			//p = r.getCorrds();
			//System.out.println("test " + p.x);
			g.setColor(new Color(R,G,B));
			g.fillOval(r.x, r.y, 10, 10);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//String update = RC[0].getName();
		//ts.updateStatus(update);
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
