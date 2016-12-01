<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>index</title>
</head>
<body>
<c:set var="language" scope="session" value="en_EN"/>
<c:set var="url" scope="session" value="logintiles.jsp"/>
<jsp:forward page="logintiles.jsp"/>
</body>
</html>