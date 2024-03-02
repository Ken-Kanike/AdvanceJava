import java.io.*;
import java.net.*;

public class person1 {

    
    public static void main(String[] args)throws Exception
    {
        ServerSocket ss = new ServerSocket(1254);
        System.out.println("Server Started........");
        Socket soc = ss.accept();
        
        // send mssg to client 
        OutputStream out = soc.getOutputStream();
        DataOutputStream dOut = new DataOutputStream(out);
        dOut.writeUTF("Connected to Server");

        // read name from client 
        InputStream in =  soc.getInputStream();
        DataInputStream dIn =new DataInputStream(in);
        String st = new String(dIn.readUTF());
        System.out.println("Client Name : " + st);

        // send mssg to client 
        dOut.writeUTF("\t Reply From Server \n \t Welcome " + st);

        
        dIn.close();
        in.close();
        dOut.close();
        out.close();
        soc.close();        
        ss.close();
    }

}