<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Default header</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
</head>
<body>

<h1><s:message code="default.header.text"/></h1>

<label class="logoutLblPos">
    <c:url value="/logout" var="logout"/>
    <form action="${logout}" method="post">
        <s:message code="welcome.label.admin"/>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button type="submit" class="btn"><s:message code="button.logout"/></button>
    </form>
</label>
<label class="localization">
    <a href="<s:url value="?locale=en"/>">EN</a>
    <a href="<s:url value="?locale=ru"/>">RU</a>
</label>
</body>
</html>
