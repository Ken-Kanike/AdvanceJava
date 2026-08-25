package rmi_and_distributed;

import java.rmi.Naming;

/**
 * <h1>RMI Banking Client</h1>
 * <p>
 * Demonstrates discovering and invoking methods on remote RMI objects:
 * <ul>
 *   <li><code>Naming.lookup("rmi://localhost:1099/BankingService")</code> to obtain remote stub.</li>
 *   <li>Casting to the shared {@link BankingService} interface.</li>
 *   <li>Invoking methods transparently as if they were local Java objects.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class RmiBankingClient {

    public static void main(String[] args) {
        String rmiUrl = "rmi://localhost:" + RmiBankingServer.RMI_PORT + "/" + RmiBankingServer.BINDING_NAME;

        System.out.println("Connecting to Remote RMI Server at: " + rmiUrl);

        try {
            // 1. Lookup remote object from RMI Registry
            BankingService bankingService = (BankingService) Naming.lookup(rmiUrl);
            System.out.println("✓ Remote BankingService reference acquired!");

            // 2. Invoke remote methods
            int targetAccount = 1001;

            System.out.println("\n--- 1. Querying Account Summary ---");
            String summary = bankingService.getAccountSummary(targetAccount);
            System.out.println("• " + summary);

            System.out.println("\n--- 2. Executing Remote Deposit ---");
            double newBal1 = bankingService.deposit(targetAccount, 1250.00);
            System.out.printf("• Deposited $1,250.00 | New Balance: $%,.2f%n", newBal1);

            System.out.println("\n--- 3. Executing Remote Withdrawal ---");
            double newBal2 = bankingService.withdraw(targetAccount, 500.00);
            System.out.printf("• Withdrew $500.00 | New Balance: $%,.2f%n", newBal2);

            System.out.println("\n--- Final Account Status ---");
            System.out.println("• " + bankingService.getAccountSummary(targetAccount));

        } catch (Exception e) {
            System.out.println("ℹ️ RMI Client note (ensure RmiBankingServer is running for live RPC invocation): " + e.getMessage());
            explainRmiConcepts();
        }
    }

    private static void explainRmiConcepts() {
        System.out.println("\n🌐 Remote Method Invocation (RMI) Core Architecture:");
        System.out.println("1. Remote Interface: Extends java.rmi.Remote; contract shared between client & server.");
        System.out.println("2. Remote Object: Extends UnicastRemoteObject; implements business logic on server.");
        System.out.println("3. Stub (Client): Transparent proxy that serializes parameters (Marshalling).");
        System.out.println("4. Skeleton / Dispatcher (Server): Deserializes parameters and executes actual method.");
        System.out.println("5. RMI Registry (Naming / rmiregistry): Port 1099 naming service for binding & lookup.");
    }
}
