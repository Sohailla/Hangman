import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Server {
  protected Socket clientSocket;
  
  public void setSocket(Socket socket) {
    this.clientSocket = socket;
  }

  public void execute() {
    try {
      DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
      DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
      impServer is = new impServer();
      Hangman hangman = new Hangman();
      
      dos.writeUTF(
        "██╗  ██╗ █████╗ ███╗   ██╗ ██████╗ ███╗   ███╗ █████╗ ███╗   ██╗\n" +
        "██║  ██║██╔══██╗████╗  ██║██╔════╝ ████╗ ████║██╔══██╗████╗  ██║\n" +
        "███████║███████║██╔██╗ ██║██║  ███╗██╔████╔██║███████║██╔██╗ ██║\n" +
        "██╔══██║██╔══██║██║╚██╗██║██║   ██║██║╚██╔╝██║██╔══██║██║╚██╗██║\n" +
        "██║  ██║██║  ██║██║ ╚████║╚██████╔╝██║ ╚═╝ ██║██║  ██║██║ ╚████║\n" +
        "╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝\n"
      );

      dos.writeUTF("Enter Your Choice: \n");
      dos.writeUTF("1. Login \n");
      dos.writeUTF("2. Register \n");

      String str = dis.readUTF();
      System.out.println("player choose: " + str);
      int choice = Integer.parseInt(str);
      boolean activation = false;

      switch (choice) {
      case 1:
        dos.writeUTF("Enter Username: ");
        String username = dis.readUTF();

        dos.writeUTF("Enter Password: ");
        String password = dis.readUTF();
        
        String onlin = is.login(username, password);

        System.out.println(onlin);
        dos.writeUTF(onlin);
        

        if (onlin.equals("true")) {
          Player player = new Player();

          player.setActivation(1);
          player.setUsername(username);

          dos.writeUTF("1. individual \n");
          dos.writeUTF("2. play in team");

          String option = dis.readUTF();
          if (option.equals("1"))

          {
            String guess = null;
            while (hangman.getCount() < 6 && hangman.getAsterisk().contains("*")) {

              dos.writeUTF("Guess any letter in the word ");
              dos.writeUTF(hangman.getAsterisk());
              // server hy2ra
              guess = dis.readUTF();
              if (guess.equals("-"))
                break;
              hangman.hang(guess);
              dos.writeUTF(String.valueOf(hangman.getCount()));
              String b = String.valueOf(hangman.getAsterisk().contains("*"));
              dos.writeUTF(b);

            }
            if (hangman.getCount() == 6) {
              dos.writeUTF("You have exceeded the number of attempts allowed");
            }
            if (guess.equals("-")) {
              dos.writeUTF("You left the game");
            }
            if (!hangman.getAsterisk().contains("*")) {
              is.EditScore(username, 10);
              dos.writeUTF("Congrats!! You are win");
            }
          } else if (option.equals("2")) {
            // TODO: ... 
          } else {
            dos.writeUTF("Are You Stupid or something??! Choose 1 OR 2");
          }
        }
        break;

      case 2:
        dos.writeUTF("Enter username : ");
        username = dis.readUTF();

        dos.writeUTF("Enter nickname: ");
        String nickname = dis.readUTF();

        dos.writeUTF("Enter Password: ");
        password = dis.readUTF();

        String b = String.valueOf(is.Register(username, nickname, password));
        dos.writeUTF(b);
        break;

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}