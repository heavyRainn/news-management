<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Add tags</title>
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
        <%@include file="/resources/js/jquery.simplePagination.tags.js" %>
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
<div id="tags-form">
    <c:forEach items="${allTags}" var="allTags">
        <p>
            <c:out value="${allTags.text}"/>
        </p>
    </c:forEach>
    <div id="pager"></div>
    <div>
        <c:url value="/addTags" var="addTagsUrl"/>
        <form action="${addTagsUrl}" method="post">
            <p>
                <label for="value">Text :</label>
                <input type="text" id="value" name="value"/>
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
