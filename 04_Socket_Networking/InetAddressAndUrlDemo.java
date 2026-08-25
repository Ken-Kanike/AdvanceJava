package socket_networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * <h1>Java InetAddress & URL Networking Utilities</h1>
 * <p>
 * Demonstrates IP resolution, hostname lookup, and HTTP URL connection handling:
 * <ul>
 *   <li><b>InetAddress</b>: Resolving localhost, DNS lookups, IP v4 / v6 addresses, reachability check.</li>
 *   <li><b>URL & URI</b>: Parsing protocols, host, port, path, and query parameters.</li>
 *   <li><b>URLConnection / HttpURLConnection</b>: Reading HTTP response headers, response codes, and data streams.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class InetAddressAndUrlDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     Java Networking: InetAddress & URL Demo     ");
        System.out.println("=================================================");

        demonstrateInetAddress();
        demonstrateUrlAndConnection();
    }

    private static void demonstrateInetAddress() {
        System.out.println("\n--- 1. InetAddress Host & DNS Resolution ---");
        try {
            // Localhost info
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("• Local Host Name:    " + local.getHostName());
            System.out.println("• Local Host Address: " + local.getHostAddress());
            System.out.println("• Is Loopback:        " + local.isLoopbackAddress());

            // Remote DNS lookup (e.g. google.com or github.com)
            String targetHost = "github.com";
            InetAddress remote = InetAddress.getByName(targetHost);
            System.out.println("\n• Remote Host:        " + targetHost);
            System.out.println("• Resolved Primary IP: " + remote.getHostAddress());

            // All IP addresses mapped to host
            InetAddress[] allIps = InetAddress.getAllByName(targetHost);
            System.out.println("• All Associated IPs (" + allIps.length + " total):");
            for (InetAddress ip : allIps) {
                System.out.println("   └─ " + ip.getHostAddress());
            }

        } catch (UnknownHostException e) {
            System.err.println("DNS resolution error: " + e.getMessage());
        }
    }

    private static void demonstrateUrlAndConnection() {
        System.out.println("\n--- 2. URL Parsing & HTTP Connection ---");
        try {
            URI uri = new URI("https://api.github.com/users/octocat");
            URL url = uri.toURL();

            System.out.println("• Protocol: " + url.getProtocol());
            System.out.println("• Host:     " + url.getHost());
            System.out.println("• Port:     " + (url.getPort() == -1 ? url.getDefaultPort() : url.getPort()));
            System.out.println("• Path:     " + url.getPath());

            // Inspecting Connection Headers
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Java-AdvanceJava-Learning-Agent");
            connection.setConnectTimeout(4000);
            connection.setReadTimeout(4000);

            int responseCode = connection.getResponseCode();
            System.out.println("\n• HTTP Response Code: " + responseCode + " " + connection.getResponseMessage());
            System.out.println("• Content Type:       " + connection.getContentType());
            System.out.println("• Content Length:     " + connection.getContentLengthLong() + " bytes");

            // Read first few lines of payload
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                System.out.println("\n• Response Preview (First 3 lines):");
                for (int i = 0; i < 3; i++) {
                    String line = reader.readLine();
                    if (line != null) {
                        System.out.println("   " + line);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("ℹ️ URL connection preview note (offline mode or rate limited): " + e.getMessage());
        }
    }
}
