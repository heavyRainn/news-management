<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>Home page</title>
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
            $('#pager').pagination('selectPage', (function () {
                $('#pager').pagination('getCurrentPage');
            }));
        });

        function reset() {
            location.reload();
        }
    </script>
</head>
<body>

<div id="filter-form">
    <c:url value="/filterNews" var="filterNewsUrl"/>
    <form action="${filterNewsUrl}" method="post">
        <p>
            <select name="theme">
                <c:forEach items="${allThemes}" var="allThemes">
                    <option value="${allThemes}">${allThemes}</option>
                </c:forEach>
            </select>
            <select name="author">
                <c:forEach items="${allAuthors}" var="allAuthors">
                    <option value="${allAuthors.id}">${allAuthors.surname}</option>
                </c:forEach>
            </select>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button style="width:123px" type="submit" class="btn1"><s:message code="button.filter"/></button>
        </p>
    </form>
</div>
<button id="resetButton" onclick="reset()"><s:message code="button.reset"/></button>
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
                    <s:message code="label.authors"/> (<c:forEach items="${allNews.authors}" var="allNewsAuthors">
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
