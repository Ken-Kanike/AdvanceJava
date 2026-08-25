package socket_networking;

import java.net.*;

/**
 * <h1>IP Multicast Socket Group Communication Demo</h1>
 * <p>
 * Demonstrates one-to-many group broadcasting using UDP Multicasting:
 * <ul>
 *   <li><b>MulticastSocket</b>: Socket configured to join Class D multicast address ranges (<code>224.0.0.0</code> to <code>239.255.255.255</code>).</li>
 *   <li><b>InetSocketAddress & NetworkInterface</b>: Joining and leaving multicast groups cleanly.</li>
 *   <li><b>DatagramPacket Broadcast</b>: Transmitting a single packet that is duplicated by network switches to all group subscribers.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class MulticastGroupDemo {
    public static final String MULTICAST_IP = "230.0.0.1";
    public static final int MULTICAST_PORT = 4446;

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     Java IP Multicast Group Networking Demo     ");
        System.out.println("=================================================");

        // Sender and Receiver simulation using background thread
        Thread receiverThread = new Thread(() -> runMulticastReceiver(), "MulticastReceiverThread");
        receiverThread.setDaemon(true);
        receiverThread.start();

        // Brief delay before sending multicast packet
        try {
            Thread.sleep(500);
            runMulticastSender("🚀 Hello Multicast Group! Distributed broadcast packet from AdvanceJava.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void runMulticastReceiver() {
        try {
            InetAddress group = InetAddress.getByName(MULTICAST_IP);
            try (MulticastSocket socket = new MulticastSocket(MULTICAST_PORT)) {
                SocketAddress groupAddress = new InetSocketAddress(group, MULTICAST_PORT);
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

                if (networkInterface != null) {
                    socket.joinGroup(groupAddress, networkInterface);
                    System.out.println("✓ [Multicast Receiver] Joined group " + MULTICAST_IP + " on interface " + networkInterface.getName());
                } else {
                    socket.joinGroup(group);
                    System.out.println("✓ [Multicast Receiver] Joined group " + MULTICAST_IP);
                }

                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.setSoTimeout(3000); // 3-second timeout

                System.out.println("✓ [Multicast Receiver] Listening for group broadcast messages...");
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("📬 [Multicast Packet Received]: " + msg);

                if (networkInterface != null) {
                    socket.leaveGroup(groupAddress, networkInterface);
                } else {
                    socket.leaveGroup(group);
                }
                System.out.println("✓ [Multicast Receiver] Left multicast group cleanly.");
            }
        } catch (Exception e) {
            System.out.println("ℹ️ Multicast Receiver note (network interface / permissions): " + e.getMessage());
        }
    }

    public static void runMulticastSender(String message) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress group = InetAddress.getByName(MULTICAST_IP);
            byte[] msgBytes = message.getBytes();
            DatagramPacket packet = new DatagramPacket(msgBytes, msgBytes.length, group, MULTICAST_PORT);

            socket.send(packet);
            System.out.println("📡 [Multicast Sender] Broadcasted message to group " + MULTICAST_IP + ":" + MULTICAST_PORT);
        } catch (Exception e) {
            System.out.println("ℹ️ Multicast Sender note: " + e.getMessage());
        }
    }
}
