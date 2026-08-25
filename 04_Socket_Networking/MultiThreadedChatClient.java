package socket_networking;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * <h1>Multi-Threaded Chat Client</h1>
 * <p>
 * Connects to {@link MultiThreadedChatServer} with non-blocking dual-thread architecture:
 * <ul>
 *   <li>Background Listener Thread: Continuously receives incoming broadcast messages from server.</li>
 *   <li>Main Thread: Reads user keyboard input and transmits chat messages.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class MultiThreadedChatClient {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = MultiThreadedChatServer.CHAT_PORT;

        System.out.println("Connecting to Multi-Threaded Chat Server at " + host + ":" + port + "...");

        try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // 1. Start background thread to listen for server broadcast messages
            Thread listenerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection to server closed.");
                }
            });
            listenerThread.setDaemon(true);
            listenerThread.start();

            // 2. Main thread sends user typed messages
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                out.println(msg);
                if ("/quit".equalsIgnoreCase(msg.trim())) {
                    break;
                }
            }

            socket.close();

        } catch (ConnectException e) {
            System.err.println("⚠️ Could not connect to chat server. Ensure MultiThreadedChatServer is started.");
        } catch (IOException e) {
            System.err.println("Chat client error: " + e.getMessage());
        }
    }
}
