package client;

public class startUp {

	public startUp() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) 
    {
    	System.out.println("Java Client Application");
    	if(args.length==1){
    		Robot[] RobotArray = new Robot[5];
    		String[] Names = {"ann","barry","charlie","Dave","Eddie"};
    		int size = 10;
    		
    		for(int i = 0; i < RobotArray.length; i++) {
    			RobotArray[i] = new Robot(Names[i], size);
    			System.out.println(RobotArray[i].getName());
    			FrontEnd f1 = new FrontEnd(args[0], RobotArray[i]);
    		}

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
