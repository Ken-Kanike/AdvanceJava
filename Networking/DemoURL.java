import java.io.InputStream;
import java.net.*;

public class DemoURL 
{
    public static void main(String[] args) throws Exception // MalformedURLException + UnknownHostException
    {
        URL u = new URL("https://142.251.42.68.443/index.html()");

        System.out.println("Protocol : " + u.getProtocol());
         System.out.println("Host name : " + u.getHost());
          System.out.println("File name : " + u.getFile());
           System.out.println("Port : " + u.getPort());
            System.out.println("Path : " + u.getPath());
             System.out.println("toExternalForm : " + u.toExternalForm());
        
             URLConnection ucon = u.openConnection();
             System.out.println("getContentLength : "+ucon.getContentLength());
              System.out.println("getContentType : "+ucon.getContentType());
               System.out.println("getExpiration : "+ucon.getExpiration());
                System.out.println("getLastModified : "+ucon.getLastModified());

                InputStream in = ucon.getInputStream();
                while(in.read() != -1)
                {
                    char ch = (char) in.read();
                    System.out.println(ch);

                }
    
    }
    
}
