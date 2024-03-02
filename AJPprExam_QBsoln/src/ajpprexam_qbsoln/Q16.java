
package ajpprexam_qbsoln;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Q16 {
    public static void main(String[] args) {
        String host;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter host name : ");
        host = input.nextLine();
        
        try {
            
            InetAddress addres = InetAddress.getByName(host);
            System.out.println("IP Address : " + addres.getHostAddress());
            System.out.println("Host Name : "+ addres.getHostName());
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
