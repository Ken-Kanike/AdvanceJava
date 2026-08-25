import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Student Data Access Object (DAO)</h1>
 * <p>
 * Encapsulates all JDBC database interactions for Student records using the DAO design pattern.
 * </p>
 */
public class StudentDao {
    private final String url;
    private final String user;
    private final String password;

    public StudentDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void initTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                + "id INT PRIMARY KEY, "
                + "name VARCHAR(50) NOT NULL, "
                + "email VARCHAR(100), "
                + "course VARCHAR(50), "
                + "grade DOUBLE)";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute(sql);
            System.out.println("✓ [DAO] 'students' table verified/initialized.");
        }
    }

    public boolean insertStudent(StudentModel s) throws SQLException {
        String sql = "INSERT INTO students (id, name, email, course, grade) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getCourse());
            ps.setDouble(5, s.getGrade());
            return ps.executeUpdate() > 0;
        }
    }

    public List<StudentModel> getAllStudents() throws SQLException {
        List<StudentModel> list = new ArrayList<>();
        String sql = "SELECT id, name, email, course, grade FROM students ORDER BY id";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new StudentModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getDouble("grade")
                ));
            }
        }
        return list;
    }

    public boolean updateStudentGrade(int id, double newGrade) throws SQLException {
        String sql = "UPDATE students SET grade = ? WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newGrade);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
