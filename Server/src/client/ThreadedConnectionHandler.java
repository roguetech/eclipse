package client;

import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ThreadedConnectionHandler extends Thread{

	private Socket clientSocket = null;				// Client socket object
    private ObjectInputStream is = null;			// Input stream
    private ObjectOutputStream os = null;			// Output stream
    private DateTimeService theDateService;
    public Robot robotUpdate, aRobot;
    public int x, y, G=0, B=0, R=0;
    private Map m;
    private Graphics a, b;
    Robot[] RC = new Robot[2]; 
    private ThreadedServer ts;
    //private Vector<Point> p;
    //private Robot[] RobotArray;
    //private Vector<Object> RobotVector;
    
	// The constructor for the connection handler
    public ThreadedConnectionHandler(Socket clientSocket, Map m, ThreadedServer ts) {
        this.clientSocket = clientSocket;
        this.m = m;
        this.ts = ts;
        
        //this.RobotVector = RobotVector;
        //Set up a service object to get the current date and time
        //theDateService = new DateTimeService();
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

    // Receive and process incoming string commands from client socket 
    /*
    private boolean readCommand() {
    	//String s = null;
        Object o = null;
        try {
        	System.out.println("robot test");
        	Robot myRobot = (Robot) is.readObject();
        	myRobot.getName();
        	
        	if(myRobot.getClass().getName().equals("blimpy"))
        	{	
        		System.out.println("b");
        		//Robot aRobot = (Robot) o;
        	}
            //s = (String) is.readObject();
        	System.out.println("robot test1");
        } 
        catch (Exception e){    // catch a general exception
        	System.out.println("robot exception");
        	this.closeSocket();
            return false;
       }
        System.out.println("01. <- Received a String object from the client (" + o + ").");
        
        // At this point there is a valid String object
        // invoke the appropriate function based on the command 
        /*
        if (s.equalsIgnoreCase("Send Command")){ 
            this.getDate(); 
        }       
        else { 
            this.sendError("Invalid command: " + o); 
        }
        return true;
    }
*/
    
    private boolean readCommand() {
        //String s = null;
        Object o = null;
        try {
        	o = is.readObject();
            //s = (String) is.readObject();
        	//System.out.println("object: " + myRobot.getName());
        } 
        catch (Exception e){    // catch a general exception
        	System.out.println(e);
        	this.closeSocket();
            return false;
        }
        System.out.println("01. <- Received a String object from the client (" + o + ").");
        
        // At this point there is a valid String object
        // invoke the appropriate function based on the command 
        if(o.getClass().getName().equals("client.Robot"))
    	{	
    		aRobot = (Robot) o;
    		System.out.println("p x: " + aRobot.x);
    		System.out.println(aRobot.getName());
    		try {
    			ts.updateArray(aRobot);
    		} catch(Exception e) {
    			System.out.println(e);
    		}
    		/*
    		if (aRobot.getName().equals("a")) {
    			System.out.println("****** 1");
        		System.out.println("starting x : " + aRobot.x);
            	System.out.println("starting y : " + aRobot.y);
            	this.x = aRobot.x;
            	this.y = aRobot.y;
            	this.G = 255;
            	//RC[0] = aRobot;
            	//m = new Map();
            	//this.m.addRobot(x, y, 0, R, G, B);
            	this.m.move(x, y, 0, RC);
    		} else if (aRobot.getName().equals("b")) {
    			System.out.println("****** 1");
        		System.out.println("starting x : " + aRobot.x);
            	System.out.println("starting y : " + aRobot.y);
            	this.x = aRobot.x;
            	this.y = aRobot.y;
            	this.R = 255;
            	//aRobot.R = 255;
            	//RC[1] = aRobot;
            	//m = new Map();
            	//this.m.addRobot(x, y, 1);
            	//this.m.move(x, y, 1, RC);
    		}
    		*/
    	} else { 
            this.sendError("Invalid command: " + o); 
        }
        /*try {
        	System.out.println("****** 2");
        	String update = "test";
        	
        	System.out.println("****** 3");
        	this.send(update);
        }
        //	this.sendUpdate();
         catch (Exception e){
        	System.out.println(e);
        }*/
        
        sendUpdate();
        
       //if (o.equalsIgnoreCase("GetDate")){ 
          //  this.getDate(); 
        //}       
        //else { 
        //    this.sendError("Invalid command: " + s); 
        //}
        return true;
    }
    
    private void sendUpdate() {
    	
    	System.out.println("****** 2");
    	Robot update = new Robot("yippie");
    	System.out.println("****** 3 " + update);
    	this.send(update);
    	//System.out.println("*** Connection Closed ***");
    	//this.closeSocket();
    }
    
    // Use our custom DateTimeService Class to get the date and time
    private void getDate() {	// use the date service to get the date
        String currentDateTimeText = theDateService.getDateAndTime();
        this.send(currentDateTimeText);
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
