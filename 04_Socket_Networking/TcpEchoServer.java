package socket_networking;

import java.io.*;
import java.net.*;

/**
 * <h1>TCP Echo Server</h1>
 * <p>
 * Demonstrates stream-based, reliable, connection-oriented socket communication:
 * <ul>
 *   <li><code>ServerSocket</code> listening on a designated port.</li>
 *   <li>Accepting incoming client connections with <code>serverSocket.accept()</code>.</li>
 *   <li>Reading text data from <code>BufferedReader</code> (InputStream).</li>
 *   <li>Writing echo responses via <code>PrintWriter</code> (OutputStream).</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class TcpEchoServer {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        System.out.println("🚀 Starting TCP Echo Server on port " + PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("✓ Server is listening. Waiting for client to connect...");

            // Accept single client connection for demonstration
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("✓ Client connected from: " + clientSocket.getRemoteSocketAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                writer.println("WELCOME to TCP Echo Server! Type 'bye' to disconnect.");

                String clientMessage;
                while ((clientMessage = reader.readLine()) != null) {
                    System.out.println("📩 [Received from Client]: " + clientMessage);
                    if ("bye".equalsIgnoreCase(clientMessage.trim())) {
                        writer.println("Goodbye! Connection closed.");
                        break;
                    }
                    writer.println("ECHO: " + clientMessage);
                }
            }
            System.out.println("✓ Client session ended cleanly.");

        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}
