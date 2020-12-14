package client;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Map extends Canvas implements MouseListener{
	private int x, y, G, B, R, x1, y1, x2, y2;
	public Vector<Robot> RobotVect;
	//private Robot aRobot;
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
	}
	
	public void move(ArrayList<Robot> RobotArray) {
		this.RobotArray = RobotArray;
		this.repaint();
	}
	
	public void move1() {
		for(int i=0; i < RobotVect.size(); i++) {
			System.out.println(this.RobotVect.get(i));
		}
	}
	
	public void partyMode() {
		this.setBackground(Color.BLUE);
		 try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		this.setBackground(Color.GREEN);
		 try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		this.setBackground(Color.RED);
		 try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		this.setBackground(Color.WHITE);
		 try {
		        TimeUnit.SECONDS.sleep(1);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		this.setBackground(Color.LIGHT_GRAY);
	}
	
	public void paint(Graphics g) {
		for(int i=0; i < RobotArray.size(); i++) {
			Robot r = RobotArray.get(i);
			
			g.setColor(new Color(r.R,r.G,r.B));
			g.fillOval(r.x, r.y, r.size, r.size);
			if(r.x1 != 0) {
				g.drawOval(r.x1, r.y1, r.size, r.size);
			}
			if(r.x2 !=0) {
				g.drawOval(r.x2, r.y2, r.size, r.size);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked");
		System.out.println(e.getComponent().getClass().getName());
		
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
