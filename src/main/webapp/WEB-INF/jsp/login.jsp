<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ShopBy</title>
    <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico">
    <link rel="stylesheet" href="/resources/css/styles.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script></head>
<body>
<jsp:include page="common/nav.jsp"></jsp:include>

<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

            <h1 class="display-5 fw-bolder">👋 LOGIN</h1>

            <form action="/user/login" method="post">
                <div class="col-form-label-lg">
                    <input type="text" class="input-group"
                           placeholder="ID"
                           id="user_id" name="user_id" required>
                </div>

                <div class="col-form-label-lg">
                    <input type="password" class="input-group"
                           placeholder="PASSWORD"
                           id="user_password" name="user_password" required>
                </div>

                <br>
                <div class="btn-group">
                    <button type="submit" class="btn btn-dark">LOGIN</button>
                    <a class="nav-link" href="/user/register">Register</a>
                </div>

            </form>

        </div>

        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <span class="alert-danger">${message}</span>
        </div>
    </div>
</section>

<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>