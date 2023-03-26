import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer extends Server implements Runnable {

  public static void main(String[] args) {
		int PORT = 9999;
  
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server Start ");

			// accept any connection.. 
			while (true) {
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(20000000);
				
				MultiThreadServer server = new MultiThreadServer();
				server.setSocket(socket);

				Thread thread = new Thread(server);
				thread.start();				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
  }

	public void run() {
    execute();
  }
}