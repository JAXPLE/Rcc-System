<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style/client/clientMypage.css">
</head>
<c:import url="../header.jsp"/>
<body>
<div class="container">
    <div class="section1">
        <h1>주문내역</h1>
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

            <input type="hidden" id="orderListClientCode" value="${sessionScope.log.clientCode}">
            <tbody id="orderList"></tbody>

        </table>
        <div id="orderListPage">
        <input type="hidden" id="page" value="0">
        </div>
    </div>
    <div class="section2">
        <h1>게시글 작성내역</h1>
        <table>
            <thead>
            <tr>
                <th>게시글 번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>작성일시</th>
            </tr>
            </thead>
            <input type="hidden" id="loginName" value="${sessionScope.log.clientName}">
            <tbody id="boardList">

            </tbody>
        </table>
        <div id="boardListPage">

        </div>
    </div>
</div>
<div class="vutton">
    <button type="button" onclick="location.href='clientLeave'" class="but">회원탈퇴</button>
    <button type="button" onclick="location.href='clientUpdate'" class="but">회원수정</button>
</div>
</body>
<c:import url="../footer.jsp"/>
<script src="../script/client/clientMypage.js"></script>
</html>