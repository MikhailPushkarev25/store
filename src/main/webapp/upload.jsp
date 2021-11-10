<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Загрузка</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            margin: 0;
            background-image: url(images/7.jpg);
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
<script>
    function validate() {
        if ($('#file').val() === '') {
            alert($('#file').attr('title'));
        }

        return false;
    }
</script>
<div class="container">
    <h2>Загрузить картинку</h2>
    <form action="<c:url value='/upload?id=${param.id}'/>" method="post" enctype="multipart/form-data">
        <div class="checkbox">
            <input type="file" name="file">
        </div>
        <button type="submit" class="btn btn-default">Добавить</button>
    </form>
</div>

</body>
</html>