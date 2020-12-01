import java.awt.*;
import java.awt.event.*;

public class MyCanvas extends Canvas implements MouseListener{
	private int d;
	private counter app;

	public MyCanvas(counter app) {
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.app = app;
	}
	
	public void setCircleSize(int d) {
		this.d = d;
		this.repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(new Color(255,0,0));
		g.drawOval(150-(d/2), 150+(d/2), d, d);
		g.setColor(new Color(0,0,255));
		g.drawOval(150-(d/2), 150+(d/2), d+5, d+5);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse clicked");
		app.incrementCountBy10();
		
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
