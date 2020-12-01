package client;

public class startUp {

	public startUp() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) 
    {
    	System.out.println("**. Java Client Application - EE402 OOP Module, DCU");
    	if(args.length==1){
    		//Client theApp = new Client(args[0]);
    		//Client theApp = new Client("127.0.0.1");
		    //theApp.getDate();
		    FrontEnd fe = new FrontEnd();
		}
    	else
    	{
    		System.out.println("Error: you must provide the address of the server");
    		System.out.println("Usage is:  java Client x.x.x.x  (e.g. java Client 192.168.7.2)");
    		System.out.println("      or:  java Client hostname (e.g. java Client localhost)");
    	}    
    	System.out.println("**. End of Application.");
    }
}
