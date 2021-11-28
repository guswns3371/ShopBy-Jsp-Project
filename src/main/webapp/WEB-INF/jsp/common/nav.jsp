<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand fw-bolder" href="/">
            <img src="/resources/assets/favicon.ico">
            ShopBy
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="/">Home</a></li>
            </ul>

            <ul class="d-flex">
                <li class="btn">
                    <%
                        String userId = (String) session.getAttribute("userId");
                        if (userId == null) {
                            out.println("<a class=\"nav-link active\" aria-current=\"page\" href=\"/user/login\">Login</a>");
                        } else {
                            out.println("<a class=\"nav-link active\" aria-current=\"page\" href=\"/user/logout\">Logout</a>");
                        }
                    %>
                    <%--                    <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user/login">Login</a>--%>
                </li>
            </ul>
            <form class="d-flex">
                <button class="btn btn-outline-dark" type="submit">
                    <i class="bi-cart-fill me-1"></i>
                    🛒
                    <%
                        if (userId != null) {
                            out.println(userId);
                        }
                    %>
                    <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                </button>
            </form>
        </div>
    </div>
</nav>