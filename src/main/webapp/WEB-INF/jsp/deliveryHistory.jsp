<%@ page import="com.shopby.model.dto.DeliveryDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shopby.model.dto.ItemDto" %>
<%@ page import="com.shopby.model.dto.DeliveryItemDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ShopBy</title>
    <link rel="icon" type="image/x-icon" href="/resources/assets/favicon.ico">
    <link rel="stylesheet" href="/resources/css/styles.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="common/nav.jsp"></jsp:include>
<jsp:include page="common/header.jsp"></jsp:include>

<div class="container px-4 px-lg-5 mt-5">
    <div class="row">

        <div class="col-12 col-lg">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">

                        <%
                            for (DeliveryDto deliveryDto : (List<DeliveryDto>) request.getAttribute("deliveryHistory")) {
                        %>
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th scope="col" colspan="3"><%=deliveryDto.getCreatedDate()%></th>
                                <th scope="col"><%=deliveryDto.getStatus()%></th>
                            </tr>
                            </thead>
                            <tbody id="cartList">

                            <%
                                for (DeliveryItemDto deliveryItemDto : deliveryDto.getItemDtoList()) {
                                    ItemDto itemDto = deliveryItemDto.getItemDto();

                            %>
                            <tr>
                                <td colspan="1">
                                    <img src="<%=itemDto.getThumbnail()%>"
                                         style="width: 100px; height: 100px;" alt="상품이미지">
                                </td>
                                <td colspan="1">
                                    <a class="nav-link" href="/item/<%=itemDto.getId()%>">
                                        <%=itemDto.getName()%>
                                    </a>
                                </td>
                                <td colspan="1">
                                    <%=deliveryItemDto.getCount()%>개
                                </td>
                                <td colspan="1">
                                    <%=itemDto.getPrice()%> 원
                                </td>
                            </tr>
                            <%
                                }
                            %>

                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="3">
                                    <span>총 결제금액</span>
                                </td>
                                <td>
                                    <%=deliveryDto.getTotalPrice()%> 원
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                        <%
                            }
                        %>

                    </div>

                </div>
            </div>
        </div>


    </div>
</div>


<jsp:include page="common/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/scripts.js"></script>
</body>
</html>