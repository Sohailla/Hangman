import java.io.*;
import java.net.*;
import java.util.*;

class Client {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    int PORT = 9999;

    try {
      Socket socket = new Socket("127.0.0.1", PORT);
      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

      // object of scanner class
      Scanner sc = new Scanner(System.in);
      String line = null;

      while (!"exit".equalsIgnoreCase(line)) {

        String str = dis.readUTF();
        System.out.println(str);

        String option = scan.nextLine();
        dos.writeUTF(option);


        switch(option) {
          // TODO: Handle Login case:
          case "1": {
            if (login(dis, dos).equals("true")) {
              str = dis.readUTF();
              dos.writeUTF("1");

              int countInt = 0;
              boolean bool = true;
              int cont1 = 0;

              while (countInt < 6 && bool) {
                System.out.println(dis.readUTF());
                System.out.println(dis.readUTF());
                String g = sc.nextLine();
                dos.writeUTF(g);

                String count = dis.readUTF();
                String bloo = dis.readUTF();

                countInt = Integer.parseInt(count);
                bool = Boolean.parseBoolean(bloo);
              }
              System.out.println(dis.readUTF());
            }
          }
          break;

          // TODO: Handle Register Function
          case "2" : {
            Scanner cin = new Scanner(System.in);

            System.out.println("Enter Username ");
            str = dis.readUTF();

            System.out.println(str);
            String rr = cin.nextLine();

            System.out.println("Enter Password ");
            dos.writeUTF(rr);

            str = dis.readUTF();
            System.out.println(str);

            rr = cin.nextLine();
            dos.writeUTF(rr);
            str = dis.readUTF();
            System.out.println(str);

            rr = cin.nextLine();
            dos.writeUTF("rr");

            str = dis.readUTF();
            System.out.println(str);
          }
          break;
        }
      }
      // closing the scanner object
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String login(DataInputStream dis, DataOutputStream dos) {
    Scanner cin = new Scanner(System.in);

    try {
      String str = dis.readUTF();
      System.out.println(str);

      String option_1 = cin.nextLine();
      dos.writeUTF(option_1);

      str = dis.readUTF();
      System.out.println(str);

      dos.writeUTF("123");
      str = dis.readUTF();

      System.out.println(str);
      return str;
    } catch (IOException e) {
      return ("Error: " + e);
    }
  }
}


