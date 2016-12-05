<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Login page</title>
    <style>
        <%@include file="/resources/css/adminLogin.css" %>
    </style>
</head>

<body>

<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <p style="color:red">
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p style="color:red">
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username"><s:message code="label.login"/> :</label>
        <input type="text" id="username" name="username" required/>
    </p>
    <p>
        <label for="password"><s:message code="label.password"/> :</label>
        <input type="password" id="password" name="password" required/>
    </p>
    <p>
        <label for="remember-me">Remember Me?</label>
        <input type="checkbox" id="remember-me" name="remember-me"/>
    </p>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <button type="submit" class="btn"><s:message code="button.login"/></button>
</form>

</body>

</html>
