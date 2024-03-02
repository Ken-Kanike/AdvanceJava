//        import java.io.IOException;
//        import java.io.PrintWriter;
//        import java.util.Date;
//
//        import javax.servlet.ServletException;
//        import javax.servlet.annotation.WebServlet;
//        import javax.servlet.http.HttpServlet;
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;
//        import javax.servlet.http.HttpSession;
//
//        @WebServlet("/SessionInfoServlet")
//        public class SessionInfoServlet extends HttpServlet {
//            private static final long serialVersionUID = 1L;
//
//            protected void doGet(HttpServletRequest request, HttpServletResponse response)
//                    throws ServletException, IOException {
//                response.setContentType("text/html");
//                PrintWriter out = response.getWriter();
//
//                // Get the session, create one if not exists
//                HttpSession session = request.getSession(true);
//
//                // Get the last accessed time
//                Date lastAccessedTime = new Date(session.getLastAccessedTime());
//
//                // Set the session timeout to 5 minutes (300 seconds)
//                session.setMaxInactiveInterval(300);
//
//                // Display last accessed time
//                out.println("<html><body>");
//                out.println("<h2>Session Information</h2>");
//                out.println("Last Accessed Time: " + lastAccessedTime);
//                out.println("<br>");
//                out.println("Session Timeout (seconds): " + session.getMaxInactiveInterval());
//                out.println("</body></html>");
//
//                // Invalidate the session if it has expired
//                if (isSessionExpired(session)) {
//                    session.invalidate();
//                    out.println("<p>Session has expired and is invalidated.</p>");
//                }
//            }
//
//            private boolean isSessionExpired(HttpSession session) {
//                // Check if the session is new or has expired
//                return session.isNew() || System.currentTimeMillis() - session.getLastAccessedTime() > session.getMaxInactiveInterval() * 1000;
//            }
//        }
