<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>defaultMenu</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
</head>
<body>
<nav>
    <ul id="menu-v">
        <li><a href="/news-admin"><s:message code="default.menu.text.nl"/></a></li>
        <li><a href="/news-admin/addNews"><s:message code="default.menu.text.an"/></a></li>
        <li><a href="/news-admin/addAuthors"><s:message code="default.menu.text.aua"/></a></li>
        <li><a href="/news-admin/addTags"><s:message code="default.menu.text.aut"/></a></li>
    </ul>
</nav>
</body>
</html>
