import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException; 
import java.util.HashMap;
import java.util.Map;

public class CSVReader implements Authenticate {
    static Map<String,Item> List;
   
   public CSVReader() {
    	// Create a HashMap to store the symbol, security name and price of a stock item
    	CSVReader.List = new HashMap<String,Item>();
    	
        BufferedReader br = null;
        try{
            // Read the CSV file
            br = new BufferedReader(new FileReader("src/stockMarket/stocks.csv"));
   
            // Read the first line from the text file 
            String line = br.readLine(); 

            // Loop until all lines are read 
            while (line != null) { 
            // Use string.split() to load a string array with the values from each line of the file, using a comma as the delimiter 
                String[] details = line.split(","); 
                try{
                    Item item = new Item(details[1], details[details.length-1]);
                    List.put(details[0],item);
                } catch (NumberFormatException e1){}

            
            // Read next line before looping if end of file reached, line would be null 
            line = br.readLine(); 
            }
            br.close();    
        }
        catch (IOException ioe){ 
        	ioe.printStackTrace();
        }        
    }

    // Given the symbol return the price
    public String getPrice(String Symbol) {
        return List.get(Symbol).getItemPrice();
    }

    // Given the symbol return the security name
    public String getSecurityName(String Symbol) {
        return List.get(Symbol).getItemSecurityName();
    }
     
    // Check if the specified security is within the stock list
    public boolean isListed(String Symbol) {
    	return List.containsKey(Symbol); 
    }

    // If a suitable bid is placed, update the price of the relevant security
    public void updatePrice(String Symbol, String input, String Price){
        Item updated_item = new Item(input,Price);
        List.put(Symbol,updated_item);
    }
} 

