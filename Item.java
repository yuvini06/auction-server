public class Item {
    private String SecurityName;
    private String Price; 

    public Item(String SecurityName, String Price) {
        this.SecurityName = SecurityName;
        this.Price = Price; 
    }
    
    public String getItemPrice() {
        return Price;
    } 

    public String getItemSecurityName() {
        return SecurityName;
    }
    
    public void setPrice(String input) {
    	this.Price=input; 	
    }
}


      
  


   


