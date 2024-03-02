import java.net.*;
import java.io.*;

public class prgServer{

    public static void main(String[] args)throws Exception
    {
        ServerSocket ss = new ServerSocket(1254);
        System.out.println("Server Started........");
        Socket soc = ss.accept();
        OutputStream out = soc.getOutputStream();
        DataOutputStream dOut = new DataOutputStream(out);
        dOut.writeUTF("Hii there..");
        dOut.close();
        out.close();
        soc.close();        
        System.out.println("Server Connected.");
        ss.close();
    }
    
}
