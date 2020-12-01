package client;

import java.awt.*;
import java.awt.event.*;

public class FrontEnd extends Frame implements ActionListener, WindowListener {
	private Button up, down, left, right;
	private Label status, warning;
	private Map m;
	private int x, y;
	
	public FrontEnd() {
		super("Robot Application");
		this.setLayout(new FlowLayout());
		Panel leftp = new Panel(new BorderLayout());
		Panel rightp = new Panel(new BorderLayout());
		
		this.addWindowListener(this);
		
		leftp.setSize(new Dimension(100, 100));
		rightp.setSize(new Dimension(640, 480));
		
		up = new Button("Up");
		up.addActionListener(this);
		leftp.add(up, BorderLayout.NORTH);
		
		down = new Button("Down");
		down.setSize(new Dimension(50, 50));
		down.addActionListener(this);
		leftp.add(down, BorderLayout.SOUTH);
		
		left = new Button("Left");
		left.setSize(new Dimension(50, 50));
		left.addActionListener(this);
		leftp.add(left, BorderLayout.WEST);
		
		right = new Button("Right");
		left.setSize(new Dimension(50, 50));
		right.addActionListener(this);
		leftp.add(right, BorderLayout.EAST);
		
		// Right side of GUI
		
		status = new Label("Connected", Label.CENTER);
		rightp.add(status, BorderLayout.NORTH);
		
		warning = new Label("Warning", Label.CENTER);
		rightp.add(warning, BorderLayout.SOUTH);
		
		m = new Map(150, 150);
		//m.
		rightp.add(m, BorderLayout.CENTER);
		
		
		this.add(leftp, BorderLayout.WEST);
		this.add(rightp, BorderLayout.EAST);
		
		this.pack();
		this.setVisible(true);
	}
	
	public void Move() {
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(up)) {
			x = m.getXLoc();
			y = m.getYLoc() - 10;
			System.out.println("x: " + x + " Y: " + y);
			m.setLocation(x, y);
			//m.move(x, y);
		} else if (e.getSource().equals(right)) {
			x = m.getXLoc() + 10;
			y = m.getYLoc();
			System.out.println("x: " + x + " Y: " + y);
			m.setLocation(x, y);
		}
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	public void windowClosed(WindowEvent e) {
		System.exit(0);
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
