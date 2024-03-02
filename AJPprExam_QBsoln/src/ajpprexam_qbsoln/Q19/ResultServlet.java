//        package ajpprexam_qbsoln.Q19;

//        import java.io.IOException;
//        import java.io.PrintWriter;

//        import javax.servlet.ServletException;
//        import javax.servlet.annotation.WebServlet;
//        import javax.servlet.http.HttpServlet;
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;

//        @WebServlet("/ResultServlet")

//        public class ResultServlet extends HttpServlet {
//            private static final long serialVersionUID = 1L;

//            protected void doPost(HttpServletRequest request, HttpServletResponse response)
//                    throws ServletException, IOException {

//                // Retrieve student marks from the request
//                String marksStr = request.getParameter("marks");

//                // Check if marks are provided and are numeric
//                if (marksStr != null && marksStr.matches("\\d+")) {
//                    int marks = Integer.parseInt(marksStr);

//                    // Determine if the student passed or failed
//                    String result = (marks >= 40) ? "Passed" : "Failed";

//                    // Send the response back to the client
//                    response.setContentType("text/html");
//                    PrintWriter out = response.getWriter();
//                    out.println("<html><body>");
//                    out.println("<h2>Result:</h2>");
//                    out.println("<p>Student Marks: " + marks + "</p>");
//                    out.println("<p>Result: " + result + "</p>");
//                    out.println("</body></html>");
//                } else {
//                    // Invalid marks provided
//                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid marks provided");
//                }
//            }
//        }
//
