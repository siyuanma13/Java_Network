import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;


public class Client { //separate process - not using thread class 
	ArrayList<String> peers = new ArrayList<String>();
	String machineName; 
	int port;
	
	public Client(ArrayList<String> peers, int port) {
		this.peers = peers;
		this.port = port;
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
			this.machineName= inetAddress.getHostAddress();
			
		} catch (UnknownHostException e) {
			System.out.println("Failed to get host name.");
		}


	}
	
	public void runClient() { 
		Socket sock; 
		PrintWriter out;
		DataInputStream in;
		
		System.out.println("Please enter a number to send to other machines: ");
		in = new DataInputStream(System.in);
		int num = 0;
		try {
			num = in.readInt();
		} catch (IOException e) {
			System.out.println("test"+e.getMessage());
		}
		for (int i = 0; i<this.peers.size(); i++) {
			//open the connection 
			while (true) {
				try {
					sock = new Socket(peers.get(i), this.port); //create a TCP socket connecting to servers
					out = new PrintWriter(sock.getOutputStream(), true);
					out.println(this.machineName+" sent "+num + ".");
					break;
				} catch(Exception e) {
					System.out.println(this.machineName + "couldn't connect to " + this.peers.get(i)+".");
				}
			}
			//close the scanner and connections
			try {
				
				in.close();
				out.close();
				sock.close();
			} catch(Exception e) {
				System.out.println("Could not close user input scanner or connections,");
			}
			
		}
		return; 
		
	}
}
