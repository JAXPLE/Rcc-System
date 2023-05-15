<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-05-01
  Time: 오후 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script src="/script/jquery-3.6.4.min.js"></script>
    <script src="/script/order/orderFormPayment.js"></script>
    <link rel="stylesheet" href="style/order/orderFormPayment.css">

    </head>
<c:import url="../header.jsp"/>
<body>
<form id="orderFormPayment">

    <div>
        <div id="clientData">

            <input type="hidden" name="clientCode" id="clientCode" readonly>
            <p>주문자 성함</p>

            <input type="text" name="clientName" id="clientName" readonly>

            <p>수거 예정 지역</p>
            <input type="text" name="address" id="address" readonly>
            <p>주문 요청사항</p>
            <input type="text" id="orderRequest" name="orderRequest" placeholder="주문사항을 입력해주세요" maxlength="100">

        </div>


        <table id="orderTypeList">





        </table>

<div id="totalPrice">

</div>
        <input type="button" name="orderValidation" id="orderValidation" value="주문하기">


    </div>


</form>


</body>
</html>