import java.net.*;

class DemoInet {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        InetAddress google = InetAddress.getByName("www.google.com");
        System.out.println(google);
        System.out.println(google.getHostAddress());

        InetAddress[] googleAddresses = InetAddress.getAllByName("www.google.com");
        for (InetAddress address : googleAddresses) {
            System.out.println(address);
        }
    }
}