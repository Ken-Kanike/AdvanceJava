
package ajpprexam_qbsoln;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class Q17 {
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        URL url = new URL("http://www.msbte.org");
        System.out.println("Host :  " +url.getHost());
        System.out.println("Protocol :  " +url.getProtocol());
        System.out.println("Port :  " +url.getPort());
        System.out.println("File :  " +url.getFile());
        
            //extra
        System.out.println("Path :  " +url.getPath());
        System.out.println("User Info :" +url.getUserInfo());

     
        System.out.println();
        
        URLConnection u = url.openConnection();   //  throws IO exception
        System.out.println("Content Type :  " + u.getContentType());
        System.out.println("Content Length :  " + u.getContentLength());
        System.out.println("Date :  " + new Date(u.getDate()));
        System.out.println("Permisson :  " + u.getPermission());
        System.out.println("URL :  " + u.getURL());

       
    }
    
}
