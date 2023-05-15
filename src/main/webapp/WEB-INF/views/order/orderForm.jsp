<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script src="/script/jquery-3.6.4.min.js"></script>
    <script src="/script/order/orderForm.js"></script>
    <!-- 분리배출 신청을 시작할 때 사용하는 폼. -->
    <link rel="stylesheet" href="style/order/orderForm.css">
</head>
<c:import url="../header.jsp"/>
<body>

<form method="post" id="orderForm" action="/orderFormPayment"   >

    <table>

        <div id="add">


        </div>


         <input type="button" class="addButton" onclick="addOrder()" value="새 주문 추가">


        <input type="button" class="submitButton"  id="submitButton" value="결제하러가기">
        <div id="priceList">  </div>
    </table>


</form>


</body>
<c:import url="../footer.jsp"/>
</html>
