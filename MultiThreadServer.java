import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer extends Server implements Runnable {
	
	public void run() {
		execute();
	}

	public static void main(String [] args)throws Exception 
	{
	 int port =6666;
	 System.out.println("Multi Threaded Server started...");
	 ServerSocket serverSocket = new ServerSocket(port);
	 
	 while(true){
		 //waiting for client connection
		 Socket socket = serverSocket.accept();
		 socket.setSoTimeout(14000000);
		 MultiThreadServer server = new MultiThreadServer();
		 server.setSocket(socket);
		 //start a new server thread...
		 Thread t = new Thread(server);
		 t.start();
		 System.out.println("Thread #"  + t.getId());
	 }
	 
   }
    
}
