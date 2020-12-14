package client;

import java.awt.*;
import java.util.Random;
import java.awt.event.*;

public class FrontEnd extends Frame implements ActionListener, WindowListener, Runnable {
	private Button forward, back, left, right, Connect, teleport;
	private Label status, warning, speedl, lastUpdate;
	private Choice speedc;
	private Map m;
	private int x, y, speed;
	public Robot R1;
	private String args, s;
	private boolean stat, conn, running, cc=false;
	private Thread thread;
	
	public FrontEnd(String args, Robot aRobot) {
		super("Robot Application");
		
		this.args = args;
		R1 = aRobot;
		R1.setTime();
		R1.color();
		
		this.x = R1.x;
		this.y = R1.y;

		this.thread = new Thread(this);
		
		// Create Display Panels
		this.setLayout(new FlowLayout());
		Panel leftp = new Panel(new BorderLayout());
		Panel rightp = new Panel(new BorderLayout());
		Panel connect = new Panel(new FlowLayout());
		Panel direction = new Panel(new BorderLayout());
		Panel speed = new Panel(new FlowLayout());
		Panel warn = new Panel(new FlowLayout());
		
		
		this.addWindowListener(this);
		
		leftp.setSize(new Dimension(400, 400));
		rightp.setPreferredSize(new Dimension(640, 480));
		
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
		teleport = new Button("Teleport");
		connect.add(teleport);
		teleport.addActionListener(this);
		warning = new Label("");
		connect.add(warning);
		
		rightp.add(connect, BorderLayout.NORTH);
		
		
		//warning = new Label("Warning");
		//rightp.add(warning, BorderLayout.NORTH);
		
		lastUpdate = new Label("Last Update: ", Label.RIGHT);
		rightp.add(lastUpdate, BorderLayout.SOUTH);
		
		// Setup Map
		
		m = new Map(R1.x, R1.y, R1.size, R1.R, R1.G, R1.B);
		
		rightp.add(m, BorderLayout.CENTER);
		
		
		this.add(leftp, BorderLayout.WEST);
		this.add(rightp, BorderLayout.EAST);
		
		this.pack();
		this.setVisible(true);
	}
	
	public boolean connectToServer(String args) {
		System.out.println("inside connect");
		Client theApp = new Client(args, this);
		
		if (theApp.status == true) {
			stat = true;
		} else if (theApp.status == false) {
			stat = false;
		}
		
		System.out.println("*********** Status: " + stat);
		System.out.println("**************************");
		System.out.println("Robot sending: " + R1.getName());
		System.out.println("**************************");
	    theApp.sendCommand(R1);
	    return stat;
	}
	
	public void updateLocation(int x, int y) {
		System.out.println("*** Updating Location ***");
	    Client theApp1 = new Client(args, this);
	    R1.updateLocation(x, y);
    	theApp1.sendCommand(R1);
	}
	
	public void updateTime() {
		lastUpdate.setText("Updated: " + R1.getTime());
	}
	
	public void updateWarning(boolean cc) {
		if(cc = true) {
			warning.setText("Warning Collison Close");
		} else if (cc = false) {
			warning.setText("Clear");
		}
	}
	
	public void run() {
		while(running) {
			System.out.println("Thread update");
			try {
				R1.setTime();
				Client theApp1 = new Client(args, this);
				theApp1.sendCommand(R1);
				Thread.sleep(30000);
			} catch(InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		// Set Speed
		s = speedc.getItem(speedc.getSelectedIndex());
		if (s.equals("Low")) {
			this.speed = 10;
		} else if (s.equals("Medium")){
			this.speed = 20;
		} else if (s.equals("High")) {
			this.speed = 30;
		}
		
		if (e.getSource().equals(forward)) {
			
			this.y = y - speed;
			m.move(x, y);
			updateLocation(x, y);
		} else if (e.getSource().equals(back)) {
			this.y = y + speed;
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
				running = false;
			} else if (stat == true) {
				status.setText("Connected");
				running = true;
				this.thread.start();
			}
		} else if (e.getSource().equals(teleport)) {
			System.out.println("teleport pushed");
			Random rand = new Random();
			this.x = rand.nextInt(300) + 1;
			this.y = rand.nextInt(300) + 1;
			m.move(x, y);
			updateLocation(x, y);
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
