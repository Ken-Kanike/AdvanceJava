<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JSP Standard Action Tags - JavaBean Integration</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #f7fafc; padding: 30px; }
        .box { background: white; max-width: 650px; margin: auto; padding: 25px; border-radius: 10px; border-left: 6px solid #4299e1; box-shadow: 0 4px 12px rgba(0,0,0,0.05); }
        code { background: #edf2f7; padding: 2px 6px; border-radius: 4px; }
    </style>
</head>
<body>
<div class="box">
    <h2>☕ JSP JavaBean Standard Actions</h2>
    <p>Demonstrates standard Java action tags: <code>&lt;jsp:useBean&gt;</code>, <code>&lt;jsp:setProperty&gt;</code>, and <code>&lt;jsp:getProperty&gt;</code>.</p>

    <%-- 1. Instantiate JavaBean in request scope --%>
    <jsp:useBean id="employee" class="java_beans_and_enterprise.EmployeeBean" scope="request" />

    <%-- 2. Populate JavaBean properties --%>
    <jsp:setProperty name="employee" property="empId" value="101" />
    <jsp:setProperty name="employee" property="name" value="Alice Johnson" />
    <jsp:setProperty name="employee" property="department" value="Enterprise Software Architecture" />
    <jsp:setProperty name="employee" property="salary" value="98500.0" />

    <h3>Instantiated JavaBean Properties:</h3>
    <ul>
        <li><b>Employee ID:</b> <jsp:getProperty name="employee" property="empId" /></li>
        <li><b>Full Name:</b> <jsp:getProperty name="employee" property="name" /></li>
        <li><b>Department:</b> <jsp:getProperty name="employee" property="department" /></li>
        <li><b>Salary:</b> $<jsp:getProperty name="employee" property="salary" /></li>
    </ul>

    <p><i>Note: JavaBean instances are encapsulated POJOs implementing <code>java.io.Serializable</code> with a no-arg constructor.</i></p>
</div>
</body>
</html>
