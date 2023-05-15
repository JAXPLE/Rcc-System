
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style/client/adminPage.css">
</head>
<c:import url="../header.jsp"/>
<body>
<div class="container">
    <h1>고객 주문내역</h1>
    <table>
        <thead>
        <tr>
            <th>주문번호</th>
            <th>주문자번호</th>
            <th>담당직원번호</th>
            <th>주소</th>
            <th>지불금액</th>
            <th>요청사항</th>
            <th>주문날짜</th>
            <th>처리날짜</th>
            <th>처리상태</th>
        </tr>
        </thead>
        <tbody id="orderList">

        </tbody>
    </table>
    <div id="orderPage">

    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../script/client/admin.js"></script>
</body>
<c:import url="../footer.jsp"/>
</html>
