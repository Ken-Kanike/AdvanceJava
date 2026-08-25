package servlets_and_enterprise;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>Database Integrated Servlet</h1>
 * <p>
 * Demonstrates integrating JDBC database operations inside Java Servlets:
 * <ul>
 *   <li>Establishing connection via <code>DriverManager</code>.</li>
 *   <li>Executing parameterized <code>PreparedStatement</code> queries based on HTTP input.</li>
 *   <li>Formatting dynamic SQL ResultSet rows into HTML table output.</li>
 *   <li>Handling SQLExceptions gracefully within web container.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
@WebServlet("/database-query")
public class DatabaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/advance_java_db?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String deptFilter = request.getParameter("department");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Database Query Results</title>");
            out.println("<style>body{font-family:Segoe UI,sans-serif;padding:30px;background:#f8f9fa} table{width:100%;border-collapse:collapse} th,td{border:1px solid #ccc;padding:8px 12px;text-align:left} th{background:#2c3e50;color:white}</style></head><body>");
            out.println("<h2>🗄️ Database Query Servlet Output</h2>");

            String sql = (deptFilter != null && !deptFilter.trim().isEmpty())
                    ? "SELECT emp_id, emp_name, department, salary FROM employees WHERE department = ?"
                    : "SELECT emp_id, emp_name, department, salary FROM employees";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                     PreparedStatement pstmt = con.prepareStatement(sql)) {

                    if (deptFilter != null && !deptFilter.trim().isEmpty()) {
                        pstmt.setString(1, deptFilter.trim());
                    }

                    try (ResultSet rs = pstmt.executeQuery()) {
                        out.println("<table>");
                        out.println("<tr><th>ID</th><th>Employee Name</th><th>Department</th><th>Salary</th></tr>");
                        while (rs.next()) {
                            out.printf("<tr><td>%d</td><td>%s</td><td>%s</td><td>$%,.2f</td></tr>%n",
                                    rs.getInt("emp_id"), rs.getString("emp_name"),
                                    rs.getString("department"), rs.getDouble("salary"));
                        }
                        out.println("</table>");
                    }
                }
            } catch (Exception e) {
                out.println("<div style='background:#fde8e8;color:#9b1c1c;padding:15px;border-radius:6px;'>");
                out.println("<strong>Database Notice:</strong> " + e.getMessage());
                out.println("<p>Simulated Table Data (Mock fallback):</p>");
                out.println("<table><tr><th>ID</th><th>Employee Name</th><th>Department</th><th>Salary</th></tr>");
                out.println("<tr><td>101</td><td>Alice Johnson</td><td>Engineering</td><td>$95,000.00</td></tr>");
                out.println("<tr><td>102</td><td>Bob Smith</td><td>Data Analytics</td><td>$82,000.00</td></tr>");
                out.println("</table></div>");
            }

            out.println("</body></html>");
        }
    }
}
