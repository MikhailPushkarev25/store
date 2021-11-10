<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
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
                background-image: url(images/2.jpg);
                background-repeat: no-repeat;
            }
        </style>

    <script>
        function validate() {
            if ($('#name').val() === '') {
                alert($('#name').attr('Введите имя!'));

            } else if ($('#surname').val() === '') {
                alert($('#surname').attr('Введите фамилию!'));

            } else if ($('#email').val() === '') {
            alert($('#email').attr('Введите E-mail!'));

        } else if ($('#password').val() === '') {
                alert($('#password').attr('Введите пароль!'));
            }
            return true;
        }
    </script>

<body>
</body>
</html>
    <title>Интернет магазин</title>
</head>
<body>
<div class="container pt-3">

    <div class="bg-success p-2 text-white">
        <div class="card" style="width: 100%">
            <div class="p-3 mb-2 bg-secondary text-white">
                Регистрация
            </div>

            <div class="card-body">
                <form action="<%=request.getContextPath()%>/reg.do" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" placeholder="Имя">
                        <label for="name">Имя</label>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="surname" placeholder="Фамилия">
                        <label for="surname">Фамилия</label>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" id="email" placeholder="E-mail">
                        <label for="email">E-mail</label>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" placeholder="Пароль">
                        <label for="password">Пароль</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Войти</button>
                    <c:if test="${not empty error}">
                        <div style="color:red; font-weight: bold; margin: 30px 0;">
                            <c:out value="${error}"/>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>