%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Concrete news</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
    <script>function goBack() {
        window.history.back();
    }</script>
</head>
<body>
<div id="news-container">
    <button onclick="goBack()" value="Back" style="width: 60px; height: 20px">Back</button>
    <ul>
        <li>
            <p><a href="#"> <c:out value="${concreteNews.mainTitle}"/> </a></p>
            <p><c:out value="${concreteNews.shortTitle}"/></p>
            <p><c:out value="${concreteNews.newsText}"/></p>
            <p><c:out value="${concreteNews.date}"/></p>
            <p><c:out value="${concreteNews.photo}"/></p>
            <p><c:out value="${concreteNews.theme}"/></p>
            <p>
                by (<c:forEach items="${concreteNews.authors}" var="concreteNewsAuthors">
                <c:out value="${concreteNewsAuthors.name}"/>
                <c:out value="${concreteNewsAuthors.surname}"/>
            </c:forEach>)
            </p>
            <p>
                <c:forEach items="${concreteNews.tags}" var="concreteNewsTags">
                    <c:out value="${concreteNewsTags.text}"/>
                </c:forEach>
            </p>
            <div id="comments">
                <p>
                    <c:forEach items="${concreteNews.comments}" var="concreteNewsComments">
                        <c:out value="${concreteNewsComments.text}"/>
                        <c:out value="${concreteNewsComments.date}"/>
                    </c:forEach>
                </p>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
