package client;


import client.Robot;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ThreadedServer extends Frame implements ActionListener, WindowListener{

	private static int portNumber = 5050;
	private Button refresh;
	private Label status;
	private static Map m;
	public int x, y;
	private Robot R1;
	private ArrayList<Robot> RobotList;
	private ArrayList<String> indexOfArray;
	
	public ThreadedServer() { 
		
		// GUI
		
		super("Robot Server Application");
		
		this.setLayout(new BorderLayout());
		Panel topPanel = new Panel(new FlowLayout());
		Panel bottomPanel = new Panel(new FlowLayout());
		
		this.addWindowListener(this);
		
		topPanel.setSize(new Dimension(100, 100));
		bottomPanel.setSize(new Dimension(640, 480));
		
		// Right side of GUI
		
		status = new Label("Selected", Label.CENTER);
		topPanel.add(status);
		
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
	
	public void updateXLocation() {
		System.out.println("*** Updating Location ***");
	    //Client theApp1 = new Client(args);
	    R1.updateLocation(x, y);
	    R1.getLocation();
	    m.move(x,y);
    	//theApp1.sendCommand(R1);
	}
	
	public void updateLocation() {
		System.out.println("*** Updating Location ***");
	    //Client theApp1 = new Client(args);
    	R1.updateLocation(x, y);
    	//theApp1.sendCommand(R1);
	}
	
	//updateMap.start();

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(refresh)) {
			//ts = new ThreadedServer();
			status.setText("Started");
			status.repaint();
		}
	}
	
	public void updateArray(Robot r) {
		//check vector for instance exists
		System.out.println("%%%%%%%%%inside array method%%%%%%%%");
		if(RobotList.size() == 0) {
			RobotList.add(r);
			System.out.println("The new Vector is: " + RobotList); 
			String indexOfRobots = r.getName();
			indexOfArray.add(indexOfRobots);
			System.out.println(indexOfArray);
		} else {
			System.out.print("Array size 1:  " + RobotList.size());
			//for(int i=0; i < RobotList.size(); i++) {
				System.out.print("Array size for:  " + RobotList.size());
				System.out.println("&&&&&&&inside for loop&&&&&");
				//Robot vr = RobotList.get(i);
				//String s1 = vr.getName();
				String s2 = r.getName();
				//System.out.println("s1: " + s1 + " s2: " + s2);
				//System.out.println(vr.getName());
				System.out.println(r.getName());
				if (indexOfArray.contains(s2)) {
					int i = indexOfArray.indexOf(s2);
					System.out.println("%%%%%%%%%inside if loop%%%%%%%%");
					//System.out.println("this is a test: " + vr.getName());
					RobotList.set(i, r);
					System.out.println(indexOfArray);
					System.out.println("The new Vector is: " + RobotList); 
				} else {
					System.out.println("inside else: ");
					System.out.print("Array size if :  " + RobotList.size());
				// else add to vector
					RobotList.add(r);
					System.out.println("The new Vector is: " + RobotList); 
					String indexOfRobots = r.getName();
					indexOfArray.add(indexOfRobots);
					System.out.println(indexOfArray);
					System.out.println("The new Vector is: " + RobotList); 
				}
				
			}
		m.move(RobotList);
		//}
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
