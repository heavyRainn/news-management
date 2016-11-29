<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html>
<head>
    <title>Add tags page</title>
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

        function showEdit(id) {
            $("#delete_" + id).show();
            $("#update_" + id).show();
            $("#cancel_" + id).show();
            $("#" + id).prop("disabled", false);
            $("#edit_" + id).hide();
        }

        function updateTag(id) {
            $.post("/news-admin/addTags/update", {id: parseInt(id), text: $("#" + id).val()}, function (result) {
                console.log("Have response: " + result);
                if (result == "success") {
                    $("#tags-form").prepend(successMsg('Tag successfully updated'));
                    setTimeout(function () {
                        $(".msg").remove();
                    }, 5000);
                    cancelEdit(id);
                } else {
                    alert("Error on server");
                }
            });
        }

        function deleteTag(id) {
            $.post("/news-admin/addTags/delete", {id: parseInt(id)}, function (result) {
                console.log("Have response: " + result);
                if (result == "success") {
                    $("#tags-form").prepend(successMsg('Tag successfully deleted'));
                    setTimeout(function () {
                        $(".msg").remove();
                    }, 5000);
                    cancelEdit(id);
                } else {
                    alert("Error on server");
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

<div id="tags-form">
    <c:forEach items="${allTags}" var="tag">
        <p>
            <label for="<c:out value="${tag.id}"/>" class="tag-label"><s:message code="label.tag"/> :</label>
            <input type="text" name="tag" id="<c:out value="${tag.id}"/>" class="tag-input"
                   value="<c:out value="${tag.text}"/>" disabled/>
            <a href="#/" id="edit_<c:out value="${tag.id}"/>" class="edit-tag-ref"
               onclick="showEdit(<c:out value="${tag.id}"/>)"><s:message code="crud.edit"/> </a>
            <a href="#" id="update_<c:out value="${tag.id}"/>" class="update-tag-ref" hidden
               onclick="updateTag(<c:out value="${tag.id}"/>)"><s:message code="crud.update"/> </a>
            <a href="#" id="delete_<c:out value="${tag.id}"/>" class="delete-tag-ref" hidden
               onclick="deleteTag(<c:out value="${tag.id}"/>)"><s:message code="crud.delete"/> </a>
            <a href="#" id="cancel_<c:out value="${tag.id}"/>" class="cancel-tag-ref"
               onclick="cancelEdit(<c:out value="${tag.id}"/>)" hidden><s:message code="crud.cancel"/> </a>
        </p>
    </c:forEach>
    <div id="pager"></div>
    <div>
        <c:url value="/addTags" var="addTagsUrl"/>
        <form action="${addTagsUrl}" method="post">
            <p>
                <label for="value"><s:message code="label.tag.text"/> :</label>
                <input type="text" id="value" name="value" required/>
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
