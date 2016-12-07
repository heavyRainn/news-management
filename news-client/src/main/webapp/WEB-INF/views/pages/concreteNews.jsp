<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Concrete news page</title>
    <script>
        <%@include file="/resources/js/jquery.min.js" %>
    </script>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</head>
<body>
<div id="news-container">
    <button onclick="goBack()" value="Back" style="width: 60px; height: 20px">Back</button>
    <ul>
        <li>
            <form action="news-client" id="add-comments-form" method="post">
                <p><a href="#"> <c:out value="${concreteNews.mainTitle}"/> </a></p>
                <p><c:out value="${concreteNews.shortTitle}"/></p>
                <p><c:out value="${concreteNews.newsText}"/></p>
                <p><c:out value="${concreteNews.date}"/></p>
                <p><c:out value="${concreteNews.photo}"/></p>
                <p><c:out value="${concreteNews.theme}"/></p>
                <p>
                    Authors (<c:forEach items="${concreteNews.authors}" var="concreteNewsAuthors">
                    <c:out value="${concreteNewsAuthors.name}"/>
                    <c:out value="${concreteNewsAuthors.surname}"/>
                </c:forEach>)
                </p>
                <p>
                    <c:forEach items="${concreteNews.tags}" var="concreteNewsTags">
                        <c:out value="${concreteNewsTags.text}"/>
                    </c:forEach>
                </p>
                <p></p>
                <div id="comments_list">
                    <p>
                        <c:forEach items="${concreteNews.comments}" var="concreteNewsComments">
                            <c:out value="${concreteNewsComments.date}"/>
                            <c:out value="${concreteNewsComments.text}"/>
                            <br>
                        </c:forEach>
                    </p>
                </div>
                <input type="hidden" name="newsId" value="${concreteNews.id}"/>
                <input type="text" name="commentText" name="commentText"/>
                <input type="hidden" name="command" value="add-comment"/>
                <input type="submit" value="Create"/>
            </form>
        </li>
    </ul>
</div>
</body>
</html>
