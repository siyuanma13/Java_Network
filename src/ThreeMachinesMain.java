import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class ThreeMachinesMain {

	public static void main(String[] args) {
		
		
		ArrayList<String> peers = new ArrayList<String>();
		
		//get peers name
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter peer 1's fully qualified IP address.");
		String peer1 = input.nextLine();
		System.out.println("Please enter peer 2's fully qualified IP address.");
		String peer2 = input.nextLine();
		System.out.println("Please enter in the port you wish to connect on.");
		int port = input.nextInt();
		
		//get own name (because we want to display the machine's own int input also 
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getLocalHost();
			peers.add(inetAddress.getHostAddress());
			
		} catch (UnknownHostException e) {
			System.out.println("Failed to get host name.");
		}
		
		//peers.add(peer1);
		//peers.add(peer2);
		
		
		
		Client c = new Client(peers, port);
		Server s = new Server(port, peers.size());
		new Thread(new Runnable() {
			public void run() {
				s.runServer();
			}
		}).start();
		

		c.runClient();


		
		try {
			input.close();
		}catch (Exception e) {
			System.out.println("Could not close user input scanner.");
		}
	}

}


