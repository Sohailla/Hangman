import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class impServer {

    public int  EditScore(String username ,  int newscore)
    {
        String pathfile ="scor.txt";
        int totatscor= 0;
        
try{      FileWriter f = new FileWriter(pathfile, true );
          PrintWriter PW = new PrintWriter(f);
          PW.println(username+" "+newscore);
          PW.close();
     
     File fw= new File(pathfile);         
     Scanner sc = new Scanner(fw);
     while(sc.hasNextLine())
     {
         String currentLine= sc.nextLine();
         String[] tokens = currentLine.split(" ");
         if(tokens[0].equals(username) )
         {
            totatscor+=Integer.parseInt(tokens[1]);
         }
        ;

     }
     
    return  totatscor;
    
    }catch(IOException E){
        E.printStackTrace();
         return -1;
    }


    }

    
    public  boolean  Register (String usernam , String nickname ,String password)
    {
        try{

            FileWriter FW = new FileWriter("playercredentials.txt", true );
            PrintWriter PW = new PrintWriter(FW);
            PW.println(usernam+" "+password+ " "+nickname+" "+0);
            PW.close();
            return true; 

           }
        catch (IOException e) 
        {e.printStackTrace();}
        return false;
    }
    

    public   String  login (String usernam , String password)
    {
       
        try {
            File file = new File("playercredentials.txt");
            Scanner scan = new Scanner(file);
            String line;
            while(scan.hasNext())
            {
              line= scan.nextLine();
              String[] info=line.split(" ");
             if(usernam.equals(info[0])){
               
                if (password.equals(info[1])){
                  
                    return "true";
                }
                else {
                    return "false";
                }

             }
             else {
                continue;
             }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "false";
        


    }
    
}
