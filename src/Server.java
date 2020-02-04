import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	int port;
	int numOfClients;
	
	public Server(int port, int numOfClients) {
		this.port = port;
		this.numOfClients = numOfClients;
	}
	
	public void runServer() {
		Socket sock;
		ServerSocket sSock;
		DataInputStream in; 
		
		try { //try establishing the connection 
			
			sSock = new ServerSocket(this.port); 
			int receivedCount = 0;
			while (receivedCount < this.numOfClients) { 
				//Since its just 3 machines, each with a one time int input,
				//no need to add multithreading 
				sock = sSock.accept();
				//takes input from clients: 
				in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
				String output = in.readUTF();
				System.out.println(output);
				
				sock.close();
				in.close();
				receivedCount++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
