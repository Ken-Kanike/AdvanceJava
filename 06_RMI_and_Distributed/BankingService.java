package rmi_and_distributed;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <h1>RMI Remote Banking Service Interface</h1>
 * <p>
 * Declares the distributed contract for Remote Method Invocation:
 * <ul>
 *   <li>Extends <code>java.rmi.Remote</code> marker interface.</li>
 *   <li>Every method declares <code>throws RemoteException</code>.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public interface BankingService extends Remote {

    /**
     * Queries the account balance remotely.
     *
     * @param accountNumber Account ID
     * @return Current balance
     * @throws RemoteException On RMI communication or network failure
     */
    double getBalance(int accountNumber) throws RemoteException;

    /**
     * Deposits funds into the remote bank account.
     *
     * @param accountNumber Account ID
     * @param amount Deposit amount
     * @return New updated balance
     * @throws RemoteException On RMI communication or network failure
     */
    double deposit(int accountNumber, double amount) throws RemoteException;

    /**
     * Withdraws funds from the remote bank account.
     *
     * @param accountNumber Account ID
     * @param amount Withdrawal amount
     * @return New updated balance
     * @throws RemoteException On RMI communication or network failure
     */
    double withdraw(int accountNumber, double amount) throws RemoteException;

    /**
     * Returns account holder information.
     *
     * @param accountNumber Account ID
     * @return Summary string
     * @throws RemoteException On RMI communication or network failure
     */
    String getAccountSummary(int accountNumber) throws RemoteException;
}
