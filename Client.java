import java.io.*;
import java.net.*;
import java.util.*;

class Client {
   public  static String login(DataInputStream otherreadSource,  DataOutputStream otherwriteSource){
    try {
    String str= otherreadSource.readUTF();
    System.out.println(str);
    Scanner scan1 = new Scanner(System.in);
      String ch1 = scan1.nextLine();
    otherwriteSource.writeUTF(ch1);
    str = otherreadSource.readUTF();
    System.out.println(str);
    /// b3d kida hwa ely mstani mani 2ktab 7aga
    otherwriteSource.writeUTF("123");
    // hyb3tol elrad 
    str = otherreadSource.readUTF();
    System.out.println(str);
    return str;
  }catch(IOException E){
    return "false";
  }
   } 
  
	public static void main(String[] args)
	{
		// establish a connection by providing host and port
		// number
		try (Socket socket = new Socket("localhost", 6666)) {
			
            DataInputStream otherreadSource = new DataInputStream(socket.getInputStream());
            DataOutputStream otherwriteSource = new DataOutputStream(socket.getOutputStream());

			// object of scanner class
			Scanner sc = new Scanner(System.in);
			String line = null;


			while (!"exit".equalsIgnoreCase(line)) {	

                String str = "";
                str= otherreadSource.readUTF();
                System.out.println(str);
                Scanner scan = new Scanner(System.in);
                  String ch = scan.nextLine();
                otherwriteSource.writeUTF(ch);
/// if(ch1.equals("1")){
  if(ch.equals("1")){
               
                if(login( otherreadSource,otherwriteSource).equals("true"))
                {
                    // 2wal 7aga an b2ra mno el menu bt3 el 3ba
                     
                    str = otherreadSource.readUTF();
                     
                  
                    otherwriteSource.writeUTF( "1" );
                    int cont =0;
                    boolean blo= true;
                    int cont1 =0;
                      while( cont < 6 &&   blo) {
                            System.out.println( otherreadSource.readUTF());
                            System.out.println( otherreadSource.readUTF());
                            String g = sc.nextLine();
                            otherwriteSource.writeUTF(g);
                            String count =otherreadSource.readUTF();
                            String bloo =otherreadSource.readUTF();
                            cont = Integer.parseInt(count);
                            blo= Boolean.parseBoolean(bloo);
                      }
                      System.out.println( otherreadSource.readUTF());
                    }
                  }
                  else if(ch.equals("2")){
                    Scanner r = new Scanner(System.in);
                    System.out.println(" an bsagal u ");
                    str = otherreadSource.readUTF();
                    System.out.println(str);
                    String rr = r.nextLine();
                    /// b3d kida hwa ely mstani mani 2ktab 7aga
                    System.out.println(" an bsagal p ");
                    otherwriteSource.writeUTF(rr);
                    
                    str = otherreadSource.readUTF();
                    System.out.println(str);
                    /// b3d kida hwa ely mstani mani 2ktab 7aga
                     rr = r.nextLine();
                    otherwriteSource.writeUTF(rr); 
                    str = otherreadSource.readUTF();
                    System.out.println(str);
                    /// b3d kida hwa ely mstani mani 2ktab 7aga
                    rr = r.nextLine();
                    otherwriteSource.writeUTF("rr");
        
                    str = otherreadSource.readUTF();
                    System.out.println(str);
        
                        }
			}
			
			// closing the scanner object
			sc.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

