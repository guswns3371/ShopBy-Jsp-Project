<%@ page import="com.shopby.model.dto.ItemDto" %>
<%@ page import="java.util.List" %>
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
<jsp:include page="common/header.jsp"></jsp:include>

<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

            <%
                List<ItemDto> itemList = (List<ItemDto>) request.getAttribute("itemList");
                for (ItemDto itemDto : itemList) {

            %>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="<%=itemDto.getImageUrl()%>" alt="...">
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <span class="fw-bolder">
                                <a class="nav-link active" href="/item<%=itemDto.getItemUrl()%>">
                                    <%=itemDto.getName()%>
                                </a>
                            </span>
                            <!-- Product price-->
                            <%=itemDto.getSplitPrice()%> 원
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div id="item-<%=itemDto.getId()%>" class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">장바구니에 담기</a></div>
                    </div>
                </div>
            </div>
            <%
                }
            %>


        </div>
    </div>
</section>

<jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>