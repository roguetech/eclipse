package client;

import java.awt.*;
import java.awt.event.*;

public class Map extends Canvas implements MouseListener{
	private int x, y;

	public Map(int x, int y) {
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.x = x;
		this.y = y;
		//this.setBackground(c);
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		this.repaint();
	}
	
	public int getXLoc() {
		return x;
	}
	
	public int getYLoc() {
		return x;
	}
	
	public void paint(Graphics g) {
		g.setColor(new Color(255,0,0));
		g.drawOval(x, y, 5, 5);
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
