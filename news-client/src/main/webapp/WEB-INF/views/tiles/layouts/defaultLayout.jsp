<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <style>
        <%@include file="/resources/css/style.css" %>
    </style>
    <title><t:getAsString name="title"/></title>
</head>

<body>

<header id="header">
    <t:insertAttribute name="header"/>
</header>

<section id="site-content">
    <t:insertAttribute name="body"/>
</section>

<footer id="footer">
    <t:insertAttribute name="footer"/>
</footer>

</body>

</html>