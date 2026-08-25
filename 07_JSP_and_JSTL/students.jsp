<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JSTL Core Tag Library Demonstration</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, sans-serif; background: #edf2f7; padding: 30px; }
        .card { background: white; max-width: 850px; margin: auto; padding: 25px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.06); }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #cbd5e0; padding: 10px 12px; text-align: left; }
        th { background: #3182ce; color: white; }
        .pass { color: #38a169; font-weight: bold; }
        .distinction { color: #805ad5; font-weight: bold; }
    </style>
</head>
<body>
<div class="card">
    <h2>📊 JSTL Core Taglib (<c:forEach>, <c:if>, <c:choose>)</h2>
    <p>Demonstrates standard JavaServer Pages Standard Tag Library (JSTL) without raw scriptlets.</p>

    <%-- Mock student array list for demo --%>
    <%
        java.util.List<java.util.Map<String, Object>> studentList = new java.util.ArrayList<>();
        java.util.Map<String, Object> s1 = new java.util.HashMap<>(); s1.put("id", 101); s1.put("name", "Alice Johnson"); s1.put("score", 96);
        java.util.Map<String, Object> s2 = new java.util.HashMap<>(); s2.put("id", 102); s2.put("name", "Bob Smith"); s2.put("score", 84);
        java.util.Map<String, Object> s3 = new java.util.HashMap<>(); s3.put("id", 103); s3.put("name", "Charlie Davis"); s3.put("score", 72);
        studentList.add(s1); studentList.add(s2); studentList.add(s3);
        request.setAttribute("students", studentList);
    %>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Student Name</th>
                <th>Score</th>
                <th>Standing / Evaluation</th>
            </tr>
        </thead>
        <tbody>
            <%-- Iterating via JSTL c:forEach --%>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td><c:out value="${student.id}" /></td>
                    <td><c:out value="${student.name}" /></td>
                    <td><c:out value="${student.score}%" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${student.score >= 90}">
                                <span class="distinction">⭐ Distinction Scholar</span>
                            </c:when>
                            <c:when test="${student.score >= 75}">
                                <span class="pass">✓ First Class Honours</span>
                            </c:when>
                            <c:otherwise>
                                <span>Passed</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
