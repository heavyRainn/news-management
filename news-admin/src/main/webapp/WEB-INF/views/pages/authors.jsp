<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Add authors page</title>
    <style>
        <%@include file="/resources/css/home.css" %>
    </style>
    <script>
        <%@include file="/resources/js/jquery.min.js" %>
    </script>
    <style>
        <%@include file="/resources/css/simplePagination.css" %>
    </style>
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

        function showEdit(id) {
            $("#delete_" + id).show();
            $("#update_" + id).show();
            $("#cancel_" + id).show();
            $("#" + id).prop("disabled", false);
            $("#edit_" + id).hide();
        }

        function updateAuthor(id) {
            var data = $("#" + id).val();
            var authorName = data.split(' ')[0].trim();
            var authorSurname = data.split(' ')[1].trim();
            $.post("/news-admin/authors/update", {
                id: parseInt(id),
                name: authorName,
                surname: authorSurname
            }, function (result) {
                console.log("Have response: " + result);
                if (result == "success") {
                    $("#authors-form").prepend(successMsg('Author successfully updated'));
                    setTimeout(function () {
                        $(".msg").remove();
                    }, 5000);
                    cancelEdit(id);
                } else {
                    alert("Error on server");
                }
            });
        }

        function deleteAuthor(id) {
            $.ajax({
                type: "POST",
                url: "/news-admin/authors/delete",
                data: {id: parseInt(id)},
                success: function (result) {
                    if (result == "success") {
                        $('#' + id).remove()
                        $("#authors-form").prepend(successMsg('Author successfully deleted'));
                        setTimeout(function () {
                            $(".msg").remove();
                        }, 5000);
                        cancelEdit(id);
                    } else {
                        alert("Error on server");
                    }
                }
            });
        }

        function cancelEdit(id) {
            $("#delete_" + id).hide();
            $("#update_" + id).hide();
            $("#cancel_" + id).hide();
            $("#" + id).prop("disabled", true);
            $("#edit_" + id).show();
        }

        function successMsg(text) {
            return '<div class="msg">' + text + '</div>';
        }
    </script>
</head>
<body>
<div id="authors-form">
    <c:forEach items="${allAuthors}" var="author">
        <p>
            <label for="<c:out value="${author.id}"/>" class="author-label"><s:message code="label.author"/>:</label>
            <input type="text" value="${author.name} ${author.surname}" id="<c:out value="${author.id}"/>" disabled>

            <a href="#/" id="edit_<c:out value="${author.id}"/>" class="edit-author-ref"
               onclick="showEdit(<c:out value="${author.id}"/>)"><s:message code="crud.edit"/></a>
            <a href="#" id="update_<c:out value="${author.id}"/>" class="update-author-ref" hidden
               onclick="updateAuthor(<c:out value="${author.id}"/>)"><s:message code="crud.update"/></a>
            <a href="#" id="delete_<c:out value="${author.id}"/>" class="delete-author-ref" hidden
               onclick="deleteAuthor(<c:out value="${author.id}"/>)"><s:message code="crud.delete"/></a>
            <a href="#" id="cancel_<c:out value="${author.id}"/>" class="cancel-author-ref"
               onclick="cancelEdit(<c:out value="${author.id}"/>)" hidden><s:message code="crud.cancel"/></a>
        </p>
    </c:forEach>
    <div id="pager"></div>
    <p></p>
    <div>
        <c:url value="/authors" var="addAuthorUrl"/>
        <form name="new-author" action="${addAuthorUrl}" method="post">
            <p>
                <label for="name"><s:message code="label.author.name"/> :</label>
                <input type="text" id="name" name="name" required/>
                <label for="surname"><s:message code="label.author.surname"/> :</label>
                <input type="text" id="surname" name="surname" required/>
            </p>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn"><s:message code="button.create"/></button>
        </form>
    </div>
</div>
</body>
</html>
