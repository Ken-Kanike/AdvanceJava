import java.io.*;
import java.net.*;

public class prgClient {

    
    public static void main(String[] args)throws Exception
    {
        Socket soc = new Socket("localhost", 1254);
        InputStream in =  soc.getInputStream();
        DataInputStream dIn =new DataInputStream(in);
        String st = new String(dIn.readUTF());
        System.out.println(st);
        System.out.println("Coonected to Server");
        dIn.close();
        in.close();
        soc.close();

    }

}