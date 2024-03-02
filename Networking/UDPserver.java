import java.net.*;

public class UDPserver
{
    public static void main(String[] args) throws Exception 
    {
        System.out.println("Server Started!    waiting for client......");

        byte[] b = new byte[125];
        int len = b.length;

        DatagramPacket dp = new DatagramPacket(b , len );
        DatagramSocket ds = new DatagramSocket(5555);

        ds.receive(dp);
        System.out.println("Data received..");

        // for (int i = 0; i < dp.getLength(); i++) 
        // {
        //     System.out.println((char) b[i]);            
        // }
        // ds.close();
        
        String receivedMssg = new String(dp.getData());
        System.out.println(receivedMssg);

        ds.close();
    }
}
