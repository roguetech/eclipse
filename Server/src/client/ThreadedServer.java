package client;


import client.Robot;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ThreadedServer extends Frame implements ActionListener, WindowListener{

	private static int portNumber = 5050;
	private Button refresh, party;
	private Label status, lastUpdate, safetyMargin;
	private TextField safety;
	private static Map m;
	public int x, y;
	private Robot R1;
	private ArrayList<Robot> RobotList;
	private ArrayList<String> indexOfArray;
	private Boolean warning = false;
	
	public ThreadedServer() { 
		
		// GUI
		
		super("Robot Server Application");
		
		this.setLayout(new BorderLayout());
		Panel topPanel = new Panel(new FlowLayout());
		Panel bottomPanel = new Panel(new FlowLayout());
		
		this.addWindowListener(this);
		
		this.setPreferredSize(new Dimension(400, 400));
		topPanel.setSize(new Dimension(400, 400));
		bottomPanel.setSize(new Dimension(400, 400));
		
		// Right side of GUI
		
		status = new Label("", Label.CENTER);
		topPanel.add(status);
		
		safetyMargin = new Label("Safety Margin: ", Label.CENTER);
		topPanel.add(safetyMargin);
		
		safety = new TextField("10");
		topPanel.add(safety);
		
		party = new Button("Party Mode");
		topPanel.add(party);
		party.addActionListener(this);
		
		lastUpdate = new Label("Update", Label.CENTER);
		bottomPanel.add(lastUpdate);
		
		refresh = new Button("Refresh");
		refresh.addActionListener(this);
		
		m = new Map(this);
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(m, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.addWindowListener(this);
		
		this.pack();
		this.setVisible(true);
		
		// Server
		
		boolean listening = true;
        ServerSocket serverSocket = null;
        
        RobotList = new ArrayList<Robot>();
        indexOfArray = new ArrayList<String>();
       
        // Set up the Server Socket
        try 
        {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("New Server has started listening on port: " + portNumber );
        } 
        catch (IOException e) 
        {
            System.out.println("Cannot listen on port: " + portNumber + ", Exception: " + e);
            System.exit(1);
        }
        
        // Server is now listening for connections or would not get to this point
        while (listening) // almost infinite loop - loop once for each client request
        {
            Socket clientSocket = null;
            try{
            	System.out.println("**. Listening for a connection...");
                clientSocket = serverSocket.accept();
                System.out.println("00. <- Accepted socket connection from a client: ");
                System.out.println("    <- with address: " + clientSocket.getInetAddress().toString());
                System.out.println("    <- and port number: " + clientSocket.getPort());
            } 
            catch (IOException e){
                System.out.println("XX. Accept failed: " + portNumber + e);
                listening = false;   // end the loop - stop listening for further client requests
            }	
            
            ThreadedConnectionHandler con = new ThreadedConnectionHandler(clientSocket, m, this);
            con.start(); 
            

            System.out.println("02. -- Finished communicating with client:" + clientSocket.getInetAddress().toString());
        }
        // Server is no longer listening for client connections - time to shut down.
        try 
        {
            System.out.println("04. -- Closing down the server socket gracefully.");
            serverSocket.close();
        } 
        catch (IOException e) 
        {
            System.err.println("XX. Could not close server socket. " + e.getMessage());
        }
    }
	
	 public void collisionDetection(Object o) {
		 Robot cc = (Robot) o;
			for(int i=0; i < RobotList.size(); i++) {
				Robot r = RobotList.get(i);
				if(cc.getName() != r.getName()) {
					int locx = cc.x - (r.x + Integer.parseInt(safety.getText()));
					int locy = cc.y - (r.y + Integer.parseInt(safety.getText()));
					locx = Math.abs(locx);
					locy = Math.abs(locy);
					if(locx <= Integer.parseInt(safety.getText()) ) {
						if(locy <= Integer.parseInt(safety.getText()) ) {
							System.out.println("Warning:" + cc.x + " " + r.x + " locx " + locx + " locy " + locy);
							warning = true;
						}
					} else {
						System.out.println("***** No Warning *****");
						warning = false;
					}
				}
			}
	}
	 
	public boolean checkCollision() {
		if (warning == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public void updateLocation() {
		System.out.println("*** Updating Location ***");
    	R1.updateLocation(x, y);
	}
	
	//updateMap.start();

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(refresh)) {
			status.setText("Started");
			status.repaint();
		} else if(e.getSource().equals(party)) {
			m.partyMode();
		}
	}
	
	public void updateArray(Robot r) {
		if(RobotList.size() == 0) {
			RobotList.add(r);
			String indexOfRobots = r.getName();
			indexOfArray.add(indexOfRobots);
		} else {
			String s2 = r.getName();
			System.out.println(r.getName());
			if (indexOfArray.contains(s2)) {
				int i = indexOfArray.indexOf(s2);
				RobotList.set(i, r);
			} else {
				RobotList.add(r);
				String indexOfRobots = r.getName();
				indexOfArray.add(indexOfRobots);
			}
				
		}
		lastUpdate.setText("Updated: " + r.getTime());
		m.move(RobotList);
	}
	
	public void updateStatus(String update) {
		status.setText(update);
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
