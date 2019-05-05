package Server;


import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable{

	private ServerSocket server;
 
	public void run()
	{
		System.out.println("Server try to start");
		while(!Main.start)
		{
			System.out.println("Server waiting to start");
			System.out.println(Main.start); 
		}
		try {
			server = new ServerSocket(Main.portnumber);
			System.out.println("Server started at port " + Main.portnumber);
			while(Main.start)
			{
				Socket socket = server.accept();
				ClientThread client = new ClientThread(socket);
				Thread thread = new Thread(client);
				thread.start();
			}
			server.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
