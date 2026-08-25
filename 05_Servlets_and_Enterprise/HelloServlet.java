package servlets_and_enterprise;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>HelloServlet - Servlet Lifecycle Demonstration</h1>
 * <p>
 * Demonstrates the core Servlet lifecycle stages:
 * <ol>
 *   <li><code>init(ServletConfig config)</code>: Initialized once when container loads the servlet.</li>
 *   <li><code>service() / doGet() / doPost()</code>: Dispatched per HTTP client request.</li>
 *   <li><code>destroy()</code>: Invoked before container removes servlet instance.</li>
 * </ol>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/hello", "/welcome"})
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int hitCounter = 0;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        hitCounter = 0;
        System.out.println("✓ [Servlet Lifecycle] HelloServlet initialized via init().");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        synchronized (this) {
            hitCounter++;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Advance Java - HelloServlet</title>");
            out.println("<style>");
            out.println("body { font-family: 'Segoe UI', sans-serif; background: #f0f4f8; padding: 40px; }");
            out.println(".card { background: white; max-width: 600px; margin: auto; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }");
            out.println(".badge { background: #2980b9; color: white; padding: 4px 10px; border-radius: 6px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='card'>");
            out.println("<h2>🚀 Welcome to Advance Java Servlet Architecture!</h2>");
            out.println("<p>This response was generated dynamically by <code>HelloServlet.java</code>.</p>");
            out.println("<p><b>Client IP:</b> " + request.getRemoteAddr() + "</p>");
            out.println("<p><b>User-Agent:</b> " + request.getHeader("User-Agent") + "</p>");
            out.println("<p><b>Total Page Visits:</b> <span class='badge'>" + hitCounter + "</span></p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public void destroy() {
        System.out.println("✓ [Servlet Lifecycle] HelloServlet destroyed via destroy().");
        super.destroy();
    }
}
