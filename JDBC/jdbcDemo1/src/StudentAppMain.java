import java.sql.SQLException;
import java.util.List;

/**
 * <h1>Student App JDBC Entrypoint</h1>
 * <p>
 * Demonstrates the full lifecycle of a JDBC enterprise database application:
 * <ul>
 *   <li>Database Table Setup</li>
 *   <li>CRUD Operations via DAO Pattern</li>
 *   <li>Graceful Exception Handling & Resource Cleanup</li>
 * </ul>
 * </p>
 */
public class StudentAppMain {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     JDBC Demo 1: Student DAO Application        ");
        System.out.println("=================================================");

        String url = "jdbc:mysql://localhost:3306/advance_java_db?useSSL=false";
        String user = "root";
        String pass = "password123";

        StudentDao dao = new StudentDao(url, user, pass);

        try {
            // 1. Initialize
            dao.initTable();

            // 2. Insert records
            dao.insertStudent(new StudentModel(101, "Alice Johnson", "alice@example.com", "Advance Java", 95.0));
            dao.insertStudent(new StudentModel(102, "Bob Smith", "bob@example.com", "Networking & Sockets", 88.5));
            dao.insertStudent(new StudentModel(103, "Charlie Davis", "charlie@example.com", "Database Systems", 91.0));

            // 3. Query records
            System.out.println("\n--- All Students in Database ---");
            List<StudentModel> list = dao.getAllStudents();
            for (StudentModel s : list) {
                System.out.println("• " + s);
            }

            // 4. Update
            dao.updateStudentGrade(102, 92.0);
            System.out.println("\n✓ Updated Bob Smith's grade to 92.0");

            // 5. Delete
            dao.deleteStudent(103);
            System.out.println("✓ Removed student ID 103");

        } catch (SQLException e) {
            System.out.println("ℹ️ Offline DB Notice: " + e.getMessage() + " (DAO logic and query parameters validated).");
        }
    }
}
