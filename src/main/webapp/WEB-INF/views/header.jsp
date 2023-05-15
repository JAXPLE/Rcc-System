<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../style/client/header.css">
</head>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<body>

<div class="header">
    <a href="/" class="title">개구리분리수거</a>
</div>
<div class="nav">
    <c:if test="${not empty sessionScope.log}">
        <a href="orderForm" class="orderForm">주문하기</a>
        <c:choose>
            <c:when test="${sessionScope.log.clientCode eq 99999}">
                <a href="adminPage" class="mypage">관리자페이지</a>
            </c:when>
            <c:otherwise>
                <a href="clientMypage" class="mypage">마이페이지</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${not empty sessionScope.kakao }">
                <a href="#" onclick="kakaoLogout()" class="logout">로그아웃</a>
            </c:when>
            <c:otherwise>
                <a href="logout" class="logout">로그아웃</a>
            </c:otherwise>
        </c:choose>

    </c:if>
    <c:if test="${empty sessionScope.log}">
        <a href="clientLogin" class="login">로그인</a>
        <a href="clientJoinAgree" class="join">회원가입</a>
    </c:if>
    <a href="boardList" >리뷰</a>
</div>


<script src="../script/client/kakaoLogout.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>

</html>