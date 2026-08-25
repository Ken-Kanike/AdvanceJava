package socket_networking;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * <h1>Multi-Threaded Broadcast Chat Server</h1>
 * <p>
 * Demonstrates a scalable, multi-client chat room server using Java concurrency and Sockets:
 * <ul>
 *   <li><code>ServerSocket</code> listening for concurrent incoming client connections.</li>
 *   <li>Thread Pool (<code>ExecutorService</code>) dispatching dedicated <code>ClientHandler</code> tasks.</li>
 *   <li>Thread-safe <code>CopyOnWriteArrayList</code> managing active client sessions.</li>
 *   <li>Real-time message broadcasting and graceful disconnection handling.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class MultiThreadedChatServer {
    public static final int CHAT_PORT = 9090;
    private static final List<ClientHandler> activeClients = new CopyOnWriteArrayList<>();
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        System.out.println("💬 Multi-Threaded Chat Server starting on port " + CHAT_PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(CHAT_PORT)) {
            System.out.println("✓ Chat Server online. Ready to accept multiple clients.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                activeClients.add(clientHandler);
                threadPool.execute(clientHandler);
            }
        } catch (IOException e) {
            System.err.println("Chat Server Exception: " + e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }

    /**
     * Broadcasts a message to all connected clients except optionally the sender.
     */
    public static void broadcastMessage(String message, ClientHandler sender) {
        System.out.println("[BROADCAST] " + message);
        for (ClientHandler client : activeClients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public static void removeClient(ClientHandler client) {
        activeClients.remove(client);
        broadcastMessage("📢 " + client.getUsername() + " has left the chat room. (" + activeClients.size() + " online)", null);
    }

    /**
     * Dedicated Runnable task for each connected client.
     */
    static class ClientHandler implements Runnable {
        private final Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username = "Anonymous";

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Enter your username:");
                String name = in.readLine();
                if (name != null && !name.trim().isEmpty()) {
                    this.username = name.trim();
                }

                out.println("✓ Welcome to the Chat Room, " + username + "! Type '/quit' to leave.");
                broadcastMessage("📢 " + username + " joined the chat room! (" + activeClients.size() + " online)", this);

                String clientMsg;
                while ((clientMsg = in.readLine()) != null) {
                    if ("/quit".equalsIgnoreCase(clientMsg.trim())) {
                        break;
                    }
                    broadcastMessage("[" + username + "]: " + clientMsg, this);
                }

            } catch (IOException e) {
                System.out.println("Client " + username + " disconnected abruptly.");
            } finally {
                removeClient(this);
                closeResources();
            }
        }

        public void sendMessage(String msg) {
            if (out != null) {
                out.println(msg);
            }
        }

        private void closeResources() {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null && !socket.isClosed()) socket.close();
            } catch (IOException e) {
                System.err.println("Error closing client resources: " + e.getMessage());
            }
        }
    }
}
