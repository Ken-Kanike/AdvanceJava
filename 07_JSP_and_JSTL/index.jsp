<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Advance Java - JSP Core Architecture</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f4f8; margin: 0; padding: 30px; }
        .container { max-width: 800px; margin: auto; background: white; border-radius: 12px; padding: 30px; box-shadow: 0 4px 20px rgba(0,0,0,0.08); }
        h1 { color: #1a5276; border-bottom: 2px solid #e2e8f0; padding-bottom: 12px; }
        .badge { background: #3498db; color: white; padding: 4px 10px; border-radius: 6px; font-weight: bold; }
        .table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        .table th, .table td { border: 1px solid #e2e8f0; padding: 10px 14px; text-align: left; }
        .table th { background: #2c3e50; color: white; }
        .code-box { background: #2d3748; color: #68d391; padding: 12px; border-radius: 8px; font-family: monospace; }
    </style>
</head>
<body>
<div class="container">
    <h1>🚀 JavaServer Pages (JSP) Dynamic Engine</h1>
    <p>Demonstrates JSP Directives, Scriptlets, Expressions, Declarations, and Implicit Objects.</p>

    <%-- 1. JSP Declaration --%>
    <%! 
        private int totalHits = 0;
        public String getSystemStatus() {
            return "ALL SYSTEMS OPERATIONAL (Enterprise Grade)";
        }
    %>

    <%-- 2. JSP Scriptlet --%>
    <%
        synchronized(this) {
            totalHits++;
        }
        String clientIp = request.getRemoteAddr();
        session.setAttribute("currentUser", "Alice Johnson");
    %>

    <h3>1. Server Runtime Metrics</h3>
    <table class="table">
        <tr><th>Implicit Object</th><th>Property Evaluated</th><th>Live Value</th></tr>
        <tr><td><code>request</code></td><td>Client Host Address</td><td><%= clientIp %></td></tr>
        <tr><td><code>session</code></td><td>Session ID</td><td><%= session.getId() %></td></tr>
        <tr><td><code>application</code></td><td>Server Info</td><td><%= application.getServerInfo() != null ? application.getServerInfo() : "Java Enterprise Container" %></td></tr>
        <tr><td><code>pageContext</code></td><td>System Timestamp</td><td><%= new Date() %></td></tr>
        <tr><td><code>global</code></td><td>Total Dynamic Invocations</td><td><span class="badge"><%= totalHits %></span></td></tr>
    </table>

    <h3>2. JSP Expression Language (EL) Preview</h3>
    <div class="code-box">
        Active User in Session: ${sessionScope.currentUser != null ? sessionScope.currentUser : "Alice Johnson"}<br>
        Engine Status: <%= getSystemStatus() %>
    </div>
</div>
</body>
</html>
