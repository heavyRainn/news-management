<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Add news page</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
</head>
<body>
<div id="creation-form">
    <c:url value="/addNews" var="addNewsUrl"/>
    <form action="${addNewsUrl}" method="post">
        <p>
            <label for="title"><s:message code="label.news.title"/> :</label>
            <input type="text" id="title" name="title" required/>
        </p>
        <p>
            <label for="date"><s:message code="label.news.date"/> :</label>
            <input type="date" id="date" name="date" required/>
        </p>
        <p>
            <label for="brief"><s:message code="label.news.brief"/> :</label>
            <input size="100" type="text" id="brief" name="brief" required/>
        </p>
        <p>
            <label for="content"><s:message code="label.news.content"/> :</label>
            <input size="100" ype="text" id="content" name="content" required/>
        </p>
        <p>
            <select name="theme">
                <c:forEach items="${allThemes}" var="allThemes">
                    <option value="${allThemes}">${allThemes}</option>
                </c:forEach>
            </select>
            <select name="author">
                <c:forEach items="${allAuthors}" var="allAuthors">
                    <option value="${allAuthors.id}">${allAuthors.surname}</option>
                </c:forEach>
            </select>
        </p>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button type="submit" class="btn"><s:message code="button.create"/></button>
    </form>
</div>
</body>
</html>
