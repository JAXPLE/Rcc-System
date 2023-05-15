<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 4:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../style/client/clientJoin.css">
</head>
<c:import url="../header.jsp"/>
<body>
<div class="container">

    <input type="text" name="name" id="name" placeholder="이름">
    <input type="text" name="phone" id="phone" placeholder="전화번호(-없이)">
    <input id="phone2" type="text" name="phone2" title="인증번호 입력" disabled required/>
    <button type="button" id="phoneChk" onclick="phoneCheck()">인증번호 보내기</button>
    <button type="button" id="phoneChk2" onclick="checkNumber()">인증</button>
    <input type="password" name="password" id="password" placeholder="비밀번호">
    <input type="text" name="zonecode" id="zonecode" placeholder="우편번호" readonly>
    <input type="text" name="address" id="address" placeholder="주소" readonly>
    <input type="text" name="detailAdress" id="detailAdress" placeholder="상세주소">
    <button type="button" id="addressSearch" onclick="findAddr()">주소검색</button>
    <c:choose>
        <c:when test="${not empty param.kakao_id}">
            <input type="hidden" id="kakaoid" value="${param.kakao_id}">
        </c:when>
        <c:otherwise>
            <input type="hidden" id="kakaoid" value=0>
        </c:otherwise>
    </c:choose>
    <select id="type" name="type">
        <c:choose>
            <c:when test="${not empty param.client_type}">
                <option value="3">카카오사용자</option>
            </c:when>
            <c:otherwise>
                <option value="1">사용자</option>
                <option value="2">직원</option>
            </c:otherwise>
        </c:choose>
    </select>


    <input type="button" onclick="joinClick()" value="회원가입">

</div>

<script src="../script/client/clientJoin.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</body>
<c:import url="../footer.jsp"/>
</html>
