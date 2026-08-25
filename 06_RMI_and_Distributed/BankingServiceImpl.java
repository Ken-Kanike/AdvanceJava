package rmi_and_distributed;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h1>RMI Remote Banking Service Implementation</h1>
 * <p>
 * Implements {@link BankingService} remote interface:
 * <ul>
 *   <li>Extends <code>java.rmi.server.UnicastRemoteObject</code> to enable point-to-point remote communication.</li>
 *   <li>Maintains thread-safe in-memory distributed account state via <code>ConcurrentHashMap</code>.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class BankingServiceImpl extends UnicastRemoteObject implements BankingService {
    private static final long serialVersionUID = 1L;

    private final Map<Integer, Double> accountBalances = new ConcurrentHashMap<>();
    private final Map<Integer, String> accountHolders = new ConcurrentHashMap<>();

    public BankingServiceImpl() throws RemoteException {
        super();
        // Initialize default mock accounts
        accountBalances.put(1001, 5000.00);
        accountHolders.put(1001, "Alice Johnson");

        accountBalances.put(1002, 3200.50);
        accountHolders.put(1002, "Bob Smith");
    }

    @Override
    public synchronized double getBalance(int accountNumber) throws RemoteException {
        return accountBalances.getOrDefault(accountNumber, 0.0);
    }

    @Override
    public synchronized double deposit(int accountNumber, double amount) throws RemoteException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        double current = getBalance(accountNumber);
        double updated = current + amount;
        accountBalances.put(accountNumber, updated);
        System.out.printf("✓ [RMI Server] Deposited $%,.2f to Acc #%d. New Balance: $%,.2f%n", amount, accountNumber, updated);
        return updated;
    }

    @Override
    public synchronized double withdraw(int accountNumber, double amount) throws RemoteException {
        double current = getBalance(accountNumber);
        if (amount > current) {
            throw new IllegalArgumentException("Insufficient funds! Available balance: $" + current);
        }
        double updated = current - amount;
        accountBalances.put(accountNumber, updated);
        System.out.printf("✓ [RMI Server] Withdrew $%,.2f from Acc #%d. New Balance: $%,.2f%n", amount, accountNumber, updated);
        return updated;
    }

    @Override
    public String getAccountSummary(int accountNumber) throws RemoteException {
        String holder = accountHolders.getOrDefault(accountNumber, "Unknown Customer");
        double balance = getBalance(accountNumber);
        return String.format("Account #%d [%s] | Balance: $%,.2f", accountNumber, holder, balance);
    }
}
