<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>

<head>
    <title>Home page</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
    <style>
        <%@include file="/resources/css/simplePagination.css" %>
    </style>
    <script>
        <%@include file="/resources/js/jquery.min.js" %>
    </script>
    <script>
        <%@include file="/resources/js/jquery.simplePagination.js" %>
    </script>
    <script>
        $(function () {
            $('#pager').pagination({
                items: ${totalCount},
                itemsOnPage: ${itemsOnPage},
                cssStyle: 'light-theme',
            })
        });

        $(function () {
            $('#pager').pagination('selectPage', $(function () {
                $('#pager').pagination('getCurrentPage');
            }));
        });
    </script>
</head>
<body>
<div id="news-container">
    <ul id="pagination">
        <c:forEach items="${allNews}" var="allNews">
            <li>
                <p><a href="/news-admin/seeNews/${allNews.id}"> <c:out value="${allNews.mainTitle}"/> </a></p>
                <p><c:out value="${allNews.shortTitle}"/></p>
                <p><c:out value="${allNews.date}"/></p>
                <p><c:out value="${allNews.photo}"/></p>
                <p><c:out value="${allNews.theme}"/></p>
                <p>
                    by (<c:forEach items="${allNews.authors}" var="allNewsAuthors">
                    <c:out value="${allNewsAuthors.name}"/>
                    <c:out value="${allNewsAuthors.surname}"/>
                </c:forEach>)
                </p>
                <p>
                    <c:forEach items="${allNews.tags}" var="allNewsTags">
                        <c:out value="${allNewsTags.text}"/>
                    </c:forEach>
                </p>
            </li>
        </c:forEach>
    </ul>
    <div id="pager"></div>
</div>
</body>

</html>
