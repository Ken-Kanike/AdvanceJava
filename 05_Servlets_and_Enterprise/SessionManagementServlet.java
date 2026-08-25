package servlets_and_enterprise;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * <h1>Session Management & Cookies Servlet</h1>
 * <p>
 * Demonstrates state management across stateless HTTP requests:
 * <ul>
 *   <li><b>HttpSession</b>: Creating sessions, storing attributes, tracking visit counts.</li>
 *   <li><b>Session Lifecycle</b>: Creation time, last accessed time, inactive interval timeout.</li>
 *   <li><b>HTTP Cookies</b>: Setting, reading, and expiring client-side cookies.</li>
 *   <li><b>Session Invalidation</b>: Logging out and destroying active session tokens.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
@WebServlet("/session-demo")
public class SessionManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Action flag for logout/invalidation
        String action = request.getParameter("action");
        if ("logout".equalsIgnoreCase(action)) {
            HttpSession currentSession = request.getSession(false);
            if (currentSession != null) {
                currentSession.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/session-demo");
            return;
        }

        // Retrieve or create HTTP Session
        HttpSession session = request.getSession(true);

        // Update visit counter stored in session state
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 1;
            session.setAttribute("userAuth", "Alice_Johnson");
        } else {
            visitCount++;
        }
        session.setAttribute("visitCount", visitCount);

        // Manage Cookie
        Cookie themeCookie = new Cookie("user_theme", "dark_flame");
        themeCookie.setMaxAge(60 * 60 * 24); // 24 hours
        response.addCookie(themeCookie);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Session Management</title>");
            out.println("<style>body{font-family:Segoe UI,sans-serif;padding:30px;background:#f1f2f6}.box{background:white;padding:25px;border-radius:10px;max-width:650px;box-shadow:0 2px 10px rgba(0,0,0,0.1)}</style></head><body>");
            out.println("<div class='box'>");
            out.println("<h2>🔐 HTTP Session State & Token Tracker</h2>");
            out.println("<p><b>Session ID:</b> <code>" + session.getId() + "</code></p>");
            out.println("<p><b>Is New Session:</b> " + session.isNew() + "</p>");
            out.println("<p><b>Logged In User:</b> " + session.getAttribute("userAuth") + "</p>");
            out.println("<p><b>Session Visits in Current Browser:</b> " + visitCount + "</p>");
            out.println("<p><b>Creation Time:</b> " + new Date(session.getCreationTime()) + "</p>");
            out.println("<p><b>Last Accessed:</b> " + new Date(session.getLastAccessedTime()) + "</p>");
            out.println("<p><b>Max Inactive Timeout:</b> " + session.getMaxInactiveInterval() + " seconds</p>");

            out.println("<h3>Active Request Cookies:</h3><ul>");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    out.println("<li><b>" + c.getName() + "</b> = " + c.getValue() + "</li>");
                }
            } else {
                out.println("<li>No cookies sent with this request yet.</li>");
            }
            out.println("</ul>");

            out.println("<p><a href='session-demo?action=logout' style='color:#e74c3c;font-weight:bold'>[Log Out & Invalidate Session]</a></p>");
            out.println("</div></body></html>");
        }
    }
}
