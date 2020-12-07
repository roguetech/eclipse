package client;


import client.Robot;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Vector;

public class ThreadedServer extends Frame implements ActionListener, WindowListener{

	private static int portNumber = 5050;
	private Button refresh;
	private Label status;
	private static Map m;
	public int x, y;
	private Robot R1;
	private Vector<Object> RobotVector;
	
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
		
		status = new Label("Not Running", Label.CENTER);
		topPanel.add(status);
		
		refresh = new Button("Refresh");
		refresh.addActionListener(this);
		
		m = new Map();
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(m, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
		
		// Server
		
		boolean listening = true;
        ServerSocket serverSocket = null;
       
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
            
            ThreadedConnectionHandler con = new ThreadedConnectionHandler(clientSocket, m);
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
