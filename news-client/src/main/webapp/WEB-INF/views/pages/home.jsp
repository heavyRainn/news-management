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

        function myFunction() {
            location.reload();
        }
    </script>
</head>
<body>
<div id="filter-form">
    <p>
        <select>
            <c:forEach items="${allThemes}" var="allThemes">
                <option value="${allThemes}">${allThemes}</option>
            </c:forEach>
        </select>
        <select>
            <c:forEach items="${allAuthors}" var="allAuthors">
                <option value="${allAuthors}">${allAuthors.surname}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn1">Filter</button>
        <button onclick="myFunction()">Reset</button>
    </p>
</div>
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
    <form action="news-client" id="pagination-form" method="post">
        <div id="pager"></div>
        <input type="hidden" name="command" value="pagination"/>
    </form>
</div>
</body>

</html>