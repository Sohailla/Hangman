import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Server {
    protected Socket clientSocket;
    public void setSocket(Socket socket) {
        this.clientSocket = socket;
	 }
     public void execute (){
      
        try{
        DataInputStream clientreadSource=null;
        DataOutputStream   clientwriteSource= null;
        impServer is = new impServer();
        Hangman hangman=null;

             clientreadSource = new DataInputStream(clientSocket.getInputStream());
             clientwriteSource = new DataOutputStream(clientSocket.getOutputStream());
             hangman=new Hangman();
            clientwriteSource.writeUTF("1 For Login  ,  2 For Register");
            // 2wal 7aga hyb3tha client 
            String   str =clientreadSource.readUTF();
            System.out.println("client said "+str);
            int   ch1= Integer.parseInt(str);
            boolean activation = false;
            

            switch (ch1)
            {
               case 1:
               clientwriteSource.writeUTF("enter username : ");
               String   username  =clientreadSource.readUTF();
               System.out.println(username);
               clientwriteSource.writeUTF("enter password : ");
               String   pass  =clientreadSource.readUTF();
               System.out.println(pass);
               String onlin = is.login(username, pass);
    

               System.out.println(onlin);
               clientwriteSource.writeUTF(onlin);
               // game 
               if(onlin.equals("true"))
               {
                   Player player = new Player();
                 
                   player.setActivation(1);
                   player.setUsername(username);
                   //OnlinePlayer.add(player);
                   // 2wal 7aga htkatb fe egam
                   clientwriteSource.writeUTF("1 to play individual   ,   2 to play in team");
           // hyktam el player
           String  option =clientreadSource.readUTF();
            if (option.equals("1"))
            
            {
                String guess=null;
               while (hangman.getCount() < 6 &&  hangman.getAsterisk().contains("*")) 
               {  
       
                   clientwriteSource.writeUTF("Guess any letter in the word ");
                   clientwriteSource.writeUTF( hangman.getAsterisk());
                   // server hy2ra
                    guess =clientreadSource.readUTF();
                   if (guess.equals("-"))
                   break;
                   hangman.hang(guess);
                   clientwriteSource.writeUTF(String.valueOf(hangman.getCount()));
                   String b = String.valueOf(hangman.getAsterisk().contains("*"));
                   clientwriteSource.writeUTF(b);
                  

               }
               if(hangman.getCount()==6){
                clientwriteSource.writeUTF("You have exceeded the number of attempts allowed");

               }
               if(guess.equals("-"))
               {
                clientwriteSource.writeUTF("You left the game");
               }
               if( !hangman.getAsterisk().contains("*")){
                is.EditScore(username, 10);
                System.out.println(username);
                clientwriteSource.writeUTF("You win");

               }

                 
            }
            else if (option.equals("2")){

              /*   for (int i =0 ; i<  OnlinePlayer.size(); i++){
                    System.out.println(" ely mawgod "+OnlinePlayer.get(i).getUsername());
                    
                }*/
              
            }
              
            else {
               clientwriteSource.writeUTF("you enter wrong option");
            }



               }
               break;

               case 2:
               clientwriteSource.writeUTF("enter username : ");
               username  =clientreadSource.readUTF();

               clientwriteSource.writeUTF("enter nickname : ");
               String nickname  =clientreadSource.readUTF();
               System.out.println(username);
               clientwriteSource.writeUTF("enter password : ");
               pass  =clientreadSource.readUTF();
               System.out.println(pass);
               String b = String.valueOf(  is.Register(username, nickname, pass));
               clientwriteSource.writeUTF(b);
               break;

            }

            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    
    }

     
}
        



