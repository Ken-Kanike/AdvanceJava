package rmi_and_distributed;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * <h1>RMI Banking Server & Registry Host</h1>
 * <p>
 * Demonstrates starting the RMI Registry and binding remote stub objects:
 * <ul>
 *   <li><code>LocateRegistry.createRegistry(1099)</code> starts embedded RMI Registry on standard port 1099.</li>
 *   <li><code>Naming.rebind("rmi://localhost:1099/BankingService", service)</code> binds the remote object for client discovery.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class RmiBankingServer {
    public static final int RMI_PORT = 1099;
    public static final String BINDING_NAME = "BankingService";

    public static void main(String[] args) {
        System.out.println("🏛️ Starting RMI Banking Server...");

        try {
            // 1. Start RMI Registry programmatically on port 1099
            LocateRegistry.createRegistry(RMI_PORT);
            System.out.println("✓ RMI Registry created on port " + RMI_PORT);

            // 2. Instantiate Remote Object Implementation
            BankingService bankingService = new BankingServiceImpl();

            // 3. Bind object in registry with URL identifier
            String rmiUrl = "rmi://localhost:" + RMI_PORT + "/" + BINDING_NAME;
            Naming.rebind(rmiUrl, bankingService);

            System.out.println("✓ Remote Object bound successfully at: " + rmiUrl);
            System.out.println("✓ RMI Server is ready and waiting for distributed client requests.");

        } catch (Exception e) {
            System.err.println("RMI Server error: " + e.getMessage());
        }
    }
}
