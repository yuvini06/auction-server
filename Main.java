import java.io.IOException;

public class Main {
	public static final int BASE_PORT = 2000;    
	public static CSVReader stockData;
	public static Panel infoDisplay;
	
	public static void main(String[] args) {
	
		Server server;
		stockData = new CSVReader();
		infoDisplay = new Panel();
	
		try {
			// Server application listens for client requests coming from port 2000
			server = new Server(BASE_PORT);
			server.go(); 
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
