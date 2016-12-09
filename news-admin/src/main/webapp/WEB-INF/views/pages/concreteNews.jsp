<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Concrete news page</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
    <script>
        <%@include file="/resources/js/jquery.min.js" %>
    </script>
    <script>
        function goBack() {
            window.history.back();
        }

        function addComment(event) {
            var commentText = $("#commentText").val();
            var newsIdValue = $("#newsId").val();
            $.post("/news-admin/comments/add",
                    {text: commentText, newsId: newsIdValue},
                    function (result) {
                        console.log(result);
                        if (result !== null) {
                            console.log("start create")
                            var rootP = document.createElement('p');

                            rootP.innerHTML = result.date + " " + result.text;
                            console.log(rootP.innerHTML);
                            $("#comments_list").append(rootP);

                            $("#news-container").prepend(successMsg('Comment successfully add'));
                            setTimeout(function () {
                                $(".msg").remove();
                            }, 5000);
                        } else {
                            alert("Error");
                        }
                    }
            );
        }

        function successMsg(text) {
            return '<div class="msg">' + text + '</div>';
        }
    </script>
</head>
<body>
<div id="news-container">
    <button onclick="goBack()" value="Back" style="width: 60px; height: 20px"><s:message code="button.back"/></button>
    <ul>
        <li>
            <input type="hidden" id="newsId" value="${concreteNews.id}"/>
            <p><a href="#"> <c:out value="${concreteNews.mainTitle}"/> </a></p>
            <p><c:out value="${concreteNews.shortTitle}"/></p>
            <p><c:out value="${concreteNews.newsText}"/></p>
            <p><c:out value="${concreteNews.date}"/></p>
            <p><img src="/news-admin/resources/images/${concreteNews.photo}"
                    alt="${concreteNews.photo}" width="100" height="100"/></p>
            <p><c:out value="${concreteNews.theme}"/></p>
            <p>
                <s:message code="label.authors"/> (<c:forEach items="${concreteNews.authors}" var="concreteNewsAuthors">
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
            <div id=" comments_list">
                <p>
                    <c:forEach items="${concreteNews.comments}" var="concreteNewsComments">
                        <c:out value="${concreteNewsComments.date}"/>
                        <c:out value="${concreteNewsComments.text}"/>
                        <br>
                    </c:forEach>
                </p>
            </div>
            <input type="text" id="commentText" name="commentText"/>
            <button onclick="addComment()" class="btn"><s:message code="button.create"/></button>
        </li>
    </ul>
</div>
</body>
</html>
