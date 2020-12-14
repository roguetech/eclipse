package client;

//import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ThreadedConnectionHandler extends Thread{

	private Socket clientSocket = null;				// Client socket object
    private ObjectInputStream is = null;			// Input stream
    private ObjectOutputStream os = null;			// Output stream
    public Robot robotUpdate, aRobot;
    public int x, y, G=0, B=0, R=0;
    Robot[] RC = new Robot[2]; 
    private ThreadedServer ts;
    
	// The constructor for the connection handler
    public ThreadedConnectionHandler(Socket clientSocket, Map m, ThreadedServer ts) {
        this.clientSocket = clientSocket;
        this.ts = ts;
    }

    // Will eventually be the thread execution method - can't pass the exception back
    public void run() {
         try {
            this.is = new ObjectInputStream(clientSocket.getInputStream());
            this.os = new ObjectOutputStream(clientSocket.getOutputStream());
            while (this.readCommand()) {}
         } 
         catch (IOException e) 
         {
        	System.out.println("XX. There was a problem with the Input/Output Communication:");
            e.printStackTrace();
         }
    }
    
    private boolean readCommand() {
        Object o = null;
        try {
        	o = is.readObject();
        } 
        catch (Exception e){    // catch a general exception
        	System.out.println(e);
        	this.closeSocket();
            return false;
        }
        System.out.println("01. <- Received a String object from the client (" + o + ").");
        
        if(o.getClass().getName().equals("client.Robot"))
    	{	
    		aRobot = (Robot) o;
    		System.out.println("p x: " + aRobot.x);
    		System.out.println(aRobot.getName());
    		try {
    			ts.updateArray(aRobot);
    		} catch(Exception e) {
    			System.out.println("Recieving Object: " + e);
    		}
    		
    		ts.collisionDetection(aRobot);
  
    	} else { 
            this.sendError("Invalid command: " + o); 
        }
       
        sendUpdate();
        return true;
    }
    
    private void sendUpdate() {
    	
    	System.out.println("****** 2");
    	Robot update = new Robot("yippie", 10);
    	if (ts.checkCollision() == true) {
			update.setWarning(true);
		} else {
			update.setWarning(false);
		}
    	
    	System.out.println("****** 3 " + update);
    	this.send(update);
    }

    // Send a generic object back to the client 
    private void send(Object o) {
        try {
            System.out.println("02. -> Sending (" + o + ") to the client.");
            this.os.writeObject(o);
            this.os.flush();
            System.out.println("##################################################");
        } 
        catch (Exception e) {
            System.out.println("XX." + e.getStackTrace());
        }
    }
    
    // Send a pre-formatted error message to the client 
    public void sendError(String message) { 
        this.send("Error:" + message);	//remember a String IS-A Object!
    }
    
    // Close the client socket 
    public void closeSocket() { //gracefully close the socket connection
        try {
            this.os.close();
            this.is.close();
            this.clientSocket.close();
        } 
        catch (Exception e) {
            System.out.println("XX. " + e.getStackTrace());
        }
    }

	public void updateMap() {
		// TODO Auto-generated method stub
		
	}

}
