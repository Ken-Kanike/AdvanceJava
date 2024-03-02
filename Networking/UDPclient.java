import java.net.*;

public class UDPclient 
{
    public static void main(String[] args) throws Exception 
    {
        String msg = "Hello World";
        byte[] b= msg.getBytes();

        //byte[] b = {'a','b','c','d'};
        int len = b.length;
        InetAddress ip = InetAddress.getLocalHost();
        int port = 5555;

        DatagramPacket dp = new DatagramPacket(b , len , ip , port );
        DatagramSocket ds = new DatagramSocket();

        ds.send(dp);
        System.out.println("Data sended..");
        ds.close();
        
    }
}
