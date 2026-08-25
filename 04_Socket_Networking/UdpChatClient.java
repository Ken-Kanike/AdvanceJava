package socket_networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * <h1>UDP Datagram Packet Sender Client</h1>
 * <p>
 * Demonstrates sending UDP datagrams without establishing a permanent connection:
 * <ul>
 *   <li>Constructing <code>DatagramPacket</code> with destination IP and Port.</li>
 *   <li>Sending packet via <code>DatagramSocket.send()</code>.</li>
 *   <li>Waiting for acknowledgment datagram packet.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class UdpChatClient {

    public static void main(String[] args) {
        System.out.println("📡 UDP Datagram Client initialized.");
        System.out.println("Enter text packets to send to UDP Server (or 'exit' to quit):");

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverIp = InetAddress.getByName("127.0.0.1");
            int serverPort = UdpChatServer.UDP_PORT;

            while (true) {
                System.out.print("> ");
                String message = scanner.nextLine();

                byte[] sendBuffer = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverIp, serverPort);
                socket.send(sendPacket);

                // Wait for Server ACK Packet
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.setSoTimeout(3000); // 3 second timeout for response
                try {
                    socket.receive(receivePacket);
                    String ack = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Server Response: " + ack);
                } catch (Exception e) {
                    System.out.println("⚠️ No response received within timeout.");
                }

                if ("exit".equalsIgnoreCase(message.trim())) {
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("UDP Client error: " + e.getMessage());
        }
    }
}
