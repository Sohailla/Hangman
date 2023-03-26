import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {
	private ArrayList <String> words=new ArrayList<>();
	private  String word;
	private String asterisk;
	private  int count = 0;
	Hangman (){

	try {
		
		File fw= new File("words.txt");         
        Scanner sc = new Scanner(fw);
	 
        String s;
		while(sc.hasNext())
		{
        words.add(sc.nextLine());
		
     }
	 for(int i=0;i<words.size();i++){
		System.out.println(words.get(i));

	 }
		this.word = words.get((int) (Math.random() * words.size()));
		 this.asterisk = new String(new char[word.length()]).replace("\0", "*");
	
	
	}catch(IOException EW){

	}
}

 	public  String getAsterisk() {
		return asterisk;
	}


	public int getCount() {
		return count;
	}


	
	public  void hang(String guess) {
		String newasterisk = "";
		for (int i = 0; i < word.length(); i++) 
		{
			// lw el7rfa msawi el 7raf hyd5al  fe elmkanda
			if (Character.toLowerCase(word.charAt(i))== Character.toLowerCase(guess.charAt(0))||Character.toUpperCase(word.charAt(i))== Character.toUpperCase(guess.charAt(0)))
			 {
				newasterisk += guess.charAt(0);
	
			} 

			// lw el mkan da fe 7arf fe el klma dy  asterisk yro7 ygimo mn el klma el2slya w ykmal 3liha fe eklma ely by3malha guess (newasterisk )
			else if(asterisk.charAt(i) != '*') 
			{
				newasterisk += word.charAt(i);
				
			} 
			// lw el7rf da m4 da mkano y7ot ngma l7ad ma twsal ly mkano
			else {
				newasterisk += "*";
			}
		}
           
		// lw elklma 5argt mn elloop ely fo2a da m4 zyda 7ada 3n el 2dyma yb2a t5main 8alt
		if (asterisk.equals(newasterisk))
		 {
			  count++;
			 hangmanMassages();
		} 
		else 
		{
			asterisk = newasterisk;
		}

		if (asterisk.equals(word))
		 {
			System.out.println("Correct! You win! The word was " + word);
		}
	}

	
	public   void  hangmanMassages() {
		
		if (count == 6) {
			
			System.out.println( "GAME OVER!");
		}
		else{
			System.out.println("Wrong guess, try again");
		}

	}
}