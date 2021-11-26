<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="account" type="javax.xml.stream.util.StreamReaderDelegate"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/sellercenter.css"/>
    <title>DHS Bill Seller</title>
</head>
<body>
<header class="header-root">
    <section class="header-container">
        <a href="${pageContext.request.contextPath}/home" class="content-logo"><img src="SellerCenter/img/android-chrome-192x192.png" alt=""></a>
        <a href="${pageContext.request.contextPath}/summary" class="style-content">
            <span>Seller Center</span>
        </a>
        <% if(session == null || session.getAttribute("account") == null) {%>
        <div class="account-avatar">
            <img class="avt" src="SellerCenter/img/avatar.jfif" alt="">
            <span>Signup/Login</span>
        </div>
        <%} else {%>
        <<div class="account-avatar">
        <img class="avt" src="SellerCenter/img/avatar.jfif" alt="">
        <span>${account.name}</span>
        <%}%>
    </div>
    </section>
</header>
<main>
    <section class="layout-container">
        <aside class="styles-left-SideBar">
            <ul class="nav-bar-account">
                <li>
                    <a href="${pageContext.request.contextPath}/summary">
                        <span>Doanh Thu</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/product">
                        <span>Sản Phẩm</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/bill" class="is-active">
                        <span>Đơn hàng</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/delivery">
                        <span>Vận chuyển</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/reviews">
                        <span>Đánh giá</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/account">
                        <span>Tài khoản</span>
                    </a>
                </li>
            </ul>
        </aside>
        <section class="styles-middle-container has-sidebar">
            <div class="layout-menu">
                <div class="main-content">
                    <div class="content">
                        <h3 class="title">Tìm kiếm đơn hàng</h3>
                        <div class="search"></div>
                    </div>
                    <div class="content">
                        <h3 class="title">Thông tin đơn hàng</h3>
                        <div class="inner">
                            <%--@elvariable id="listBill" type="java.util.List"--%>
                            <c:if test="${empty listBill}">
                                    <span>Load Faile</span>
                            </c:if>
                            <c:if test="${not empty listBill}">
                                <c:forEach items="${listBill}" var="element">
                            <table>
                                <thead>
                                <tr>
                                    <th>Mã thanh toán</th>
                                    <th>Loại</th>
                                    <th>Tag</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>${element[0]}</td>
                                    <td>${element[1]}</td>
                                    <td>${element[2]}</td>
                                    <td>${element[3]}</td>
                                    <td>${element[4]}</td>
                                </tr>
                                </tbody>
                            </table>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>
</main>
</body>
</html>
