package socket_networking;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * <h1>TCP Echo Client</h1>
 * <p>
 * Connects to {@link TcpEchoServer} via TCP Socket stream:
 * <ul>
 *   <li>Establishes connection to <code>localhost:8080</code>.</li>
 *   <li>Sends user input over socket output stream.</li>
 *   <li>Displays echoed responses from the server.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class TcpEchoClient {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = TcpEchoServer.PORT;

        System.out.println("Connecting to TCP Server at " + host + ":" + port + "...");

        try (Socket socket = new Socket(host, port);
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);
             Scanner console = new Scanner(System.in)) {

            System.out.println("✓ Connected! Server greeting: " + serverIn.readLine());
            System.out.println("Enter messages to send (or 'bye' to quit):");

            while (true) {
                System.out.print("> ");
                String input = console.nextLine();
                serverOut.println(input);

                String response = serverIn.readLine();
                if (response == null) break;
                System.out.println("Server replied: " + response);

                if ("bye".equalsIgnoreCase(input.trim())) {
                    break;
                }
            }

        } catch (ConnectException e) {
            System.err.println("⚠️ Could not connect to server. Ensure TcpEchoServer is running first.");
        } catch (IOException e) {
            System.err.println("Client communication error: " + e.getMessage());
        }
    }
}
