<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Default header</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
</head>
<body>

<h1>News portal </h1>
<label class="localization">
    <a href="${pageContext.request.contextPath}/news-client?command=change-locale&language=en_EN">EN</a>
    <a href="${pageContext.request.contextPath}/news-client?command=change-locale&language=ru_RU">RU</a>
</label>
</body>
</html>
