<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ShopBy</title>
    <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico">
    <link rel="stylesheet" href="/resources/css/styles.css" type="text/css">
</head>
<body>
<jsp:include page="common/nav.jsp"></jsp:include>

<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

            <h1 class="display-5 fw-bolder">🤝 REGISTER</h1>

            <form action="/user/register" method="post">
                <div class="col-form-label-lg">
                    <input type="text" class="input-group"
                           placeholder="ID"
                           id="user_id" name="user_id" required>
                </div>

                <div class="col-form-label-lg">
                    <input type="text" class="input-group"
                           placeholder="NAME"
                           id="user_name" name="user_name" required>
                </div>

                <div class="col-form-label-lg">
                    <input type="password" class="input-group"
                           placeholder="PASSWORD"
                           id="user_password" name="user_password" required>
                </div>

                <div class="col-form-label-lg">
                    <input type="password" class="input-group"
                           placeholder="PASSWORD CHECK"
                           id="user_password_2" name="user_password2" required>
                </div>

                <br>
                <button type="submit" class="btn btn-dark">REGISTER</button>

            </form>

        </div>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <span class="alert-danger">${param.message}</span>
        </div>
    </div>
</section>

<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>