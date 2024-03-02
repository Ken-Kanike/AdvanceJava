import java.net.*;
import java.util.*;
import java.io.*;

public class person2{

  public static void main(String[] args)throws Exception
    {
        Socket soc = new Socket("localhost", 1254);

        // receive mssg from server
        InputStream in =  soc.getInputStream();
        DataInputStream dIn =new DataInputStream(in);
        String st1 = new String(dIn.readUTF());
        System.out.println(st1);


        // read name
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your Name: ");
        String name = sc.nextLine();

        // send name to server
        OutputStream out = soc.getOutputStream();
        DataOutputStream dOut = new DataOutputStream(out);
        dOut.writeUTF(name);

        // receive mssg from server
        String st2 = new String(dIn.readUTF());
        System.out.println(st2);


        dIn.close();
        in.close();
        dOut.close();
        out.close();
        soc.close();


    }
    
}
