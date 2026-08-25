package socket_networking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * <h1>Java NIO Non-Blocking High-Performance Echo Server</h1>
 * <p>
 * Demonstrates high-concurrency non-blocking I/O using the Java NIO framework:
 * <ul>
 *   <li><b>Selector</b>: Multiplexes multiple channels on a single thread.</li>
 *   <li><b>ServerSocketChannel & SocketChannel</b>: Configured in non-blocking mode (<code>configureBlocking(false)</code>).</li>
 *   <li><b>SelectionKey</b>: Inspecting readiness events (<code>OP_ACCEPT</code>, <code>OP_READ</code>, <code>OP_WRITE</code>).</li>
 *   <li><b>ByteBuffer</b>: Direct buffer allocation, flipping (<code>buffer.flip()</code>), and compacting for binary/text I/O.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class JavaNioNonBlockingEchoServer {
    public static final int NIO_PORT = 7070;

    public static void main(String[] args) {
        System.out.println("⚡ Starting Java NIO Non-Blocking Echo Server on port " + NIO_PORT + "...");

        try (Selector selector = Selector.open();
             ServerSocketChannel serverChannel = ServerSocketChannel.open()) {

            // Bind server channel and configure as non-blocking
            serverChannel.bind(new InetSocketAddress(NIO_PORT));
            serverChannel.configureBlocking(false);

            // Register server channel with selector for incoming connection events
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("✓ NIO Server initialized with multiplexed Selector. Waiting for events...");

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int loopLimit = 0;
            while (true) {
                // Wait for I/O events (blocks until at least one channel is ready)
                int readyChannels = selector.select(3000); // 3s timeout for demo safety
                if (readyChannels == 0) {
                    loopLimit++;
                    if (loopLimit > 20) {
                        System.out.println("ℹ️ NIO Selector idle poll timeout reached in test harness.");
                        break;
                    }
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove(); // Remove key to avoid duplicate processing

                    if (!key.isValid()) continue;

                    // 1. New client connection ready to be accepted
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        if (client != null) {
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            System.out.println("✓ [NIO Accept] Client connected: " + client.getRemoteAddress());
                        }
                    }
                    // 2. Client data ready to be read
                    else if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        buffer.clear();
                        int bytesRead = client.read(buffer);

                        if (bytesRead == -1) {
                            System.out.println("✓ [NIO Disconnect] Client closed connection: " + client.getRemoteAddress());
                            client.close();
                            key.cancel();
                        } else {
                            buffer.flip(); // Prepare buffer for reading/writing out
                            String received = new String(buffer.array(), 0, buffer.limit()).trim();
                            System.out.println("📩 [NIO Read (" + bytesRead + " bytes)]: " + received);

                            // Echo message back to client
                            String echoMsg = "NIO-ECHO: " + received + "\n";
                            ByteBuffer responseBuffer = ByteBuffer.wrap(echoMsg.getBytes());
                            client.write(responseBuffer);
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("NIO Server Exception: " + e.getMessage());
        }
    }
}
