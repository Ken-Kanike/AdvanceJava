package jdbc_database;

import java.sql.*;

/**
 * <h1>PreparedStatement CRUD Operations & SQL Injection Defense</h1>
 * <p>
 * Demonstrates:
 * <ul>
 *   <li>Table creation and schema preparation via DDL.</li>
 *   <li><b>Create</b>: Parameterized <code>INSERT</code> with type-safe placeholders (<code>?</code>).</li>
 *   <li><b>Read</b>: <code>SELECT</code> queries with parameterized filter criteria.</li>
 *   <li><b>Update</b>: Modifying existing records safely without string concatenation.</li>
 *   <li><b>Delete</b>: Parameterized record removal.</li>
 *   <li><b>Batch Processing</b>: Executing multiple queries in a single round-trip via <code>addBatch()</code> and <code>executeBatch()</code>.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class PreparedStatementCrudDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  JDBC PreparedStatement & CRUD Operations Demo  ");
        System.out.println("=================================================");

        try (Connection con = JdbcConnectionManager.getConnection()) {
            setupDatabaseSchema(con);

            // 1. INSERT (Create)
            insertEmployee(con, 101, "Alice Johnson", "Engineering", 95000.00);
            insertEmployee(con, 102, "Bob Smith", "Data Analytics", 82000.00);
            insertEmployee(con, 103, "Charlie Davis", "Cyber Security", 88000.00);

            // 2. READ (Select all)
            queryAllEmployees(con);

            // 3. UPDATE (Modify salary)
            updateEmployeeSalary(con, 102, 89000.00);

            // 4. READ (Select with filter)
            queryEmployeesByDepartment(con, "Engineering");

            // 5. DELETE
            deleteEmployee(con, 103);

            // Final state
            queryAllEmployees(con);

        } catch (SQLException e) {
            System.out.println("ℹ️ JDBC execution note (DB server unreachable or simulated): " + e.getMessage());
            demonstrateSqlInjectionPreventionConcept();
        }
    }

    private static void setupDatabaseSchema(Connection con) throws SQLException {
        String dropSql = "DROP TABLE IF EXISTS employees";
        String createSql = "CREATE TABLE IF NOT EXISTS employees ("
                + "emp_id INT PRIMARY KEY, "
                + "emp_name VARCHAR(100) NOT NULL, "
                + "department VARCHAR(50), "
                + "salary DECIMAL(10, 2)"
                + ")";

        try (Statement stmt = con.createStatement()) {
            stmt.execute(dropSql);
            stmt.execute(createSql);
            System.out.println("✓ [Schema] 'employees' table initialized successfully.");
        }
    }

    public static void insertEmployee(Connection con, int id, String name, String dept, double salary) throws SQLException {
        String sql = "INSERT INTO employees (emp_id, emp_name, department, salary) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, dept);
            pstmt.setDouble(4, salary);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("✓ [INSERT] Inserted " + rowsAffected + " row(s) for employee: " + name);
        }
    }

    public static void queryAllEmployees(Connection con) throws SQLException {
        String sql = "SELECT emp_id, emp_name, department, salary FROM employees ORDER BY emp_id";
        System.out.println("\n--- Current Employees List ---");
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.printf("%-8s | %-18s | %-16s | %-10s%n", "ID", "Name", "Department", "Salary");
            System.out.println("---------------------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("emp_name");
                String dept = rs.getString("department");
                double salary = rs.getDouble("salary");
                System.out.printf("%-8d | %-18s | %-16s | $%,10.2f%n", id, name, dept, salary);
            }
        }
    }

    public static void queryEmployeesByDepartment(Connection con, String dept) throws SQLException {
        String sql = "SELECT emp_id, emp_name, salary FROM employees WHERE department = ?";
        System.out.println("\n--- Employees in Department: " + dept + " ---");
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, dept);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("• ID: %d | Name: %s | Salary: $%,.2f%n",
                            rs.getInt("emp_id"), rs.getString("emp_name"), rs.getDouble("salary"));
                }
            }
        }
    }

    public static void updateEmployeeSalary(Connection con, int id, double newSalary) throws SQLException {
        String sql = "UPDATE employees SET salary = ? WHERE emp_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, id);
            int rows = pstmt.executeUpdate();
            System.out.println("✓ [UPDATE] Updated salary for employee ID " + id + " (" + rows + " row affected).");
        }
    }

    public static void deleteEmployee(Connection con, int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println("✓ [DELETE] Removed employee ID " + id + " (" + rows + " row deleted).");
        }
    }

    /**
     * Demonstrates how PreparedStatements prevent SQL Injection attacks.
     */
    private static void demonstrateSqlInjectionPreventionConcept() {
        System.out.println("\n🛡️ SQL Injection Defense Theory:");
        System.out.println("1. Vulnerable (Statement): \"SELECT * FROM users WHERE user = '\" + userInput + \"'\"");
        System.out.println("   Attacker input: ' OR '1'='1' -- (Bypasses authentication)");
        System.out.println("2. Secure (PreparedStatement): \"SELECT * FROM users WHERE user = ?\"");
        System.out.println("   Database driver pre-compiles query structure and treats user input strictly as literal data.");
    }
}
