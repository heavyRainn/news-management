<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>Filter home page</title>
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
        <%@include file="/resources/js/jquery.simplePagination.filter.js" %>
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

        function reset() {
            window.location = "http://localhost:8080/news-client/news-client?command=pagination&page=1";
        }
    </script>
</head>
<body>

<div id="filter-form">
    <form action="news-client" method="post">
        <p>
            <select name="theme">
                <c:forEach items="${allThemes}" var="t">
                    <c:if test="${theme  eq t}">
                        <option selected value="${t}">${t}</option>
                    </c:if>
                    <c:if test="${ theme ne t}">
                        <option value="${t}">${t}</option>
                    </c:if>
                </c:forEach>
            </select>
            <select name="author">
                <c:forEach items="${allAuthors}" var="allAuthors">
                    <option value="${allAuthors}">${allAuthors.name} ${allAuthors.surname}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="command" value="filter-news">
            <button type="submit" class="btn1">Filter</button>
        </p>
    </form>
</div>
<button id="resetButton" onclick="reset()">Reset</button>
<div id="news-container">
    <ul id="pagination">
        <c:forEach items="${allNews}" var="allNews">
            <form action="news-client" id="${allNews.id}" method="post">
                <li>
                    <p><a href="#" onclick="document.getElementById('${allNews.id}').submit();"> <c:out
                            value="${allNews.mainTitle}"/> </a></p>
                    <input type="hidden" name="newsId" value="${allNews.id}"/>
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
                <input type="hidden" name="command" value="concrete-news"/>
            </form>
        </c:forEach>
    </ul>
    <div id="pager"></div>
</div>
</body>

</html>
