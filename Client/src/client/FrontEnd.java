package client;

import java.awt.*;
import java.awt.event.*;

public class FrontEnd extends Frame implements ActionListener, WindowListener {
	private Button forward, back, left, right, Connect;
	private Label status, warning, speedl;
	private Choice speedc;
	private Map m;
	private int x, y, speed;
	private Robot R1;
	private String args, s;
	private boolean stat, conn;
	//private boolean north, south, east, west;
	
	public FrontEnd(String args, Robot aRobot) {
		super("Robot Application");
		
		this.args = args;
		//R1 = new Robot("blimpy");
		//R2 = new Robot("holden");
		R1 = aRobot;
		
		this.x = R1.x;
		this.y = R1.y;
		
		System.out.println("X: " + x + " Y: " + y);
		
		//this.x1 = R2.x;
		//this.y1 = R2.y;
		
		// Create Display Panels
		this.setLayout(new FlowLayout());
		Panel leftp = new Panel(new BorderLayout());
		Panel rightp = new Panel(new BorderLayout());
		Panel connect = new Panel(new FlowLayout());
		Panel direction = new Panel(new BorderLayout());
		Panel speed = new Panel(new FlowLayout());
		
		this.addWindowListener(this);
		
		leftp.setSize(new Dimension(100, 100));
		rightp.setSize(new Dimension(640, 480));
		
		//Add Buttons and Labels
		
		forward = new Button("Forward");
		forward.addActionListener(this);
		direction.add(forward, BorderLayout.NORTH);
		
		back = new Button("Back");
		back.setSize(new Dimension(50, 50));
		back.addActionListener(this);
		direction.add(back, BorderLayout.SOUTH);
		
		left = new Button("Left");
		left.setSize(new Dimension(50, 50));
		left.addActionListener(this);
		direction.add(left, BorderLayout.WEST);
		
		right = new Button("Right");
		left.setSize(new Dimension(50, 50));
		right.addActionListener(this);
		direction.add(right, BorderLayout.EAST);
		
		speedl = new Label("Speed");
		speedc = new Choice();
		speedc.add("Low");
		speedc.add("Medium");
		speedc.add("High");
		speed.add(speedl);
		speed.add(speedc);
		
		leftp.add(direction, BorderLayout.CENTER);
		leftp.add(speed, BorderLayout.SOUTH);
		
		// Right side of GUI
		
		status = new Label("Not Connected", Label.CENTER);
		connect.add(status);
		Connect = new Button("Connect");
		connect.add(Connect);
		Connect.addActionListener(this);
		
		rightp.add(connect, BorderLayout.NORTH);
		
		
		warning = new Label("Warning", Label.CENTER);
		rightp.add(warning, BorderLayout.SOUTH);
		
		// Setup Map
		
		m = new Map(x, y);
		
		rightp.add(m, BorderLayout.CENTER);
		
		
		this.add(leftp, BorderLayout.WEST);
		this.add(rightp, BorderLayout.EAST);
		
		this.pack();
		this.setVisible(true);
	}
	
	public boolean connectToServer(String args) {
		System.out.println("inside connect");
		Client theApp = new Client(args);
		
		if (theApp.status == true) {
			stat = true;
		} else if (theApp.status == false) {
			stat = false;
		}
		
		System.out.println("*********** Status: " + stat);
		//R1.setName("Holder");
		//Client theApp = new Client("127.0.0.1");
	    //theApp.getDate();
		//System.out.println("send command");
		System.out.println("test command");
    	//Robot r = new Robot("blimpy");
		System.out.println("**************************");
		System.out.println("Robot sending: " + R1.getName());
		System.out.println("**************************");
	    theApp.sendCommand(R1);
	    return stat;
	}
	
	public void updateLocation(int x, int y) {
		System.out.println("*** Updating Location ***");
	    Client theApp1 = new Client(args);
	    R1.updateLocation(x, y);
    	theApp1.sendCommand(R1);
	}
	
	public void updateYLocation(int y) {
		System.out.println("*** Updating Location ***");
	    Client theApp1 = new Client(args);
    	R1.updateLocation(x, y);
    	theApp1.sendCommand(R1);
	}

	public void actionPerformed(ActionEvent e) {
		// Set Speed
		s = speedc.getItem(speedc.getSelectedIndex());
		if (s.equals("Low")) {
			this.speed = 5;
		} else if (s.equals("Medium")){
			this.speed = 10;
		} else if (s.equals("High")) {
			this.speed = 20;
		}
		
		if (e.getSource().equals(forward)) {
			this.y = y - speed;
			//y = m.getYLoc() - 10;
			System.out.println("x: " + x + " Y: " + y);
			//m.setLocation(x, y);
			m.move(x, y);
			updateLocation(x, y);
		} else if (e.getSource().equals(back)) {
			this.y = y + speed;
			//y = m.getYLoc();
			System.out.println("x: " + x + " Y: " + y);
			//m.setLocation(x, y);
			m.move(x, y);
			updateLocation(x, y);
		} else if (e.getSource().equals(right)) {
			this.x = x + speed;
			m.move(x, y);
			updateLocation(x, y);
		} else if (e.getSource().equals(left)) {
			this.x = x - speed;
			m.move(x, y);
			updateLocation(x, y);
		} else if (e.getSource().equals(Connect)) {
			System.out.println("Connect pushed");
			stat = connectToServer(args);
			System.out.println("*********** Status 2: " + stat);
			if (stat == false) {
				status.setText("Disconnected");
			} else if (stat == true) {
				status.setText("Connected");
			}
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
