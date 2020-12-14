package client;

import java.awt.*;
import java.awt.event.*;

public class Map extends Canvas implements MouseListener{
	private int x, y, G, B, R, x1, y1, x2, y2, size;

	public Map(int x, int y, int size, int R, int G, int B) {
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.x = x;
		this.y = y;
		this.size = size;
		this.R = R;
		this.G = G;
		this.B = B;
	}

	public void move(int x, int y) {
		this.x2 = this.x1;
		this.y2 = this.y1;
		this.x1 = this.x;
		this.y1 = this.y;
		this.x = x;
		this.y = y;
		this.repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(new Color(R,G,B));
		g.fillOval(x, y, size, size);
		if(x1 != 0) {
			g.drawOval(x1, y1, size, size);
		}
		if(x2 !=0) {
			g.drawOval(x2, y2, size, size);
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
