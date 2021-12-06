<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/">
            <img src="/resources/assets/favicon.ico">
            <span class="fw-bolder display-7">ShopBy</span>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="/">홈페이지</a></li>
            </ul>

            <ul class="d-flex">
                <li class="btn">
                    <%
                        String userId = (String) session.getAttribute("userId");
                        if (userId == null) {
                            out.println("<a class=\"nav-link active\" aria-current=\"page\" href=\"/user/login\">로그인</a>");
                        } else {
                            out.println("<a class=\"nav-link active\" aria-current=\"page\" href=\"/user/logout\">로그아웃</a>");
                        }
                    %>
                </li>
            </ul>
            <%
                if (userId != null) {
                    HashMap<Long, Integer> cart = (HashMap<Long, Integer>) session.getAttribute("cart" + userId);
            %>
            <form class="d-flex">
                <a class="btn btn-outline-dark" href="/cart/<%=userId%>">
                    <i class="bi-delivery-fill me-1"></i>
                     <span id="userId"><%=userId%></span>
                    <span class="badge bg-dark text-white ms-1 rounded-pill">
                        🛒
                        <span id="cartCount">
                            <%
                                if (cart == null) {
                                    out.println(0);
                                } else {
                                    out.println(cart.keySet().size());
                                }
                            %>
                        </span>
                    </span>
                </a>
            </form>
            <%
                }
            %>
        </div>
    </div>
</nav>