import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class Client extends Thread {  
    
    private Socket connectionSocket;  
    String line; 
    private String symbol;
    private String securityName;
    private String price; 
    private String name;
    String bid;
    private String bid_price;
    int row = 10;
    
    public Client(Socket sock) { 
	this.connectionSocket = sock; 
    }
    
    public void run() { 
	try { 
	    BufferedReader in = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
	    PrintWriter out = new PrintWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));	 
	   
	    // Name of the client
	    out.print("Enter name: ");
		out.flush();
		 while(true) {
		    	name=in.readLine();
		    	if(name.equals("quit")) {
		    		connectionSocket.close();
		    		return;
		    	}
		    	else if (!name.equals("")){
		    		out.print("Enter symbol: ");
		    		out.flush();
		    		System.out.println("User: "+name+" is connected");
		    		break;
		    	}
		    	else {
		    		out.println("Name not specified");
		    		out.flush();
		    	}
		 }
		 while(true) {
			 line = in.readLine();
			 if(Main.stockData.isListed(line)) {
		    		// Item parameters if found in stock list
		    		symbol=line;
		    		price = Main.stockData.getPrice(line);
		    		securityName=Main.stockData.getSecurityName(line);
		    		if (symbol.equals("FB")) row = 0;
					else if (symbol.equals("VRTU")) row = 1;
					else if (symbol.equals("MSFT")) row = 2;
					else if (symbol.equals("GOOGL")) row = 3;
					else if (symbol.equals("YHOO")) row = 4;
					else if (symbol.equals("XLNX")) row = 5;
					else if (symbol.equals("TSLA")) row = 6;
					else if (symbol.equals("TXN")) row = 7;
		    		
		    		// Display price corresponding to the given symbol
		    		out.println("Current price: "+price);		
		    		out.flush();
		    		break;
			 } 
			 else {	// Symbol not found in stock list	
		    		out.println("-1");
		    		out.flush();
		    	}		 
		 } 
		// Placement of a bid
 		out.println("Place bid");
 		out.flush();
		
	    for(line = in.readLine();line != null && !line.equals("quit");line = in.readLine()) { 
		    while(true) {
	    			bid_price=line;	
	    	    			try {
	    	    				Double.parseDouble(bid_price);
	    	    				if(Double.parseDouble(bid_price)> Double.parseDouble(Main.stockData.getPrice(symbol))) {
	    	    					// Update item 
		    	    				Main.stockData.updatePrice(symbol,securityName,bid_price);
		    	    				price=bid_price;
		    	    				out.println("Bid successfully placed on: "+symbol+", Bid Price: "+bid_price);
			    		    		out.flush();
			    		    		System.out.println("User: "+name +", Security: " + symbol+", Bid Price:"+bid_price);
			    		    		if (row!=10) Panel.table.setValueAt(bid_price,row,2);		
									Panel.addLabel(name,securityName,bid_price);
			    		    		out.println("If you wish to place another bid enter bid price. Else type 'quit'");
			    		     		out.flush();	    			    		
	    	    				}
	    	    				else {
	    	    					out.println("Offered price is less than the current value of the security.Current price: "+price+". Re-enter bid price.");
	    	    					out.flush();	    	    					
	    	    				}
	    	    				break;
	    	    								
	    		    		}
	    		    		catch(NumberFormatException e1) {
	    		    			out.println("Invalid format for price. Re-enter bid price.");
	    		    			out.flush();
	    		    			break;
	    		    		}		
	    	    } // End of while loop 		    
	    } // End of for loop
	    System.out.println("User: "+name+" disconnected");
	}// End of try 
	catch (IOException e) { 
	    System.out.println(e); 
	} 
	try { 	    
	    this.connectionSocket.close(); 
	} catch(IOException e) {}
	
    }    
}
