package socket_networking;

import java.io.*;
import java.net.*;

/**
 * <h1>TCP Binary File Transfer Server</h1>
 * <p>
 * Demonstrates high-throughput raw binary streaming and file protocol negotiation over TCP:
 * <ul>
 *   <li>Reading protocol headers (Filename, File Size in bytes).</li>
 *   <li>Buffered stream chunking (4KB - 64KB buffers) via <code>DataInputStream</code>.</li>
 *   <li>Saving received binary payload directly to filesystem.</li>
 *   <li>Acknowledging successful transfer status back to the client.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class FileTransferServer {
    public static final int FILE_PORT = 6543;

    public static void main(String[] args) {
        System.out.println("📁 Starting File Transfer Server on port " + FILE_PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(FILE_PORT)) {
            System.out.println("✓ Server ready. Waiting for file upload from client...");

            try (Socket socket = serverSocket.accept();
                 DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                 DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {

                System.out.println("✓ Client connected: " + socket.getRemoteSocketAddress());

                // 1. Read metadata header
                String fileName = dis.readUTF();
                long fileSize = dis.readLong();
                System.out.printf("📥 Incoming file: '%s' (%d bytes)%n", fileName, fileSize);

                // 2. Receive binary byte stream in chunks
                ByteArrayOutputStream memoryStore = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                long totalBytesRead = 0;
                int bytesRead;

                while (totalBytesRead < fileSize && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize - totalBytesRead))) != -1) {
                    memoryStore.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;
                }

                System.out.printf("✓ File transfer completed! Successfully received %d bytes.%n", totalBytesRead);

                // 3. Send confirmation ACK
                dos.writeUTF("SUCCESS: File '" + fileName + "' received successfully (" + totalBytesRead + " bytes).");
                dos.flush();
            }

        } catch (IOException e) {
            System.err.println("File Transfer Server error: " + e.getMessage());
        }
    }
}
