package jdbc_database;

import java.sql.*;

/**
 * <h1>JDBC Metadata Inspection Demo</h1>
 * <p>
 * Demonstrates inspecting database and result metadata at runtime:
 * <ul>
 *   <li><b>DatabaseMetaData</b>: Database product name, version, driver name, supported transaction isolation levels, tables list.</li>
 *   <li><b>ResultSetMetaData</b>: Number of columns, column names, SQL data types, display sizes, and nullability.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class MetadataDemo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   JDBC DatabaseMetaData & ResultSetMetaData     ");
        System.out.println("=================================================");

        try (Connection con = JdbcConnectionManager.getConnection()) {
            inspectDatabaseMetadata(con);
            inspectResultSetMetadata(con);
        } catch (SQLException e) {
            System.out.println("ℹ️ Offline DB notice: " + e.getMessage());
            printMetadataReferenceSummary();
        }
    }

    public static void inspectDatabaseMetadata(Connection con) throws SQLException {
        DatabaseMetaData dbmd = con.getMetaData();
        System.out.println("\n--- Database Capabilities & Server Metadata ---");
        System.out.println("• Database Product Name:    " + dbmd.getDatabaseProductName());
        System.out.println("• Database Product Version: " + dbmd.getDatabaseProductVersion());
        System.out.println("• JDBC Driver Name:         " + dbmd.getDriverName());
        System.out.println("• JDBC Driver Version:      " + dbmd.getDriverVersion());
        System.out.println("• Max Columns In Table:     " + dbmd.getMaxColumnsInTable());
        System.out.println("• Supports Transactions:    " + dbmd.supportsTransactions());
        System.out.println("• Supports Batch Updates:   " + dbmd.supportsBatchUpdates());
        System.out.println("• Supports Savepoints:      " + dbmd.supportsSavepoints());
    }

    public static void inspectResultSetMetadata(Connection con) throws SQLException {
        String sql = "SELECT * FROM employees LIMIT 1";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            System.out.println("\n--- Table Schema & ResultSetMetaData ---");
            System.out.println("Total Columns: " + columnCount);
            System.out.printf("%-4s | %-16s | %-16s | %-12s | %-10s%n",
                    "#", "Column Name", "Data Type Name", "Column Type ID", "Nullable");
            System.out.println("------------------------------------------------------------------");

            for (int i = 1; i <= columnCount; i++) {
                String colName = rsmd.getColumnName(i);
                String typeName = rsmd.getColumnTypeName(i);
                int typeId = rsmd.getColumnType(i);
                int nullable = rsmd.isNullable(i);

                System.out.printf("%-4d | %-16s | %-16s | %-14d | %-10s%n",
                        i, colName, typeName, typeId, (nullable == ResultSetMetaData.columnNullable ? "YES" : "NO"));
            }
        }
    }

    private static void printMetadataReferenceSummary() {
        System.out.println("\n📚 Metadata Key Concepts:");
        System.out.println("1. DatabaseMetaData = con.getMetaData() -> DB-wide schema, features, driver version.");
        System.out.println("2. ResultSetMetaData = rs.getMetaData() -> Query result structure (columns, types, size).");
    }
}
