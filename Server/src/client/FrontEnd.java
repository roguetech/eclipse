package client;

import java.awt.*;
import java.awt.event.*;

public class FrontEnd extends Frame implements ActionListener, WindowListener {
	private Button start;
	private Label status;
	private Map m;
	private int x, y;
	private Robot R1;
	
	public FrontEnd() {
		super("Robot Server Application");
		
		this.setLayout(new BorderLayout());
		Panel topPanel = new Panel(new FlowLayout());
		Panel bottomPanel = new Panel(new FlowLayout());
		
		this.addWindowListener(this);
		
		topPanel.setSize(new Dimension(100, 100));
		bottomPanel.setSize(new Dimension(640, 480));
		
		// Right side of GUI
		
		status = new Label("Not Running", Label.CENTER);
		topPanel.add(status);
		start = new Button("Start");
		topPanel.add(start);
		start.addActionListener(this);
		
		//m = new Map();
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(m, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}
	
	public void updateXLocation() {
		System.out.println("*** Updating Location ***");
	    //Client theApp1 = new Client(args);
	    //R1.updateXLocation(10);
	    //R1.getLocation();
	    //m.move(x,y);
    	//theApp1.sendCommand(R1);
	}
	
	public void updateYLocation() {
		System.out.println("*** Updating Location ***");
	    //Client theApp1 = new Client(args);
    	//R1.updateYLocation(10);
    	//theApp1.sendCommand(R1);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(start)) {
			//ts = new ThreadedServer();
			status.setText("Started");
			status.repaint();
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
