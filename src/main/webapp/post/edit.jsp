<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="storeInfo.model.Post" %>
<%@ page import="storeInfo.store.OnlineDB" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <%@page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <style type="text/css">
        body{
            margin: 0;
            background-image: url(images/6.jpg);
            background-repeat: no-repeat;
        }
    </style>
    <title>Интернет магазин</title>
</head>
<body>

<%
    String id = request.getParameter("id");
    Post post = new Post(0, "");
    if (id != null) {
        post = OnlineDB.instOf().findByIdPost(Integer.parseInt(id));
    }
%>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/posts.do">Заказы клиентов</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/post/edit.jsp">Добавить заказ</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | </a>
            </li>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/log.do">Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="card" style="width: 100%">
        <div class="card-header">
            <% if (id == null) { %>
            Новый пост
            <% } else { %>
            редактирование поста
            <% } %>
        </div>
        <div class="card-body">
            <form action="<%=request.getContextPath()%>/posts.do?id=<%=post.getId()%>" method="post">
                <div class="form-group">
                    <label for="name">Имя</label>
                    <input type="text" class="form-control" name="name" value="<%=post.getName()%>" id="name">
                </div>
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
