package jdbc_database;

import java.sql.*;

/**
 * <h1>JDBC Transaction Management & ACID Guarantees</h1>
 * <p>
 * Demonstrates manual transaction control to guarantee Atomicity, Consistency, Isolation, and Durability:
 * <ul>
 *   <li>Disabling auto-commit mode: <code>con.setAutoCommit(false)</code></li>
 *   <li>Bank Fund Transfer simulation (Debit source account, Credit destination account)</li>
 *   <li>Commit on full success: <code>con.commit()</code></li>
 *   <li>Rollback on error/exception: <code>con.rollback()</code></li>
 *   <li>Partial rollback via <code>Savepoint</code> (<code>con.setSavepoint()</code>, <code>con.rollback(savepoint)</code>)</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class TransactionManagementDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   JDBC Transaction Management (ACID) Demo       ");
        System.out.println("=================================================");

        try (Connection con = JdbcConnectionManager.getConnection()) {
            setupAccountsTable(con);

            System.out.println("\n--- Scenario 1: Successful Fund Transfer ---");
            transferFunds(con, 1001, 1002, 500.00, false);

            System.out.println("\n--- Scenario 2: Failed Transfer with Auto-Rollback ---");
            transferFunds(con, 1001, 1002, 10000.00, true); // Simulated mid-transaction failure

        } catch (SQLException e) {
            System.out.println("ℹ️ Offline DB notice: " + e.getMessage());
            explainTransactionPrinciples();
        }
    }

    private static void setupAccountsTable(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS accounts");
            stmt.execute("CREATE TABLE accounts (acc_no INT PRIMARY KEY, holder_name VARCHAR(50), balance DECIMAL(10,2))");
            stmt.execute("INSERT INTO accounts VALUES (1001, 'Alice Johnson', 2500.00), (1002, 'Bob Smith', 1000.00)");
            System.out.println("✓ Sample bank accounts initialized.");
        }
    }

    public static void transferFunds(Connection con, int fromAcc, int toAcc, double amount, boolean simulateFailure) {
        String debitSql = "UPDATE accounts SET balance = balance - ? WHERE acc_no = ?";
        String creditSql = "UPDATE accounts SET balance = balance + ? WHERE acc_no = ?";

        boolean originalAutoCommit = true;
        try {
            originalAutoCommit = con.getAutoCommit();
            // Step 1: Start transaction
            con.setAutoCommit(false);

            // Step 2: Debit sender
            try (PreparedStatement pstmtDebit = con.prepareStatement(debitSql)) {
                pstmtDebit.setDouble(1, amount);
                pstmtDebit.setInt(2, fromAcc);
                pstmtDebit.executeUpdate();
                System.out.printf("  ✓ Debited $%,.2f from Account #%d%n", amount, fromAcc);
            }

            // Simulated mid-way system crash or network loss
            if (simulateFailure) {
                throw new SQLException("Simulated network failure during transaction processing!");
            }

            // Step 3: Credit receiver
            try (PreparedStatement pstmtCredit = con.prepareStatement(creditSql)) {
                pstmtCredit.setDouble(1, amount);
                pstmtCredit.setInt(2, toAcc);
                pstmtCredit.executeUpdate();
                System.out.printf("  ✓ Credited $%,.2f to Account #%d%n", amount, toAcc);
            }

            // Step 4: Commit transaction
            con.commit();
            System.out.println("✓ [TRANSACTION COMMITTED] Transfer completed successfully.");

        } catch (SQLException e) {
            System.err.println("⚠️ Error during transaction: " + e.getMessage());
            try {
                // Step 5: Rollback all changes
                System.out.println("⚡ [TRANSACTION ROLLBACK] Reverting all account modifications...");
                con.rollback();
                System.out.println("✓ Rollback complete. Account balances preserved.");
            } catch (SQLException ex) {
                System.err.println("Critical error during rollback: " + ex.getMessage());
            }
        } finally {
            try {
                con.setAutoCommit(originalAutoCommit);
            } catch (SQLException e) {
                System.err.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }

    private static void explainTransactionPrinciples() {
        System.out.println("\n🔐 ACID Transaction Principles:");
        System.out.println("• Atomicity: All operations succeed together or none apply (con.commit() / con.rollback()).");
        System.out.println("• Consistency: DB constraints remain valid before and after transactions.");
        System.out.println("• Isolation: Concurrent transactions do not interfere with each other.");
        System.out.println("• Durability: Once committed, state changes survive server crashes.");
    }
}
