package socket_networking;

import java.io.*;
import java.net.*;

/**
 * <h1>TCP Binary File Transfer Client</h1>
 * <p>
 * Transmits binary file payload with custom packet header over TCP socket:
 * <ul>
 *   <li>Sends header metadata (Filename, Size in bytes).</li>
 *   <li>Streams binary content in buffered chunks (4096 bytes).</li>
 *   <li>Receives transfer verification acknowledgment from {@link FileTransferServer}.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class FileTransferClient {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = FileTransferServer.FILE_PORT;

        System.out.println("Connecting to File Transfer Server at " + host + ":" + port + "...");

        try (Socket socket = new Socket(host, port);
             DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
             DataInputStream dis = new DataInputStream(socket.getInputStream())) {

            // Create sample in-memory test file payload
            String sampleFileName = "advance_java_architecture_manifesto.txt";
            byte[] fileData = ("=====================================================\n"
                    + " ADVANCE JAVA ENTERPRISE ARCHITECTURE CHEATSHEET\n"
                    + " Topics: Swing, Applets, JDBC, Sockets, NIO, RMI, JSP\n"
                    + "=====================================================\n").getBytes();

            long fileSize = fileData.length;

            System.out.printf("📤 Uploading '%s' (%d bytes)...%n", sampleFileName, fileSize);

            // 1. Send metadata header
            dos.writeUTF(sampleFileName);
            dos.writeLong(fileSize);

            // 2. Stream byte data
            dos.write(fileData, 0, fileData.length);
            dos.flush();

            // 3. Read ACK response
            String serverAck = dis.readUTF();
            System.out.println("✓ Server Response: " + serverAck);

        } catch (ConnectException e) {
            System.err.println("⚠️ Could not connect. Ensure FileTransferServer is running first.");
        } catch (IOException e) {
            System.err.println("Client file transfer exception: " + e.getMessage());
        }
    }
}
