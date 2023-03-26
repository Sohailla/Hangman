import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class impServer {

  public boolean Register(String username, String nickname, String password) {
    try {
      FileWriter fileWriter = new FileWriter("playerInfo.txt", true);
      PrintWriter printWriter = new PrintWriter(fileWriter);
      
      printWriter.println(username + " " + password + " " + nickname + " " + 0);
      printWriter.close();

      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public String login(String usernam, String password) {
    try {
      File file = new File("playerInfo.txt");
      Scanner cin = new Scanner(file);      
      
      while (cin.hasNext()) {
        String line = cin.nextLine();
        String[] info = line.split(" ");
        
        if (usernam.equals(info[0])) {
          return (password.equals(info[1])) ? "true" : "false";
        } else {
          continue;
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "false";
  }

  public int EditScore(String username, int newscore) {
    String pathfile = "db.txt";
    int totalScore = 0;

    try {
      FileWriter fileWriter = new FileWriter(pathfile, true);
      PrintWriter printWriter = new PrintWriter(fileWriter);
      
      printWriter.println(username + " " + newscore);
      printWriter.close();

      File file = new File(pathfile);
      Scanner cin = new Scanner(file);
      
      while (cin.hasNextLine()) {
        String currentLine = cin.nextLine();
        String[] tokens = currentLine.split(" "); 
        if (tokens[0].equals(username)) totalScore = totalScore + Integer.parseInt(tokens[1]);
      }
      return totalScore;

    } catch (IOException e) {
      e.printStackTrace();
      return -1;
    }

  }

}