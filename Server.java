import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server { 
    
    private static ServerSocket serverSocket; 
    
    public Server(int socket) throws IOException { 
		serverSocket = new ServerSocket(socket);	
    }
  
    public void go() throws IOException { 
   	// The server goes into a permanent loop waiting (and servicing) for client requests
		while(true) { 
			// Server makes a new Socket to communicate with this client
		    Socket socket = serverSocket.accept(); 	    
		    Client worker = new Client(socket); 
		    worker.start(); 
		}
    }

}



