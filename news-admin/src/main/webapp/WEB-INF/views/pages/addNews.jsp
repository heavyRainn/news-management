<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Add news</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
</head>
<body>
<div id="creation-form">
    <c:url value="/addNews" var="addNewsUrl"/>
    <form action="${addNewsUrl}" method="post">
        <p>
            <label for="title">Title :</label>
            <input type="text" id="title" name="title"/>
        </p>
        <p>
            <label for="date">Date :</label>
            <input type="date" id="date" name="date"/>
        </p>
        <p>
            <label for="brief">Brief :</label>
            <input type="text" id="brief" name="brief"/>
        </p>
        <p>
            <label for="content">Content :</label>
            <input type="text" id="content" name="content"/>
        </p>
        <p>
            <select>
                <c:forEach items="${allThemes}" var="allThemes">
                    <option value="1">${allThemes}</option>
                </c:forEach>
            </select>
        </p>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button type="submit" class="btn">Create</button>
    </form>
</div>
</body>
</html>
