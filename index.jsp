<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AI Health Assistant</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="container">
    <div class="header">
        <h1>AI Health Assistant</h1>
        <p style="margin: 5px 0 0; font-size: 14px; opacity: 0.9;">Your Virtual Medical Companion</p>
    </div>

    <div class="content">
        <div class="main-panel">
            <form action="analyze" method="post">
                <label for="symptoms" style="display: block; margin-bottom: 10px; font-weight: 600; color: #333;">Describe your symptoms:</label>
                <textarea id="symptoms" name="symptoms" placeholder="E.g., I have a high fever and a headache..." required><c:out value="${symptoms}"/></textarea>
                <button type="submit" class="btn-submit">Get AI Recommendation</button>
            </form>

            <c:if test="${not empty advice}">
                <div class="prescription-card">
                    <h3>Dr. AI Recommendation</h3>
                    <p style="line-height: 1.6; color: #333;">
                        <c:out value="${advice}"/>
                    </p>
                </div>
            </c:if>
        </div>

        <div class="history-panel">
            <span class="history-title">Recent Consultations</span>
            <c:choose>
                <c:when test="${not empty sessionScope.history}">
                    <c:forEach items="${sessionScope.history}" var="entry">
                        <div class="history-item">
                            <c:out value="${entry}"/>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p style="color: #999; font-style: italic; font-size: 13px;">No recent history.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>
