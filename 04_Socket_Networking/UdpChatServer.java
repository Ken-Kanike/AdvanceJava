package socket_networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * <h1>UDP Datagram Packet Receiver Server</h1>
 * <p>
 * Demonstrates connectionless, packet-based UDP networking:
 * <ul>
 *   <li><code>DatagramSocket</code> bound to port 9876.</li>
 *   <li>Receiving incoming <code>DatagramPacket</code> payloads.</li>
 *   <li>Inspecting sender address and reply port.</li>
 *   <li>Transmitting an acknowledgment datagram packet back to the client.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class UdpChatServer {
    public static final int UDP_PORT = 9876;

    public static void main(String[] args) {
        System.out.println("📡 UDP Datagram Server listening on port " + UDP_PORT + "...");

        try (DatagramSocket socket = new DatagramSocket(UDP_PORT)) {
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String receivedText = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.printf("📨 [UDP Packet from %s:%d]: %s%n", clientAddress.getHostAddress(), clientPort, receivedText);

                // Send ACK response back
                String ackMessage = "ACK: Received [" + receivedText + "]";
                byte[] sendData = ackMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);

                if ("exit".equalsIgnoreCase(receivedText.trim())) {
                    System.out.println("UDP Server received termination packet. Exiting...");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("UDP Server error: " + e.getMessage());
        }
    }
}
