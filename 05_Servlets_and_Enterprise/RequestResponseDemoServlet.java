package servlets_and_enterprise;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h1>HttpServletRequest & HttpServletResponse Demo</h1>
 * <p>
 * Demonstrates HTTP request processing and response crafting:
 * <ul>
 *   <li>Parsing GET query parameters and POST form data.</li>
 *   <li>Enumerating request headers (Accept, User-Agent, Host).</li>
 *   <li>Setting response headers, status codes (200 OK, 400 Bad Request), and MIME types.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
@WebServlet("/request-response")
public class RequestResponseDemoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("X-Custom-Server", "AdvanceJava-Enterprise-Engine/1.0");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Request & Response Inspector</title>");
            out.println("<style>body{font-family:Segoe UI, sans-serif;padding:30px;background:#f8f9fa} table{border-collapse:collapse;width:100%} th,td{border:1px solid #dee2e6;padding:8px 12px;text-align:left} th{background:#e9ecef}</style></head><body>");
            out.println("<h2>HTTP Request & Response Inspector</h2>");

            out.println("<h3>1. Request Metadata</h3>");
            out.println("<table>");
            out.println("<tr><th>Property</th><th>Value</th></tr>");
            out.println("<tr><td>HTTP Method</td><td>" + request.getMethod() + "</td></tr>");
            out.println("<tr><td>Request URI</td><td>" + request.getRequestURI() + "</td></tr>");
            out.println("<tr><td>Query String</td><td>" + request.getQueryString() + "</td></tr>");
            out.println("<tr><td>Server Name & Port</td><td>" + request.getServerName() + ":" + request.getServerPort() + "</td></tr>");
            out.println("<tr><td>Context Path</td><td>" + request.getContextPath() + "</td></tr>");
            out.println("</table>");

            out.println("<h3>2. Incoming HTTP Headers</h3>");
            out.println("<table><tr><th>Header Name</th><th>Header Value</th></tr>");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                out.println("<tr><td>" + headerName + "</td><td>" + request.getHeader(headerName) + "</td></tr>");
            }
            out.println("</table>");

            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentName = request.getParameter("studentName");
        String course = request.getParameter("course");

        if (studentName == null || studentName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameter: studentName");
            return;
        }

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.printf("{\"status\": \"success\", \"message\": \"Enrolled %s in %s\", \"code\": 201}%n", studentName, course);
        }
    }
}
