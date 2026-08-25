package jdbc_database;

import java.sql.*;

/**
 * <h1>JDBC CallableStatement & Stored Procedures</h1>
 * <p>
 * Demonstrates calling database stored procedures and functions:
 * <ul>
 *   <li>Standard SQL escape syntax: <code>{call procedure_name(?, ?)}</code></li>
 *   <li>Binding <b>IN</b> parameters using setter methods.</li>
 *   <li>Registering <b>OUT</b> parameters using <code>registerOutParameter(index, java.sql.Types)</code>.</li>
 *   <li>Retrieving return values and out values after execution.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class CallableStatementDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   JDBC CallableStatement Stored Procedure Demo  ");
        System.out.println("=================================================");

        try (Connection con = JdbcConnectionManager.getConnection()) {
            createSampleStoredProcedure(con);

            // Invoke procedure with IN (emp_id) and OUT (calculated_bonus) parameters
            int empId = 101;
            double bonus = calculateEmployeeBonus(con, empId);
            System.out.printf("✓ Stored Procedure Result: Employee ID %d Bonus = $%,.2f%n", empId, bonus);

        } catch (SQLException e) {
            System.out.println("ℹ️ Simulated / Offline Database note: " + e.getMessage());
            explainCallableStatementPattern();
        }
    }

    private static void createSampleStoredProcedure(Connection con) throws SQLException {
        String dropProc = "DROP PROCEDURE IF EXISTS GetEmployeeBonus";
        String createProc = "CREATE PROCEDURE GetEmployeeBonus ("
                + "  IN p_emp_id INT, "
                + "  OUT p_bonus DECIMAL(10,2)"
                + ") "
                + "BEGIN "
                + "  SELECT (salary * 0.15) INTO p_bonus FROM employees WHERE emp_id = p_emp_id; "
                + "END";

        try (Statement stmt = con.createStatement()) {
            stmt.execute(dropProc);
            stmt.execute(createProc);
            System.out.println("✓ Stored procedure 'GetEmployeeBonus' created.");
        }
    }

    public static double calculateEmployeeBonus(Connection con, int empId) throws SQLException {
        String procedureCall = "{call GetEmployeeBonus(?, ?)}";
        try (CallableStatement cstmt = con.prepareCall(procedureCall)) {
            // 1. Set IN parameter
            cstmt.setInt(1, empId);

            // 2. Register OUT parameter type
            cstmt.registerOutParameter(2, Types.DECIMAL);

            // 3. Execute
            cstmt.execute();

            // 4. Retrieve OUT value
            return cstmt.getDouble(2);
        }
    }

    private static void explainCallableStatementPattern() {
        System.out.println("\n💡 CallableStatement Syntax Pattern Guide:");
        System.out.println("1. Prepare call: CallableStatement cs = con.prepareCall(\"{call ProcName(?, ?)}\");");
        System.out.println("2. Set IN values: cs.setInt(1, 101);");
        System.out.println("3. Register OUT types: cs.registerOutParameter(2, Types.VARCHAR);");
        System.out.println("4. Execute: cs.execute();");
        System.out.println("5. Read results: String outVal = cs.getString(2);");
    }
}
