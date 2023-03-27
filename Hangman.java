import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
  private ArrayList <String> words = new ArrayList<> ();
  private String word;
  private String asterisk;
  private int count = 0;

  public Hangman() {
    try {
      File fileWriter = new File("game.txt");
      Scanner cin = new Scanner(fileWriter);

      String s;
      while (cin.hasNext()) {
        words.add(cin.nextLine());
      }

      for (int i = 0; i < words.size(); i++) {
        System.out.println(words.get(i));

      }
      this.word = words.get((int)(Math.random() * words.size()));
      this.asterisk = new String(new char[word.length()]).replace("\0", "*");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getAsterisk() {
    return asterisk;
  }

  public int getCount() {
    return count;
  }

  public void hang(String guess) {
    String newAsterisk = "";
    for (int i = 0; i < word.length(); i++) {
      if (Character.toLowerCase(word.charAt(i)) == Character.toLowerCase(guess.charAt(0)) ||
              Character.toUpperCase(word.charAt(i)) == Character.toUpperCase(guess.charAt(0))) {
        newAsterisk += guess.charAt(0);
      } else if (asterisk.charAt(i) != '*') {
        newAsterisk += word.charAt(i);
      } else {
        newAsterisk += "*";
      }
    }

    if (asterisk.equals(newAsterisk)) {
      count++;
      hangmanMassages();
    } else
      asterisk = newAsterisk;

    if (asterisk.equals(word))
      System.out.println("Correct! You win! The word was " + word);

  }

  public void hangmanMassages() {
    System.out.println(count == 6 ? "Game Over!" : "Wrong, Try Again" );
  }
}