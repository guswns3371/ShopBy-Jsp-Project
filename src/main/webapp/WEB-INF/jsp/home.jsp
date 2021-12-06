<%@ page import="com.shopby.model.dto.ItemDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shopby.model.dto.ItemInfoDto" %>
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
<jsp:include page="common/header.jsp"></jsp:include>

<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

            <%
                for (ItemDto itemDto : (List<ItemDto>) request.getAttribute("itemList")) {

            %>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <a href="/item/<%=itemDto.getId()%>">
                        <img class="card-img-top" src="<%=itemDto.getThumbnail()%>" alt="...">
                    </a>
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <%=itemDto.getBrand()%>
                            <span class="fw-bolder">
                                <a class="nav-link active" href="/item/<%=itemDto.getId()%>">
                                    <%=itemDto.getName()%>
                                </a>
                            </span>
                            <!-- Product price-->
                            <%=itemDto.getPrice()%> 원
                        </div>
                    </div>
                    <!-- Product actions-->
<%--                    <%--%>
<%--                        if (session.getAttribute("userId") != null) {--%>
<%--                    %>--%>
<%--                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">--%>
<%--                        <div class="text-center">--%>
<%--                            <a id="<%=itemDto.getId()%>" class="btn btn-outline-dark mt-auto"--%>
<%--                               onclick="addToCart(this)">--%>
<%--                                장바구니에 담기--%>
<%--                            </a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <%--%>
<%--                        }--%>
<%--                    %>--%>
                </div>
            </div>
            <%
                }
            %>


        </div>
    </div>
</section>

<jsp:include page="common/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/scripts.js"></script>
</body>
</html>