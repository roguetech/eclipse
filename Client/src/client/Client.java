package client;

import java.net.*;
import java.io.*;

public class Client {
	
	private static int portNumber = 5050;
    private Socket socket = null;
    private ObjectOutputStream os = null;
    private ObjectInputStream is = null;
    public boolean status;
    private FrontEnd fe;

	// the constructor expects the IP address of the server - the port is fixed
    public Client(String serverIP, FrontEnd fe) {
    	System.out.println("test test test");
    	if (!connectToServer(serverIP)) {
    		System.out.println("XX. Failed to open socket connection to: " + serverIP);            
    	}
    	this.fe = fe;
    }

    private boolean connectToServer(String serverIP) {
    	try { // open a new socket to the server 
    		this.socket = new Socket(serverIP,portNumber);
    		this.os = new ObjectOutputStream(this.socket.getOutputStream());
    		this.is = new ObjectInputStream(this.socket.getInputStream());
    		System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress() 
    				+ " on port: " + this.socket.getPort());
    		System.out.println("    -> from local address: " + this.socket.getLocalAddress() 
    				+ " and port: " + this.socket.getLocalPort());
    		this.status = true;
    	} 
        catch (Exception e) {
        	System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
        	System.out.println("    Exception: " + e.toString());
        	this.status = false;
        	return false;
        }
		return true;
    }
    
    void sendCommand(Object o) {
    	
    	Robot ro = (Robot) o;
    	this.send(ro);
    	
    	try {
    		Robot r1 = (Robot) receive();
    		if(r1.getWarning() == true) {
    			System.out.println("&&&&& WARNING &&&&&");
    			fe.updateWarning(true);
    		} else {
    			fe.updateWarning(false);
    		}
    		System.out.println("05. <- The Server responded with: ");
    		System.out.println("    <- " + r1.getName());
    		os.reset();
    		
    	}
    	catch (Exception e){
    		System.out.println(e);
    		System.out.println("XX. There was an invalid object sent back from the server");
    	}
    	System.out.println("06. -- Disconnected from Server.");	
    }
    
    // method to send a generic object.
    private void send(Object o) {
    	Robot ro = (Robot) o;
		try {
		    System.out.println("02. -> Sending an object...");
		    System.out.println("test " + o.getClass().getName());
		    ro.setTime();
		    System.out.println(ro.getTime());
		    fe.updateTime();
		    os.writeObject(o);
		    os.flush();
		} 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Sending: " +  e.toString());
		}
    }

    // method to receive a generic object.
    private Object receive() 
    {
		Object o = null;
		try {
			System.out.println("03. -- About to receive an object...");
		    o = is.readObject();
		    System.out.println("04. <- Object received...");
		} 
	    catch (Exception e) {
		   System.out.println("XX. Exception Occurred on Receiving:" + e);
		}
		return o;
    }
}