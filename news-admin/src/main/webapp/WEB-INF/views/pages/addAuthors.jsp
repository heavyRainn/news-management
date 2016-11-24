<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Add authors</title>
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
        <%@include file="/resources/js/jquery.simplePagination.authors.js" %>
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
<div id="authors-form">
    <c:forEach items="${allAuthors}" var="allAuthors">
        <p>
            <c:out value="${allAuthors.name}"/>
            <c:out value="${allAuthors.surname}"/>
        </p>
    </c:forEach>
    <div id="pager"></div>
    <p></p>
    <div>
        <c:url value="/addAuthors" var="addAuthorUrl"/>
        <form action="${addAuthorUrl}" method="post">
            <p>
                <label for="name">Name :</label>
                <input type="text" id="name" name="name"/>
                <label for="surname">Surname :</label>
                <input type="text" id="surname" name="surname"/>
            </p>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn">Create</button>
        </form>
    </div>
</div>
</body>
</html>
