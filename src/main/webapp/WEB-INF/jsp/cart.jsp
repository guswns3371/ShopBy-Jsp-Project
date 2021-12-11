<%@ page import="java.util.List" %>
<%@ page import="com.shopby.model.dto.CartItemDto" %>
<%@ page import="com.shopby.utils.StringSplitBuilder" %>
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

<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row">

            <%
                List<CartItemDto> cartItemDtoList = (List<CartItemDto>) request.getAttribute("cartItems");
                if (cartItemDtoList.size() > 0) {
            %>
            <div class="col-12 col-lg">
                <div class="card">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th scope="col">삭제</th>
                                    <th scope="col">상품이미지</th>
                                    <th scope="col">상품명</th>
                                    <th scope="col">수량</th>
                                    <th scope="col">가격</th>
                                    <th scope="col">선택</th>
                                </tr>
                                </thead>
                                <tbody id="cartList">

                                <%
                                    for (CartItemDto cartItem : cartItemDtoList) {
                                        String price = StringSplitBuilder.getSplitPrice(cartItem.getPrice());
                                %>
                                <tr>
                                    <td colspan="1">
                                        <a class="btn" id="<%=cartItem.getId()%>" onclick="deleteItem(this)">🗑️</a>
                                    </td>
                                    <td colspan="1">
                                        <img src="<%=cartItem.getImage()%>"
                                             style="width: 100px; height: 100px;" alt="상품이미지">
                                    </td>
                                    <td colspan="1">
                                        <a class="nav-link" href="/item/<%=cartItem.getId()%>">
                                            <%=cartItem.getName()%>
                                        </a>
                                    </td>
                                    <td colspan="1">
                                        <input id="itemCount" type="number" class="form-control" style="max-width: 4rem"
                                               min="0"
                                               alt="<%=cartItem.getId()%>"
                                               name="<%=cartItem.getPrice()%>"
                                               value="<%=cartItem.getCount()%>"/>
                                    </td>
                                    <td colspan="1">
                                        <span id="itemPrice">
                                            <%=price%> 원
                                        </span>
                                    </td>
                                    <td colspan="1">
                                        <input type="checkbox" id="cartCheck" value="1" checked/>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>

                                <tfoot>
                                <tr>
                                    <td colspan="4" class="text-right">
                                        <strong>총 금액</strong>
                                    </td>
                                    <td colspan="1">
                                        <strong id="totalPrice"></strong> 원
                                    </td>
                                    <td colspan="1">
                                        <a class="btn btn-primary" id="checkout" onclick="checkout()">결제하기</a>
                                    </td>
                                </tr>
                                </tfoot>

                            </table>
                        </div>

                    </div>
                </div>
            </div>
            <%
                } else {
                    out.println("장바구니가 비어있습니다.");
                }

                String userId = (String) session.getAttribute("userId");
                if (userId != null) {
            %>
            <a class="btn btn-primary" href="/cart/delivery/<%=userId%>">배송 현황</a>
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