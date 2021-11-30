<%@ page import="com.shopby.model.dto.ItemInfoDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
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
<%
    ItemInfoDto itemInfo = (ItemInfoDto) request.getAttribute("itemInfo");
    if (itemInfo != null) {
%>
<!-- Product section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0"
                                       src="<%=itemInfo.getImage()%>" alt="..."></div>
            <div class="col-md-6">
                <div class="small mb-1"><%=itemInfo.getBrand()%>
                </div>
                <h1 class="display-6 fw-bolder"><%=itemInfo.getName()%>
                </h1>
                <br>
                <div class="fs-5 mb-5">
                    <span><%=itemInfo.getPrice()%> 원</span>
                </div>
                <p class="lead"></p>
                <%
                    String userId = (String) session.getAttribute("userId");
                    if (userId != null) {
                        HashMap<Long, Integer> cart = (HashMap<Long, Integer>) session.getAttribute("cart" + userId);
                %>
                <div class="d-flex">
                    <label for="inputQuantity"></label>
                    <input class="form-control text-center me-3" id="inputQuantity" type="number" value="1"
                                                              style="max-width: 3rem" />
                    <a id="<%=itemInfo.getId()%>" class="btn btn-outline-dark flex-shrink-0"
                       onclick="addToCart(this)">
                        <i class="bi-cart-fill me-1"></i>
                        <%
                            if (cart != null && cart.containsKey(itemInfo.getId())) {
                                out.println("<span class=\"added-cart\">");
                                out.println("장바구니에서 삭제하기");
                            } else {
                                out.println("<span class=\"removed-cart\">");
                                out.println("장바구니 추가");
                            }
                            out.println("</span>");
                        %>
                    </a>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</section>
<!-- Related items section-->
<section class="py-5 bg-light">
    <div class="container">
        <h2 class="fw-bolder mb-4">📸 Details</h2>
        <div class="row justify-content-center">
            <div class="col">
                <%=itemInfo.getInformation()%>
            </div>
        </div>
        <%--        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">--%>
        <%--            <div class="col mb-5">--%>
        <%--                <div class="card h-100">--%>
        <%--                    <!-- Product image-->--%>
        <%--                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="...">--%>
        <%--                    <!-- Product details-->--%>
        <%--                    <div class="card-body p-4">--%>
        <%--                        <div class="text-center">--%>
        <%--                            <!-- Product name-->--%>
        <%--                            <h5 class="fw-bolder">Fancy Product</h5>--%>
        <%--                            <!-- Product price-->--%>
        <%--                            $40.00 - $80.00--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                    <!-- Product actions-->--%>
        <%--                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">--%>
        <%--                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--            <div class="col mb-5">--%>
        <%--                <div class="card h-100">--%>
        <%--                    <!-- Sale badge-->--%>
        <%--                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale--%>
        <%--                    </div>--%>
        <%--                    <!-- Product image-->--%>
        <%--                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="...">--%>
        <%--                    <!-- Product details-->--%>
        <%--                    <div class="card-body p-4">--%>
        <%--                        <div class="text-center">--%>
        <%--                            <!-- Product name-->--%>
        <%--                            <h5 class="fw-bolder">Special Item</h5>--%>
        <%--                            <!-- Product reviews-->--%>
        <%--                            <div class="d-flex justify-content-center small text-warning mb-2">--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                            </div>--%>
        <%--                            <!-- Product price-->--%>
        <%--                            <span class="text-muted text-decoration-line-through">$20.00</span>--%>
        <%--                            $18.00--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                    <!-- Product actions-->--%>
        <%--                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">--%>
        <%--                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--            <div class="col mb-5">--%>
        <%--                <div class="card h-100">--%>
        <%--                    <!-- Sale badge-->--%>
        <%--                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale--%>
        <%--                    </div>--%>
        <%--                    <!-- Product image-->--%>
        <%--                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="...">--%>
        <%--                    <!-- Product details-->--%>
        <%--                    <div class="card-body p-4">--%>
        <%--                        <div class="text-center">--%>
        <%--                            <!-- Product name-->--%>
        <%--                            <h5 class="fw-bolder">Sale Item</h5>--%>
        <%--                            <!-- Product price-->--%>
        <%--                            <span class="text-muted text-decoration-line-through">$50.00</span>--%>
        <%--                            $25.00--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                    <!-- Product actions-->--%>
        <%--                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">--%>
        <%--                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--            <div class="col mb-5">--%>
        <%--                <div class="card h-100">--%>
        <%--                    <!-- Product image-->--%>
        <%--                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="...">--%>
        <%--                    <!-- Product details-->--%>
        <%--                    <div class="card-body p-4">--%>
        <%--                        <div class="text-center">--%>
        <%--                            <!-- Product name-->--%>
        <%--                            <h5 class="fw-bolder">Popular Item</h5>--%>
        <%--                            <!-- Product reviews-->--%>
        <%--                            <div class="d-flex justify-content-center small text-warning mb-2">--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                                <div class="bi-star-fill"></div>--%>
        <%--                            </div>--%>
        <%--                            <!-- Product price-->--%>
        <%--                            $40.00--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                    <!-- Product actions-->--%>
        <%--                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">--%>
        <%--                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
    </div>
</section>
<%
    }
%>
<jsp:include page="common/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/js/scripts.js"></script>
</body>
</html>